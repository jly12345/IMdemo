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
 * @since 2019-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Apply implements Serializable {


    private static final long serialVersionUID = -6130380557956447566L;
    private Long id;

    private Long createAt;

    private String avatar;

    private Boolean isread;

    private String name;

    private String remark;

    private Integer result;

    private Long toid;

    private Integer type;

    private Long uid;

    private Long groupId;

}
