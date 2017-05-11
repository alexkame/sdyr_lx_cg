/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mzb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mzb.entity.MzbYearcheck;
import com.thinkgem.jeesite.modules.mzb.dao.MzbYearcheckDao;

/**
 * 用于提交年检申报的信息Service
 * @author chenww
 * @version 2016-09-13
 */
@Service
@Transactional(readOnly = true)
public class MzbYearcheckService extends CrudService<MzbYearcheckDao, MzbYearcheck> {

	public MzbYearcheck get(String id) {
		return super.get(id);
	}
	
	public List<MzbYearcheck> findList(MzbYearcheck mzbYearcheck) {
		return super.findList(mzbYearcheck);
	}
	
	public Page<MzbYearcheck> findPage(Page<MzbYearcheck> page, MzbYearcheck mzbYearcheck) {
		return super.findPage(page, mzbYearcheck);
	}
	
	@Transactional(readOnly = false)
	public void save(MzbYearcheck mzbYearcheck) {
		super.save(mzbYearcheck);
	}
	
	@Transactional(readOnly = false)
	public void delete(MzbYearcheck mzbYearcheck) {
		super.delete(mzbYearcheck);
	}
	
}