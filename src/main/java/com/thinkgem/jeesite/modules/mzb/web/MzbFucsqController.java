/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mzb.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.mzb.entity.MzbFucsq;
import com.thinkgem.jeesite.modules.mzb.service.MzbFucsqService;
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
 * 扶持申请Controller
 * @author 陈威伟
 * @version 2016-11-24
 */
@Controller
@RequestMapping(value = "${adminPath}/mzb/mzbFucsq")
public class MzbFucsqController extends BaseController {

	@Autowired
	private MzbFucsqService mzbFucsqService;
	
	@ModelAttribute
	public MzbFucsq get(@RequestParam(required=false) String id) {
		MzbFucsq entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mzbFucsqService.get(id);
		}
		if (entity == null){
			entity = new MzbFucsq();
		}
		return entity;
	}
	
	@RequiresPermissions("mzb:mzbFucsq:view")
	@RequestMapping(value = {"list", ""})
	public String list(MzbFucsq mzbFucsq, HttpServletRequest request, HttpServletResponse response, Model model) {

		String isSave = request.getParameter("isSave");
		SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
		//判断是否从手机端过来的
		if (principal.isMobileLogin() && StringUtils.equals(isSave,"isSave")) {
			mzbFucsqService.save(mzbFucsq);
		}
		String status = request.getParameter("status");
		mzbFucsq.setStatus(status);
		//查询当前用户下创建的基本信息
		mzbFucsq.getSqlMap().put("dsf", BaseService.dataScopeFilter(mzbFucsq.getCurrentUser(), "o", "u"));
		Page<MzbFucsq> page = mzbFucsqService.findPage(new Page<MzbFucsq>(request, response), mzbFucsq);
		model.addAttribute("page", page);
		model.addAttribute("status", status);
		//判断是否从手机端过来的
		if (principal.isMobileLogin()) {
			return "modules/mzb/fcsq_section";
		}

		return "modules/mzb/mzbFucsqList";
	}

	//获取社会组织往年的json
	@RequiresPermissions("mzb:mzbFucsq:view")
	@RequestMapping(value = "getListJson")
	public void getListJson(MzbFucsq mzbFucsq, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		String status = request.getParameter("status");
		mzbFucsq.setStatus(status);
		mzbFucsq.getSqlMap().put("dsf", BaseService.dataScopeFilter(mzbFucsq.getCurrentUser(), "o", "u"));
		Page<MzbFucsq> page = mzbFucsqService.findPage(new Page<MzbFucsq>(request, response), mzbFucsq);

		JSONArray array = new JSONArray();
		//查询当前用户下创建的基本信息
		for (MzbFucsq mzbFucsq1 : page.getList()) {
			JSONObject json = new JSONObject();
			json.put("title",mzbFucsq1.getTitle());
			json.put("summary", mzbFucsq1.getSummary());
			json.put("createDate", DateUtils.formatDate(mzbFucsq1.getCreateDate(),"yyyy-MM-dd"));
			json.put("cash", mzbFucsq1.getCash());
			json.put("id", mzbFucsq1.getId());
			json.put("officeName", mzbFucsq1.getCreateBy().getOffice().getName());
			array.add(json);
		}

		PrintWriter out = response.getWriter();
		out.print(array);
	}

	@RequiresPermissions("mzb:mzbFucsq:view")
	@RequestMapping(value = "form")
	public String form(MzbFucsq mzbFucsq, Model model) {
		model.addAttribute("mzbFucsq", mzbFucsq);
		return "modules/mzb/mzbFucsqForm";
	}

	//跳转到审核页面
	@RequiresPermissions("mzb:mzbFucsq:view")
	@RequestMapping(value = "checkform")
	public String checkform(MzbFucsq mzbFucsq, Model model) {
		model.addAttribute("mzbFucsq", mzbFucsq);
		return "modules/mzb/mzbFucsqCheckingForm";
	}

	//跳转到预览页面
	@RequiresPermissions("mzb:mzbFucsq:view")
	@RequestMapping(value = "view")
	public String view(MzbFucsq mzbFucsq, Model model) {
		model.addAttribute("mzbFucsq", mzbFucsq);
		//判断是否从手机端过来的
		SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
		if (principal.isMobileLogin()) {
			return "modules/mzb/mzbFucsq_view_section";
		}
		return "modules/mzb/mzbFucsqView";
	}
	
	
	@RequiresPermissions("mzb:mzbFucsq:edit")
	@RequestMapping(value = "save")
	public String save(MzbFucsq mzbFucsq, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mzbFucsq)){
			return form(mzbFucsq, model);
		}
		mzbFucsqService.save(mzbFucsq);
		//判断是否从手机端过来的
		SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
		if (principal.isMobileLogin()) {
			return "modules/mzb/fcsq_section";
		}
		addMessage(redirectAttributes, "保存扶持申请成功");
		int aa = 0;
		if (StringUtils.isBlank(mzbFucsq.getRemarks()) && mzbFucsq.getStatus().equals("1")) {
			aa=1;
		}

		addMessage(redirectAttributes, "保存扶持申请成功");
		return "redirect:"+Global.getAdminPath()+"/mzb/mzbFucsq/?status=" + aa;
	}
	
	@RequiresPermissions("mzb:mzbFucsq:edit")
	@RequestMapping(value = "delete")
	public String delete(MzbFucsq mzbFucsq, RedirectAttributes redirectAttributes) {
		mzbFucsqService.delete(mzbFucsq);
		addMessage(redirectAttributes, "删除扶持申请成功");
		return "redirect:"+Global.getAdminPath()+"/mzb/mzbFucsq/?repage";
	}

}