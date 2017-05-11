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
import com.thinkgem.jeesite.modules.mzb.entity.MzbYearcheck;
import com.thinkgem.jeesite.modules.mzb.service.MzbYearcheckService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.security.SystemAuthorizingRealm;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.weixin.utils.NoticeTemplateMsgUtil;
import com.thinkgem.jeesite.weixin.utils.ResultTemplateMsgUtil;
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
 * 用于提交年检申报的信息Controller
 *
 * @author chenww
 * @version 2016-09-13
 */
@Controller
@RequestMapping(value = "${adminPath}/mzb/mzbYearcheck")
public class MzbYearcheckController extends BaseController {

    @Autowired
    private MzbYearcheckService mzbYearcheckService;

    //业务申报通知
    @Autowired
    private NoticeTemplateMsgUtil noticeTemplateMsgUtil;

    //审核结果通知
    @Autowired
    private ResultTemplateMsgUtil resultTemplateMsgUtil;

    @ModelAttribute
    public MzbYearcheck get(@RequestParam(required = false) String id) {
        MzbYearcheck entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = mzbYearcheckService.get(id);
        }
        if (entity == null) {
            entity = new MzbYearcheck();
        }
        return entity;
    }

    @RequiresPermissions("mzb:mzbYearcheck:view")
    @RequestMapping(value = {"list", ""})
    public String list(MzbYearcheck mzbYearcheck, HttpServletRequest request, HttpServletResponse response, Model model) {
        //如果是手机端提交的就跳转
        String abc = request.getParameter("model");
        if (StringUtils.isNotBlank(abc)) {
            return "modules/mzb/njsb_section";
        }
        Page<MzbYearcheck> page = mzbYearcheckService.findPage(new Page<MzbYearcheck>(request, response), mzbYearcheck);
        model.addAttribute("page", page);
        return "modules/mzb/mzbYearcheckList";
    }


    @RequiresPermissions("mzb:mzbYearcheck:view")
    @RequestMapping(value = "uncheck")
    public String uncheck(MzbYearcheck mzbYearcheck, HttpServletRequest request, HttpServletResponse response, Model model) {


        //本年公示查询
        mzbYearcheck.setStatus("1");
        // mzbYearcheck.setStatus("12");
        //查询当前用户下创建的基本信息
        mzbYearcheck.getSqlMap().put("dsf", BaseService.dataScopeFilter(mzbYearcheck.getCurrentUser(), "o", "u"));
        Page<MzbYearcheck> page = mzbYearcheckService.findPage(new Page<MzbYearcheck>(request, response), mzbYearcheck);
        model.addAttribute("page", page);
        model.addAttribute("isOld",false);

        //判断是否从手机端过来的
        SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
        if (principal.isMobileLogin()) {
            return "modules/mzb/njsb_section";
        }
        return "modules/mzb/mzbYearcheckList";
    }

    @RequiresPermissions("mzb:mzbYearcheck:view")
    @RequestMapping(value = "check")
    public String check(MzbYearcheck mzbYearcheck, HttpServletRequest request, HttpServletResponse response, Model model) {
        mzbYearcheck.setStatus("0");
        mzbYearcheck.getSqlMap().put("dsf", BaseService.dataScopeFilter(mzbYearcheck.getCurrentUser(), "o", "u"));
        Page<MzbYearcheck> page = mzbYearcheckService.findPage(new Page<MzbYearcheck>(request, response), mzbYearcheck);
        model.addAttribute("page", page);
        model.addAttribute("isOld",true);
        return "modules/mzb/mzbYearcheckList";
    }


    //获取社会组织往年的json
    @RequiresPermissions("mzb:mzbYearcheck:view")
    @RequestMapping(value = "getListJson")
    public void getListJson(MzbYearcheck mzbYearcheck, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        mzbYearcheck.setStatus("0");
        mzbYearcheck.getSqlMap().put("dsf", BaseService.dataScopeFilter(mzbYearcheck.getCurrentUser(), "o", "u"));
        Page<MzbYearcheck> page = mzbYearcheckService.findPage(new Page<MzbYearcheck>(request, response), mzbYearcheck);

        JSONArray array = new JSONArray();
        //查询当前用户下创建的基本信息
        for (MzbYearcheck mzbYearcheck1 : page.getList()) {
            JSONObject json = new JSONObject();
            json.put("name",mzbYearcheck1.getCreateBy().getName());
            json.put("id", mzbYearcheck1.getId());
            json.put("createDate", mzbYearcheck1.getCreateDate());
            array.add(json);
        }
        PrintWriter out = response.getWriter();
        out.print(array);
    }

    @RequiresPermissions("mzb:mzbYearcheck:view")
    @RequestMapping(value = "form")
    public String form(MzbYearcheck mzbYearcheck, Model model) {
        model.addAttribute("mzbYearcheck", mzbYearcheck);
        return "modules/mzb/mzbYearcheckForm";
    }

    //年检申报审批查看
    @RequiresPermissions("mzb:mzbYearcheck:view")
    @RequestMapping(value = "view")
    public String view(MzbYearcheck mzbYearcheck, Model model) {

        model.addAttribute("mzbYearcheck", mzbYearcheck);
        //判断是否从手机端过来的
        SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
        if (principal.isMobileLogin()) {
            return "modules/mzb/mzbYearcheck_view_section";
        }
        return "modules/mzb/mzbYearcheckView";
    }


    @RequiresPermissions("mzb:mzbYearcheck:edit")
    @RequestMapping(value = "save")
    public String save(MzbYearcheck mzbYearcheck, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, mzbYearcheck)) {
            return form(mzbYearcheck, model);
        }
        if (StringUtils.isBlank(mzbYearcheck.getStatus())) {
            mzbYearcheck.setStatus("1");
        }
        if (StringUtils.isBlank(mzbYearcheck.getRemarks())) {
            mzbYearcheck.setRemarks("");
        }
        if (mzbYearcheck.getStatus().equals("2")) {
            mzbYearcheck.setStatus("4");
        }
        mzbYearcheckService.save(mzbYearcheck);
        addMessage(redirectAttributes, "保存年检申报成功");
        return "redirect:" + Global.getAdminPath() + "/mzb/mzbYearcheck/uncheck?repage";
    }


    @RequiresPermissions("mzb:mzbYearcheck:edit")
    @RequestMapping(value = "checked")
    public String checked(MzbYearcheck mzbYearcheck, Model model, RedirectAttributes redirectAttributes) {
        User recordUser = UserUtils.get(mzbYearcheck.getCreateBy().getId());
        //年检审核不通过
        if ("2".equals(mzbYearcheck.getStatus())) {
            String openid  = recordUser.getOpenid();
            resultTemplateMsgUtil.send2Word(openid, recordUser.getName() + "您好,您提交的材料未通过审核.","年检申报", DictUtils.getDictLabel("2", "check_flag", ""),"审批意见:" + mzbYearcheck.getRemarks());
        }
        //年检审核通过
        if ("3".equals(mzbYearcheck.getStatus())) {
            String openid  = recordUser.getOpenid();
            resultTemplateMsgUtil.send2Word(openid, recordUser.getName() + "您好,您提交的材料已通过审核.","年检申报", DictUtils.getDictLabel("3", "check_flag", ""),"审批意见:" + mzbYearcheck.getRemarks());
        }

        mzbYearcheckService.save(mzbYearcheck);
        addMessage(redirectAttributes, "审核年检申报成功");
        return "redirect:" + Global.getAdminPath() + "/mzb/mzbYearcheck/uncheck?repage";
    }


    @RequiresPermissions("mzb:mzbYearcheck:edit")
    @RequestMapping(value = "delete")
    public String delete(MzbYearcheck mzbYearcheck, RedirectAttributes redirectAttributes) {
        mzbYearcheckService.delete(mzbYearcheck);
        addMessage(redirectAttributes, "删除年检申报成功");
        return "redirect:" + Global.getAdminPath() + "/mzb/mzbYearcheck/uncheck?repage";
    }

}