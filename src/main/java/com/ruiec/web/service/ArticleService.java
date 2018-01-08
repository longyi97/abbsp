package com.ruiec.web.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.ruiec.web.dto.ArticelSelectDTO;
import com.ruiec.web.dto.ArticleDTO;
import com.ruiec.web.model.Article;

/**
 * 后台帖子接口
 * @author 王伟 
 * date:2017年12月1日09:31:50
 */
public interface ArticleService extends BaseService<Article> {
	/**
	 * 判断帖子数量
	 * 
	 * @author 王伟
	 * @Date:2017年10月29日16:22:27
	 */
	Map<String, Long> getType();
	/**
	 * 通过帖子查询出用户的回复
	 * 
	 * @author 王伟
	 * @Date:2017年10月29日16:22:27
	 */
	public List<Article> selectleft();

	/**
	 * 直接删除帖子
	 * 
	 * @author 王伟
	 * @Date:2017年10月29日16:22:27
	 */
	public int deleteArticleId(Integer id);

	/**
	 * 通过用户ID获取所有该用户的帖子
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年10月30日 下午2:57:37
	 */
	public List<Article> selectByUserAllArticle(Integer userId);

	/**
	 * 通过时间段获取帖子数量（版块）
	 * 
	 * @author bingo<br>
	 * @date 2017年10月31日 下午2:31:59
	 */
	Integer countByDate(Timestamp queryDate, Integer moduleId);

	/**
	 * 获取版块下帖子数量
	 * 
	 * @author bingo<br>
	 * @date 2017年11月6日 上午11:21:08
	 */
	Integer countByModuleId(Integer moduleId);

	/**
	 * 通过顶级版块ID获取所有帖子
	 * 
	 * @author bingo<br>
	 * @date 2017年11月11日 上午10:28:30
	 */
	List<Article> selectByTopModule(Integer startPage, Integer pageSize, String orderClause, Integer moduleId);
	
	/**
	 * 帖子假删除（水贴）
	 * 
	 * @author bingo<br>
	 * @date 2017年12月8日 下午4:49:04
	 */
	Integer fakeDelete(Article article);

	/**
	 * 用户发表的帖子总数
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月1日 下午1:46:06
	 */
	Integer selectByUserAllArticleCount(Integer userId);
	
	/**
	 * 用户发表的帖子火帖总数
	 * 
	 * @author 王伟<br>
	 * @date 2017年11月1日 下午1:46:06
	 */
	Integer selectByUserAllArticleHitCount(Integer userId);
	/**
	 * 获取带有图片的帖子
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月8日 下午3:24:18
	 */
	List<Article> selectShufflingArticle(Integer moduleId);

	/**
	 * 增加点击量
	 * 
	 * @author 王伟
	 * @return
	 * @date:2017年11月8日15:49:14
	 */
	public int updateAddHit(Integer id);

	/**
	 * 通过id去查询帖子名称
	 * 
	 * @author 王伟
	 * @date:2017年11月16日11:26:07
	 */
	public String selectArticleTitl(Integer id);

	/**
	 * 根据发帖人搜索
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月16日 下午10:14:37
	 */
	public List<Article> selectByArticleAllUser(ArticleDTO articleDTO);
	
	
	/**
	 * 分页查询(通过回复数量排序)
	 * @author 王伟<br>
	 * @Date: 2017年01月05日
	 */
	public List<Article> selectByPageType(ArticelSelectDTO articelSelectDTO);
	
	/**
	 * 通过数组批量假删除
	 * @author 王伟
	 * @date：2017年12月7日21:43:15
	 */
	public int updateByPrimary(Integer[] ids, Class<Article> t);
	
	/**
	 * 保存帖子
	 * 
	 * @author bingo<br>
	 * @date 2017年12月8日 下午7:19:22
	 */
	int insertSelective(Article article, String[] attachmentUrls);
	
	/**
	 * 输入框输入查询
	 * @author 王伟
	 * @date:2017年12月9日11:22:43
	 */
	public Article selectKey(Integer id);

}
