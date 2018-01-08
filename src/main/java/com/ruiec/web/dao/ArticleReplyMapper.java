package com.ruiec.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruiec.web.dto.ArticleReplyDTO;
import com.ruiec.web.model.ArticleReply;

/**
 * 帖子回复数据访问接口
 * 
 * @author Jerry<br>
 * @date 2017年10月27日 下午1:59:39
 */
public interface ArticleReplyMapper extends BaseMapper<ArticleReply> {

	/***
	 * 通过帖子id查询回复数量
	 * 
	 * @author 王伟 
	 * @Date:2017年10月29日16:51:15
	 */
	public List<ArticleReply> selecctArticleId(@Param("articleId") Integer id);

	/***
	 * 通过帖子id删除所有的评论
	 * 
	 * @author 王伟
	 * @date:2017年10月31日11:19:45
	 */
	public int deleteArticle(@Param("articleId") Integer id);

	/***
	 * 通过帖子id删除对应的评论
	 * 
	 * @author Jerry
	 * @date:2017年12月2日15:12:00
	 */
	public int deleteArticleId(@Param("articleId") Integer articleId);

	/**
	 * 通过帖子id获取到回帖的数量
	 * 
	 * @author 王伟 
	 * @date:2017年11月9日15:57:53
	 */
	public int selecctArticleIdCount(@Param("articleId") Integer id);

	/**
	 * 条件连表查询(回复人名称，发帖时间查询)
	 * 
	 * @author 王伟
	 * @date:2017年11月15日16:10:02
	 */
	public List<ArticleReply> selecctArticReplyUserName(@Param("username") String username,
			@Param("createTime") String createTime, @Param("replyArticleId") Integer replyArticleId);

	/**
	 * 通过引用回复ID删除回复
	 * 
	 * @author bingo<br>
	 * @date 2017年11月15日 下午5:23:24
	 */
	public int deleteByReplyArticleId(@Param("replyArticleId") Integer replyArticleId);

	/**
	 * 根据引用回复ID获取回复
	 * 
	 * @author bingo<br>
	 * @date 2017年11月15日 下午5:44:47
	 */
	public List<ArticleReply> selectByReplyArticleId(@Param("replyArticleId") Integer replyArticleId);

	/**
	 * 查询子类是否存在
	 * 
	 * @param id
	 * @return
	 * @author 王伟 date:2017年11月15日16:10:02
	 */
	public List<ArticleReply> selectArticleidArticleReply(@Param("replyArticleId") Integer id);

	/**
	 * 通过帖子id，回复id得到是否有下级
	 * 
	 * @author 王伟
	 * @date:2017年11月16日11:26:07
	 */
	public Integer selectArticleIdArticleReplyId(@Param("replyArticleId") Integer id);

	/**
	 * 根据条件搜索回复贴
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月17日 上午11:14:59
	 */
	List<ArticleReplyDTO> selectByArticleReplyAll(ArticleReplyDTO articleReplyDTO);
	
	/**
	 * 条件查询（计数）
	 * 
	 * @author bingo<br>
	 * @date 2017年12月5日 上午11:02:36
	 */
	Integer countByArticleReplyDTO(ArticleReplyDTO articleReplyDTO);

}