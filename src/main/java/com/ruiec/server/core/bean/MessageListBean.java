package com.ruiec.server.core.bean;

import java.io.Serializable;

/**
 * 消息列表bean
 * Date：2017年08月02日
 */
public class MessageListBean implements Serializable {

	private static final long serialVersionUID = -1379987224717512793L;
	
	/**消息编号*/
	private String id;
	
	/**消息标题*/
    private String title;
    
    /**消息内容*/
    private String content;

    /**消息类型枚举*/
    private String messageType;

    /**是否已阅读*/
    private Boolean isRead;

    /**推送日期*/
    private String pushDate;

    /**消息接收人的用户名*/
    private String username;

	public MessageListBean(String id, String content, String messageType,
			Boolean isRead, String pushDate, String username) {
		super();
		this.id = id;
		this.content = content;
		this.messageType = messageType;
		this.isRead = isRead;
		this.pushDate = pushDate;
		this.username = username;
	}

	public MessageListBean() {
		super();
	}
	
	/**消息编号*/
	public String getId() {
		return id;
	}
	
	/**消息编号*/
	public void setId(String id) {
		this.id = id;
	}

	 /**消息标题*/
    public String getTitle() {
        return title;
    }

    /**消息标题*/
    public void setTitle(String title) {
        this.title = title;
    }
	
	/**消息内容*/
	public String getContent() {
		return content;
	}

	/**消息内容*/
	public void setContent(String content) {
		this.content = content;
	}

    /**消息类型枚举*/
	public String getMessageType() {
		return messageType;
	}

    /**消息类型枚举*/
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

    /**是否已阅读*/
	public Boolean getIsRead() {
		return isRead;
	}

    /**是否已阅读*/
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

    /**推送日期*/
	public String getPushDate() {
		return pushDate;
	}

    /**推送日期*/
	public void setPushDate(String pushDate) {
		this.pushDate = pushDate;
	}

    /**消息接收人的用户名*/
	public String getUsername() {
		return username;
	}

    /**消息接收人的用户名*/
	public void setUsername(String username) {
		this.username = username;
	}

}
