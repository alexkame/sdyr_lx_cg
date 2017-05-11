/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mzb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mzb.entity.MzbGongyct;
import com.thinkgem.jeesite.modules.mzb.dao.MzbGongyctDao;

/**
 * 公益创投的表Service
 * @author 陈威伟
 * @version 2016-11-24
 */
@Service
@Transactional(readOnly = true)
public class MzbGongyctService extends CrudService<MzbGongyctDao, MzbGongyct> {

	public MzbGongyct get(String id) {
		return super.get(id);
	}
	
	public List<MzbGongyct> findList(MzbGongyct mzbGongyct) {
		return super.findList(mzbGongyct);
	}
	
	public Page<MzbGongyct> findPage(Page<MzbGongyct> page, MzbGongyct mzbGongyct) {
		return super.findPage(page, mzbGongyct);
	}
	
	@Transactional(readOnly = false)
	public void save(MzbGongyct mzbGongyct) {
		super.save(mzbGongyct);
	}
	
	@Transactional(readOnly = false)
	public void delete(MzbGongyct mzbGongyct) {
		super.delete(mzbGongyct);
	}
	
}