package com.demo.tio.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.tio.study.entity.User;
import com.demo.tio.study.result.BaseDataViewModel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lingyun.jiang
 * @since 2019-10-22
 */
public interface UserService extends IService<User> {

    BaseDataViewModel getBaseList(Long userId);
}
