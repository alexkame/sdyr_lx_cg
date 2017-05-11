/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinButtonSub;

/**
 * 微信菜单类DAO接口
 * @author cww
 * @version 2016-06-08
 */
@MyBatisDao
public interface WeixinButtonSubDao extends CrudDao<WeixinButtonSub> {
	
}