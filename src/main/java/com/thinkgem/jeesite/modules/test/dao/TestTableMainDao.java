/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.test.entity.TestTableMain;

/**
 * 简单的一对多示例DAO接口
 * @author cww
 * @version 2016-05-31
 */
@MyBatisDao
public interface TestTableMainDao extends CrudDao<TestTableMain> {
	
}