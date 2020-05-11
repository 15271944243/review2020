package review.cmpp.channelhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import review.cmpp.codec.CmppHeaderCodec;
import review.cmpp.codec.CmppMessageCodec;
import review.cmpp.Constants;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/7 17:01
 */
public class CmppCodecChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addBefore(Constants.CMPP_CODEC_PIPE_NAME, "FrameDecoder",
                new LengthFieldBasedFrameDecoder(4 * 1024 , 0, 4, -4, 0, true));

        ch.pipeline().addBefore(Constants.CMPP_CODEC_PIPE_NAME, "CmppHeaderCodec", new CmppHeaderCodec());

        ch.pipeline().addBefore(Constants.CMPP_CODEC_PIPE_NAME, "codecName", new CmppMessageCodec());
    }
}
