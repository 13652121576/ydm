package com.ydm.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

public class MapUtil {
	/**
	 * getPageList分页传jsonStr转map
	 * @param queryJson
	 * @return
	 */
  	public static Map<String, Object> toParamMap(String queryJson){
  		Map<String, Object> paramMap=new HashMap<String, Object>();
  		int pageIndex=1;
  		int pageSize=Page.DEFAULT_PAGE_SIZE;
  		if(StringUtils.isNotBlank(queryJson)){
			paramMap=JSON.parseObject(queryJson);
		}
  		if (paramMap.containsKey("pageIndex")) {
  			String pageIndexStr=String.valueOf( paramMap.get("pageIndex"));
			if(StringUtils.isNotBlank(pageIndexStr)&&pageIndexStr!="null"){
				pageIndex=Integer.parseInt(pageIndexStr);
			}
		}
		if (paramMap.containsKey("pageSize")) {
			String pageSizeStr=String.valueOf( paramMap.get("pageSize"));
			if(StringUtils.isNotBlank(pageSizeStr)&&pageSizeStr!="null"){
				pageSize=Integer.parseInt(pageSizeStr);
			}
		}
		paramMap.put("pageIndex", pageIndex);
		paramMap.put("firstResult", (pageIndex - 1) * pageSize);
		paramMap.put("pageSize", pageSize);
		paramMap.put("maxResult", pageSize);
		return paramMap;
  	}
  	/**
  	 * getPageList分页传map值转换
  	 * @param map
  	 * @return
  	 */
  	public static Map<String, Object> changeMap(Map<String, Object> paramMap){
  		int pageIndex=1;
  		int pageSize=Page.DEFAULT_PAGE_SIZE;
  		if(paramMap==null){
			paramMap=new HashMap<String, Object>();
		}
  		if (paramMap.containsKey("pageIndex")) {
  			String pageIndexStr=String.valueOf( paramMap.get("pageIndex"));
			if(StringUtils.isNotBlank(pageIndexStr)&&pageIndexStr!="null"){
				pageIndex=Integer.parseInt(pageIndexStr);
			}
		}
		if (paramMap.containsKey("pageSize")) {
			String pageSizeStr=String.valueOf( paramMap.get("pageSize"));
			if(StringUtils.isNotBlank(pageSizeStr)&&pageSizeStr!="null"){
				pageSize=Integer.parseInt(pageSizeStr);
			}
		}
		paramMap.put("pageIndex", pageIndex);
		paramMap.put("firstResult", (pageIndex - 1) * pageSize);
		paramMap.put("pageSize", pageSize);
		paramMap.put("maxResult", pageSize);
		return paramMap;
  	}
  	/**
  	 * geList传map值转换
  	 * @param map
  	 * @return
  	 */
  	public static Map<String, Object> listMap(Map<String, Object> paramMap){
  		int firstResult=0;
  		int maxResult=Integer.MAX_VALUE;
  		if(paramMap==null){
			paramMap=new HashMap<String, Object>();
		}
  		if (paramMap.containsKey("firstResult")) {
  			String firstResultStr=String.valueOf( paramMap.get("firstResult"));
			if(StringUtils.isNotBlank(firstResultStr)&&firstResultStr!="null"){
				firstResult=Integer.parseInt(firstResultStr);
			}
  		}
  		if (paramMap.containsKey("maxResult")) {
  			String maxResultStr=String.valueOf( paramMap.get("maxResult"));
			if(StringUtils.isNotBlank(maxResultStr)&&maxResultStr!="null"){
				maxResult=Integer.parseInt(maxResultStr);
			}
  		}
		paramMap.put("firstResult", firstResult);
		paramMap.put("maxResult", maxResult);
		return paramMap;
  	}
  	/**
      * 获取利用反射获取类里面的值和名称
     */
    public static Map<String, Object> beanToMap(Object bean) {
    	Class<? extends Object> clazz = bean.getClass();  
        Map<String, Object> returnMap = new HashMap<String, Object>();  
        BeanInfo beanInfo = null;  
        try {  
            beanInfo = Introspector.getBeanInfo(clazz);  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (int i = 0; i < propertyDescriptors.length; i++) {  
                PropertyDescriptor descriptor = propertyDescriptors[i];  
                String propertyName = descriptor.getName();  
                if (!propertyName.equals("class")) {  
                    Method readMethod = descriptor.getReadMethod();  
                    Object result = null;  
                    result = readMethod.invoke(bean, new Object[0]);  
                    if (null != propertyName) {  
                        propertyName = propertyName.toString();  
                    }  
                    returnMap.put(propertyName, result);  
                }  
            }  
        } catch (IntrospectionException e) {  
            System.out.println("分析类属性失败");  
        } catch (IllegalAccessException e) {  
            System.out.println("实例化JavaBean失败");  
        } catch (IllegalArgumentException e) {  
            System.out.println("映射错误");
        } catch (InvocationTargetException e) {  
            System.out.println("调用属性的setter方法失败");  
        }  
        return returnMap;  
    }
}
