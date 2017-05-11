/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mzb.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mzb.entity.MzbUnitinfo;

/**
 * 社会组织基本信息DAO接口
 * @author chenww
 * @version 2016-11-14
 */
@MyBatisDao
public interface MzbUnitinfoDao extends CrudDao<MzbUnitinfo> {
	
}