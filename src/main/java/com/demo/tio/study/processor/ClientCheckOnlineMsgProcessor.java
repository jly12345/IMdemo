package com.demo.tio.study.processor;

import com.demo.tio.study.packet.BodyConvert;
import com.demo.tio.study.packet.CheckOnlineRequestBody;
import com.demo.tio.study.packet.LayimAbsMsgProcessor;
import com.demo.tio.study.packet.LayimToClientCheckOnlineMsgBody;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.utils.lock.SetWithLock;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;

/**
 * @Auther: lingyun.jiang
 * @Date: 2019/11/1 10:28
 * @Description:
 */
public class ClientCheckOnlineMsgProcessor extends LayimAbsMsgProcessor<CheckOnlineRequestBody> {

    @Override
    public Class<CheckOnlineRequestBody> getBodyClass() {
        return CheckOnlineRequestBody.class;
    }

    @Override
    public WsResponse process(WsRequest layimPacket, CheckOnlineRequestBody body, ChannelContext channelContext) throws Exception {
        SetWithLock<ChannelContext> checkChannelContexts =
                Tio.getByUserid(channelContext.getTioConfig(),body.getId());
        //检查是否在线
        boolean isOnline;
        if(checkChannelContexts == null||checkChannelContexts.getObj().size()==0){
            isOnline = false;
        }else {
            ChannelContext checkChannelContext = checkChannelContexts.getObj().iterator().next();
            isOnline = checkChannelContext != null && !checkChannelContext.isClosed;
        }
        //组织返回数据
        LayimToClientCheckOnlineMsgBody msgBody = new LayimToClientCheckOnlineMsgBody(isOnline);
        msgBody.setId(body.getId());
        //返回信息
        send(msgBody,channelContext);
        return null;
    }

    protected void send(Object body,ChannelContext channelContext) throws Exception{
        WsResponse toClientBody = BodyConvert.getInstance().convertToTextResponse(body);
        Tio.send(channelContext,toClientBody);
    }
}
