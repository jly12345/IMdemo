package com.demo.tio.study.web;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: lingyun.jiang
 * @Date: 2019/10/22 09:57
 * @Description:
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/login")
    public String login(){
        return "account/login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request) throws Exception{
        request.getSession().removeAttribute("login_msg");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        String msg ;
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                msg = "account";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                msg = "pwd";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                msg = "code";
            } else {
                msg = "other";
            }
            request.getSession().setAttribute("login_msg",msg);
        }
        // 此方法不处理登录成功,由shiro进行处理
        log.info("登录成功");

        return "account/login";
    }


    @GetMapping("/reg")
    public String reg(){
        return "account/reg";
    }
}
