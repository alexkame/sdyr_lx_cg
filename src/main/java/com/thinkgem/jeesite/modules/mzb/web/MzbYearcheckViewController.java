/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mzb.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.mzb.entity.MzbYearcheckView;
import com.thinkgem.jeesite.modules.mzb.service.MzbYearcheckViewService;
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
 * 年报公示预警Controller
 * @author 陈威伟
 * @version 2016-12-04
 */
@Controller
@RequestMapping(value = "${adminPath}/mzb/mzbYearcheckView")
public class MzbYearcheckViewController extends BaseController {

	@Autowired
	private MzbYearcheckViewService mzbYearcheckViewService;
	
	@ModelAttribute
	public MzbYearcheckView get(@RequestParam(required=false) String id) {
		MzbYearcheckView entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mzbYearcheckViewService.get(id);
		}
		if (entity == null){
			entity = new MzbYearcheckView();
		}
		return entity;
	}
	
	@RequiresPermissions("mzb:mzbYearcheckView:view")
	@RequestMapping(value = {"list", ""})
	public String list(MzbYearcheckView mzbYearcheckView, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MzbYearcheckView> page = mzbYearcheckViewService.findPage(new Page<MzbYearcheckView>(request, response), mzbYearcheckView); 
		model.addAttribute("page", page);
		return "modules/mzb/mzbYearcheckViewList";
	}

	@RequiresPermissions("mzb:mzbYearcheckView:view")
	@RequestMapping(value = "form")
	public String form(MzbYearcheckView mzbYearcheckView, Model model) {
		model.addAttribute("mzbYearcheckView", mzbYearcheckView);
		return "modules/mzb/mzbYearcheckViewForm";
	}

	@RequiresPermissions("mzb:mzbYearcheckView:edit")
	@RequestMapping(value = "save")
	public String save(MzbYearcheckView mzbYearcheckView, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mzbYearcheckView)){
			return form(mzbYearcheckView, model);
		}
		mzbYearcheckViewService.save(mzbYearcheckView);
		addMessage(redirectAttributes, "保存年报公示预警成功");
		return "redirect:"+Global.getAdminPath()+"/mzb/mzbYearcheckView/?repage";
	}
	
	@RequiresPermissions("mzb:mzbYearcheckView:edit")
	@RequestMapping(value = "delete")
	public String delete(MzbYearcheckView mzbYearcheckView, RedirectAttributes redirectAttributes) {
		mzbYearcheckViewService.delete(mzbYearcheckView);
		addMessage(redirectAttributes, "删除年报公示预警成功");
		return "redirect:"+Global.getAdminPath()+"/mzb/mzbYearcheckView/?repage";
	}

	public void test() {
		System.out.println("12341234");
	}
}