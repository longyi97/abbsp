package com.ruiec.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruiec.web.model.ArticleCategory;

/**
 * 主题数据访问接口
 * 
 * @date 2017年10月25日 下午8:14:35
 */
public interface ArticleCategoryMapper extends BaseMapper<ArticleCategory> {

	/**
	 * 根据版块ID获取主题数量
	 * 
	 * @date 2017年11月6日 下午2:28:14
	 */
	Integer countByModuleId(@Param(value = "moduleId") Integer moduleId);
	
	/**
	 * 通过板块id获取到下面主题
	 * @date 2017年11月30日 下午12:28:14
	 */
	public List<ArticleCategory> selectmoduleId(@Param(value = "moduleId") Integer moduleId);
	/**
	 * 通过id获取到主题名称
	 * @date：2017年12月1日11:15:26
	 */
	ArticleCategory selecctCategoryId(Integer articleCategoryId2);
}