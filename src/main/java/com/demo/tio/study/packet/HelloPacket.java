package com.demo.tio.study.packet;

import org.tio.core.intf.Packet;

/**
 * 消息包实体
 *
 * @author yangjian
 */
public class HelloPacket extends Packet {
    private static final long serialVersionUID = -1702651644100947087L;

    public static final int HEADER_LENGTH = 4;//消息头的长度
    public static final String CHARSET = "utf-8";
    private byte[] body;
    /**
     * @return the body
     */
    public byte[] getBody() {
        return body;
    }
    /**
     * @param body the body to set
     */
    public void setBody(byte[] body) {
        this.body = body;
    }
}