package edu.xd.bdilab.iotplatform.netty.server;

import edu.xd.bdilab.iotplatform.netty.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class NettyServer implements ApplicationRunner {

//    public static void main(String[] args) {
//        ServerBootstrap serverBootstrap = new ServerBootstrap();
//
//        NioEventLoopGroup boss = new NioEventLoopGroup();
//        NioEventLoopGroup worker = new NioEventLoopGroup();
//        serverBootstrap
//                .group(boss, worker)
//                .channel(NioServerSocketChannel.class)
//                .childHandler(new ChannelInitializer<NioSocketChannel>() {
//                    protected void initChannel(NioSocketChannel ch) {
//                        ch.pipeline().addLast(new SendCodeHandler());
//                        ch.pipeline().addLast(new DataDecoder());
//                        ch.pipeline().addLast(new GatewayHandler());
//                        ch.pipeline().addLast(new THHandler());
//                        ch.pipeline().addLast(new PMHandler());
//                    }
//                })
//                .bind(8080);
//    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new SendCodeHandler());
                        ch.pipeline().addLast(new DataDecoder());
                        ch.pipeline().addLast(new GatewayHandler());
                        ch.pipeline().addLast(new THHandler());
                        ch.pipeline().addLast(new PMHandler());
                    }
                })
                .bind(8088);

    }
}
