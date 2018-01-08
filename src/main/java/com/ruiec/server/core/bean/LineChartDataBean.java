package com.ruiec.server.core.bean;

import java.io.Serializable;

/**
 * 折线图数据
 * @author 刘立雯
 * Date：2017年07月14日
 */
public class LineChartDataBean implements Serializable {

	private static final long serialVersionUID = -9066358711894016446L;
	/** 下单数量 */
	private int orderCount=0;
	/** 完成数量 */
	private int carryOutCount=0;
	
	/** 下单数量 */
	public int getOrderCount() {
		return orderCount;
	}
	/** 下单数量 */
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	/** 完成数量 */
	public int getCarryOutCount() {
		return carryOutCount;
	}
	/** 完成数量 */
	public void setCarryOutCount(int carryOutCount) {
		this.carryOutCount = carryOutCount;
	}
	
}
