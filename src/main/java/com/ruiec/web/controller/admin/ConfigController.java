package com.ruiec.web.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.model.Config;
import com.ruiec.web.service.ConfigService;

/**
 * 系统配置控制器
 * 
 * @author bingo<br>
 * @date 2017年11月29日 上午9:08:11
 */
@Controller
@RequestMapping("/admin/config")
public class ConfigController extends BaseAdminController {

	protected static final Logger LOGGER = LoggerFactory.getLogger(ConfigController.class);
	
	@Resource
	private ConfigService configService;
	
	/**
	 * 进入编辑页面
	 * 
	 * @author bingo<br>
	 * @date 2017年11月29日 上午9:13:56
	 */
	@RequestMapping("/edit")
	public String edit(Model model) {
		Map<String, String> map = new HashMap<String, String>();
		for (Config config : configService.selectAll()) {
			map.put(config.getKey(), config.getValue());
		}
		model.addAttribute("configs", map);
		return "/admin/config/edit";
	}
	
	/**
	 * 更新
	 * 
	 * @author bingo<br>
	 * @date 2017年11月29日 上午9:16:19
	 */
	@RequestMapping("/update")
	public String update(@RequestParam Map<String, String> configs, Model model) {
		for(Map.Entry<String, String> entry: configs.entrySet()) {
			Config config = new Config();
			config.setKey(entry.getKey());
			config.setValue(entry.getValue());
			configService.updateByKey(config);
		}
		return "redirect:edit.shtml";
	}
	
}
