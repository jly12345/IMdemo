package com.demo.tio.study.web;

import com.demo.tio.study.utils.ContextUser;
import com.demo.tio.study.utils.ShiroUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {
     private Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/")
    public String index(HttpSession session){
        ContextUser currentUser = ShiroUtil.getCurrentUser();
        session.setAttribute("user",currentUser);
        return "index";
    }

}