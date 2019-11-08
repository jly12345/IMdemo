package com.demo.tio.study.handler;

import com.demo.tio.study.entity.User;
import com.demo.tio.study.packet.LayimAbsMsgProcessor;
import com.demo.tio.study.packet.LayimMsgProperty;
import com.demo.tio.study.processor.LayimMsgProcessorManager;
import com.demo.tio.study.service.UserService;
import com.demo.tio.study.service.impl.UserServiceImpl;
import com.demo.tio.study.utils.ContextUser;
import com.demo.tio.study.utils.JwtUtil;
import com.demo.tio.study.utils.JwtVertifyResult;
import com.demo.tio.study.utils.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.http.common.HttpResponseStatus;
import org.tio.utils.json.Json;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.starter.TioWebSocketMsgHandler;

import java.nio.ByteBuffer;

@Component
public class IMSocketMsgHandler implements TioWebSocketMsgHandler {
    private static Logger log = LoggerFactory.getLogger(IMSocketMsgHandler.class);
    /**
     * 握手时走这个方法，业务可以在这里获取cookie，request参数等
     */

    private UserService getUserService(){
        return SpringUtil.getBean(UserServiceImpl.class);
    }
    @Override
    public HttpResponse handshake(HttpRequest request, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        String access_token = request.getParam("access_token");
        JwtVertifyResult result = JwtUtil.verifyToken(access_token);
        if (result.isVertified()) {
            bindUser(channelContext,result.getUserId());
        }else {
            httpResponse.setStatus(HttpResponseStatus.C401);
        }
        return httpResponse;
    }

    private void bindUser(ChannelContext channelContext, Long userId) {
        //绑定用户
        Tio.bindUser(channelContext, userId.toString());
        User user = getUserService().getById(userId);
        ContextUser currentUser = new ContextUser();
        currentUser.setUserid(userId.toString());
        currentUser.setUsername(user.getUserName());
        currentUser.setAvatar(user.getAvatar());
        channelContext.setAttribute(userId.toString(),currentUser);
    }

    @Override
    public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        log.info("..........onAfterHandshaked");
    }

    /**
     * 字节消息（binaryType = arraybuffer）过来后会走这个方法
     */
    @Override
    public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        String ss = new String(bytes, "utf-8");
        log.info("收到byte消息:{},{}", bytes, ss);
        //		byte[] bs1 = "收到byte消息".getBytes("utf-8");
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);

        buffer.put(bytes);

        byte msgType = bytes[4];
        String targetId = String.valueOf(bytes[0]);
        String content = String.valueOf(bytes[5]);
        log.info("msgType:{},targetId:{},content:{}", msgType, targetId,content);

        LayimAbsMsgProcessor processor = LayimMsgProcessorManager.getProcessor(msgType);
        log.info("LayimServerAioHandler:或得到的消息处理器为：{}",processor.getClass().getName());
        boolean unknown = processor == null;
        if(!unknown) {
            processor.process(wsRequest, channelContext);
        }
        return buffer;
    }

    /**
     * 当客户端发close flag时，会走这个方法
     */
    @Override
    public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        Tio.remove(channelContext, "receive close flag");
        return null;
    }

    /**
     * 字符消息（binaryType = blob）过来后会走这个方法
     */
    @Override
    public Object onText(WsRequest wsRequest, String text, ChannelContext channelContext) throws Exception {
        log.info("收到onText消息:{}", text);
        LayimMsgProperty property = Json.toBean(text,LayimMsgProperty.class);
        LayimAbsMsgProcessor processor = LayimMsgProcessorManager.getProcessor(property.getMtype());
        log.info("LayimServerAioHandler:或得到的消息处理器为：{}",processor.getClass().getName());
        boolean unknown = processor == null;
        if(!unknown) {
            processor.process(wsRequest, channelContext);
        }
        return null;
    }
}
