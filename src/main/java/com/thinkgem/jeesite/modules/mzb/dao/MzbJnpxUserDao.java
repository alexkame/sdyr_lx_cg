/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mzb.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mzb.entity.MzbJnpxUser;

/**
 * 技能培训DAO接口
 *
 * @author 陈威伟
 * @version 2016-11-28
 */
@MyBatisDao
public interface MzbJnpxUserDao extends CrudDao<MzbJnpxUser> {
    /**
     * 根据通知ID删除通知记录
     * @param mzbJnpxMainId 通知ID
     * @return
     */
    public int deleteByMzbJnpxUserId(String mzbJnpxMainId);

    public MzbJnpxUser getByMainUserId(String mainId,String userId);



}