package com.thinkgem.jeesite.modules.weixin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by MacBook on 16/8/26.
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/login")
public class WxLoginDistriControlller {

    @RequestMapping(value = "distribute")
    public String createMenu(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        String count = request.getParameter("count");
        return "";
    }
}
