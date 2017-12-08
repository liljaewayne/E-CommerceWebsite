package com.commerce.controller.backend;


import com.commerce.common.Const;
import com.commerce.common.ServerResponse;
import com.commerce.pojo.User;
import com.commerce.service.OrderService;
import com.commerce.service.UserService;
import com.commerce.vo.OrderVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/manage/order")
public class OrderManageController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderList(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");

        }
        if (userService.checkAdminRole(user).isSuccess()) {
            //填充我们增加产品的业务逻辑
            return orderService.manageList(pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<OrderVo> orderDetail(HttpSession session, Long orderNo) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");

        }
        if (userService.checkAdminRole(user).isSuccess()) {
            //填充我们增加产品的业务逻辑

            return orderService.manageDetail(orderNo);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }


    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderSearch(HttpSession session, Long orderNo, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");

        }
        if (userService.checkAdminRole(user).isSuccess()) {
            //填充我们增加产品的业务逻辑
            return orderService.manageSearch(orderNo, pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }


    @RequestMapping("send_goods.do")
    @ResponseBody
    public ServerResponse<String> orderSendGoods(HttpSession session, Long orderNo) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");

        }
        if (userService.checkAdminRole(user).isSuccess()) {
            //填充我们增加产品的业务逻辑
            return orderService.manageSendGoods(orderNo);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }


}
