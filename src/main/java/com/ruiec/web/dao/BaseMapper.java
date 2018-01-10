
package com.ruiec.web.dao;

import java.util.List;

/**
 * 数据访问基础服务接口
 * Version 1.0<br>
 * Date: 2017年01月05日
 */
public interface BaseMapper<T> {
	/**
	 * 通过主键删除
	 * Date: 2017年01月05日
	 */
	public int deleteByPrimaryKey(Integer id);
	
	/**
	 * 通过主键批量删除
	 * Date: 2017年01月05日
	 */
	public int deleteByPrimaryKeys(Integer[] ids);

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
