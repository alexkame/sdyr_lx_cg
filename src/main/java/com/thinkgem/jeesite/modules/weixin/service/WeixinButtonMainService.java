/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.weixin.dao.WeixinButtonMainDao;
import com.thinkgem.jeesite.modules.weixin.dao.WeixinButtonSubDao;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinButtonMain;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinButtonSub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信菜单类Service
 * @author cww
 * @version 2016-06-08
 */
@Service
@Transactional(readOnly = true)
public class WeixinButtonMainService extends CrudService<WeixinButtonMainDao, WeixinButtonMain> {

	@Autowired
	private WeixinButtonSubDao weixinButtonSubDao;
	
	public WeixinButtonMain get(String id) {
		WeixinButtonMain weixinButtonMain = super.get(id);
		weixinButtonMain.setWeixinButtonSubList(weixinButtonSubDao.findList(new WeixinButtonSub(weixinButtonMain)));
		return weixinButtonMain;
	}
	
	public List<WeixinButtonMain> findList(WeixinButtonMain weixinButtonMain) {
		return super.findList(weixinButtonMain);
	}


//	只有通过getid()才能在主表中填充子表数据
	public List<WeixinButtonMain> findSubList(WeixinButtonMain weixinButtonMain) {
		List<WeixinButtonMain> list  =  super.findList(weixinButtonMain);
		List<WeixinButtonMain> list1 = new ArrayList<WeixinButtonMain>();
		WeixinButtonMain weixinButtonMain1  = null;
		for (WeixinButtonMain buttonMain : list) {
			weixinButtonMain1 = get(buttonMain.getId());
			list1.add(weixinButtonMain1);
		}

		return list1;
	}

	public Page<WeixinButtonMain> findPage(Page<WeixinButtonMain> page, WeixinButtonMain weixinButtonMain) {
		return super.findPage(page, weixinButtonMain);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinButtonMain weixinButtonMain) {
		super.save(weixinButtonMain);
		for (WeixinButtonSub weixinButtonSub : weixinButtonMain.getWeixinButtonSubList()){
			if (weixinButtonSub.getId() == null){
				continue;
			}
			if (WeixinButtonSub.DEL_FLAG_NORMAL.equals(weixinButtonSub.getDelFlag())){
				if (StringUtils.isBlank(weixinButtonSub.getId())){
					weixinButtonSub.setWeixinButtonMainId(weixinButtonMain);
					weixinButtonSub.preInsert();
					weixinButtonSubDao.insert(weixinButtonSub);
				}else{
					weixinButtonSub.preUpdate();
					weixinButtonSubDao.update(weixinButtonSub);
				}
			}else{
				weixinButtonSubDao.delete(weixinButtonSub);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinButtonMain weixinButtonMain) {
		super.delete(weixinButtonMain);
		weixinButtonSubDao.delete(new WeixinButtonSub(weixinButtonMain));
	}
	
}