package com.commerce.service;

import com.commerce.common.ServerResponse;
import com.commerce.pojo.Shipping;
import com.github.pagehelper.PageInfo;

public interface ShippingService {

    ServerResponse add(Integer userId, Shipping shipping);

    ServerResponse<String> del(Integer userId, Integer shippingId);

    ServerResponse update(Integer userId, Shipping shipping);

    ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);

}
