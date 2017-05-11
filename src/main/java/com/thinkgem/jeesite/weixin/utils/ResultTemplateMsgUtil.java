package com.thinkgem.jeesite.weixin.utils;

import com.github.sd4324530.fastweixin.api.TemplateMsgAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.TemplateMsg;
import com.github.sd4324530.fastweixin.api.entity.TemplateParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 结果通知模板工具
 */
@Component
public class ResultTemplateMsgUtil {
    //加载微信的配置文件
    @Value("#{WEIXIN['appid']}")
    private  String appid;

    @Value("#{WEIXIN['secret']}")
    private String secret;

    @Value("#{WEIXIN['result.model.id']}")
    private String modelId;

    @Value("#{WEIXIN['result.top.color']}")
    private String topColor;

    @Value("#{WEIXIN['result.param.color']}")
    private String color;

    @Value("#{WEIXIN['result.url']}")
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

        //发送模板
        templateMsgAPI.send(templateMsg);
    }

    /**
     * 带两个keyword的模板
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
        templateMsg.setUrl(url);

        //发送模板
        templateMsgAPI.send(templateMsg);
    }


}
