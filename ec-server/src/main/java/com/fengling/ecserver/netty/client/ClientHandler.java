package com.fengling.ecserver.netty.client;

import com.fengling.ecserver.netty.server.MessageDecodeHandler;
import com.fengling.ecserver.netty.server.MessageEncodeHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author Administrator
 */
public class ClientHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new MessageEncodeHandler());
        pipeline.addLast(new MessageDecodeHandler());
        pipeline.addLast(new ClientListenerHandler());
    }

}
