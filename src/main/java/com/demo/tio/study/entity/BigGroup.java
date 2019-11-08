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
public class BigGroup implements Serializable {


    private static final long serialVersionUID = 7031493555272336785L;
    private Long id;

    private Long createAt;

    private String avatar;

    private String description;

    private String groupName;

    private Long uid;


}
