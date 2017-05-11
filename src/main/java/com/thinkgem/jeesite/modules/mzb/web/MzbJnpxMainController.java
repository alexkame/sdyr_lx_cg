/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mzb.web;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.mzb.entity.MzbJnpxMain;
import com.thinkgem.jeesite.modules.mzb.entity.MzbJnpxUser;
import com.thinkgem.jeesite.modules.mzb.service.MzbJnpxMainService;
import com.thinkgem.jeesite.modules.sys.security.SystemAuthorizingRealm;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.weixin.utils.NoticeTemplateMsgUtil;
import org.apache.shiro.SecurityUtils;
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
import java.util.Date;
import java.util.List;

/**
 * 技能培训Controller
 * @author 陈威伟
 * @version 2016-11-28
 */
@Controller
@RequestMapping(value = "${adminPath}/mzb/mzbJnpxMain")
public class MzbJnpxMainController extends BaseController {
	//业务申报通知
	@Autowired
	private NoticeTemplateMsgUtil noticeTemplateMsgUtil;

	@Autowired
	private MzbJnpxMainService mzbJnpxMainService;
	
	@ModelAttribute
	public MzbJnpxMain get(@RequestParam(required=false) String id) {
		MzbJnpxMain entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mzbJnpxMainService.get(id);
		}
		if (entity == null){
			entity = new MzbJnpxMain();
		}
		return entity;
	}

	@RequiresPermissions("mzb:mzbJnpxMain:view")
	@RequestMapping(value = {"list", ""})
	public String list(MzbJnpxMain mzbJnpxMain, HttpServletRequest request, HttpServletResponse response, Model model) {

		//查询登录的用户是否已分配为技能培训
		if (!SecurityUtils.getSubject().isPermitted("mzb:mzbJnpxMain:check")) {
			//判断
			mzbJnpxMain.setTest2(UserUtils.getUser().getId());
		}

		Page<MzbJnpxMain> page = mzbJnpxMainService.findPage(new Page<MzbJnpxMain>(request, response), mzbJnpxMain);
		model.addAttribute("page", page);
		//判断是否从手机端过来的
		SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
		if (principal.isMobileLogin()) {
			return "modules/mzb/jnpx_section";
		}
		return "modules/mzb/mzbJnpxMainList";
	}

	@RequiresPermissions("mzb:mzbJnpxMain:view")
	@RequestMapping(value = "form")
	public String form(MzbJnpxMain mzbJnpxMain, Model model) {
		model.addAttribute("mzbJnpxMain", mzbJnpxMain);
		//判断是否从手机端过来的

		String content = Encodes.unescapeHtml(mzbJnpxMain.getContent());
		mzbJnpxMain.setContent(content);
		SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
		if (principal.isMobileLogin()) {
			return "modules/mzb/mzbJnpx_content_section";
		}
		return "modules/mzb/mzbJnpxMainForm";
	}


	@RequiresPermissions("mzb:mzbJnpxMain:view")
	@RequestMapping(value = "test")
	public String test(MzbJnpxMain mzbJnpxMain, Model model) {
		model.addAttribute("mzbJnpxTestList", mzbJnpxMain.getMzbJnpxTestList());
		model.addAttribute("mzbJnpxMain", mzbJnpxMain);

		MzbJnpxUser mzbJnpxUser = mzbJnpxMainService.isDone(mzbJnpxMain.getId(),UserUtils.getUser().getId());
		if (mzbJnpxUser == null) {
			mzbJnpxUser = new MzbJnpxUser();
		}
		model.addAttribute("isDone", mzbJnpxUser.getIsDone());
		//判断是否从手机端过来的
		SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
		if (principal.isMobileLogin()) {
			return "modules/mzb/mzbJnpx_test_section";
		}
		return "modules/mzb/mzbJnpxMainForm";
	}


	//获取社会组织往年的json
	@RequiresPermissions("mzb:mzbJnpxMain:view")
	@RequestMapping(value = "through")
	public void through(MzbJnpxMain mzbJnpxMain, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		String userId = request.getParameter("userId");
		String mainId = request.getParameter("mainId");
		int score = Integer.parseInt(request.getParameter("score"));
		String message = "";

		MzbJnpxUser mzbJnpxUser = mzbJnpxMainService.getMzbJnpxUserDao().getByMainUserId(mainId, userId);

    	int passScore = Integer.parseInt(DictUtils.getDictValue("通过分数","pass_score",""));
		if (passScore <= score) {
			mzbJnpxUser.setIsDone("1");
			message = "真棒!您通过了全部测试!";
		}else{
			mzbJnpxUser.setIsDone("0");
			message = "您的得分是" + score + ",未通过测试,可'重新答题'！";
		}
		mzbJnpxUser.setTest1("" + score);
		mzbJnpxUser.setDoneDate(DateUtils.formatDate(new Date(),"yyyy-MM-dd"));
		mzbJnpxMainService.getMzbJnpxUserDao().update(mzbJnpxUser);
		JSONObject json = new JSONObject();
		json.put("message1",message);

		PrintWriter out = response.getWriter();
		out.print(json);
	}

	@RequiresPermissions("mzb:mzbJnpxMain:edit")
	@RequestMapping(value = "save")
	public String save(MzbJnpxMain mzbJnpxMain, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mzbJnpxMain)){
			return form(mzbJnpxMain, model);
		}
		mzbJnpxMainService.save(mzbJnpxMain);


		//取所有用户的openid并发送
		List<MzbJnpxUser> mzbJnpxUserList = mzbJnpxMain.getMzbJnpxUserList();
		for (MzbJnpxUser mzbJnpxUser : mzbJnpxUserList) {
			MzbJnpxUser recordUser = mzbJnpxMainService.getMzbJnpxUserDao().get(mzbJnpxUser);//mzbJnpxUser.getUser();//UserUtils.get(mzbJnpxUser.getUser());
			if (StringUtils.isBlank(recordUser.getUser().getOpenid())) {
				continue;
			}
			noticeTemplateMsgUtil.send3Word(recordUser.getUser().getOpenid(), recordUser.getUser().getName() + "您好,您收到一条来自海沧民政局的通知","技能培训", DateUtils.formatDate(mzbJnpxUser.getCreateDate(), "yyyy-MM-dd HH:mm:ss") ,   mzbJnpxMain.getTitle(),"");
		}

		addMessage(redirectAttributes, "保存技能培训成功");
		return "redirect:"+Global.getAdminPath()+"/mzb/mzbJnpxMain/?repage";
	}
	
	@RequiresPermissions("mzb:mzbJnpxMain:edit")
	@RequestMapping(value = "delete")
	public String delete(MzbJnpxMain mzbJnpxMain, RedirectAttributes redirectAttributes) {
		mzbJnpxMainService.delete(mzbJnpxMain);
		addMessage(redirectAttributes, "删除技能培训成功");
		return "redirect:"+Global.getAdminPath()+"/mzb/mzbJnpxMain/?repage";
	}

}