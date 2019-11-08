package com.demo.tio.study.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.tio.study.entity.Apply;
import com.demo.tio.study.entity.User;
import com.demo.tio.study.result.BaseDataViewModel;
import com.demo.tio.study.result.JsonResult;
import com.demo.tio.study.service.ApplyService;
import com.demo.tio.study.service.FriendGroupService;
import com.demo.tio.study.service.UserService;
import com.demo.tio.study.utils.ContextUser;
import com.demo.tio.study.utils.DateUtil;
import com.demo.tio.study.utils.JwtUtil;
import com.demo.tio.study.utils.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.server.ServerTioConfig;
import org.tio.utils.lock.SetWithLock;
import org.tio.websocket.starter.TioWebSocketServerBootstrap;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lingyun.jiang
 * @since 2019-10-22
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ApplyService applyService;
    @Autowired
    private UserService userService;
    @Autowired
    private FriendGroupService friendGroupService;

    @Autowired
    private TioWebSocketServerBootstrap bootstrap;
    /**
     * layim基础初始化数据
     * 好友信息，群组信息，个人信息
     * */
    @GetMapping(value = "/base")
    public JsonResult getBaseData(){
        Long userId = ShiroUtil.getUserId();
        BaseDataViewModel baseList = userService.getBaseList(userId);

        baseList.getFriend().forEach(d -> d.getList().forEach(u->{
            ServerTioConfig context = bootstrap.getServerTioConfig();
            //找到用户
            SetWithLock<ChannelContext> channelContexts = Tio.getByUserid(context, u.getId().toString());
            if (channelContexts == null) {
                u.setSort(0); //下线
            }else {
                if (channelContexts.getObj().size() > 0) {
                    ChannelContext channelContext = channelContexts.getObj().iterator().next();
                    u.setSort(channelContext!=null && !channelContext.isClosed&&!channelContext.isRemoved?1:0);
                }
            }

        }));
        return JsonResult.success(baseList);

    }

    /**
     * 获取用户token，调用api会用到token
     * */
    @GetMapping(value = "/token")
    public JsonResult getToken() {
        Long userId = ShiroUtil.getUserId();
        if (userId > 0) {
            return JsonResult.success(JwtUtil.createToken(userId));
        }
        return JsonResult.fail("no login");
    }

    /**
     * 用户未读消息个数
     * */
    @GetMapping(value = "/apply-unread")
    public JsonResult msgCount(){
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",ShiroUtil.getUserId());
        queryWrapper.eq("isread",0);
        int count = applyService.count(queryWrapper);
        return JsonResult.success(count);
    }

    /**
     * 用户首页
     * */
    @RequestMapping(value = "/{uid}")
    public ModelAndView home(@PathVariable("uid") Long uid, Model model){
        ModelAndView mv = new ModelAndView("user/home");
        User user = userService.getById(uid);
        Map<String, Object> map = setModel(user);
        model.addAttribute("user",map);
        model.addAttribute("page","home");
        mv.addObject(model);
        return mv;
    }

    /**
     * 属性赋值
     * */
    private Map<String,Object> setModel(User user){
        long currentUserId = ShiroUtil.getUserId();
        long visitUserId = user.getId();
        //是否是自己
        boolean isSelf = currentUserId == visitUserId;
        //两个用户是否已经是好友
        boolean isFriend = friendGroupService.isFriend(currentUserId,visitUserId);

        Map<String,Object> userMap = new HashMap<>(8);
        userMap.put("avatar",user.getAvatar());
        userMap.put("username",user.getUserName());
        userMap.put("addtime", DateUtil.getDateStr(user.getCreateAt())+" 加入");
        if(user.getSign()==null ||user.getSign().length()==0) {
            userMap.put("sign", "");
        }else {
            userMap.put("sign", "(" + user.getSign() + ")");
        }
        userMap.put("userid",user.getId());
        userMap.put("self",isSelf || isFriend);

        return userMap;
    }


    /**
     * 用户设置页面
     * */
    @RequestMapping("/set")
    public ModelAndView  set(Model model){
        ModelAndView mv = new ModelAndView("user/set");
        ContextUser currentUser = ShiroUtil.getCurrentUser();
        model.addAttribute("user",currentUser);
        model.addAttribute("page","set");
        mv.addObject(model);
        return mv;
    }

    /**
     * 我的消息
     * */
    @RequestMapping("/message")
    public ModelAndView  message(Model model){
        ModelAndView mv = new ModelAndView("user/message");
        ContextUser currentUser = ShiroUtil.getCurrentUser();
        model.addAttribute("user",currentUser);
        model.addAttribute("page","message");
        mv.addObject(model);
        return mv;
    }

    /**
     * 用户中心
     * */
    @RequestMapping("/index")
    public ModelAndView index(Model model){
        ModelAndView mv = new ModelAndView("user/index");
        ContextUser currentUser = ShiroUtil.getCurrentUser();
        model.addAttribute("user",currentUser);
        model.addAttribute("page","index");
        mv.addObject(model);
        return mv;
    }
}

