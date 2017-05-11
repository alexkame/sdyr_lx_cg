/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.test.entity.TestTableMain;
import com.thinkgem.jeesite.modules.test.service.TestTableMainService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 简单的一对多示例Controller
 * @author cww
 * @version 2016-05-31
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testTableMain")
public class TestTableMainController extends BaseController {
	@Value("#{APP_PROP['jdbc.driver']}")
	private String jdbcDriver;

	@Autowired
	private TestTableMainService testTableMainService;
	
	@ModelAttribute
	public TestTableMain get(@RequestParam(required=false) String id) {
		TestTableMain entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testTableMainService.get(id);
		}
		if (entity == null){
			entity = new TestTableMain();
		}
		return entity;
	}
	
	@RequiresPermissions("test:testTableMain:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestTableMain testTableMain, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("是是是是少时诵诗书是是是是是1" + jdbcDriver);
		Page<TestTableMain> page = testTableMainService.findPage(new Page<TestTableMain>(request, response), testTableMain); 
		model.addAttribute("page", page);
		return "modules/test/testTableMainList";
	}

	@RequiresPermissions("test:testTableMain:view")
	@RequestMapping(value = "form")
	public String form(TestTableMain testTableMain, Model model) {
		model.addAttribute("testTableMain", testTableMain);
		return "modules/test/testTableMainForm";
	}

	@RequiresPermissions("test:testTableMain:edit")
	@RequestMapping(value = "save")
	public String save(TestTableMain testTableMain, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testTableMain)){
			return form(testTableMain, model);
		}
		testTableMainService.save(testTableMain);
		addMessage(redirectAttributes, "保存关联表成功");
		return "redirect:"+Global.getAdminPath()+"/test/testTableMain/?repage";
	}
	
	@RequiresPermissions("test:testTableMain:edit")
	@RequestMapping(value = "delete")
	public String delete(TestTableMain testTableMain, RedirectAttributes redirectAttributes) {
		testTableMainService.delete(testTableMain);
		addMessage(redirectAttributes, "删除关联表成功");
		return "redirect:"+Global.getAdminPath()+"/test/testTableMain/?repage";
	}

}