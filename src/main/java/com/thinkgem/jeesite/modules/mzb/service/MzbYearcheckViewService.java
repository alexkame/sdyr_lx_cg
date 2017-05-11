/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mzb.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mzb.dao.MzbYearcheckViewDao;
import com.thinkgem.jeesite.modules.mzb.entity.MzbYearcheckView;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 年报公示预警Service
 * @author 陈威伟
 * @version 2016-12-04
 */
@Service
@Transactional(readOnly = true)
public class MzbYearcheckViewService extends CrudService<MzbYearcheckViewDao, MzbYearcheckView> {

	public MzbYearcheckView get(String id) {
		return super.get(id);
	}
	
	public List<MzbYearcheckView> findList(MzbYearcheckView mzbYearcheckView) {
		return super.findList(mzbYearcheckView);
	}
	
	public Page<MzbYearcheckView> findPage(Page<MzbYearcheckView> page, MzbYearcheckView mzbYearcheckView) {
		return super.findPage(page, mzbYearcheckView);
	}
	
	@Transactional(readOnly = false)
	public void save(MzbYearcheckView mzbYearcheckView) {
		super.save(mzbYearcheckView);
	}
	
	@Transactional(readOnly = false)
	public void delete(MzbYearcheckView mzbYearcheckView) {
		super.delete(mzbYearcheckView);
	}
	
}