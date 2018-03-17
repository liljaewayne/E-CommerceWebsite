package com.commerce.controller.backend;

import com.commerce.common.ServerResponse;
import com.commerce.service.StatisticService;
import com.commerce.vo.StatisticVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manage/statistic")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @RequestMapping("base_count.do")
    @ResponseBody
    public ServerResponse<StatisticVo> getChildrenParallelCategory() {
        return statisticService.getBaseCount();
    }
}
