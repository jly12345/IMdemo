package com.demo.tio.study.mapper;

import com.demo.tio.study.entity.FriendGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lingyun.jiang
 * @since 2019-10-22
 */
public interface FriendGroupMapper extends BaseMapper<FriendGroup> {

    long countByUidInGroup(Map<String,Long> map);
}
