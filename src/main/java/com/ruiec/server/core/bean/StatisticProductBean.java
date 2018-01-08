package com.ruiec.server.core.bean;

import java.io.Serializable;

/**
 * 产品统计bean
 * @author 刘立雯
 * Date：2017年08月03日
 */
public class StatisticProductBean implements Serializable {

	private static final long serialVersionUID = -3768825557070076019L;

	/** 产品总量 */
	private int productCount;
	
	/** 正常产品数量 */
	private int normalProductCount;
	
	/** 完成产品数量 */
	private int completeTheProductCount;

	/** 延期项目数量 */
	private int extensionOfTheProductCount;
	
	/** 暂停项目数量 */
	private int suspendProductCount;

	/** 产品总量 */
	public int getProductCount() {
		return productCount;
	}

	/** 产品总量 */
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	/** 正常产品数量 */
	public int getNormalProductCount() {
		return normalProductCount;
	}
	
	/** 正常产品数量 */
	public void setNormalProductCount(int normalProductCount) {
		this.normalProductCount = normalProductCount;
	}

	/** 完成产品数量 */
	public int getCompleteTheProductCount() {
		return completeTheProductCount;
	}

	/** 完成产品数量 */
	public void setCompleteTheProductCount(int completeTheProductCount) {
		this.completeTheProductCount = completeTheProductCount;
	}

	/** 延期项目数量 */
	public int getExtensionOfTheProductCount() {
		return extensionOfTheProductCount;
	}

	/** 延期项目数量 */
	public void setExtensionOfTheProductCount(int extensionOfTheProductCount) {
		this.extensionOfTheProductCount = extensionOfTheProductCount;
	}

	/** 暂停项目数量 */
	public int getSuspendProductCount() {
		return suspendProductCount;
	}

	/** 暂停项目数量 */
	public void setSuspendProductCount(int suspendProductCount) {
		this.suspendProductCount = suspendProductCount;
	}

	@Override
	public String toString() {
		return "StatisticProductBean [productCount=" + productCount
				+ ", normalProductCount=" + normalProductCount
				+ ", completeTheProductCount=" + completeTheProductCount
				+ ", extensionOfTheProductCount=" + extensionOfTheProductCount
				+ ", suspendProductCount=" + suspendProductCount + "]";
	}
	
}
