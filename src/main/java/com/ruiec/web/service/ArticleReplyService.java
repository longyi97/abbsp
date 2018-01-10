package com.ruiec.web.service;

import java.util.List;

import com.ruiec.web.dto.ArticleReplyDTO;
import com.ruiec.web.model.ArticleReply;

/**
 * 帖子回复服务接口
 * 
 * @date 2017年10月27日 下午2:04:43
 */
public interface ArticleReplyService extends BaseService<ArticleReply> {

	/***
	 * 通过帖子查询出回复
	 * 
	 * @date：2017年10月29日16:49:45
	 */
	List<ArticleReply> selecctArticleId(Integer id);

	/***
	 * 通过帖子id删除评论
	 * 
	 * @date：2017年10月31日11:15:24
	 */
	public int deleteArticle(Integer id);

	/**
	 * 通过帖子查询出回复的数量
	 * date:2017年11月9日16:10:02
	 */
	public int selecctArticleIdCount(Integer id);

	/**
	 * 条件连表查询(回复人名称，发帖时间查询)
	 * 

	 * date:2017年11月15日16:10:02
	 */
	List<ArticleReply> selecctArticReplyUserName(String username, String createTime, Integer replyArticleId);

	/**
	 * 查询子类是否存在
	 *  date:2017年11月15日16:10:02
	 */
	List<ArticleReply> selectArticleidArticleReply(Integer replyArticleId);

	/**
	 * 通过帖子id，回复id得到是否有下级
	 * 
	 * @date:2017年11月16日11:26:07
	 */
	Integer selectArticleIdArticleReplyId(Integer id);

	/**
	 * 根据条件搜索回复贴
	 * 
	 * @date 2017年11月17日 上午11:14:59
	 */
	public List<ArticleReplyDTO> selectByArticleReplyAll(ArticleReplyDTO articleReplyDTO);

}
