package com.ruiec.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruiec.web.dao.ArticleAttachmentMapper;
import com.ruiec.web.model.ArticleAttachment;
import com.ruiec.web.service.ArticleAttachmentService;

/**
 * 附件服务实现类
 * @author Jerry<br>
 * @date 2017年10月30日 下午4:14:55
 */
@Service("articleAttachmentServiceImpl")
public class ArticleAttachmentServiceImpl extends BaseServiceImpl<ArticleAttachment> implements ArticleAttachmentService{

	/** 附件DAO */
	private ArticleAttachmentMapper articleAttachmentMapper;
	
	/** 注入附件DAO */
	@Resource
	public void setArticleAttachmentMapper(ArticleAttachmentMapper articleAttachmentMapper){
		this.baseMapper = this.articleAttachmentMapper = articleAttachmentMapper;
	}
	
	/**
	 * 查询全部帖子附件
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年12月8日 下午7:58:54
	 */
	public List<ArticleAttachment> selectByArticleAttachment(ArticleAttachment articleAttachment){
		return articleAttachmentMapper.selectByArticleAttachment(articleAttachment);
	}
}
