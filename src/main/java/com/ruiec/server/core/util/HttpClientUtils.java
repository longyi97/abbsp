package com.ruiec.server.core.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils{

	/**
	 * 模拟请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doGet(String url, Map<String, String> params) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//设置get请求超时链接
		RequestConfig requesuConfig = RequestConfig.custom().setSocketTimeout(200).setConnectTimeout(200).build();
		if(!url.contains("?")){
			url = url + "?";
		}
		if (params != null && params.size()>0){
			for (Map.Entry<String, String> entry : params.entrySet()) {
				String name = entry.getKey();
				String value = entry.getValue();
				if (name != null && value != null) {
					url = url + name + "=" + value + "&";
				}
			}
			url = url.substring(0, url.length() - 1); // 去除最末尾的&
		}

		

		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requesuConfig);
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpGet);
			/*
			 * if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			 * return true; }
			 */
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity);
			return content;
		} catch (ClientProtocolException e) {
//			throw new RuntimeException(e);
			return null;
		} catch (IOException e) {
			return null;
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// return null;
	}
	
	/**
	 * 模拟请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static boolean doGetRequest(String url, Map<String, String> params){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		url = url + "?";
		if(params != null)
		for(Map.Entry<String, String> entry : params.entrySet()){
			String name = entry.getKey();
			String value = entry.getValue();
			if(name != null&& value != null){
				url = url + name + "=" + value + "&";
			}
		}
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpGet);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				return true;
			}
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally{
			if(response != null){
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(httpclient != null){
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	/**
	 * 发送post请求
	 * 
	 * @param strURL
	 * @param req
	 * @return
	 */
	public static String doPostQueryCmd(String strURL, Map<String, String> req)
	{
		CloseableHttpResponse response = null;
		try
		{
			CloseableHttpClient httpClient = HttpClients.createDefault();
			  HttpPost post = new HttpPost(strURL);
			  RequestConfig config = RequestConfig.custom()
			    .setConnectionRequestTimeout(10000).setConnectTimeout(10000)
			    .setSocketTimeout(10000).build();
			  
			   List formparams = new ArrayList();
			   
			   for (Map.Entry<String, String> entry : req.entrySet()){
					formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			   post.setEntity(new UrlEncodedFormEntity(formparams, "UTF-8"));
			   post.setConfig(config);
			   
			   if(("".equals(req.get("sessionId"))||null==req.get("sessionId")))
				   System.out.println("sessionId为空");
			   else			   
			   post.setHeader("Cookie", "JSESSIONID=" + req.get("sessionId"));
			   
			   response = httpClient.execute(post);
			   HttpEntity entity = response.getEntity();
			   String content = EntityUtils.toString(entity);
			   EntityUtils.consume(entity);
			   
			   if (response.getLastHeader("Set-Cookie") != null && !"".equals(response.getLastHeader("Set-Cookie"))) {
				   System.out.println("Set-Cookie: " + response.getLastHeader("Set-Cookie").getValue());
			   }
			
			return content;
			
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try {
			    response.close();
			   } catch (IOException e) {
			    e.printStackTrace();
			   }
		}
			
			return null;
			
	}
	
	/**
	 * 发送post请求
	 * @author 刘立雯
	 * Date：2016年09月19日
	 */
	public static String doPostQueryCmd(String url, String xml){
		 //关闭  
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");     
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");     
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "stdout");    
          
        //创建httpclient工具对象  
        HttpClient client = new HttpClient();    
        //创建post请求方法  
        PostMethod myPost = new PostMethod(url);    
        /*//设置请求超时时间  
        client.setConnectionTimeout(300*1000);  */
        String responseString = null;    
        byte[] strByte=null;
        try{    
            //设置请求头部类型  
            myPost.setRequestHeader("Content-Type","text/xml");  
            myPost.setRequestHeader("charset","utf-8");  
            myPost.setRequestEntity(new StringRequestEntity(xml, "text/xml", "utf-8"));;  
            int statusCode = client.executeMethod(myPost);    
            if(statusCode == HttpStatus.SC_OK){    
                BufferedInputStream bis = new BufferedInputStream(myPost.getResponseBodyAsStream());    
                byte[] bytes = new byte[1024];    
                ByteArrayOutputStream bos = new ByteArrayOutputStream();    
                int count = 0;    
                while((count = bis.read(bytes))!= -1){    
                    bos.write(bytes, 0, count);    
                }    
                strByte = bos.toByteArray();    
                responseString = new String(strByte,0,strByte.length,"utf-8");    
                bos.close();    
                bis.close();    
            }    
        }catch (Exception e) {    
            e.printStackTrace();    
        }    
        myPost.releaseConnection();    
        return responseString;     
	}
	
	/**
	 * 拼接链接和参数
	 * @param baseUrl
	 * @param params
	 * @return
	 */
	public static String joinUrlAndParams(String baseUrl, Map<String, String> params){
		if(params.size()>0){
			int flag = 1;
			StringBuffer sb = new StringBuffer(baseUrl + "?");
			for(Map.Entry<String, String> entry : params.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				sb.append(key).append("=").append(value);
				if(flag < params.size()){
					sb.append("&");
				}
				flag ++;
			}
			return sb.toString();
		}
		return baseUrl;
	}
	
	/**
	 * 屏蔽异常
	 * @param client
	 * @param httpGet
	 * @return
	 */
	public static CloseableHttpResponse getResponse(CloseableHttpClient client, HttpGet httpGet){
		CloseableHttpResponse response = null;
		try {
			response = client.execute(httpGet);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return response;
	}
	
	/**
	 * 屏蔽异常
	 * @param client
	 * @param httpRequest
	 * @return
	 */
	public static CloseableHttpResponse getResponse(CloseableHttpClient client, HttpUriRequest httpRequest){
		CloseableHttpResponse response = null;
		try {
			response = client.execute(httpRequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return response;
	}
	
	/**
	 * 获取响应流
	 * @param response
	 * @return
	 */
	public static InputStream getInputStream(CloseableHttpResponse response){
		InputStream localInputStream = null;
		try {
			localInputStream = response.getEntity().getContent();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return localInputStream;
	}
	/**
	 * 状态码检查
	 * @param response
	 * @param statusCode
	 * @return
	 */
	public static boolean validStatus(CloseableHttpResponse response, int statusCode){
		return response.getStatusLine().getStatusCode() == statusCode;
	}
	/**
	 * 获取响应体并屏蔽异常
	 * @param response
	 * @return
	 */
	public static String getContent(CloseableHttpResponse response){
		HttpEntity entity = response.getEntity();
		String content = null;
		try {
			content = EntityUtils.toString(entity);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return content;
	}
	/**
	 * 获取指定头的值
	 * @param response
	 * @param headKey
	 * @return
	 */
	public static String getHead(CloseableHttpResponse response, String headKey){
		Header[] headers = response.getHeaders(headKey);
		if(headers.length<1)
			return null;
		Header headerCaptcha = headers[0];	
		String headValue = headerCaptcha.getValue();
		return headValue;
	}
	
	/**
	 * 获取UrlEncodedFormEntity对象并屏蔽异常
	 * @param params 参数对集合
	 * @return
	 */
	public static UrlEncodedFormEntity getFormEntity(List<NameValuePair> params){
		try {
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params);
			return formEntity;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}
