/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.test.entity.TestNewTable;
import com.thinkgem.jeesite.modules.test.dao.TestNewTableDao;

/**
 * 我的测试Service
 * @author cww
 * @version 2016-05-26
 */
@Service
@Transactional(readOnly = true)
public class TestNewTableService extends CrudService<TestNewTableDao, TestNewTable> {

	public TestNewTable get(String id) {
		return super.get(id);
	}
	
	public List<TestNewTable> findList(TestNewTable testNewTable) {
		return super.findList(testNewTable);
	}
	
	public Page<TestNewTable> findPage(Page<TestNewTable> page, TestNewTable testNewTable) {
		return super.findPage(page, testNewTable);
	}
	
	@Transactional(readOnly = false)
	public void save(TestNewTable testNewTable) {
		super.save(testNewTable);
	}
	
	@Transactional(readOnly = false)
	public void delete(TestNewTable testNewTable) {
		super.delete(testNewTable);
	}
	
}