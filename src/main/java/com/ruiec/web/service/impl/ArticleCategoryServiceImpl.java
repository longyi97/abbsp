/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruiec.web.dao.ArticleCategoryMapper;
import com.ruiec.web.model.ArticleCategory;
import com.ruiec.web.service.ArticleCategoryService;

/**
 * 主题服务实现类
 * @author Jerry<br>
 * @date 2017年10月25日 下午8:20:37
 */
@Service("articleCategoryServiceImpl")
public class ArticleCategoryServiceImpl extends BaseServiceImpl<ArticleCategory> implements ArticleCategoryService{

	private ArticleCategoryMapper articleCategoryMapper;
	
	@Resource
	public void setArticleCategoryMapper(ArticleCategoryMapper articleCategoryMapper){
		this.baseMapper = this.articleCategoryMapper = articleCategoryMapper;
		
	}

	/**
	 * 根据版块ID获取主题数量
	 * 
	 * @author bingo<br>
	 * @date 2017年11月6日 下午2:28:14
	 */
	@Override
	public Integer countByModuleId(Integer moduleId) {
		return articleCategoryMapper.countByModuleId(moduleId);
	}
	
	/**
	 * 通过板块id获取到主题
	 * @author 王伟
	 * @date 2017年11月30日 下午12:28:14
	 */
	@Override
	public List<ArticleCategory> selectmoduleId(Integer moduleId) {
		return articleCategoryMapper.selectmoduleId(moduleId);
	}
	/**
	 * 通过id获取到主题名城
	 * @author 王伟
	 * @date：2017年12月1日11:15:26
	 */
	@Override
	public ArticleCategory selecctCategoryId(Integer articleCategoryId) {
		return articleCategoryMapper.selecctCategoryId(articleCategoryId);
	}
}
