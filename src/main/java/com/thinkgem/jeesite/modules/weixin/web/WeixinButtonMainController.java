/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinButtonMain;
import com.thinkgem.jeesite.modules.weixin.service.WeixinButtonMainService;
import com.thinkgem.jeesite.weixin.utils.MenuManager;
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
import java.util.List;

/**
 * 微信菜单类Controller
 * @author cww
 * @version 2016-06-07
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/weixinButtonMain")
public class WeixinButtonMainController extends BaseController {

	@Autowired
	private WeixinButtonMainService weixinButtonMainService;

	@ModelAttribute
	public WeixinButtonMain get(@RequestParam(required=false) String id) {
		WeixinButtonMain entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinButtonMainService.get(id);
		}
		if (entity == null){
			entity = new WeixinButtonMain();
		}
		return entity;
	}


	@RequestMapping(value = {"list", ""})
	public String list(WeixinButtonMain weixinButtonMain, HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("hahaha");
		return "modules/weixin/weixinButtonMainList";
	}

	@RequiresPermissions("weixin:weixinButtonMain:view")
	@RequestMapping(value = "form")
	public String form(WeixinButtonMain weixinButtonMain,HttpServletRequest request,  Model model) {
        weixinButtonMain.setRemarks(request.getParameter("remarks"));
		model.addAttribute("weixinButtonMain", weixinButtonMain);
		return "modules/weixin/weixinButtonMainForm";
	}

	@RequiresPermissions("weixin:weixinButtonMain:edit")
	@RequestMapping(value = "save")
	public String save(WeixinButtonMain weixinButtonMain, HttpServletRequest request,Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinButtonMain)){
			return form(weixinButtonMain, request,model);
		}

		weixinButtonMainService.save(weixinButtonMain);
		addMessage(redirectAttributes, "保存菜单成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinButtonMain?remarks=" + weixinButtonMain.getRemarks();
	}

	@RequiresPermissions("weixin:weixinButtonMain:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinButtonMain weixinButtonMain, RedirectAttributes redirectAttributes) {
		weixinButtonMainService.delete(weixinButtonMain);
		addMessage(redirectAttributes, "删除菜单成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinButtonMain?remarks=" + weixinButtonMain.getRemarks();
	}


    @RequiresPermissions("weixin:weixinButtonMain:edit")
    @RequestMapping(value = "createMenu")
    public String createMenu(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        String count = request.getParameter("count");
        WeixinButtonMain weixinButtonMain = new WeixinButtonMain();
        weixinButtonMain.setRemarks(request.getParameter("remarks"));

        //1.获取主菜单列表
        List<WeixinButtonMain> weixinButtonMainList = (List<WeixinButtonMain>) weixinButtonMainService.findSubList(weixinButtonMain);

        String message = "";
        //主菜按钮不能超过3个
        if (StringUtils.toInteger(count) > 3) {
            message = "主菜单不能超过3个";
        }else{
            //2.执行菜单生成操作
            String result =  MenuManager.createMenu(weixinButtonMainList);

            logger.info(result);
            message = "微信菜单生成成功!";
        }


        //3.返回通知
        addMessage(redirectAttributes,message);
        return "redirect:"+Global.getAdminPath()+"/weixin/weixinButtonMain?remarks=" + weixinButtonMain.getRemarks();
    }
}