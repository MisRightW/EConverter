package com.fengling.ecserver.config;

import com.fengling.ecserver.netty.client.ClientHandler;
import com.fengling.ecserver.netty.server.ServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 */
@Configuration
@EnableConfigurationProperties
public class NettyConfig {

    @Autowired
    NettyProperties nettyProperties;

    /**
     * boss 线程池
     * 负责客户端连接
     * @return
     */
    @Bean
    public NioEventLoopGroup boosGroup(){
        return new NioEventLoopGroup(nettyProperties.getBoss());
    }

    /**
     * worker线程池
     * 负责业务处理
     * @return
     */
    @Bean
    public NioEventLoopGroup workerGroup(){
        return  new NioEventLoopGroup(nettyProperties.getWorker());
    }
    /**
     * 服务器启动器
     * @return
     */
    @Bean
    public ServerBootstrap serverBootstrap(){
        ServerBootstrap serverBootstrap  = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup(),workerGroup())   // 指定使用的线程组
                .channel(NioServerSocketChannel.class) // 指定使用的通道
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,nettyProperties.getTimeout()) // 指定连接超时时间
                .childHandler(new ServerHandler()); // 指定worker处理器
        return serverBootstrap;
    }

    /**
     * 客户端启动器
     * @return
     */
    @Bean
    public Bootstrap bootstrap(){
        // 新建一组线程池
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup(nettyProperties.getBoss());
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(eventExecutors)   // 指定线程组
                .option(ChannelOption.SO_KEEPALIVE, true)
                .channel(NioSocketChannel.class) // 指定通道
                .handler(new ClientHandler()); // 指定处理器
        return bootstrap;
    }

}
