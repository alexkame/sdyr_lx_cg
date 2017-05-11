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
import com.thinkgem.jeesite.modules.mzb.entity.MzbGongyct;
import com.thinkgem.jeesite.modules.mzb.service.MzbGongyctService;
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
 * 公益创投的表Controller
 * @author 陈威伟
 * @version 2016-11-24
 */
@Controller
@RequestMapping(value = "${adminPath}/mzb/mzbGongyct")
public class MzbGongyctController extends BaseController {

	@Autowired
	private MzbGongyctService mzbGongyctService;
	
	@ModelAttribute
	public MzbGongyct get(@RequestParam(required=false) String id) {
		MzbGongyct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mzbGongyctService.get(id);
		}
		if (entity == null){
			entity = new MzbGongyct();
		}
		return entity;
	}
	
	@RequiresPermissions("mzb:mzbGongyct:view")
	@RequestMapping(value = {"list", ""})
	public String list(MzbGongyct mzbGongyct, HttpServletRequest request, HttpServletResponse response, Model model) {
		String status = request.getParameter("status");
		mzbGongyct.setStatus(status);
		//查询当前用户下创建的基本信息
		mzbGongyct.getSqlMap().put("dsf", BaseService.dataScopeFilter(mzbGongyct.getCurrentUser(), "o", "u"));
		Page<MzbGongyct> page = mzbGongyctService.findPage(new Page<MzbGongyct>(request, response), mzbGongyct); 
		model.addAttribute("page", page);
		model.addAttribute("status", status);
		//判断是否从手机端过来的
		SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
		if (principal.isMobileLogin()) {
			return "modules/mzb/gyct_section";
		}
		return "modules/mzb/mzbGongyctList";
	}

	@RequiresPermissions("mzb:mzbGongyct:view")
	@RequestMapping(value = "form")
	public String form(MzbGongyct mzbGongyct, Model model) {
		model.addAttribute("mzbGongyct", mzbGongyct);
		return "modules/mzb/mzbGongyctForm";
	}

	//跳转到审核页面
	@RequiresPermissions("mzb:mzbGongyct:view")
	@RequestMapping(value = "checkform")
	public String checkform(MzbGongyct mzbGongyct, Model model) {
		model.addAttribute("mzbGongyct", mzbGongyct);
		return "modules/mzb/mzbGongyctCheckingForm";
	}

	//跳转到预览页面
	@RequiresPermissions("mzb:mzbGongyct:view")
	@RequestMapping(value = "view")
	public String view(MzbGongyct mzbGongyct, Model model) {
		model.addAttribute("mzbGongyct", mzbGongyct);
		//判断是否从手机端过来的
		SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
		if (principal.isMobileLogin()) {
			return "modules/mzb/mzbGongyct_view_section";
		}
		return "modules/mzb/mzbGongyctView";
	}

	//获取社会组织往年的json
	@RequiresPermissions("mzb:mzbGongyct:view")
	@RequestMapping(value = "getListJson")
	public void getListJson(MzbGongyct mzbGongyct, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		String status = request.getParameter("status");
		mzbGongyct.setStatus(status);
		mzbGongyct.getSqlMap().put("dsf", BaseService.dataScopeFilter(mzbGongyct.getCurrentUser(), "o", "u"));
		Page<MzbGongyct> page = mzbGongyctService.findPage(new Page<MzbGongyct>(request, response), mzbGongyct);

		JSONArray array = new JSONArray();
		//查询当前用户下创建的基本信息
		for (MzbGongyct mzbGongyct1 : page.getList()) {
			JSONObject json = new JSONObject();
			json.put("title",mzbGongyct1.getTitle());
			json.put("summary", mzbGongyct1.getSummary());
			json.put("createDate", DateUtils.formatDate(mzbGongyct1.getCreateDate(),"yyyy-MM-dd"));
			json.put("cash", mzbGongyct1.getCash());
			json.put("id", mzbGongyct1.getId());
			json.put("officeName", mzbGongyct1.getCreateBy().getOffice().getName());
			array.add(json);
		}

		PrintWriter out = response.getWriter();
		out.print(array);
	}
	@RequiresPermissions("mzb:mzbGongyct:edit")
	@RequestMapping(value = "save")
	public String save(MzbGongyct mzbGongyct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mzbGongyct)){
			return form(mzbGongyct, model);
		}
		mzbGongyctService.save(mzbGongyct);
		addMessage(redirectAttributes, "保存公益创投成功");
		int aa = 0;
		if (StringUtils.isBlank(mzbGongyct.getRemarks()) && mzbGongyct.getStatus().equals("1")) {
			aa=1;
		}
		return "redirect:"+Global.getAdminPath()+"/mzb/mzbGongyct/?status=" + aa;
	}
	
	@RequiresPermissions("mzb:mzbGongyct:edit")
	@RequestMapping(value = "checking")
	public String checking(MzbGongyct mzbGongyct, Model model, RedirectAttributes redirectAttributes) {
		mzbGongyct.setStatus("1");
		mzbGongyctService.save(mzbGongyct);
		addMessage(redirectAttributes, "保存公益创投成功");
		return "redirect:"+Global.getAdminPath()+"/mzb/mzbGongyct/?status=0" ;
	}



	@RequiresPermissions("mzb:mzbGongyct:edit")
	@RequestMapping(value = "delete")
	public String delete(MzbGongyct mzbGongyct, RedirectAttributes redirectAttributes) {
		mzbGongyctService.delete(mzbGongyct);
		addMessage(redirectAttributes, "删除公益创投成功");
		return "redirect:"+Global.getAdminPath()+"/mzb/mzbGongyct/?repage";
	}

}