package com.commerce.vo;

public class StatisticVo {

    private Long userCount;
    private Long productCount;
    private Long orderCount;

    public StatisticVo() {
    }

    public StatisticVo(Long userCount, Long productCount, Long orderCount) {
        this.userCount = userCount;
        this.productCount = productCount;
        this.orderCount = orderCount;
    }

    public Long getUserCount() {
        return userCount;
    }

    public void setUserCount(Long userCount) {
        this.userCount = userCount;
    }

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }
}
