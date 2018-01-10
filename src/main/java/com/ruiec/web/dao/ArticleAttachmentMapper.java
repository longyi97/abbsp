package com.ruiec.web.dao;

import java.util.List;

import com.ruiec.web.model.ArticleAttachment;

/**
 * 附件数据访问接口
 * @date 2017年10月30日 下午5:01:30
 */
public interface ArticleAttachmentMapper extends BaseMapper<ArticleAttachment>{
	
	/**
	 * 查询全部帖子附件
	 * 
	 * @date 2017年12月8日 下午7:58:54
	 */
	List<ArticleAttachment> selectByArticleAttachment(ArticleAttachment articleAttachment);
} 