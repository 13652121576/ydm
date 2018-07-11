package com.ydm.dataSource;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
@Aspect  
@Component
public class DataSourceAspect {
	private static Logger logger = LogManager.getLogger(DataSourceAspect.class); 
	@Autowired  
	private  HttpServletRequest request;  
	@Pointcut("execution(* com.ydm.service.*.*(..))")
    public void pointcut() {}
	
	@Before("pointcut()")
	public void before(JoinPoint joinPoint) {
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
		try {
			if (methodName.indexOf("add") > -1 || methodName.indexOf("create") > -1 
					|| methodName.indexOf("save") > -1 || methodName.indexOf("insert") > -1 
					|| methodName.indexOf("edit") > -1 || methodName.indexOf("update") > -1
					|| methodName.indexOf("batch") > -1 || methodName.indexOf("execute") > -1
					|| methodName.indexOf("delete") > -1 || methodName.indexOf("remove") > -1){
				System.out.println(className + "======" + methodName + " 切换到: master");
				DataSourceSwitcher.setMaster();
			} else {
				System.out.println(className + "======" + methodName + " 切换到: slave");
				DataSourceSwitcher.setSlave();
			}
		} catch (Exception ex) {
			logger.error("[*]"+"["+className+"]"+"["+methodName+"]",ex);
			ex.printStackTrace();
		}
	}
	// 方法执行完之后被调用
	@AfterReturning(pointcut = "pointcut()")
	public void afterReturning(JoinPoint joinPoint) {
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String jsonStr = JSONObject.toJSONString(args);//将java对象转换为字符串
//        WqUser wqUser=null;
//        if (request.getSession().getAttribute(SysConstant.SESSION_USER)!= null){
//        	wqUser=(WqUser) request.getSession().getAttribute(SysConstant.SESSION_USER);
//        }
//        String userStr = JSONObject.toJSONString(wqUser);//将java对象转换为字符串
//		if (methodName.indexOf("add") > -1 || methodName.indexOf("create") > -1 
//				|| methodName.indexOf("save") > -1 || methodName.indexOf("insert") > -1) {
//			logger.info("[create]"+"["+className+"]"+"["+methodName+"]"+jsonStr+"[operator]"+userStr+"[ip]"+IpAdrressUtils.getIpAdrress(request));
//		}else if(methodName.indexOf("edit") > -1 || methodName.indexOf("update") > -1
//				|| methodName.indexOf("batch") > -1 || methodName.indexOf("execute") > -1) {
//			logger.info("[update]"+"["+className+"]"+"["+methodName+"]"+jsonStr+"[operator]"+userStr+"[ip]"+IpAdrressUtils.getIpAdrress(request));
//		}else if(methodName.indexOf("delete") > -1 || methodName.indexOf("remove") > -1){
//			logger.info("[delete]"+"["+className+"]"+"["+methodName+"]"+jsonStr+"[operator]"+userStr+"[ip]"+IpAdrressUtils.getIpAdrress(request));
//		} else {
//			logger.debug("[query]"+"["+className+"]"+"["+methodName+"]"+jsonStr+"[operator]"+userStr+"[ip]"+IpAdrressUtils.getIpAdrress(request));
//		}
	}
	// 抛出Exception之后被调用
	@AfterThrowing(pointcut = "pointcut()", throwing = "error")
	public void afterThrowing(JoinPoint joinPoint, Throwable error) {
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String jsonStr = JSONObject.toJSONString(args);//将java对象转换为字符串
		logger.error("[*]"+"["+className+"]"+"["+methodName+"]"+jsonStr,error);
	}
	
}
