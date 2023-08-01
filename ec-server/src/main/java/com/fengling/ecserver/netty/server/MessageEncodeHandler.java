package com.fengling.ecserver.netty.server;

import com.fengling.ecserver.netty.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Administrator
 */
public class MessageEncodeHandler extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message messageBean, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(messageBean.getLen());
        byteBuf.writeBytes(messageBean.getContent());
    }

}
