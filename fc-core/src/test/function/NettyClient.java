package function;

import com.easyArch.net.NettyDecoder;
import com.easyArch.net.NettyEncoder;
import com.easyArch.net.model.Message;
import com.easyArch.utils.Beat;
import com.easyArch.utils.serialize.imp.ProtoStuffSerializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.*;

public class NettyClient{
    private volatile ChannelFuture future;
//    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,200,
//            TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(5));
    private static ExecutorService executor = Executors.newSingleThreadExecutor();
    public NettyClient(){
        init();
    }
    private void init() {
        Bootstrap client = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        client.group(group);

        client.channel(NioSocketChannel.class);

        client.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new IdleStateHandler(0, 0,
                        Beat.BEAT_INTERVAL * 3, TimeUnit.SECONDS));
                ch.pipeline().addLast(new NettyEncoder(Message.class,new ProtoStuffSerializer()));
                ch.pipeline().addLast(new NettyDecoder(Message.class,new ProtoStuffSerializer()));
                ch.pipeline().addLast(new SimpleClientHandler());
            }
        }).option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);
        try {
            future = client.connect("localhost",8888).sync();
            System.out.println("------connect------");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    future.channel().writeAndFlush(message).sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

    }

}
