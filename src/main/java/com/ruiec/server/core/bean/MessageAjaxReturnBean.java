package com.ruiec.server.core.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 消息ajax加载返回bean
 * Date：2017年08月02日
 */
public class MessageAjaxReturnBean implements Serializable {
	
	private static final long serialVersionUID = -1379987224717512793L;
	
	/** 消息集合 */
	private List<MessageListBean> messageListBeans;
	/** 索引 */
	private int msg_m;
	/** 条数 */
	private int msg_n;
	/** 是否加载结束 */
	private boolean isEnd;
	/** 消息对象 */
	private MessageListBean message;

	/** 消息集合 */
	public List<MessageListBean> getMessageListBeans() {
		return messageListBeans;
	}
	/** 消息集合 */
	public void setMessageListBeans(List<MessageListBean> messageListBeans) {
		this.messageListBeans = messageListBeans;
	}
	/** 索引 */
	public int getMsg_m() {
		return msg_m;
	}
	/** 索引 */
	public void setMsg_m(int msg_m) {
		this.msg_m = msg_m;
	}
	/** 条数 */
	public int getMsg_n() {
		return msg_n;
	}
	/** 条数 */
	public void setMsg_n(int msg_n) {
		this.msg_n = msg_n;
	}
	/** 是否加载结束 */
	public boolean getIsEnd() {
		return isEnd;
	}
	/** 是否加载结束 */
	public void setIsEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}
	/** 消息对象 */
	public MessageListBean getMessage() {
		return message;
	}
	/** 消息对象 */
	public void setMessage(MessageListBean message) {
		this.message = message;
	}
	
}
