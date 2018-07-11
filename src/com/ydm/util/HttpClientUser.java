package com.ydm.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import com.alibaba.fastjson.JSONException;
/**
 * 抓取页面数据和http请求预处理类及ajax中转(类似sina邮箱登陆帐号对接)
 * @author tappy
 */
public class HttpClientUser {
	/**
	 * URL符号替换操作
	 * @param url 请求地址
	 * @param fal true字符flase正常url
	 * @return
	 */
	public static String UrlHandle(String url,boolean fal){		
		//String url="+、*、|、\\http://mjbkj-comld6.sinacorp.cn/control/login.php?domain=mjbkj.com&username=s&passwd=s&vcode=&ssl=false";
		String[][] urlparm=new String[][]{
		{"/","MJB1A"},
		{":","MJB2A"},
		{"&","MJB3A"},
		{"=","MJB4A"},
		{"\\?","MJB5A"},
		{"\\*","MJB6A"},
		{"\\|","MJB7A"},
		{"\\/","MJB8A"},
		{"\\+","MJB9A"},
		{"\\!","MJB0A"}};
		//+、*、|、\等符号在正则表达示中有相应的不同意义。一般来讲只需要加[]、或是\\即可
		if(fal){//转成字符
			for(int i=0;i<urlparm.length;i++){
				url=url.replaceAll(urlparm[i][0],urlparm[i][1]);
			}
		}else{//转成正常URL
			for(int i=0;i<urlparm.length;i++){
				url=url.replaceAll(urlparm[i][1],urlparm[i][0]);
			}
		}
		return url;
	}
	/**
	 * http请求post方式
	 * @param url 请求的完整的地址
	 * @return 抓取信息
	 */
	 public static String Http_Post(String url,Integer dk){
		    String rdata="";//定义请求结果
		    HttpClient client = new HttpClient(); 		      
		    //定义代理ip
		    String urltop=url.substring(url.indexOf("//")+2,url.length());
		    urltop=urltop.substring(0,urltop.indexOf("/"));
	        client.getHostConfiguration().setProxy(urltop,dk); //设置代理服务器地址和端口   
	        HttpMethod method = new PostMethod(url);//使用POST方法
	        try {
				client.executeMethod(method);				
			    //System.out.println(method.getStatusLine());//打印服务器返回的状态			    
			    rdata=method.getResponseBodyAsString();//打印返回的信息			   
			    method.releaseConnection(); //释放连接
			} catch (HttpException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
			return rdata;
	 }
	/**
	 * http请求get方式
	 * @param url 请求的完整的地址
	 * @return 抓取信息
	 */
	 public static String Http_Get(String url,Integer dk){
		    String rdata="";//定义请求结果
		    HttpClient client = new HttpClient(); 	
		    //定义代理ip
		    String urltop=url.substring(url.indexOf("//")+2,url.length());urltop=urltop.substring(0,urltop.indexOf("/"));
	        client.getHostConfiguration().setProxy(urltop,dk); //设置代理服务器地址和端口    
	        //使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https 
	        HttpMethod method=new GetMethod(url);
	        try {
				client.executeMethod(method);				
			    //System.out.println(method.getStatusLine());//打印服务器返回的状态			   
			    rdata=method.getResponseBodyAsString(); //打印返回的信息			    
			    method.releaseConnection();//释放连接
			} catch (HttpException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	  
			return rdata;
	 }
	/**
	 * http请求
	 * @param url 完成请求地址
	 * @param fale （true为post请求，false为get请求）
	 * @return 抓取信息
	 */
	 public static String HttpClient(String url,boolean fale,Integer dk){
		    if(fale)return Http_Post(url,dk);
		    else return Http_Get(url,dk);
     }
	 /**
	  * 获取post请求参数
	  * @param rq
	  * @return
	  * @throws JSONException
	  * @throws IOException
	  */
	 public static String getPostParam(HttpServletRequest rq) throws IOException  {
			InputStream inputStream = rq.getInputStream();
			String strMessage = "";
			String strResponse = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));	
			while ((strMessage = reader.readLine()) != null) {
				strResponse+=strMessage;
			}
			reader.close();
			inputStream.close();
	        return strResponse;
    }
}
