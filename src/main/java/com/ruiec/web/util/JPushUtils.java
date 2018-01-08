package com.ruiec.web.util;
/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
/**
 * 极光推送工具接口
 * @author 贺云<br>
 * Version: 1.0<br>
 * Date: 2017年7月27日
 */
public class JPushUtils {
	
	protected static final Logger LOG = LoggerFactory.getLogger(JPushUtils.class);
	private static final String appKey ="205df204447443731725649e";//极光分配的AppKey
	private static final String masterSecret = "7f9804e64f3c48cce84a0129";//极光分配的Master Secret
	private static JPushClient client;

		/**
		 * @param msgContent 推送消息体
		 * @param personIds 推送给指定用户的ID
		 * @param msgId 消息ID
		 * @param map 需求带上的推送内容（目前暂时不使用）
		 * @author 贺云<br>
		 * Version: 1.0<br>
		 * Date: 2017年7月27日
		 */
	    public static boolean pushByIdMessages(String msgContent, String personIds,String msgId,Map<String,String> map){
	    	//推送给IOS设备
	    	PushPayload pushPayload = PushPayload.newBuilder()
	                .setPlatform(Platform.ios())
	                .setAudience(Audience.alias(personIds))
	                .setNotification(Notification.newBuilder()
	                		.setAlert(msgContent)
	                		.addPlatformNotification(IosNotification.newBuilder()
	                				.incrBadge(1)
	                				.setSound("waimai.wav")
	                				.addExtra("msgId", msgId)
	                				.build())
	                		.build())
	                .build();
	    	//推送给Android设备
	    	PushPayload pushPayload1 = PushPayload.newBuilder()
	                .setPlatform(Platform.android())
	                .setAudience(Audience.alias(personIds))
	                .setMessage(Message.newBuilder().setMsgContent(msgContent)
	                		.addExtra("msgId", msgId)
	                		.setTitle("项目管理系统").build())
	                .build();
			sendPush(pushPayload);
			sendPush(pushPayload1);
			return true;
		}
		/**
		 * @param msgContent 推送消息体
		 * @param personIds 推送给指定用户的ID
		 * @param msgId 消息ID
		 * @param map 需求带上的推送内容（目前暂时不使用）
		 * @author 贺云<br>
		 * Version: 1.0<br>
		 * Date: 2017年7月27日
		 */
	    public static boolean pushByIds(String msgContent, String personIds,String msgId,Map<String,String> map){
	    	PushPayload pushPayload=PushPayload.newBuilder()
	                .setPlatform(Platform.all())
	                .setAudience(Audience.alias(personIds))
	                .setNotification(Notification.alert(msgContent))
	                .build();
			sendPush(pushPayload);
			return true;
		}
	    
	    public static boolean pushAlls(String msgContent,String msgId,Map<String,String> map){
	    	//推送给全部用户
	    	PushPayload pushPayload = PushPayload.alertAll(msgContent);
	    	sendPush(pushPayload);
			return true;
		}
	    
	    public boolean pushByIds_all(String msgContent){
			
				PushPayload pushPayload = PushPayload.alertAll(msgContent);
				return sendPush(pushPayload);
			}

	    private static boolean sendPush(PushPayload pushPayload){
			boolean isSuccess = false;
			try {
				initClient();
				LOG.info("推送任务开始");
				long isSuccess1 = client.sendPush(pushPayload).msg_id;
				LOG.info("推送任务完成");
			} catch (APIConnectionException e) {
				LOG.error("", e);
			} catch (APIRequestException e) {
				LOG.error("", e);
			} catch (Exception e){
				LOG.error("", e);
			}
			return isSuccess;
		}

	    private static void initClient(){
			if(client == null)
				client = new JPushClient(masterSecret, appKey,false, 86400);
		}

	    public static void main(String[] args) {
	    	for(int i=0;i<120;i++){
	    		JPushUtils.pushByIds("你收到了么？这是一个很牛逼的推送。", "rZwNEfaBSkirRJC5JdsG5w","1111das", null);
	    		try {
					new Thread().sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
//	    	JPushTest.pushAlls("你收到了么？", "", null);
		}
}
