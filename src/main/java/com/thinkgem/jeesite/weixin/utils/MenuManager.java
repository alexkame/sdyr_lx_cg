package com.thinkgem.jeesite.weixin.utils;

import com.github.sd4324530.fastweixin.api.MenuAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.Menu;
import com.github.sd4324530.fastweixin.api.entity.MenuButton;
import com.github.sd4324530.fastweixin.api.enums.MenuType;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinButtonMain;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinButtonSub;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MacBook on 16/6/3.
 */
public class MenuManager  {


    //    public void createMenu(){
//        //3-1.生成菜单实体
//        Menu menu = new Menu();
//
//        //3-1-1.设置具体菜单
//        menu.setButton(assembleMenu());
//
//        //1.通过.properties文件获取 appid 和 secret
//        String appid = "wx3fa2ab4ba50ce421";
//        String secret = "c12ac0b9765fc81f36c059166e7215ff";
//
//        //2.初始化MenuAPI
//        MenuAPI menuAPI = new MenuAPI(new ApiConfig(appid,secret));
//
//        //3.执行生成菜单
//
//        ResultType  resultType = menuAPI.createMenu(menu);
//
//        System.out.println(resultType.getDescription());
//    }
//
//    public List<MenuButton> assembleMenu(){
//        ArrayList<MenuButton> menuButtonList = new ArrayList<MenuButton>();
//        //主菜单一
//        MenuButton mainButton1 = new MenuButton();
//
//        List<MenuButton> menuButtonList1 = new ArrayList<MenuButton>();
//
//        //子菜单11
//        MenuButton button1 = new MenuButton();
//        button1.setName("11");
//        button1.setKey("11");
//        button1.setType(MenuType.CLICK);
//
//        //子菜单12
//        MenuButton button2 = new MenuButton();
//        button2.setName("11");
//        button2.setType(MenuType.VIEW);
//        button2.setUrl("http://www.baidu.com");
//
//        menuButtonList1.add(button1);
//        menuButtonList1.add(button2);
//
//        mainButton1.setSubButton(menuButtonList1);
//        mainButton1.setName("主菜单1");
//
//        //主菜单二
//        MenuButton mainButton2 = new MenuButton();
//        mainButton2.setName("主菜单2");
//        mainButton2.setType(MenuType.VIEW);
//        mainButton2.setUrl("http://www.baidu.com");
//        //主菜单三
//        MenuButton mainButton3 = new MenuButton();
//        mainButton3.setName("主菜单3");
//        mainButton3.setType(MenuType.VIEW);
//        mainButton3.setUrl("http://www.baidu.com");
//
//        menuButtonList.add(mainButton1);
//        menuButtonList.add(mainButton2);
//        menuButtonList.add(mainButton3);
//        return menuButtonList;
//    }
    
    public static List<MenuButton> assembleMenu(List weixinButtonMainList){
        //1.创建返回的集合
        ArrayList<MenuButton> menuButtonList = new ArrayList<MenuButton>();

        //设置子主菜单临时存放实体
        MenuButton mainButton = null;
        MenuButton subButton  = null;
        //2.(给集合填充值)解析list,将其给对应的button设值
        for (Object buttonMain : weixinButtonMainList) {
            WeixinButtonMain weixinMain = (WeixinButtonMain)buttonMain;
            mainButton = new MenuButton();
            mainButton.setName(weixinMain.getName());
            //2.1 有子菜单的给子菜单填充
            List weixinSubButton = weixinMain.getWeixinButtonSubList();

            List weixinSubButton1 = new ArrayList();

                if(weixinSubButton.size() != 0){
                    for (Object o : weixinSubButton) {
                        WeixinButtonSub weixinButtonSub = (WeixinButtonSub) o;
                        subButton = new MenuButton();
                        subButton.setName(weixinButtonSub.getName());
                        if("view".equals(weixinButtonSub.getType())){
                            subButton.setUrl(weixinButtonSub.getUrl());
                            subButton.setType(MenuType.VIEW);
                        }else if ("click".equals(weixinButtonSub.getType())){
                            subButton.setKey(weixinButtonSub.getKey());
                            subButton.setType(MenuType.CLICK);
                        }
                        weixinSubButton1.add(subButton);
                    }
                    mainButton.setSubButton(weixinSubButton1);
                }else{
                    if("view".equals(weixinMain.getType())){
                        mainButton.setUrl(weixinMain.getUrl());
                        mainButton.setType(MenuType.VIEW);
                    }else if ("click".equals(weixinMain.getType())){
                        mainButton.setKey(weixinMain.getKey());
                        mainButton.setType(MenuType.CLICK);
                    }
                }

            //2.2 给主菜单填充值
            menuButtonList.add(mainButton);
        }
        //2.3 返回集合
        return menuButtonList;



//        //子菜单11
//        MenuButton button1 = new MenuButton();
//        button1.setName("11");
//        button1.setKey("11");
//        button1.setType(MenuType.CLICK);
//
//        //子菜单12
//        MenuButton button2 = new MenuButton();
//        button2.setName("11");
//        button2.setType(MenuType.VIEW);
//        button2.setUrl("http://www.baidu.com");
//
//        menuButtonList1.add(button1);
//        menuButtonList1.add(button2);
//
//        mainButton1.setSubButton(menuButtonList1);
//        mainButton1.setName("主菜单1");
//
//        //主菜单二
//        MenuButton mainButton2 = new MenuButton();
//        mainButton2.setName("主菜单2");
//        mainButton2.setType(MenuType.VIEW);
//        mainButton2.setUrl("http://www.baidu.com");
//        //主菜单三
//        MenuButton mainButton3 = new MenuButton();
//        mainButton3.setName("主菜单3");
//        mainButton3.setType(MenuType.VIEW);
//        mainButton3.setUrl("http://www.baidu.com");
//
//        menuButtonList.add(mainButton1);
//        menuButtonList.add(mainButton2);
//        menuButtonList.add(mainButton3);
//        //3.返回集合
//        return menuButtonList;
    }

    public static String createMenu(List weixinButtonMainList) {
        ResultType resultType = null;

        //3-1.生成菜单实体
        Menu menu = new Menu();

        //3-1-1.设置具体菜单
        menu.setButton(assembleMenu(weixinButtonMainList));

        //2.初始化MenuAPI
        String appid = "wx3fa2ab4ba50ce421";
        String secret = "c12ac0b9765fc81f36c059166e7215ff";
        MenuAPI menuAPI = new MenuAPI(new ApiConfig(appid, secret));

        //3.执行生成菜单
        resultType = menuAPI.createMenu(menu);

        return resultType.getDescription();
    }
}

