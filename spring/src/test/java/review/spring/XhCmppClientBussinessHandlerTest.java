//package review.spring;
//
//import com.smschannel.biz.cmpp20.client.message.request.SubmitRequestMessage;
//import com.smschannel.biz.service.service.cmppclienthandler.AsyncXhCmppClientBusinessHandler;
//import com.smschannel.biz.service.service.cmppclienthandler.XhCmppClientBussinessHandler;
//import io.netty.util.CharsetUtil;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.*;
//import org.mockito.junit.MockitoJUnitRunner;
//
///**
// * @description:
// * @author: xiaoxiaoxiang
// * @date: 2020/12/28 13:46
// */
//@RunWith(MockitoJUnitRunner.class)
//public class XhCmppClientBussinessHandlerTest {
//
//    @Mock
//    private AsyncXhCmppClientBusinessHandler asyncXhCmppClientBusinessHandler;
//
//    @Spy
//    @InjectMocks
//    private XhCmppClientBussinessHandler xhCmppClientBussinessHandler;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void submitHandleTest() {
//        Mockito.doNothing().when(asyncXhCmppClientBusinessHandler).submitHandle(
//                ArgumentMatchers.any(SubmitRequestMessage.class),
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.anyString());
//        SubmitRequestMessage message = initSubmitRequestMessage();
//        xhCmppClientBussinessHandler.submitHandle(message, "test", "miaoXin");
//    }
//
//    private SubmitRequestMessage initSubmitRequestMessage() {
//        SubmitRequestMessage submitMessage = new SubmitRequestMessage();
//        submitMessage.setDestterminalId(new String[] {"15271944243"});
//        submitMessage.setDestUsrTl((byte) submitMessage.getDestterminalId().length);
//        submitMessage.setServiceId("test");
//        submitMessage.setSrcId("test");
//        submitMessage.setMsgSrc("test");
//        submitMessage.setMsgContentText("test");
//        submitMessage.setMsgContent("test".getBytes(CharsetUtil.UTF_16BE));
//        submitMessage.setMsgLength(submitMessage.getMsgContent().length);
//        return submitMessage;
//    }
//
//}
