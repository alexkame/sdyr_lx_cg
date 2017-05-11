/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mzb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mzb.entity.MzbFucsq;
import com.thinkgem.jeesite.modules.mzb.dao.MzbFucsqDao;

/**
 * 扶持申请Service
 * @author 陈威伟
 * @version 2016-11-24
 */
@Service
@Transactional(readOnly = true)
public class MzbFucsqService extends CrudService<MzbFucsqDao, MzbFucsq> {

	public MzbFucsq get(String id) {
		return super.get(id);
	}
	
	public List<MzbFucsq> findList(MzbFucsq mzbFucsq) {
		return super.findList(mzbFucsq);
	}
	
	public Page<MzbFucsq> findPage(Page<MzbFucsq> page, MzbFucsq mzbFucsq) {
		return super.findPage(page, mzbFucsq);
	}
	
	@Transactional(readOnly = false)
	public void save(MzbFucsq mzbFucsq) {
		super.save(mzbFucsq);
	}
	
	@Transactional(readOnly = false)
	public void delete(MzbFucsq mzbFucsq) {
		super.delete(mzbFucsq);
	}
	
}