package com.demo.tio.study.service;

import com.demo.tio.study.entity.FriendGroup;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lingyun.jiang
 * @since 2019-10-22
 */
public interface FriendGroupService extends IService<FriendGroup> {

    boolean isFriend(long currentUserId, long visitUserId);
}
