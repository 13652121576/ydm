package com.ydm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ydm.util.CookieUtils;
import com.ydm.util.ResMsgUtils;
import com.ydm.util.SysConstant;

/**
 * 后台系统登录拦截
 */
public class AdminInterceptor implements HandlerInterceptor{
	private static Logger logger = LogManager.getLogger(AdminInterceptor.class); 
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		String requestUri = request.getRequestURI();  //   /wq/admin/item/getList
//		String webName=request.getContextPath();//系统应用名称
//        //请求controller中的方法名 
//        HandlerMethod handlerMethod = (HandlerMethod)handler;
//        //解析HandlerMethod
//        String methodName = handlerMethod.getMethod().getName();
//        String className = handlerMethod.getBean().getClass().getSimpleName();
//        System.out.println("webName:"+webName+"===methodName:"+methodName+"===className:"+className);
//        if(className.contains("LoginController")
//        		||className.contains("WqCompanyController")
//        		||className.contains("WqComTemPerController")
//        		||(className.contains("WqResourcesController")&&!methodName.equals("getPower"))){//放行登录接口 及字典接口
//        	return true;
//        }
//        String userPower=(String) request.getSession().getAttribute(SysConstant.SESSION_USERPOWER);
//        if (request.getSession().getAttribute(SysConstant.SESSION_USER)!= null){
//        	if(StringUtils.isNotBlank(userPower)) {
//	        	String[] actionStr=userPower.split("|");
//	        	for (String str : actionStr) {
//	        		if(requestUri.indexOf(str)>-1) {
//	        			return true;
//	            	}
//				}
//				ResMsgUtils.sendMessage(response, "adminNoPower");
//				System.out.println("=======没有权限=======");
//	        	return false;
//        	}else {
//        		ResMsgUtils.sendMessage(response, "adminNoPower");
//        		System.out.println("=======没有权限=======");
//        		return false;
//        	}
//        }
//        System.out.println("=======没有登录，或session已失效=======");
//        ResMsgUtils.sendMessage(response, "adminInterceptor");
//        CookieUtils.deleteCookie(request, response, "JSESSIONID");
//        //response.sendRedirect(webName+"/admin/login/index"); //前后端不分离
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    	if(ex != null) {
    		logger.error("[**]",ex);
    	}
    }
}
