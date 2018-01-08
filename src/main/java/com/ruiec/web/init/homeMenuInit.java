/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.init;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.ruiec.web.common.DictionaryUtil;
import com.ruiec.web.model.Module;
import com.ruiec.web.service.ModuleService;

/**
 * 前台菜单初始化
 * @author 钟国城<br>
 * @date 2017年12月27日 上午9:04:10
 */
public class homeMenuInit implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOGGER = Logger.getLogger(homeMenuInit.class);

	@Resource
	private ModuleService moduleService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() != null) {
			return;
		}
		LOGGER.info("前台菜单开始初始化。。。");
		moduleService.getSubList(null);
		try {
			if(DictionaryUtil.getMap()==null||DictionaryUtil.getMap().size()==0){
				HashMap<String, Module> map=new HashMap<String, Module>();
				List<Module> dics = moduleService.getSubList(null);
				for (Module dic : dics) {
					if(dic!=null&&dic.getId()!=null){
						map.put(dic.getId().toString(), dic);
					}
				}
				DictionaryUtil.setMap(map);
				LOGGER.info("初始化前台菜单的全局静态变量成功，共找到"+DictionaryUtil.getMap().size()+"前台菜单");
			}
		} catch (Exception e) {
			LOGGER.info("初始化前台菜单的全局静态变量时，出现错误："+e);
		}
	}
}
