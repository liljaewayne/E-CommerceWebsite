package com.commerce.controller.backend;


import com.commerce.common.ServerResponse;
import com.commerce.service.OrderService;
import com.commerce.vo.OrderVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/manage/order")
public class OrderManageController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        return orderService.manageList(pageNum, pageSize);
    }

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<OrderVo> orderDetail(Long orderNo) {

        return orderService.manageDetail(orderNo);
    }


    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderSearch(Long orderNo, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return orderService.manageSearch(orderNo, pageNum, pageSize);
    }


    @RequestMapping("send_goods.do")
    @ResponseBody
    public ServerResponse<String> orderSendGoods(Long orderNo) {

        return orderService.manageSendGoods(orderNo);
    }


}
