
package com.ruiec.web.service;

import java.util.List;

/**
 * 基础服务接口实现类
 * Version 1.0<br>
 * Date: 2017年01月05日
 */
public interface BaseService<T> {
	
	/**
	 * 通过主键删除,类型参数主要用于记日志获取类型信息
	 * Date: 2017年01月05日
	 */
	public int deleteByPrimaryKey(Integer id, Class<T> t );
	
	/**
	 * 通过主键批量删除,类型参数主要用于记日志获取类型信息
	 * Date: 2017年01月05日
	 */
	public int deleteByPrimaryKeys(Integer[] ids, Class<T> t);
	
	/**
	 * 插入(全部字段)
	 * Date: 2017年01月05日
	 */
	public int insert(T record);

	/**
	 * 插入(指定字段)
	 * Date: 2017年01月05日
	 */
	public int insertSelective(T record);

	/**
	 * 通过主键查询
	 * Date: 2017年01月05日
	 */
	public T selectByPrimaryKey(Integer id);

	/**
	 * 通过主键更新(指定字段)
	 * Date: 2017年01月05日
	 */
	public int updateByPrimaryKeySelective(T record);

	/**
	 * 通过主键更新(全部字段)
	 * Date: 2017年01月05日
	 */
	public int updateByPrimaryKey(T record);
	
	/**
	 * 查询全部
	 * Date: 2017年01月05日
	 */
	public List<T> selectAll();
	
	/**
	 * 分页查询
	 * Date: 2017年01月05日
	 */
	public List<T> selectByPage(T record);
}
