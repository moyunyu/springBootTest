package com.zcx.test.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component //交给spring容器管理
public class LoginInterceptor implements HandlerInterceptor {

    //执行 controller 之前 先执行 preHandle
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("\n==========");
		String authorization = request.getHeader("Authorization");
		if (authorization == null) {
			response.sendError(HttpStatus.UNAUTHORIZED.value(), "未登录或登录已过期");
			return false;
		}
		Map<String, Object> payload = new HashMap<>();
		try {
			payload = JwtHelper.parser(authorization);
		} catch (Exception e) {
			response.sendError(HttpStatus.UNAUTHORIZED.value(), "未登录或登录已过期");
			return false;
		}
		if (payload != null) {
			request.setAttribute("payload", payload);
		}
		try {
			String ip = IPUtil.getIpFromRequest(request);
			String loginIp = payload.get("ip").toString();
			System.out.println("请求的IP地址为：" + ip);
			if (!ip.equals(loginIp)) {
				response.sendError(HttpStatus.UNAUTHORIZED.value(), "检测到登录地变更，请重新登陆");
				return false;
			}
		} catch (Exception e) {
			response.sendError(HttpStatus.UNAUTHORIZED.value(), "IP信息异常，请重新登陆");
			return false;
		}

		System.out.println("请求路径为：" + request.getRequestURI());
        return true; //true代表放行 false 代表拦截
    }

    //controller 执行后 但还未 渲染页面 就执行 postHandle
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// System.out.println("postHandle");
    }

    //所有操作执行完毕
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// System.out.println("afterCompletion");
    }
}
