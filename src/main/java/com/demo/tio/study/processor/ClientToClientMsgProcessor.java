package com.demo.tio.study.processor;

import com.demo.tio.study.entity.ChatLog;
import com.demo.tio.study.packet.BodyConvert;
import com.demo.tio.study.packet.ChatRequestBody;
import com.demo.tio.study.packet.LayimAbsMsgProcessor;
import com.demo.tio.study.packet.LayimToClientChatMsgBody;
import com.demo.tio.study.service.ChatLogService;
import com.demo.tio.study.utils.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.utils.lock.SetWithLock;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;

/**
 * @author fyp
 * @crate 2017/11/19 23:38
 * @project SpringBootLayIM
 */
public class ClientToClientMsgProcessor extends LayimAbsMsgProcessor<ChatRequestBody> {

    private static final Logger logger = LoggerFactory.getLogger(ClientToClientMsgProcessor.class);

    @Override
    public WsResponse process(WsRequest layimPacket, ChatRequestBody body, ChannelContext channelContext) throws Exception {
        logger.info("ClientToClientMsgProcessor.process");

        LayimToClientChatMsgBody msgBody = BodyConvert.getInstance().convertToClientMsgBody(body,channelContext);
        WsResponse toClientBody = BodyConvert.getInstance().convertToTextResponse(msgBody);
        //发送消息
        logger.info(msgBody.toString());
        logger.info("LayimAbsMsgProcessor:消息处理完毕，发送给对方");
        saveChatLog(body, channelContext, msgBody, toClientBody);
        return null;
    }

    private void saveChatLog(ChatRequestBody body, ChannelContext channelContext, LayimToClientChatMsgBody msgBody, WsResponse toClientBody) {
        ChatLog log =new ChatLog();

        log.setAvatar(msgBody.getAvatar());
        log.setContent(msgBody.getContent());
        log.setCreateAt(msgBody.getTimestamp());
        log.setType(msgBody.getType());
        log.setToid(Long.valueOf(msgBody.getToid()));
        log.setUsername(msgBody.getUsername());
        log.setUid(Long.valueOf(msgBody.getId()));
        getChatLogService().save(log);
        send(channelContext,toClientBody,body.getToId());
    }

    private ChatLogService getChatLogService(){
        return (ChatLogService) SpringUtil.getBean("chatLogServiceImpl");
    }

    /**
     * 这个方法提出来的目的，是让 ClientToGroupMsgProcessor 进行重写（当然这么设计只是符合Layim，讲究通用性的话应该是分开设计比较好）
     * */
    public void send(ChannelContext channelContext, WsResponse toClientBody, String toId){
        SetWithLock<ChannelContext> toChannelContext = Tio.getByUserid(channelContext.getTioConfig(),toId);
        if(toChannelContext == null) {
            return;
        }
        Tio.sendToSet(channelContext.getTioConfig(),toChannelContext,toClientBody,null);
    }

    @Override
    public Class<ChatRequestBody> getBodyClass() {
        return ChatRequestBody.class;
    }
}
