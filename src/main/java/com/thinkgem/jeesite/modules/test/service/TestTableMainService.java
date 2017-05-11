/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.test.entity.TestTableMain;
import com.thinkgem.jeesite.modules.test.dao.TestTableMainDao;
import com.thinkgem.jeesite.modules.test.entity.TestTableChild;
import com.thinkgem.jeesite.modules.test.dao.TestTableChildDao;
import com.thinkgem.jeesite.modules.test.entity.TestTableChild2;
import com.thinkgem.jeesite.modules.test.dao.TestTableChild2Dao;

/**
 * 简单的一对多示例Service
 * @author cww
 * @version 2016-05-31
 */
@Service
@Transactional(readOnly = true)
public class TestTableMainService extends CrudService<TestTableMainDao, TestTableMain> {

	@Autowired
	private TestTableChildDao testTableChildDao;
	@Autowired
	private TestTableChild2Dao testTableChild2Dao;
	
	public TestTableMain get(String id) {
		TestTableMain testTableMain = super.get(id);
		testTableMain.setTestTableChildList(testTableChildDao.findList(new TestTableChild(testTableMain)));
		testTableMain.setTestTableChild2List(testTableChild2Dao.findList(new TestTableChild2(testTableMain)));
		return testTableMain;
	}
	
	public List<TestTableMain> findList(TestTableMain testTableMain) {
		return super.findList(testTableMain);
	}
	
	public Page<TestTableMain> findPage(Page<TestTableMain> page, TestTableMain testTableMain) {
		return super.findPage(page, testTableMain);
	}
	
	@Transactional(readOnly = false)
	public void save(TestTableMain testTableMain) {
		super.save(testTableMain);
		for (TestTableChild testTableChild : testTableMain.getTestTableChildList()){
			if (testTableChild.getId() == null){
				continue;
			}
			if (TestTableChild.DEL_FLAG_NORMAL.equals(testTableChild.getDelFlag())){
				if (StringUtils.isBlank(testTableChild.getId())){
					testTableChild.setTestDataMainId(testTableMain);
					testTableChild.preInsert();
					testTableChildDao.insert(testTableChild);
				}else{
					testTableChild.preUpdate();
					testTableChildDao.update(testTableChild);
				}
			}else{
				testTableChildDao.delete(testTableChild);
			}
		}
		for (TestTableChild2 testTableChild2 : testTableMain.getTestTableChild2List()){
			if (testTableChild2.getId() == null){
				continue;
			}
			if (TestTableChild2.DEL_FLAG_NORMAL.equals(testTableChild2.getDelFlag())){
				if (StringUtils.isBlank(testTableChild2.getId())){
					testTableChild2.setTestTableMainId(testTableMain);
					testTableChild2.preInsert();
					testTableChild2Dao.insert(testTableChild2);
				}else{
					testTableChild2.preUpdate();
					testTableChild2Dao.update(testTableChild2);
				}
			}else{
				testTableChild2Dao.delete(testTableChild2);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(TestTableMain testTableMain) {
		super.delete(testTableMain);
		testTableChildDao.delete(new TestTableChild(testTableMain));
		testTableChild2Dao.delete(new TestTableChild2(testTableMain));
	}
	
}