package com.demo.tio.study.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fyp
 * @crate 2017/11/19 23:56
 * @project SpringBootLayIM
 * 单聊信息发送包，客户端发送过来的包 ChatRequestBody 转化为 LayimToClientChatMsgBody
 * 转化方法: packet.convert.BodyConvert.convertToClientMsgBody
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LayimToClientChatMsgBody extends LayimBaseBody{

    private static final long serialVersionUID = 8065094328885689319L;
    private String from;
    private String id;
    private String avatar;
    private String content;
    private String username;
    private String type;
    private String toid;
}
