//package com.demo.tio.study.utils;
//
//
//import com.demo.tio.study.entity.Apply;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.tio.core.ChannelContext;
//import org.tio.core.Tio;
//import org.tio.server.ServerTioConfig;
//import org.tio.utils.lock.SetWithLock;
//import org.tio.websocket.common.WsResponse;
//import org.tio.websocket.starter.TioWebSocketServerBootstrap;
//
//import java.io.IOException;
//
///**
// * @author fyp
// * @crate 2017/12/6 22:24
// * @project SpringBootLayIM
// * 服务端主动推送
// */
//public final class PushUtil {
//
//
//
//    private static Logger logger = LoggerFactory.getLogger(PushUtil.class);
//
//
//
//    private static ServerTioConfig getServerGroupContext(){
//        return getStarter().getServerGroupContext();
//    }
//    /**
//     * 服务端主动推送消息
//     * */
//    private static void push(ChannelContext channelContext,Object msg) {
//        try {
//            WsResponse response = BodyConvert.getInstance().convertToTextResponse(msg);
//            Tio.send(channelContext, response);
//        }catch (IOException ex){
//
//        }
//    }
//
//    /**
//     * 获取channelContext
//     * */
//    private static ChannelContext getChannelContext(String toId) {
//        ServerTioConfig context = getServerGroupContext();
//        //找到用户
//        SetWithLock<ChannelContext> channelContexts = Tio.getByUserid(context, toId);
//        if (channelContexts == null) {
//            return null;
//        }
//        if (channelContexts.getObj().size() > 0) {
//            return channelContexts.getObj().iterator().next();
//        }
//        return null;
//    }
//    /**
//     * 服务端推送消息
//     * */
//    public static void pushApplyMessage(String toId) {
//        logger.info("执行到了发送方法:pushApplyMessage");
//        LayimToClientNoticeMsgBody body = new LayimToClientNoticeMsgBody();
//        ChannelContext channelContext = getChannelContext(toId);
//        //先判断是否在线，再去查询数据库，减少查询次数
//        if (channelContext != null && !channelContext.isClosed()) {
//            int count = getApplyService().getUnreadMsgCount(Long.parseLong(toId));
//            body.setCount(count);
//
//            push(channelContext, body);
//        }
//    }
//    public static void pushApplyMessage(long toId){
//        pushApplyMessage(toId+"");
//    }
//
//    /**
//     * 添加好友成功之后向对方推送消息
//     * */
//    public static void pushAddFriendMessage(long applyid){
//        if(applyid==0){
//            return;
//        }
//        Apply apply = applyService.getApply(applyid);
//        ChannelContext channelContext = getChannelContext(""+apply.getUid());
//        //先判断是否在线，再去查询数据库，减少查询次数
//        if (channelContext != null && !channelContext.isClosed()) {
//            LayimToClientAddFriendMsgBody body = new LayimToClientAddFriendMsgBody();
//            User user = getUserService().getUser(apply.getToid());
//            if (user==null){return;}
//            //对方分组ID
//            body.setGroupid(apply.getGroup());
//
//            //当前用户的基本信息，用于调用layim.addList
//            body.setAvatar(user.getAvatar());
//            body.setId(user.getId());
//            body.setSign(user.getSign());
//            body.setType("friend");
//            body.setUsername(user.getUserName());
//
//            push(channelContext, body);
//        }
//    }
//
//    /**
//     * 判断一个用户是否在线
//     * */
//    public static boolean isOnline(long userId){
//        ChannelContext channelContext = getChannelContext(userId+"");
//        return channelContext!=null && !channelContext.isClosed()&&!channelContext.isRemoved();
//    }
//
//}
