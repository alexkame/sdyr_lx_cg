/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mzb.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mzb.entity.MzbYearcheck;

/**
 * 用于提交年检申报的信息DAO接口
 * @author chenww
 * @version 2016-09-13
 */
@MyBatisDao
public interface MzbYearcheckDao extends CrudDao<MzbYearcheck> {
	
}