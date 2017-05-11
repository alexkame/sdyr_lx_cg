package com.thinkgem.jeesite.weixin.utils;

import com.github.sd4324530.fastweixin.api.OauthAPI;
import com.github.sd4324530.fastweixin.api.TemplateMsgAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.TemplateMsg;
import com.github.sd4324530.fastweixin.api.entity.TemplateParam;
import com.github.sd4324530.fastweixin.api.enums.OauthScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class NoticeTemplateMsgUtil {
    //加载微信的配置文件
    @Value("#{WEIXIN['appid']}")
    private  String appid;

    @Value("#{WEIXIN['secret']}")
    private String secret;

    @Value("#{WEIXIN['notice.model.id']}")
    private String modelId;

    @Value("#{WEIXIN['notice.top.color']}")
    private String topColor;

    @Value("#{WEIXIN['notice.param.color']}")
    private String color;

    @Value("#{WEIXIN['notice.url']}")
    private String url;


    /**
     * 带两个keyword的模板
     * @param openid 用户唯一表示
     * @param first
     * @param keyword1
     * @param keyword2
     * @param remark
     */
    public  void send2Word(String openid,String first,String keyword1,String keyword2,String remark){
        ApiConfig apiConfig = new ApiConfig(appid, secret);
        TemplateMsgAPI templateMsgAPI = new TemplateMsgAPI(apiConfig);
        TemplateMsg templateMsg = new TemplateMsg();
        templateMsg.setTouser(openid);
        templateMsg.setTemplateId(modelId);
        templateMsg.setTopcolor(topColor);

        //组装模板
        Map<String, TemplateParam> templateMsgMap = new HashMap<String, TemplateParam>();
        TemplateParam templateParam = null;
        String[] contents = new String[]{first,keyword1,keyword2,remark};
        String[] names = new String[]{"first", "keyword1", "keyword2", "remark"};
        for (int i = 0; i < 4; i++) {
            templateParam = new TemplateParam();

            templateParam.setColor(color);
            templateParam.setValue(contents[i]);
            templateMsgMap.put(names[i], templateParam);
        }

        templateMsg.setData(templateMsgMap);
        templateMsg.setUrl(url);

        //组装网页授权的地址
        OauthAPI oauthAPI = new OauthAPI(apiConfig);
        String oauthUrl =  oauthAPI.getOauthPageUrl(url, OauthScope.SNSAPI_BASE, null);
        templateMsg.setUrl(oauthUrl);

        //发送模板
        templateMsgAPI.send(templateMsg);
    }

    /**
     * 带三个keyword网页授权模板
     * @param openid 用户唯一表示
     * @param first
     * @param keyword1
     * @param keyword2
     * @param remark
     */
    public  void send3Word(String openid,String first,String keyword1,String keyword2,String  keyword3,String remark){
        ApiConfig apiConfig = new ApiConfig(appid, secret);
        TemplateMsgAPI templateMsgAPI = new TemplateMsgAPI(apiConfig);
        TemplateMsg templateMsg = new TemplateMsg();
        templateMsg.setTouser(openid);
        templateMsg.setTemplateId(modelId);
        templateMsg.setTopcolor(topColor);

        //组装模板
        Map<String, TemplateParam> templateMsgMap = new HashMap<String, TemplateParam>();
        TemplateParam templateParam = null;
        String[] contents = new String[]{first,keyword1,keyword2,keyword3,remark};
        String[] names = new String[]{"first", "keyword1", "keyword2", "keyword3","remark"};
        for (int i = 0; i < 5; i++) {
            templateParam = new TemplateParam();
            templateParam.setColor(color);
            templateParam.setValue(contents[i]);
            templateMsgMap.put(names[i], templateParam);
        }

        templateMsg.setData(templateMsgMap);
        //组装网页授权的地址
        OauthAPI oauthAPI = new OauthAPI(apiConfig);
        String oauthUrl =  oauthAPI.getOauthPageUrl(url, OauthScope.SNSAPI_BASE, null);
        System.out.println(oauthUrl);
        templateMsg.setUrl(oauthUrl);

        //发送模板
        templateMsgAPI.send(templateMsg);
    }


}
