package com.demo.tio.study.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lingyun.jiang
 * @since 2019-11-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ChatLog implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    private Long uid;

    private String username;

    private Long createAt;

    private String avatar;

    private String content;

    private Long timestamp;
    private Long toid;

    private String type;
}
