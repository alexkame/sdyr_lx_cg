package com.thinkgem.jeesite.weixin.utils;

import com.github.sd4324530.fastweixin.api.OauthAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.response.OauthGetTokenResponse;
import com.thinkgem.jeesite.common.utils.PropertiesLoader;

/**
 * Created by MacBook on 16/8/29.
 */
public class OauthUtil {

    /**
     * 获取用户的openid
     * @param code
     * @return
     */

    public String getOpenid(String code) {
        //获取配置文件
        PropertiesLoader propertiesLoader = new PropertiesLoader("classpath:weixin.properties");
        //根据code取当前用户的openid
        ApiConfig apiConfig = new ApiConfig(propertiesLoader.getProperty("appid"), propertiesLoader.getProperty("secret"));
        OauthAPI oauthAPI = new OauthAPI(apiConfig);
        OauthGetTokenResponse oauthGetTokenResponse = oauthAPI.getToken(code);
        return oauthGetTokenResponse != null ? oauthGetTokenResponse.getOpenid() : "";
    }
}
