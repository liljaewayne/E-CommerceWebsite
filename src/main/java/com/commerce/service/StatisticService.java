package com.commerce.service;

import com.commerce.common.ServerResponse;
import com.commerce.vo.StatisticVo;

public interface StatisticService {

    ServerResponse<StatisticVo> getBaseCount();
}
