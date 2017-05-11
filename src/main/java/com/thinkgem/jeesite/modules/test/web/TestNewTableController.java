/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.test.entity.TestNewTable;
import com.thinkgem.jeesite.modules.test.service.TestNewTableService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 我的测试Controller
 * @author cww
 * @version 2016-05-26
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testNewTable")
public class TestNewTableController extends BaseController {

	@Autowired
	private TestNewTableService testNewTableService;

	@ModelAttribute
	public TestNewTable get(@RequestParam(required=false) String id) {
		TestNewTable entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testNewTableService.get(id);
		}
		if (entity == null){
			entity = new TestNewTable();
		}
		return entity;
	}

	@RequiresPermissions("test:testNewTable:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestNewTable testNewTable, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("外网搜索");
		Page<TestNewTable> page = testNewTableService.findPage(new Page<TestNewTable>(request, response), testNewTable);
		model.addAttribute("page", page);
		return "modules/test/testNewTableList";
	}

	@RequiresPermissions("test:testNewTable:view")
	@RequestMapping(value = "form")
	public String form(TestNewTable testNewTable, Model model) {
		model.addAttribute("testNewTable", testNewTable);
		return "modules/test/testNewTableForm";
	}

	@RequiresPermissions("test:testNewTable:edit")
	@RequestMapping(value = "save")
	public String save(TestNewTable testNewTable, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testNewTable)){
			return form(testNewTable, model);
		}
		testNewTableService.save(testNewTable);
		addMessage(redirectAttributes, "保存测试成功");
		return "redirect:"+Global.getAdminPath()+"/test/testNewTable/?repage";
	}
	
	@RequiresPermissions("test:testNewTable:edit")
	@RequestMapping(value = "delete")
	public String delete(TestNewTable testNewTable, RedirectAttributes redirectAttributes) {
		testNewTableService.delete(testNewTable);
		addMessage(redirectAttributes, "删除测试成功");
		return "redirect:"+Global.getAdminPath()+"/test/testNewTable/?repage";
	}

}