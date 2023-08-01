package com.fengling.ecserver.netty.server;

import cn.hutool.json.JSONUtil;
import com.fengling.ecserver.netty.ChatMessage;
import com.fengling.ecserver.netty.ChatMsg;
import com.fengling.ecserver.netty.DataContent;
import com.fengling.ecserver.netty.MessageActionEnum;
import com.fengling.ecserver.util.JsonUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

/**
 * @author Administrator
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class ServerListenerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 当建立链接时将Channel放置在Group当中
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("有新的客户端链接：[{}]", ctx.channel().id().asLongText());
        // 添加到channelGroup 通道组
        UserConnectPool.getChannelGroup().add(ctx.channel());
    }

    /**
     * 读取数据
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        /**
         * 1.接受到msg
         * 2.将msg转化为实体类
         * 3.解析消息类型
         * 将实体类当中的userid和连接的Channel进行对应
         * */
        String content = msg.text();
        //1.获取客户端发来的消息
        ChatMessage dataContent = null;
        if (content.contains("sendName")) {
            dataContent = JSONUtil.toBean(content.substring(1, content.length() - 1).replace("\\", ""), ChatMessage.class);
            dataContent.setAction(2);
        } else {
            dataContent = JSONUtil.toBean(content, ChatMessage.class);
        }
        assert dataContent != null;
        System.out.println("----->" + dataContent);
        Integer action = dataContent.getAction();
        Channel channel = ctx.channel();

        //2.判断消息类型，根据不同的类型来处理不同的业务
        if (Objects.equals(action, MessageActionEnum.CONNECT.type)) {
            //2.1 当websocket 第一次open的时候，初始化channel，把用的channel 和 userid 关联起来
            String senderId = dataContent.getSendName();
            UserConnectPool.getChannelMap().put(senderId, channel);
            //这里是输出一个用户关系
            UserConnectPool.output();
        } else if (Objects.equals(action, MessageActionEnum.CHAT.type)) {
            /**
             * 解析你的消息，然后进行持久化，或者其他的操作，看你自己
             * */
            ChatMsg chatMsg = new ChatMsg();
            chatMsg.setMsg(dataContent.getSendText());
            chatMsg.setMsgId(UUID.randomUUID().toString());
            chatMsg.setReceiverId(dataContent.getReceviceName());
            chatMsg.setSenderId(dataContent.getSendName());

            //发送消息
            Channel receiverChannel = UserConnectPool.getChannel(chatMsg.getReceiverId());
            if (receiverChannel == null) {
                //用户不在线
            } else {
                //为了保险起见你还可以在你的Group里面去查看有没有这样的Channel
                //毕竟不太能够保证原子性操作嘛，反正底层也是CurrentMap
                Channel findChannel = UserConnectPool.getChannelGroup().find(ctx.channel().id());
                if (findChannel != null) {
                    receiverChannel.writeAndFlush(
                            new TextWebSocketFrame(
                                    JsonUtils.objectToJson(chatMsg)
                            )
                    );
                } else {
                    //离线
                }
            }

        } else if (Objects.equals(action, MessageActionEnum.SIGNED.type)) {
            System.out.println("收到来自channel 为[" + channel + "]的消息签收");
        } else if (Objects.equals(action, MessageActionEnum.PULL_FRIEND.type)) {
            System.out.println("收到来自channel 为[" + channel + "]的拉取好友请求");
        } else if (Objects.equals(action, MessageActionEnum.KEEPALIVE.type)) {
            //2.4 心跳类型的消息
            System.out.println("收到来自channel 为[" + channel + "]的心跳包");
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        String chanelId = ctx.channel().id().asShortText();
        log.info("客户端被移除：channel id 为：" + chanelId);
        UserConnectPool.getChannelGroup().remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //发生了异常后关闭连接，同时从channelgroup移除
        ctx.channel().close();
        UserConnectPool.getChannelGroup().remove(ctx.channel());
    }
}
