package com.commerce.controller.common.interceptor;

import com.commerce.common.ServerResponse;
import com.commerce.pojo.User;
import com.commerce.util.CookieUtil;
import com.commerce.util.JsonUtil;
import com.commerce.util.RedisSharededPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 请求controller中的方法名
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 解析HandlerMethod
        String methodName = handlerMethod.getMethod().getName();
        String className = handlerMethod.getBean().getClass().getSimpleName();

        StringBuilder requestParamBuffer = new StringBuilder();
        Map paramMap = request.getParameterMap();
        Iterator it = paramMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String mapKey = (String) entry.getKey();
            String mapValue = "";

            // request的这个参数map的value返回的是一个String[]
            Object obj = entry.getValue();
            if (obj instanceof String[]) {
                String[] strs = (String[]) obj;
                mapValue = Arrays.toString(strs);
            }
            requestParamBuffer.append(mapKey).append("=").append(mapValue);
        }

        log.info("权限拦截器拦截到请求,className:{},methodName:{},param:{}", className, methodName, requestParamBuffer);

        User user = null;
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isNotBlank(loginToken)) {
            String userJsonStr = RedisSharededPoolUtil.get(loginToken);
            user = JsonUtil.stringToObj(userJsonStr, User.class);
        }

        // 上传由于富文本的控件要求，要特殊处理返回值，这里面区分是否登录以及是否有权限
        if (user == null) {
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");

            PrintWriter out = response.getWriter();
            out.print(JsonUtil.objToString(ServerResponse.createByErrorMessage("拦截器拦截,用户未登录")));
            out.flush();
            out.close();
            return false;
        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }

}
