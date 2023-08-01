package com.fengling.ecserver.service.impl;

import com.fengling.ecserver.netty.ChatMsg;
import com.fengling.ecserver.netty.DataContent;
import com.fengling.ecserver.netty.server.UserConnectPool;
import com.fengling.ecserver.service.MessageService;
import com.fengling.ecserver.util.JsonUtils;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Administrator
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public void pushMsgToOne(DataContent dataContent) {
        ChatMsg chatMsg = dataContent.getChatMsg();
        Channel channel = UserConnectPool.getChannel(chatMsg.getReceiverId());
        if (Objects.isNull(channel)) {
            throw new RuntimeException("未连接socket服务器");
        }

        channel.writeAndFlush(
                new TextWebSocketFrame(
                        JsonUtils.objectToJson(chatMsg)
                )
        );
    }
}
