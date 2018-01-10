package com.ruiec.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.web.dao.ArticleMapper;
import com.ruiec.web.dao.ArticleReplyMapper;
import com.ruiec.web.dto.ArticleReplyDTO;
import com.ruiec.web.model.Article;
import com.ruiec.web.model.ArticleReply;
import com.ruiec.web.service.ArticleReplyService;
import com.ruiec.web.service.ConfigService;
import com.ruiec.web.service.UserPointsDetailService;
import com.ruiec.web.service.UserPointsService;


/**
 * 帖子回复实现类
 * @author Jerry<br>
 * @date 2017年10月27日 下午2:07:55
 */		  
@Service("articleReplyServiceImpl")
public class ArticleReplyServiceImpl extends BaseServiceImpl<ArticleReply> implements ArticleReplyService{
	
	private ArticleReplyMapper articleReplyMapper;
	@Resource
	private ArticleMapper articleMapper;
	@Resource
	private UserPointsService userPointsService;
	@Resource
	private UserPointsDetailService userPointsDetailService;
	@Resource
	private ConfigService configService;
	
	@Resource
	public void setArticleReplyMapper(ArticleReplyMapper articleReplyMapper){
		this.baseMapper = this.articleReplyMapper = articleReplyMapper;
	}
	
	/***
	 * 通过帖子id查询回复数量
	 * @author 王伟
	 * @Date:2017年10月29日16:51:15
	 */
	@Override
	public List<ArticleReply> selecctArticleId(Integer id) {
		return articleReplyMapper.selecctArticleId(id);
	}
	
	/***
	 * 通过id删除评论
	 * @author 王伟
	 * @date：2017年10月31日11:17:53
	 */
	@Override
	public int deleteArticle(Integer id) {
		return articleReplyMapper.deleteArticle(id);
	}
	
	/***
	 * 通过帖子查询回帖的数量
	 * @author 王伟
	 * @date：2017年11月9日16:11:42
	 */
	@Override
	public int selecctArticleIdCount(Integer id) {
		return articleReplyMapper.selecctArticleIdCount(id);
	}
	
	/**
	 * 条件连表查询(回复人名称，发帖时间查询)
	 * @param id
	 * @return
	 * @author 王伟
	 * @date:2017年11月15日16:10:02
	 */
	@Override
	public List<ArticleReply> selecctArticReplyUserName(String username, String createTime,Integer replyArticleId) {
		return articleReplyMapper.selecctArticReplyUserName(username,createTime,replyArticleId);
	}
	/**
	 * 查询子类是否存在
	 * @author 王伟
	 * @date:2017年11月15日16:10:02
	 */
	@Override
	public List<ArticleReply> selectArticleidArticleReply(Integer id) {
		return articleReplyMapper.selectArticleidArticleReply(id);
	}
	
	/**
	 * 通过帖子id，回复id得到是否有下级
	 * @author 王伟
	 * @date:2017年11月16日11:26:07
	 */
	@Override
	public Integer selectArticleIdArticleReplyId(Integer id) {
		return articleReplyMapper.selectArticleIdArticleReplyId(id);
	}

	/**
	 * 根据条件搜索回复贴
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月17日 上午11:14:59
	 */
	public List<ArticleReplyDTO> selectByArticleReplyAll(ArticleReplyDTO articleReplyDTO) {
		return articleReplyMapper.selectByArticleReplyAll(articleReplyDTO);
	}

	/**
	 * 保存回复
	 * 
	 * @author bingo<br>
	 * @date 2017年12月1日 下午4:28:32
	 */
	@Override
	@Transactional
	public int insertSelective(ArticleReply articleReply) {
		// *********************************帖子被回复帖主加积分***********************************
		Article article = articleMapper.selectByPrimaryKey(articleReply.getArticleId());
		// 自己回复自己不得积分
		if (!articleReply.getUserId().equals(article.getUserId())) {
			Integer articleUserId = article.getUserId();
			float pointsMax = Float.valueOf(configService.selectByKey("points_pre_user_pre_day_max").getValue());
			float pointsPostReplied = Float.valueOf(configService.selectByKey("points_post_replied").getValue());
			// 每帖积分上限
			float pointsPreArticleMax = article.getPoints_max();
			float articlePoints = article.getPoints();
			float exactPoints = isPointsMax(pointsPreArticleMax, articlePoints, pointsPostReplied);
			float articleUserPointsToday = userPointsDetailService.countUserPointsToday(articleUserId);
			if (exactPoints == pointsPostReplied) {
				// 用户帖子被回复加积分
				// 每日积分上限
				exactPoints = userPointsService.changePointsLimitMax(articleUserId, "帖子被回复获得积分", exactPoints, pointsMax,
						articleUserPointsToday);
			} else if (exactPoints > 0 && exactPoints < pointsPostReplied) {
				// 用户帖子被回复加积分(帖子积分上限)
				// 每日积分上限
				exactPoints = userPointsService.changePointsLimitMax(articleUserId, "帖子被回复获得积分(帖子积分上限)", exactPoints, pointsMax,
						articleUserPointsToday);
			}
			// 更新帖子积分字段
			article.setPoints(article.getPoints() + exactPoints);
			articleMapper.updateByPrimaryKeySelective(article);

			// ************************************回复帖子加积分***********************************
			// 每日回复积分次数
			int pointsReplyTimes = Integer.valueOf(configService.selectByKey("points_reply_times").getValue());
			int userReplyTimes = articleReplyMapper.countByArticleReplyDTO(new ArticleReplyDTO(articleReply.getUserId(), new Date(), null));
			if (pointsReplyTimes > userReplyTimes) {
				float pointsReply = Float.valueOf(configService.selectByKey("points_reply").getValue());
				float replyUserPointsToday = userPointsDetailService.countUserPointsToday(articleReply.getUserId());
				// 回复加积分
				// 每日积分上限
				userPointsService.changePointsLimitMax(articleReply.getUserId(), "回复帖子获得积分", pointsReply, pointsMax, replyUserPointsToday);
			}
		}
		return super.insertSelective(articleReply);
	}
	
	/**
	 * 是否积分上限
	 * 
	 * @return 末达上限返回正常分值；临界上限返回上限值减当前分值；超过上限返回0.00
	 * @author bingo<br>
	 * @date 2017年12月5日 下午5:09:17
	 */
	public Float isPointsMax(Float max, Float current, Float points) {
		if (max >= current + points) {
			return points;
		} else if (max > current && max < current + points) {
			return max - current;
		}
		return 0f;
	}
	
}
