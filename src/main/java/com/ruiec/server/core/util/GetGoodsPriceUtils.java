/**
 */
package com.ruiec.server.core.util;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * 获取商品价格行情工具类<br>
 * Version: 1.0<br>
 * Date: 2016-12-24
 */
public class GetGoodsPriceUtils {
	private PoolingHttpClientConnectionManager cm;
	private CloseableHttpClient httpclient;
	private RequestConfig requesuConfig;
	{
		//设置最多链接参数
		cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(100);
		httpclient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
		//设置get请求超时链接
		requesuConfig = RequestConfig.custom().setSocketTimeout(150).setConnectTimeout(150).build();
	}
	
	/**
	 * 根据url和参数获取商品价格
	 * Version: 1.0<br>
	 * Date: 2016-12-24
	 */
	public String doGet(String url, Map<String, String> params) {
//		System.out.println(System.currentTimeMillis());
		//url参数配置
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
		
		HttpContext context = new BasicHttpContext();

		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requesuConfig);
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpGet, context);
			/*
			 * if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			 * return true; }
			 */
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity);
//			System.out.println(System.currentTimeMillis());
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
			
		}
		// return null;
	}
	
	/**
	 * bean销毁时调用函数
	 * Version: 1.0<br>
	 * Date: 2016-12-24
	 */
	public void destroy(){
		if (httpclient != null) {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
