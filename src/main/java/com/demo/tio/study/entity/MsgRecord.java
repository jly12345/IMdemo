package com.demo.tio.study.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lingyun.jiang
 * @since 2019-10-22
 */
public class MsgRecord implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    private Integer fromId;

    private Integer toId;

    private Long roomId;

    private String contents;

    private Long createAt;

    private Integer mtype;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public Integer getMtype() {
        return mtype;
    }

    public void setMtype(Integer mtype) {
        this.mtype = mtype;
    }

    @Override
    public String toString() {
        return "MsgRecord{" +
        "id=" + id +
        ", fromId=" + fromId +
        ", toId=" + toId +
        ", roomId=" + roomId +
        ", contents=" + contents +
        ", createAt=" + createAt +
        ", mtype=" + mtype +
        "}";
    }
}
