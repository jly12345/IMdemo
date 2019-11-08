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
public class User implements Serializable {


    private static final long serialVersionUID = 81386801620600296L;
    private Long id;

    private Long createAt;

    private String avatar;

    private String sign;

    private String userName;

}
