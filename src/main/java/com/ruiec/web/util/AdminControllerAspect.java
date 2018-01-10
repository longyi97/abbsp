/*
 * 版权所有：深圳源中瑞科技有限公司<br>

 */
package com.ruiec.web.util;

import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.CustomValidatorBean;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.ruiec.web.model.BaseModel;
import com.ruiec.web.service.BaseService;

/**
 * 后台控制器切面类(主要用于添加实体时统一设置添加时间、修改时间、id和实体校验、记录日志等功能)
 * @author 杨龙香<br>
 * Version 1.0<br>
 * Date: 2017年01月05日
 */
@Aspect
public class AdminControllerAspect {
	
	protected static final String ERROR = "/admin/common/error";
	protected static final String LIST = "redirect:list.shtml";
	
	@Resource
	private Validator validator;
	
	/**
	 * 保存实体切点(切点为BaseService的insert方法，以下的切点可以类比)
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	@Pointcut("execution(public * com.ruiec.web.dao.*.insert*(..))")
	public void saveEntityPointCut() {
	}
	
	/**
	 * 保存实体过程中调用
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	@Around("saveEntityPointCut() && args(model,..)")
	@Transactional
	public Object saveEntity(ProceedingJoinPoint pjp, BaseModel model) throws Throwable{
		Date now = new Date();
		model.setCreateTime(now);
		model.setModifyTime(now);
//		model.setId(PrimaryKeyGenerateUtils.generatePrimaryKey().toString());
		if(!validate(model)){
			return ERROR;
		}
		Object[] os = pjp.getArgs();
//		Class<? extends BaseModel> c = model.getClass();
		//触发该方法的不是操作日志才保存日志
//		if(!"OperationLog".equals(c.getSimpleName())){
//			String desc="表"+c.getSimpleName()+"添加了一条新的记录";
//			OperationLog log=new OperationLog(TargetType.valueOf(changeTheFirstCharToLowerCase(c.getSimpleName())), model.getId(), 
//					OperateType.add, desc, /*(String) SecurityUtils.getSubject().getPrincipal()*/"", "", model.toString());
//			LogUtil.saveLog(log);
//		}
		return pjp.proceed(os);
	}
	
	/**
	 * 修改实体切点
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	@Pointcut("execution(public * com.ruiec.web.dao.*.update*(..))")
	public void updateEntityPointCut() {
	}
	
	/**
	 * 修改实体过程中调用
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	@Around("updateEntityPointCut() && args(model,..)")
	@Transactional
	public Object updateEntity(ProceedingJoinPoint pjp, BaseModel model) throws Throwable{
		/*if(!validate(model)){
			return ERROR;
		}*/
		Class<? extends BaseModel> c = model.getClass();
		model.setModifyTime(new Date());
		//获取更新前的实体
		String simpleName = c.getSimpleName();
		if(simpleName.endsWith("WithBLOBs")){
			simpleName = simpleName.replace("WithBLOBs", "");
		}
		String serviceName = changeTheFirstCharToLowerCase(/*c.getSimpleName()*/simpleName)+"ServiceImpl";
		BaseService<BaseModel> baseService = (BaseService<BaseModel>)com.ruiec.server.autocreatetable.util.SpringUtils.getBean(serviceName);
		BaseModel original = baseService.selectByPrimaryKey(model.getId());
		Object result = pjp.proceed(pjp.getArgs());
		//记录日志
//		String desc="表"+c.getSimpleName()+"修改了一条新的记录";
//		OperationLog log=new OperationLog(TargetType.valueOf(changeTheFirstCharToLowerCase(c.getSimpleName())), model.getId(), 
//				OperateType.update, desc, /*(String) SecurityUtils.getSubject().getPrincipal()*/"", original.toString(), model.toString());
//		LogUtil.saveLog(log);
		return result;
	}
	
	/**
	 * 删除实体切点
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	@Pointcut("execution(public * com.ruiec.web.dao.*.delete*(..))")
	public void deleteEntityPointCut() {
	}	
	
	/**
	 * 修改实体过程中调用
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	@Around("deleteEntityPointCut() && args(ids,c,..)")
	@Transactional
	public Object deleteEntity(ProceedingJoinPoint pjp, String[] ids, Class<? extends BaseModel> c) throws Throwable{
		//获取删除的实体
		Object result = pjp.proceed(pjp.getArgs());
		//记录日志
//		String desc="表"+c.getSimpleName()+"删除了"+ids.length+"条新的记录";
//		StringBuilder sbd = new StringBuilder();
//		for(String id : ids){
//			sbd.append(id).append(",");
//		}
//		OperationLog log=new OperationLog(TargetType.valueOf(changeTheFirstCharToLowerCase(c.getSimpleName())), sbd.toString(), 
//				OperateType.delete, desc, /*(String) SecurityUtils.getSubject().getPrincipal()*/"", "", "");
//		LogUtil.saveLog(log);
		return result;
	}
	
	/**
	 * 校验实体
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	private boolean validate(Object entity){
		Set<ConstraintViolation<Object>> localSet = validator.validate(entity);
		if(localSet.isEmpty()){
			return true;
		}
		RequestAttributes localRequestAttributes = RequestContextHolder.currentRequestAttributes();
	    localRequestAttributes.setAttribute("constraintViolations", localSet, RequestAttributes.SCOPE_REQUEST);
		return false;
		
	}
	
	/**
	 * 使字符串的首个大写字母变为小写字母(此处不做异常判断，其它地方不能随便调用)
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	private String changeTheFirstCharToLowerCase(String str){
		char[] c = new char[str.length()];
		for(int i=0;i<str.length();i++){
			if(i==0){
				c[i] = (char) (str.charAt(i)+32);
				continue;
			}
			c[i] = str.charAt(i);
		}
		return new String(c);
	}
	
	/**
	 * 手动注入校验bean,不注入会报错(因为aop是什么在applicationContext而不是在spring mvc下，所以需要在applicationContext中注入合适的校验器)
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	@Bean
	public Validator getValidator(){
		return new CustomValidatorBean();
	}
}
