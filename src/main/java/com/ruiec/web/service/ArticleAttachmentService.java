/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.service;

import java.util.List;

import com.ruiec.web.model.ArticleAttachment;

/**
 * 附件服务接口
 * 
 * @author Jerry<br>
 * @date 2017年10月30日 下午4:11:46
 */
public interface ArticleAttachmentService extends BaseService<ArticleAttachment> {
	
	/**
	 * 查询全部帖子附件
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年12月8日 下午7:58:54
	 */
	List<ArticleAttachment> selectByArticleAttachment(ArticleAttachment articleAttachment);

}