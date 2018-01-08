package com.ruiec.web.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.ruiec.web.dao.ArticleAttachmentMapper;
import com.ruiec.web.dao.ArticleMapper;
import com.ruiec.web.dao.ArticleReplyMapper;
import com.ruiec.web.dto.ArticelSelectDTO;
import com.ruiec.web.dto.ArticleDTO;
import com.ruiec.web.model.Article;
import com.ruiec.web.model.ArticleAttachment;
import com.ruiec.web.service.ArticleService;
import com.ruiec.web.service.ConfigService;
import com.ruiec.web.service.ModuleService;
import com.ruiec.web.service.UserPointsDetailService;
import com.ruiec.web.service.UserPointsService;

/**
 * 帖子接口实现
 * 
 * @author 王伟<br>
 * @date 2017年12月18日 下午2:59:20
 */
@Service("articleServiceImpl")
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService {

	private ArticleMapper articleMapper;
	@Resource
	private ArticleReplyMapper articleReplyMapper;
	@Resource
	private ArticleAttachmentMapper articleAttachmentMapper;
	@Resource
	private ModuleService moduleService;
	@Resource
	private UserPointsService userPointsService;
	@Resource
	private UserPointsDetailService userPointsDetailService;
	@Resource
	private ConfigService configService;

	@Resource
	public void setArticleMapper(ArticleMapper articleMapper) {
		this.baseMapper = this.articleMapper = articleMapper;
	}

	/**
	 * 判断帖子数量
	 * 
	 * @author 王伟
	 * @date 2017年12月18日 下午2:59:55
	 */
	@Override
	@Transactional
	public Map<String, Long> getType() {
		return articleMapper.getType();
	}

	/**
	 * 连表查询回复
	 * 
	 * @author 王伟
	 * @date 2017年12月18日 下午2:59:55
	 */
	@Override
	public List<Article> selectleft() {
		return null;
	}

	/**
	 * 直接删除帖子
	 * 
	 * @author 王伟
	 * @date 2017年12月18日 下午2:59:55
	 */
	@Override
	@Transactional
	public int deleteArticleId(Integer id) {
		// 先删除所有的评论
		articleReplyMapper.deleteArticle(id);
		return articleMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 通过用户ID获取所有该用户的帖子
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年10月30日 下午2:57:37
	 */
	public List<Article> selectByUserAllArticle(Integer userId) {
		return articleMapper.selectByUserAllArticle(userId);
	}

	/**
	 * 通过时间段获取帖子数量（版块）
	 * 
	 * @author bingo<br>
	 * @date 2017年10月31日 下午2:31:59
	 */
	@Override
	public Integer countByDate(Timestamp queryDate, Integer moduleId) {
		List<Integer> ids = moduleService.selectSubModuleIds(moduleId);
		return articleMapper.countByDate(queryDate, moduleId, ids);
	}

	/**
	 * 用户发表的帖子总数
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月1日 下午1:46:06
	 */
	public Integer selectByUserAllArticleCount(Integer userId) {
		return articleMapper.selectByUserAllArticleCount(userId);
	}

	/**
	 * 获取版块下帖子数量
	 * 
	 * @author bingo<br>
	 * @date 2017年11月6日 上午11:21:08
	 */
	@Override
	public Integer countByModuleId(Integer moduleId) {
		List<Integer> ids = moduleService.selectSubModuleIds(moduleId);
		return articleMapper.countByModuleId(ids);
	}

	/**
	 * 获取带有图片的帖子
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月8日 下午3:24:18
	 */
	public List<Article> selectShufflingArticle(Integer moduleId) {
		return articleMapper.selectShufflingArticle(moduleId);
	}

	/**
	 * 增加点击量，
	 * 
	 * @author 王伟 date：
	 * @date 2017年12月18日 下午2:59:55
	 */
	@Override
	public int updateAddHit(Integer id) {
		return articleMapper.updateAddHit(id);
	}

	/**
	 * 通过顶级版块ID获取所有帖子
	 * 
	 * @author bingo<br>
	 * @date 2017年11月11日 上午10:28:30
	 */
	@Override
	public List<Article> selectByTopModule(Integer startPage, Integer pageSize, String orderClause, Integer moduleId) {
		List<Integer> ids = moduleService.selectSubModuleIds(moduleId);
		// 热帖（10条）
		PageHelper.startPage(startPage, pageSize, orderClause);
		return articleMapper.selectByTopModule(moduleId, ids);
	}

	/**
	 * 帖子假删除（水贴）
	 * 
	 * @author bingo<br>
	 * @date 2017年12月8日 下午4:49:04
	 */
	@Override
	@Transactional
	public Integer fakeDelete(Article article) {
		// 扣除积分
		userPointsService.changePointsBusiness(articleMapper.selectByPrimaryKey(article.getId()).getUserId(), "水贴扣除积分",
				Float.valueOf(configService.selectByKey("points_bad_article").getValue()));
		return updateByPrimaryKeySelective(article);
	}

	/**
	 * 保存帖子
	 * 
	 * @author bingo<br>
	 * @date 2017年12月1日 下午3:35:02
	 */
	@Override
	@Transactional
	public synchronized int insertSelective(Article article) {
		// 初始化帖子所获得积分最大值
		article.setPoints_max(Float.valueOf(configService.selectByKey("points_pre_article_max").getValue()));

		// 每日发帖积分次数
		ArticleDTO articleDTO = new ArticleDTO();
		articleDTO.setUserId(article.getUserId());
		articleDTO.setQueryDate(new Timestamp(new Date().getTime()));
		int pointsPostTimes = Integer.valueOf(configService.selectByKey("points_post_times").getValue());
		// for update 解决并发问题
		int userPostTimes = articleMapper.countByArticleDTO(articleDTO);
		if (pointsPostTimes > userPostTimes) {
			float pointsPost = Float.valueOf(configService.selectByKey("points_post").getValue());
			float pointsMax = Float.valueOf(configService.selectByKey("points_pre_user_pre_day_max").getValue());
			float userPointsToday = userPointsDetailService.countUserPointsToday(article.getUserId());
			// 每日积分上限
			float exactPoints = userPointsService.changePointsLimitMax(article.getUserId(), "发帖获得积分", pointsPost, pointsMax,
					userPointsToday);
			// 更新帖子积分字段
			article.setPoints(exactPoints);
		}

		return super.insertSelective(article);
	}

	/**
	 * 保存帖子
	 * 
	 * @author bingo<br>
	 * @date 2017年12月1日 下午3:35:02
	 */
	@Override
	@Transactional
	public int insertSelective(Article article, String[] attachments) {
		// 保存帖子
		int result = insertSelective(article);
		// 保存帖子附件
		for (int i = 0; i < attachments.length; i++) {
			ArticleAttachment articleAttachment = new ArticleAttachment();
			articleAttachment.setArticleId(article.getId());
			// 字符串为"url;filename"
			articleAttachment.setUrl(StringUtils.substringBefore(attachments[i], ";"));
			articleAttachment.setName(StringUtils.substringAfter(attachments[i], ";"));
			result = articleAttachmentMapper.insertSelective(articleAttachment);
		}
		return result;
	}

	/**
	 * 通过帖子id删除对应的评论,添加事务，帖子回复删除时才能删除帖子
	 * 
	 * @author Jerry<br>
	 * @modify bingo<br>
	 * @date 2017年12月18日 下午2:59:55
	 */
	@Override
	@Transactional
	public int deleteByPrimaryKeys(Integer[] ids, Class<Article> t) {
		for (int i = 0; i < ids.length; i++) {
			// 删除帖子回复
			articleReplyMapper.deleteArticleId(ids[i]);
		}
		return super.deleteByPrimaryKeys(ids, t);
	}

	/**
	 * 通过id去查询帖子名称
	 * 
	 * @author 王伟
	 * @date 2017年12月18日 下午2:59:55
	 */
	@Override
	public String selectArticleTitl(Integer id) {
		return articleMapper.selectArticleTitl(id);
	}

	/**
	 * 根据发帖人搜索
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月16日 下午10:14:37
	 */
	public List<Article> selectByArticleAllUser(ArticleDTO articleDTO) {
		return articleMapper.selectByArticleAllUser(articleDTO);
	}

	/**
	 * 分页查询(通过回复数量排序)
	 * 
	 * @author 王伟<br>
	 * @date 2017年12月18日 下午2:59:55
	 */
	@Override
	public List<Article> selectByPageType(ArticelSelectDTO articelSelectDTO) {
		return articleMapper.selectByPageType(articelSelectDTO);
	}

	/**
	 * 通过数组批量假删除
	 * 
	 * @author 王伟
	 * @date 2017年12月18日 下午2:59:55
	 */
	@Override
	public int updateByPrimary(Integer[] ids, Class<Article> t) {
		return articleMapper.updateByPrimary(ids);
	}

	/**
	 * 输入框输入查询
	 * 
	 * @author 王伟
	 * @date 2017年12月18日 下午2:59:55
	 */
	@Override
	public Article selectKey(Integer id) {
		return articleMapper.selectKey(id);
	}
	/**
	 * 用户发表的帖子火帖总数
	 * 
	 * @author 王伟<br>
	 * @date 2017年12月21日 下午1:46:06
	 */
	@Override
	public Integer selectByUserAllArticleHitCount(Integer userId) {
		return articleMapper.selectByUserAllArticleHitCount(userId);
	}
}
