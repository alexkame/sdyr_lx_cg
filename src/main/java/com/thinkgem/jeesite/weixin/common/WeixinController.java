package com.thinkgem.jeesite.weixin.common;

import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.ImageReqMsg;
import com.github.sd4324530.fastweixin.message.req.LocationReqMsg;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenww@kingtopinfo.com(邮箱)
 * @date 16/4/12
 * @since v1.0
 */
@Controller
@RequestMapping(value = "/weixin")
public class WeixinController extends WeixinControllerSupport{

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private static final String TOKEN = "weixin";
    //1.案件类别
    private String[] strArr = {"1","2","3","4","5"};




    /**
     * 子类提供token用于绑定微信公众平台
     *
     * @return token值
     */
    @Override
    protected String getToken() {
        return TOKEN;
    }


    /**
     * @param
     * @return
     */
    @Override
    protected BaseMsg handleTextMsg(TextReqMsg msg) {
        String textMsg = msg.getContent();

        logger.info("后台接收的信息是:"+ msg);

        return new TextMsg(textMsg);
    }



    /**
     * 处理位置信息
     * @param msg 封装好的输入消息
     * @return
     */
    @Override
    protected BaseMsg handleLocationMsg(LocationReqMsg msg) {
        double xx = msg.getLocationX();
        double yy = msg.getLocationY();
        return new TextMsg("location");
    }


    @Override
    protected BaseMsg handleImageMsg(ImageReqMsg msg) {
        String imgUrl = msg.getPicUrl();
        System.out.println(imgUrl);
        return new TextMsg("image");
    }

}
