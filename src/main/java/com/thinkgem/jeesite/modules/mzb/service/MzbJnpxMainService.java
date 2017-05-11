/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mzb.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.mzb.dao.MzbJnpxMainDao;
import com.thinkgem.jeesite.modules.mzb.dao.MzbJnpxTestDao;
import com.thinkgem.jeesite.modules.mzb.dao.MzbJnpxUserDao;
import com.thinkgem.jeesite.modules.mzb.entity.MzbJnpxMain;
import com.thinkgem.jeesite.modules.mzb.entity.MzbJnpxTest;
import com.thinkgem.jeesite.modules.mzb.entity.MzbJnpxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 技能培训Service
 * @author 陈威伟
 * @version 2016-11-28
 */
@Service
@Transactional(readOnly = true)
public class MzbJnpxMainService extends CrudService<MzbJnpxMainDao, MzbJnpxMain> {

	@Autowired
	private MzbJnpxTestDao mzbJnpxTestDao;
	@Autowired
	private MzbJnpxUserDao mzbJnpxUserDao;

	public MzbJnpxTestDao getMzbJnpxTestDao() {
		return mzbJnpxTestDao;
	}

	public MzbJnpxUserDao getMzbJnpxUserDao() {
		return mzbJnpxUserDao;
	}

	public MzbJnpxMain get(String id) {
		MzbJnpxMain mzbJnpxMain = super.get(id);
		mzbJnpxMain.setMzbJnpxTestList(mzbJnpxTestDao.findList(new MzbJnpxTest(mzbJnpxMain)));
		mzbJnpxMain.setMzbJnpxUserList(mzbJnpxUserDao.findList(new MzbJnpxUser(mzbJnpxMain)));
		return mzbJnpxMain;
	}
	
	public List<MzbJnpxMain> findList(MzbJnpxMain mzbJnpxMain) {
		return super.findList(mzbJnpxMain);
	}
	
	public Page<MzbJnpxMain> findPage(Page<MzbJnpxMain> page, MzbJnpxMain mzbJnpxMain) {
		return super.findPage(page, mzbJnpxMain);
	}
	
	@Transactional(readOnly = false)
	public void save(MzbJnpxMain mzbJnpxMain) {
		super.save(mzbJnpxMain);
		for (MzbJnpxTest mzbJnpxTest : mzbJnpxMain.getMzbJnpxTestList()){
			if (mzbJnpxTest.getId() == null){
				continue;
			}
			if (MzbJnpxTest.DEL_FLAG_NORMAL.equals(mzbJnpxTest.getDelFlag())){
				if (StringUtils.isBlank(mzbJnpxTest.getId())){
					mzbJnpxTest.setMzbJnpxMain(mzbJnpxMain);
					mzbJnpxTest.preInsert();
					mzbJnpxTestDao.insert(mzbJnpxTest);
				}else{
					mzbJnpxTest.preUpdate();
					mzbJnpxTestDao.update(mzbJnpxTest);
				}
			}else{
				mzbJnpxTestDao.delete(mzbJnpxTest);
			}
		}
		mzbJnpxUserDao.deleteByMzbJnpxUserId(mzbJnpxMain.getId());
		for (MzbJnpxUser mzbJnpxUser : mzbJnpxMain.getMzbJnpxUserList()){
			if (mzbJnpxUser.getId() == null){
				continue;
			}
			if (MzbJnpxUser.DEL_FLAG_NORMAL.equals(mzbJnpxUser.getDelFlag())){
					mzbJnpxUser.setMzbJnpxMainId(mzbJnpxMain);
					mzbJnpxUser.preInsert();
					mzbJnpxUserDao.insert(mzbJnpxUser);
			}else{
				mzbJnpxUserDao.delete(mzbJnpxUser);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(MzbJnpxMain mzbJnpxMain) {
		super.delete(mzbJnpxMain);
		mzbJnpxTestDao.delete(new MzbJnpxTest(mzbJnpxMain));
		mzbJnpxUserDao.delete(new MzbJnpxUser(mzbJnpxMain));
	}

	//查询用户在该案件下是否经过培训.
	public MzbJnpxUser isDone(String mainId,String userId){
		return mzbJnpxUserDao.getByMainUserId(mainId,userId);
	}


}