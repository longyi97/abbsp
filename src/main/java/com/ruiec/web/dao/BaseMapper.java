/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.dao;

import java.util.List;

/**
 * 数据访问基础服务接口
 * @author 杨龙香<br>
 * Version 1.0<br>
 * Date: 2017年01月05日
 */
public interface BaseMapper<T> {
	/**
	 * 通过主键删除
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	public int deleteByPrimaryKey(Integer id);
	
	/**
	 * 通过主键批量删除
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	public int deleteByPrimaryKeys(Integer[] ids);

	/**
	 * 插入(全部字段)
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	public int insert(T record);

	/**
	 * 插入(指定字段)
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	public int insertSelective(T record);

	/**
	 * 通过主键查询
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	public T selectByPrimaryKey(Integer id);

	/**
	 * 通过主键更新(指定字段)
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	public int updateByPrimaryKeySelective(T record);

	/**
	 * 通过主键更新(全部字段)
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	public int updateByPrimaryKey(T record);
	
	/**
	 * 查询全部
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	public List<T> selectAll();
	
	/**
	 * 分页查询
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	public List<T> selectByPage(T record);
}
