package com.ruiec.web.service;

import java.util.List;

import com.ruiec.web.model.ArticleCategory;


/**
 * 主题服务接口
 * @date 2017年10月25日 下午8:16:44
 */
public interface ArticleCategoryService extends BaseService<ArticleCategory>{
	
	/**
	 * 根据版块ID获取主题数量
	 * 
	 * @date 2017年11月6日 下午2:28:14
	 */
	Integer countByModuleId(Integer moduleId);
	
	/**
	 * 通过板块id获取到主题
	 * @date 2017年11月30日 下午12:28:14
	 */
	public List<ArticleCategory> selectmoduleId(Integer moduleId);
	
	/**
	 * 通过id获取到主题名称
	 * @date：2017年12月1日11:15:26
	 */
	public ArticleCategory selecctCategoryId(Integer articleCategoryId);
}
