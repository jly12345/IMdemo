package com.demo.tio.study.packet;

import lombok.Data;

/**
 * @Auther: lingyun.jiang
 * @Date: 2019/11/1 10:34
 * @Description:
 */
@Data
public class LayimToClientCheckOnlineMsgBody extends LayimBaseBody {

    public LayimToClientCheckOnlineMsgBody(boolean online){
        this.online = online;
    }

    private String id;

    private boolean online;


    @Override
    public byte getMtype() {
        return LayimMsgType.CLIENT_CHECK_ONLINE;
    }
}