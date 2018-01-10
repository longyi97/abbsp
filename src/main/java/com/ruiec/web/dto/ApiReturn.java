package com.ruiec.web.dto;
/**
 * 手机端公用返回操作结果的bean
 * Date：2016年07月18日
 */
public class ApiReturn {

	
	private Object data=null;
	
	public ApiReturn(Object data){
		this.data=data;
		
	}
	public ApiReturn(int statusCode,String returnResult, boolean returnFlag){
		
	}
	
	public ApiReturn(int statusCode,String returnResult, boolean returnFlag,Object data){
	
		this.data=data;
	}
	

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(",data=");
		builder.append(data);
		builder.append("]");
		return null;

		
	}
	
}
