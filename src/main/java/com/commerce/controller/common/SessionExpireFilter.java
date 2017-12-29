package com.commerce.controller.common;

import com.commerce.common.Const;
import com.commerce.pojo.User;
import com.commerce.util.CookieUtil;
import com.commerce.util.JsonUtil;
import com.commerce.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SessionExpireFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if (StringUtils.isNotBlank(loginToken)) {
            String userJson = RedisPoolUtil.get(loginToken);
            User user = JsonUtil.stringToObj(userJson, User.class);
            if (user != null) {
                // 重置session时间, 即调用expire
                RedisPoolUtil.expire(loginToken, Const.RedisCacheExTime.REDIS_SESSION_EX_TIME);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
