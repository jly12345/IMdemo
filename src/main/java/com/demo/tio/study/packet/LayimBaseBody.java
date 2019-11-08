package com.demo.tio.study.packet;

import java.io.Serializable;

/**
 * @author fyp
 * @crate 2017/11/19 20:56
 * @project SpringBootLayIM
 */
public class LayimBaseBody extends LayimMsgProperty implements Serializable{

    private static final long serialVersionUID = -528765914537773151L;

    public long getTimestamp() {
        if (timestamp == 0){
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    private long timestamp;
}
