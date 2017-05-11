/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.test.entity.TestNewTable;

/**
 * 我的测试DAO接口
 * @author cww
 * @version 2016-05-26
 */
@MyBatisDao
public interface TestNewTableDao extends CrudDao<TestNewTable> {
	
}