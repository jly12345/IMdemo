package com.demo.tio.study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.tio.study.entity.User;
import com.demo.tio.study.mapper.UserMapper;
import com.demo.tio.study.result.BaseDataViewModel;
import com.demo.tio.study.result.JsonResult;
import com.demo.tio.study.result.UserViewModel;
import com.demo.tio.study.service.UserService;
import com.demo.tio.study.utils.ShiroUtil;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lingyun.jiang
 * @since 2019-10-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseDataViewModel getBaseList(Long userId) {

        BaseDataViewModel users = userMapper.selectList(userId);
        return users;
    }
}
