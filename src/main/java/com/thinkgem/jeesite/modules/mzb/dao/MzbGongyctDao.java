/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mzb.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mzb.entity.MzbGongyct;

/**
 * 公益创投的表DAO接口
 * @author 陈威伟
 * @version 2016-11-24
 */
@MyBatisDao
public interface MzbGongyctDao extends CrudDao<MzbGongyct> {
	
}