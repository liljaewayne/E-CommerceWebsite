package com.commerce.controller.backend;

import com.commerce.common.Const;
import com.commerce.common.ServerResponse;
import com.commerce.pojo.User;
import com.commerce.service.UserService;
import com.commerce.util.CookieUtil;
import com.commerce.util.JsonUtil;
import com.commerce.util.RedisSharededPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/manage/user")
public class UserManageController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login.do", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session, HttpServletResponse httpServletResponse) {
        ServerResponse<User> response = userService.login(username, password);
        if (response.isSuccess()) {
            User user = response.getData();
            if (user.getRole() == Const.Role.ROLE_ADMIN) {

                RedisSharededPoolUtil.setEx(session.getId(), JsonUtil.objToString(user), Const.RedisCacheExTime.REDIS_SESSION_EX_TIME_SECONDS);
                CookieUtil.writeLoginToken(httpServletResponse, session.getId());

                return response;
            } else {
                return ServerResponse.createByErrorMessage("不是管理员,无法登录管理后台");
            }
        }
        return response;
    }

    @RequestMapping(value = "list.do", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ServerResponse list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return userService.listAll(pageNum, pageSize);
    }

}
