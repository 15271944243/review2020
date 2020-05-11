package review.cmpp.packet;

import review.cmpp.Constants;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/7 16:58
 */
public enum Cmpp20PacketType {
    CMPPCONNECTREQUEST(Constants.CMPP_CONNECT_REQUEST_CODE);
//    CMPPCONNECTRESPONSE(0x80000001, Cmpp20ConnectResponse.class,Cmpp20ConnectResponseMessageCodec.class),
//    CMPPTERMINATEREQUEST(0x00000002, CmppTerminateRequest.class,CmppTerminateRequestMessageCodec.class),
//    CMPPTERMINATERESPONSE(0x80000002, CmppTerminateResponse.class,CmppTerminateResponseMessageCodec.class),
//    CMPPSUBMITREQUEST(0x00000004, Cmpp20SubmitRequest.class,Cmpp20SubmitRequestMessageCodec.class),
//    CMPPSUBMITRESPONSE(0x80000004, Cmpp20SubmitResponse.class,Cmpp20SubmitResponseMessageCodec.class),
//    CMPPDELIVERREQUEST(0x00000005, Cmpp20DeliverRequest.class,Cmpp20DeliverRequestMessageCodec.class),
//    CMPPDELIVERRESPONSE(0x80000005, Cmpp20DeliverResponse.class,Cmpp20DeliverResponseMessageCodec.class),
//    CMPPQUERYREQUEST(0x00000006, CmppQueryRequest.class,CmppQueryRequestMessageCodec.class),
//    CMPPQUERYRESPONSE(0x80000006, CmppQueryResponse.class,CmppQueryResponseMessageCodec.class),
//    CMPPCANCELREQUEST(0x00000007, CmppCancelRequest.class,CmppCancelRequestMessageCodec.class),
//    CMPPCANCELRESPONSE(0x80000007, CmppCancelResponse.class,CmppCancelResponseMessageCodec.class),
//    CMPPACTIVETESTREQUEST(0x00000008, CmppActiveTestRequest.class,CmppActiveTestRequestMessageCodec.class),
//    CMPPACTIVETESTRESPONSE(0x80000008, CmppActiveTestResponse.class,CmppActiveTestResponseMessageCodec.class);
    
    private int commandId;
//    private Class<? extends PacketStructure> packetStructure;
//    private Class<? extends MessageToMessageCodec> codec;

    /**
     *
     * @param commandId
     */
    Cmpp20PacketType(int commandId) {
        this.commandId = commandId;
    }

    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }
}
