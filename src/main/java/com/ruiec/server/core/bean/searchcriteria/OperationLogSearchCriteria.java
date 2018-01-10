
package com.ruiec.server.core.bean.searchcriteria;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志搜索条件
 * Version 1.0<br>
 * Date: 2016年12月08日
 */
public class OperationLogSearchCriteria implements Serializable {
	
	private static final long serialVersionUID = 5901833577746585017L;
	
	/** 管理员 */
	private String adminName;
	/** 描述 */
	private String description;
	/** 日志类型 */
	private String targetType;
	/** 操作类型 */
	private String operateType;
	/** 创建时间(开始) */
	private Date startDate;
	/** 创建时间(结束) */
	private Date endDate;

	/** 管理员 */
	public String getAdminName() {
		return adminName;
	}
	/** 管理员 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	/** 描述 */
	public String getDescription() {
		return description;
	}
	/** 描述 */
	public void setDescription(String description) {
		this.description = description;
	}

	/** 日志类型 */
	public String getTargetType() {
		return targetType;
	}
	/** 日志类型 */
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	/** 操作类型 */
	public String getOperateType() {
		return operateType;
	}
	/** 操作类型 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
	/** 创建时间(开始) */
	public Date getStartDate() {
		return startDate;
	}
	/** 创建时间(开始) */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/** 创建时间(结束) */
	public Date getEndDate() {
		return endDate;
	}
	/** 创建时间(结束) */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OperationLogSearchCriteria [adminName=");
		builder.append(adminName);
		builder.append(", description=");
		builder.append(description);
		builder.append(", targetType=");
		builder.append(targetType);
		builder.append(", operateType=");
		builder.append(operateType);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append("]");
		return builder.toString();
	}
	
}
