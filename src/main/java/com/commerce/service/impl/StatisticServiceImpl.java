package com.commerce.service.impl;

import com.commerce.common.ServerResponse;
import com.commerce.dao.StatisticMapper;
import com.commerce.service.StatisticService;
import com.commerce.vo.StatisticVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private StatisticMapper statisticMapper;

    @Override
    public ServerResponse<StatisticVo> getBaseCount() {
        return ServerResponse.createBySuccess(statisticMapper.getBaseCount());
    }
}
