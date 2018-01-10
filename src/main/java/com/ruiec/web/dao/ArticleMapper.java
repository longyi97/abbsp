package com.ruiec.web.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruiec.web.dto.ArticelSelectDTO;
import com.ruiec.web.dto.ArticleDTO;
import com.ruiec.web.model.Article;

/**
 * 帖子数据访问接口
 * 
 *  date；2017年10月24日
 */
public interface ArticleMapper extends BaseMapper<Article> {

	/**
	 * 判断帖子数量
	 * 
	 * @date：2017年12月1日09:29:35
	 */
	Map<String, Long> getType();

	/**
	 * 通过用户ID获取所有该用户的帖子
	 * 
	 * @date 2017年10月30日 下午2:57:37
	 */
	List<Article> selectByUserAllArticle(Integer userId);

	/**
	 * 通过时间段获取帖子数量(版块)
	 * 
	 * @date 2017年10月31日 下午2:31:59
	 */
	Integer countByDate(@Param(value = "queryDate") Timestamp queryDate, @Param(value = "moduleId") Integer moduleId, @Param(value = "ids") List<Integer> ids);

	/**
	 * 通过版块ID获取帖子数量
	 * 
	 * @date 2017年11月6日 上午11:34:02
	 */
	Integer countByModuleId(@Param(value = "ids") List<Integer> ids);
	
	/**
	 * 通过版块ID获取帖子
	 * 
	 * @date 2017年11月11日 上午10:28:30
	 */
	List<Article> selectByModuleId(@Param(value = "moduleId") Integer moduleId);

	/**
	 * 通过顶级版块ID获取所有帖子
	 * （通过自定义sql函数实现）
	 * 
	 * @date 2017年11月11日 上午10:28:30
	 */
	List<Article> selectByTopModule(@Param(value = "moduleId") Integer moduleId, @Param(value = "ids") List<Integer> ids);
	
	/**
	 * 条件查询（计数）
	 * 
	 * @date 2017年12月2日 上午10:18:22
	 */
	Integer countByArticleDTO(ArticleDTO articleDTO);

	/**
	 * 用户发表的帖子总数
	 * 
	 * @date 2017年11月1日 下午1:46:06
	 */
	int selectByUserAllArticleCount(Integer userId);

	/**
	 * 获取带有图片的帖子
	 * 
	 * @date 2017年11月8日 下午3:24:18
	 */
	List<Article> selectShufflingArticle(@Param(value = "moduleId") Integer moduleId);

	/**
	 * 增加点击量
	 * 
	 * @date：2017年11月8日15:55:42
	 */
	public int updateAddHit(Integer id);

	/**
	 * 通过id去查询帖子名称
	 * @date:2017年11月16日11:26:07
	 */
	String selectArticleTitl(@Param(value = "id") Integer id);

	/**
	 * 根据发帖人搜索
	 * 
	 * @date 2017年11月16日 下午10:14:37
	 */
	List<Article> selectByArticleAllUser(ArticleDTO articleDTO);
	
	/**
	 * 分页查询(通过回复数量排序)
	 * @Date: 2017年01月05日
	 */
	List<Article> selectByPageType(ArticelSelectDTO articelSelectDTO);
	
	/**
	 * 通过数组批量假删除
	 * @date：2017年12月7日21:43:15
	 */
	int updateByPrimary(Integer[] ids)
	;
	/**
	 * 输入框输入查询
	 * @date:2017年12月9日11:22:43
	 */
	Article selectKey(Integer id);
	
	/**
	 * 用户发表的帖子火帖总数
	 * 
	 * @date 2017年12月21日 下午1:46:06
	 */
	Integer selectByUserAllArticleHitCount(Integer userId);
}