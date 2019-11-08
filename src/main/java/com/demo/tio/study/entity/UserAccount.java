package com.demo.tio.study.entity;

import lombok.Data;

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
public class UserAccount implements Serializable {
    private static final long serialVersionUID = -1256463921348116906L;
    private Long id;

    private String password;

    private String username;

    private Long createAt;

}
