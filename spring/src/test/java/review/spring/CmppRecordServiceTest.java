//package review.spring;
//
//import com.smschannel.common.dal.dto.SmsInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @description:
// * @author: xiaoxiaoxiang
// * @date: 2020/12/24 15:49
// */
////@SpringBootTest(classes = SmsChannelBatchTimingApplication.class)
////@RunWith(SpringRunner.class)
//public class CmppRecordServiceTest {
//
//
//    @Autowired
//    private CmppRecordService cmppRecordService;
//    @Autowired
//    private CmppRecordServiceHelper cmppRecordServiceHelper;
//
////    @Test
//    public void batchInsertTest() {
//        SmsInfo smsInfo = initSmsInfo();
//        List<SmsInfo> smsInfoList = new ArrayList<>();
//        smsInfoList.add(smsInfo);
//        cmppRecordService.batchInsert(smsInfoList);
//    }
//
////    @Test
//    public void sendMqTest() {
//        SmsInfo smsInfo = initSmsInfo();
//        // 测试时记得改成public
////        cmppRecordServiceHelper.sendSmsReceipt(smsInfo);
//    }
//
//    private SmsInfo initSmsInfo() {
//        SmsInfo smsInfo = new SmsInfo();
//        smsInfo.setBizId("12312312321312");
//        smsInfo.setPkNumber(1);
//        smsInfo.setPkTotal(1);
//        smsInfo.setMobile("15271944243");
//        smsInfo.setSubmitSeqId(123123123);
//        smsInfo.setRecordStatus(7);
//        smsInfo.setPartnerPlatform("miaoXin");
//        smsInfo.setPlatformStatus("0");
//        smsInfo.setPlatformTime("202012241402");
//        smsInfo.setSubMsgId("1223180559306704951124");
//        smsInfo.setReportStatus("DELIVER");
//        smsInfo.setReportTime("202012241402");
//        smsInfo.setAddition("12312312321312");
//        return smsInfo;
//    }
//}
