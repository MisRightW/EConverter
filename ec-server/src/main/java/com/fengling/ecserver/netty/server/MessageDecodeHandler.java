package com.fengling.ecserver.netty.server;

import com.fengling.ecserver.netty.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author Administrator
 */
public class MessageDecodeHandler extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int len = byteBuf.readInt();
        byte[] content = new byte[len];
        byteBuf.readBytes(content);
        Message messageBean = new Message();
        messageBean.setContent(content);
        messageBean.setLen(len);
        list.add(messageBean);
    }

}

