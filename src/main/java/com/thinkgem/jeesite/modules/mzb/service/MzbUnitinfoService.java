/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mzb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mzb.entity.MzbUnitinfo;
import com.thinkgem.jeesite.modules.mzb.dao.MzbUnitinfoDao;

/**
 * 社会组织基本信息Service
 * @author chenww
 * @version 2016-11-14
 */
@Service
@Transactional(readOnly = true)
public class MzbUnitinfoService extends CrudService<MzbUnitinfoDao, MzbUnitinfo> {

	public MzbUnitinfo get(String id) {
		return super.get(id);
	}
	
	public List<MzbUnitinfo> findList(MzbUnitinfo mzbUnitinfo) {
		return super.findList(mzbUnitinfo);
	}
	
	public Page<MzbUnitinfo> findPage(Page<MzbUnitinfo> page, MzbUnitinfo mzbUnitinfo) {
		return super.findPage(page, mzbUnitinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(MzbUnitinfo mzbUnitinfo) {
		super.save(mzbUnitinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(MzbUnitinfo mzbUnitinfo) {
		super.delete(mzbUnitinfo);
	}
	
}