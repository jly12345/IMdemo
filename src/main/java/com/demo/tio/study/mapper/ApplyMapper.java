package com.demo.tio.study.mapper;

import com.demo.tio.study.entity.Apply;
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
public interface ApplyMapper extends BaseMapper<Apply> {

    int getUnreadMsgCount(Map<String, Object> map);
}
