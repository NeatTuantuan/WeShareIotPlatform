package edu.xd.bdilab.iotplatform.netty.server.handler;


import edu.xd.bdilab.iotplatform.netty.util.DataUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SendCodeHandler extends ChannelInboundHandlerAdapter {

    Timer time = new Timer() ;
    private final String thCode = "020302000002C580";
    private final String pmCode = "0103000000070408";


    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {

//        time.scheduleAtFixedRate(
//                new TimerTask() {
//                    @Override
//                    public void run() {
//                        System.out.println(new Date()+" 发出数据1 ");
//                        ByteBuf byteBuf = getByteBuf(ctx,thCode);
//                        ctx.channel().writeAndFlush(byteBuf);
//                    }
//                },100,5000
//        );
//
//        time.scheduleAtFixedRate(
//                new TimerTask() {
//                    @Override
//                    public void run() {
//                        System.out.println(new Date()+" 发出数据2");
//                        ByteBuf byteBuf = getByteBuf(ctx,pmCode);
//                        ctx.channel().writeAndFlush(byteBuf);
//                    }
//                },100,5000
//        );

        SendCodeHandler sendCodeHandler = new SendCodeHandler();
        //发送温湿度指令
        sendCodeHandler.sendCode(ctx,thCode);
        //发送pm2.5指令
        sendCodeHandler.sendCode(ctx,pmCode);

    }


    //发出数据方法
    private  void sendCode(final ChannelHandlerContext ctx, final String code){
        time.scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println(new Date()+" 发出数据 "+code);
                        ByteBuf byteBuf = getByteBuf(ctx,code);
                        ctx.channel().writeAndFlush(byteBuf);
                    }
                },100,5000
        );
    }


    private ByteBuf getByteBuf(ChannelHandlerContext ctx, String string) {

        byte[] bytes = DataUtil.deocde(string);

        ByteBuf buffer = ctx.alloc().buffer();

        buffer.writeBytes(bytes);

        return buffer;
    }

}
