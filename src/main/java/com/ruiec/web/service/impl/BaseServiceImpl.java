/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ruiec.web.dao.BaseMapper;
import com.ruiec.web.service.BaseService;

/**
 * 基础服务接口实现类
 * @author 杨龙香<br>
 * Version 1.0<br>
 * Date: 2017年01月05日
 */
public class BaseServiceImpl<T> implements BaseService<T>{

	protected BaseMapper<T> baseMapper;

	/**
	 * 通过主键删除,类型参数主要用于记日志获取类型信息
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	public int deleteByPrimaryKey(Integer id, Class<T> t ){
		return baseMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 通过主键批量删除
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	@Override
	@Transactional
	public int deleteByPrimaryKeys(Integer[] ids, Class<T> t) {
		return baseMapper.deleteByPrimaryKeys(ids);
	}
	
	/**
	 * 插入(全部字段)
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	@Override
	@Transactional
	public int insert(T t) {
		return baseMapper.insert(t);
	}
	
	/**
	 * 插入(指定字段)
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	@Override
	@Transactional
	public int insertSelective(T t) {
		return baseMapper.insertSelective(t);
	}

	/**
	 * 通过主键查询
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	@Override
	@Transactional
	public T selectByPrimaryKey(Integer id) {
		return baseMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过主键更新(指定字段)
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	@Override
	@Transactional
	public int updateByPrimaryKeySelective(T t) {
		return baseMapper.updateByPrimaryKeySelective(t);
	}

	/**
	 * 通过主键更新(全部字段)
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	@Override
	@Transactional
	public int updateByPrimaryKey(T t) {
		return baseMapper.updateByPrimaryKey(t);
	}
	
	/**
	 * 查询全部
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	@Override
	@Transactional
	public List<T> selectAll() {
		return baseMapper.selectAll();
	}
	
	/**
	 * 分页查询
	 * @author 杨龙香<br>
	 * Date: 2017年01月05日
	 */
	@Override
	@Transactional
	public List<T> selectByPage(T t) {
		return baseMapper.selectByPage(t);
	}

}
