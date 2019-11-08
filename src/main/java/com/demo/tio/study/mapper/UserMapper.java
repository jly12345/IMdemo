package com.demo.tio.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.tio.study.entity.User;
import com.demo.tio.study.result.BaseDataViewModel;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lingyun.jiang
 * @since 2019-10-22
 */
public interface UserMapper extends BaseMapper<User> {

    BaseDataViewModel selectList(Long userid);
}
