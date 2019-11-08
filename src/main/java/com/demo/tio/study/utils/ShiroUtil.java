package com.demo.tio.study.utils;

import com.demo.tio.study.entity.User;
import com.demo.tio.study.entity.UserAccount;
import com.demo.tio.study.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroUtil {
    private static Logger logger = LoggerFactory.getLogger(ShiroUtil.class);
    /**
     * 从shiro中拿用户ID
     * */
    public static Long getUserId(){
        Subject subject = SecurityUtils.getSubject();
        Long userId = 0L;
        if(subject.isAuthenticated()) {
            UserAccount userAccount = (UserAccount) subject.getPrincipal();
            userId = userAccount.getId();
        }
        logger.info("current user：{}",userId);
        return userId;
    }

    /**
     * 获取当前用户信息
     * */
    public static ContextUser getCurrentUser(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()) {
            if(subject.getSession().getAttribute("user") ==null){
                UserAccount userAccount = (UserAccount) subject.getPrincipal();
                User user = SpringUtil.getBean(UserServiceImpl.class).getById(userAccount.getId());
                ContextUser contextUser = new ContextUser();
                contextUser.setUsername(user.getUserName());
                contextUser.setUserid(user.getId().toString());
                contextUser.setAvatar(user.getAvatar());
                subject.getSession().setAttribute("user",contextUser);
                return contextUser;
            }else {
                return (ContextUser)subject.getSession().getAttribute("user");
            }
        }
        return null;

    }
}
