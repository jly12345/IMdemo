package com.demo.tio.study.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class UserBigGroup implements Serializable {

    private static final long serialVersionUID = -8790053596148656287L;
    @TableId
    private Long uid;

    private Long groupId;

}
