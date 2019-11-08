package com.demo.tio.study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.tio.study.entity.FriendGroup;
import com.demo.tio.study.mapper.FriendGroupMapper;
import com.demo.tio.study.service.FriendGroupService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lingyun.jiang
 * @since 2019-10-22
 */
@Service
public class FriendGroupServiceImpl extends ServiceImpl<FriendGroupMapper, FriendGroup> implements FriendGroupService {

    @Override
    public boolean isFriend(long currentUserId, long visitUserId) {
        Map<String,Long> param = new HashMap<>();
        param.put("currentUserId",currentUserId);
        param.put("visitUserId",visitUserId);
        return baseMapper.countByUidInGroup(param) > 0;
    }
}
