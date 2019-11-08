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
public class FriendGroup implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    private Long createAt;

    private String name;

    private Long uid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "FriendGroup{" +
        "id=" + id +
        ", createAt=" + createAt +
        ", name=" + name +
        ", uid=" + uid +
        "}";
    }
}
