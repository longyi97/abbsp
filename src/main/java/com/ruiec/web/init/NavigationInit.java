/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.init;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;

import com.ruiec.web.model.Navigation;
import com.ruiec.web.service.NavigationService;

/**
 * 后台导航初始化
 * @author 刘立雯<br>
 * Version 1.0<br>
 * Date: 2017年07月06日
 */
public class NavigationInit implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOGGER = Logger.getLogger(NavigationInit.class);

	@Resource
	private NavigationService navigationService;

	@SuppressWarnings("rawtypes")
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() != null) {
			return;
		}
		LOGGER.info("init navigations begin ...");
		List<Navigation> navigations = navigationService.selectAll();
		if (navigations.size() < 1) {
			try {
				File file = new ClassPathResource("navigations.xml").getFile();
				Document document = new SAXReader().read(file);
				List list = document.selectNodes("/navigations/navigation");
				Iterator iterator = list.iterator();
				while (iterator.hasNext()) {
					org.dom4j.Element element = (org.dom4j.Element) iterator.next();
					String id = element.attributeValue("id");
					String name = element.attributeValue("name");
					String parentId = element.attributeValue("parentId");
					String sort = element.attributeValue("sort");
					String isShow = element.attributeValue("isShow");
					String link = element.attributeValue("link");
					String level = element.attributeValue("level");
					Navigation localNavigation = new Navigation();
					localNavigation.setId(Integer.parseInt(id));
					localNavigation.setName(name);
					if (parentId != null && !parentId.isEmpty()) {
						localNavigation.setParentId(Integer.parseInt(parentId));
					}
					localNavigation.setSort(Integer.parseInt(sort));
					localNavigation.setIsShow(isShow.equals("0")?false:true);
					localNavigation.setLink(link);
					localNavigation.setLevel(Integer.parseInt(level));
					navigationService.insertSelective(localNavigation);
				}
				LOGGER.info("update navigations success ...");
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.info("init navigations failed ...");
			} finally {
				
			}
		}
	}
}
