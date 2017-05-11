/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mzb.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.mzb.entity.MzbUnitinfo;
import com.thinkgem.jeesite.modules.mzb.service.MzbUnitinfoService;
import com.thinkgem.jeesite.modules.sys.security.SystemAuthorizingRealm;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
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
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 社会组织基本信息Controller
 * @author chenww
 * @version 2016-11-14
 */
@Controller
@RequestMapping(value = "${adminPath}/mzb/mzbUnitinfo")
public class MzbUnitinfoController extends BaseController {

	@Autowired
	private MzbUnitinfoService mzbUnitinfoService;
	
	@ModelAttribute
	public MzbUnitinfo get(@RequestParam(required=false) String id) {
		MzbUnitinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mzbUnitinfoService.get(id);
		}
		if (entity == null){
			entity = new MzbUnitinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("mzb:mzbUnitinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(MzbUnitinfo mzbUnitinfo, HttpServletRequest request, HttpServletResponse response, Model model) {

		//查询当前用户下创建的基本信息
		mzbUnitinfo.getSqlMap().put("dsf", BaseService.dataScopeFilter(mzbUnitinfo.getCurrentUser(), "o", "u"));
		Page<MzbUnitinfo> page = mzbUnitinfoService.findPage(new Page<MzbUnitinfo>(request, response), mzbUnitinfo); 
		model.addAttribute("page", page);

		//判断是否从手机端过来的
		SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
		if (principal.isMobileLogin()) {
			return "modules/mzb/mzbUnitinfo_view_section";
		}

		return "modules/mzb/mzbUnitinfoList";
	}

	//返回基本信息的json
	@RequiresPermissions("mzb:mzbUnitinfo:view")
	@RequestMapping(value = "getListJson")
	public void getListJson(MzbUnitinfo mzbUnitinfo, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

		JSONArray array = new JSONArray();
		//查询当前用户下创建的基本信息
		mzbUnitinfo.getSqlMap().put("dsf", BaseService.dataScopeFilter(mzbUnitinfo.getCurrentUser(), "o", "u"));
		Page<MzbUnitinfo> page = mzbUnitinfoService.findPage(new Page<MzbUnitinfo>(request, response), mzbUnitinfo);


		for (MzbUnitinfo mzbUnitinfo1 : page.getList()) {
			JSONObject json = new JSONObject();
			json.put("name",mzbUnitinfo1.getDanwMc());
			json.put("id", mzbUnitinfo1.getId());
			array.add(json);
		}
		PrintWriter out = response.getWriter();
		out.print(array);
	}



	@RequiresPermissions("mzb:mzbUnitinfo:view")
	@RequestMapping(value = "form")
	public String form(MzbUnitinfo mzbUnitinfo, Model model) {
		model.addAttribute("mzbUnitinfo", mzbUnitinfo);
		return "modules/mzb/mzbUnitinfoForm";
	}

	@RequiresPermissions("mzb:mzbUnitinfo:view")
	@RequestMapping(value = "view")
	public String view(MzbUnitinfo mzbUnitinfo, Model model) {
		//判断是否从手机端过来的
		SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
		if (principal.isMobileLogin()) {
			return "modules/mzb/mzbUnitinfo_view_section";
		}

		model.addAttribute("mzbUnitinfo", mzbUnitinfo);
		return "modules/mzb/mzbUnitinfoView";
	}

	//申请修改
	@RequiresPermissions("mzb:mzbUnitinfo:view")
	@RequestMapping(value = "apply")
	public String apply(MzbUnitinfo mzbUnitinfo, Model model) {
		model.addAttribute("mzbUnitinfo", mzbUnitinfo);
		return "modules/mzb/mzbUnitinfoApply";
	}


	@RequiresPermissions("mzb:mzbUnitinfo:edit")
	@RequestMapping(value = "save")
	public String save(MzbUnitinfo mzbUnitinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mzbUnitinfo)){
			return form(mzbUnitinfo, model);
		}
		//社会组织提交基本信息审核
		if (StringUtils.equals(mzbUnitinfo.getIsself(), "") || mzbUnitinfo.getIsself() == null) {
			mzbUnitinfo.setIsself("1");
			mzbUnitinfoService.save(mzbUnitinfo);
			addMessage(redirectAttributes, "提交审核信息成功");
		} else if(StringUtils.equals(mzbUnitinfo.getIsself(), "1")) {
			mzbUnitinfo.setIsself("2");
			mzbUnitinfoService.save(mzbUnitinfo);
			addMessage(redirectAttributes, "通过审核修改意见");
		} else if(StringUtils.equals(mzbUnitinfo.getIsself(), "2")) {
			mzbUnitinfo.setIsself("3");
			mzbUnitinfoService.save(mzbUnitinfo);
			addMessage(redirectAttributes, "保存基本信息成功");
		}else if(StringUtils.equals(mzbUnitinfo.getIsself(), "3")) {
			mzbUnitinfo.setIsself("");
			mzbUnitinfoService.save(mzbUnitinfo);
			addMessage(redirectAttributes, "保存基本信息成功");
		}else {
			mzbUnitinfoService.save(mzbUnitinfo);
			addMessage(redirectAttributes, "保存基本信息成功");
		}


		return "redirect:"+Global.getAdminPath()+"/mzb/mzbUnitinfo/?repage";
	}
	
	@RequiresPermissions("mzb:mzbUnitinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(MzbUnitinfo mzbUnitinfo, RedirectAttributes redirectAttributes) {
		mzbUnitinfoService.delete(mzbUnitinfo);
		addMessage(redirectAttributes, "删除基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/mzb/mzbUnitinfo/?repage";
	}

}