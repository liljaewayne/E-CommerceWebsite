package com.commerce.controller.portal;

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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do")
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session, HttpServletResponse httpServletResponse) {
        ServerResponse<User> response = userService.login(username, password);
        if (response.isSuccess()) {
            
            /*
            登录时, 将生成的JSESSIONID作为key写入redis, 并将名为commerce_login_token(值为JSESSIONID)的cookie写回浏览器
             */
            RedisSharededPoolUtil.setEx(session.getId(),
                    JsonUtil.objToString(response.getData()),
                    Const.RedisCacheExTime.REDIS_SESSION_EX_TIME_SECONDS);


            CookieUtil.writeLoginToken(httpServletResponse, session.getId());

        }
        return response;
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        RedisSharededPoolUtil.del(loginToken);
        CookieUtil.delLoginToken(httpServletRequest, httpServletResponse);

        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user) {
        return userService.register(user);
    }


    @RequestMapping(value = "check_valid.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String str, String type) {
        return userService.checkValid(str, type);
    }


    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpServletRequest httpServletRequest) {

        
        /*
        查询登录信息时, 从浏览器带过来的所有cookie中找到指定为commerce_login_token, 用其值到redis中作为key查找对应用户的序列化数据
         */
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        String userJson = RedisSharededPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObj(userJson, User.class);

        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }

        return ServerResponse.createBySuccess(user);
    }


    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username) {
        return userService.selectQuestion(username);
    }


    @RequestMapping(value = "forget_check_answer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        return userService.checkAnswer(username, question, answer);
    }


    @RequestMapping(value = "forget_reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
        return userService.forgetResetPassword(username, passwordNew, forgetToken);
    }


    @RequestMapping(value = "reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpServletRequest httpServletRequest, String passwordOld, String passwordNew) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        String userJson = RedisSharededPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObj(userJson, User.class);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return userService.resetPassword(passwordOld, passwordNew, user);
    }


    @RequestMapping(value = "update_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> update_information(HttpServletRequest httpServletRequest, User user) {

        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        String userJson = RedisSharededPoolUtil.get(loginToken);
        User currentUser = JsonUtil.stringToObj(userJson, User.class);
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }

        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        ServerResponse<User> response = userService.updateInformation(user);

        if (response.isSuccess()) {
            response.getData().setUsername(currentUser.getUsername());
            RedisSharededPoolUtil.setEx(loginToken, JsonUtil.objToString(response.getData()), Const.RedisCacheExTime.REDIS_SESSION_EX_TIME_SECONDS);
        }
        return response;
    }

    @RequestMapping(value = "get_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> get_information(HttpServletRequest httpServletRequest) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        String userJson = RedisSharededPoolUtil.get(loginToken);
        User currentUser = JsonUtil.stringToObj(userJson, User.class);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.NEED_LOGIN.getCode(), "未登录,需要强制登录status=10");
        }
        return userService.getInformation(currentUser.getId());
    }


}
