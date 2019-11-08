package com.demo.tio.study.service.impl;

import com.demo.tio.study.entity.UserAccount;
import com.demo.tio.study.mapper.UserAccountMapper;
import com.demo.tio.study.service.UserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lingyun.jiang
 * @since 2019-10-22
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}
