/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mzb.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 技能培训Entity
 * @author 陈威伟
 * @version 2016-11-28
 */
public class MzbJnpxUser extends DataEntity<MzbJnpxUser> {
	
	private static final long serialVersionUID = 1L;
	private MzbJnpxMain mzbJnpxMainId;		// mzb_jnpx_main_id 父类
	private User user;		// user_id
	private String isDone;		// 是否完成
	private String doneDate;		// 完成时间
	private String test1;		// 分数
	private String test2;		// test2
	private String test3;		// test3
	private String test4;		// test4
	
	public MzbJnpxUser() {
		super();
	}

	public MzbJnpxUser(String id){
		super(id);
	}

	public MzbJnpxUser(MzbJnpxMain mzbJnpxMainId){
		this.mzbJnpxMainId = mzbJnpxMainId;
	}

	@Length(min=0, max=255, message="mzb_jnpx_main_id长度必须介于 0 和 255 之间")
	public MzbJnpxMain getMzbJnpxMainId() {
		return mzbJnpxMainId;
	}

	public void setMzbJnpxMainId(MzbJnpxMain mzbJnpxMainId) {
		this.mzbJnpxMainId = mzbJnpxMainId;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=1, message="是否完成长度必须介于 0 和 1 之间")
	public String getIsDone() {
		return isDone;
	}

	public void setIsDone(String isDone) {
		this.isDone = isDone;
	}
	
	@Length(min=0, max=255, message="完成时间长度必须介于 0 和 255 之间")
	public String getDoneDate() {
		return doneDate;
	}

	public void setDoneDate(String doneDate) {
		this.doneDate = doneDate;
	}
	
	@Length(min=0, max=255, message="test1长度必须介于 0 和 255 之间")
	public String getTest1() {
		return test1;
	}

	public void setTest1(String test1) {
		this.test1 = test1;
	}
	
	@Length(min=0, max=255, message="test2长度必须介于 0 和 255 之间")
	public String getTest2() {
		return test2;
	}

	public void setTest2(String test2) {
		this.test2 = test2;
	}
	
	@Length(min=0, max=255, message="test3长度必须介于 0 和 255 之间")
	public String getTest3() {
		return test3;
	}

	public void setTest3(String test3) {
		this.test3 = test3;
	}
	
	@Length(min=0, max=255, message="test4长度必须介于 0 和 255 之间")
	public String getTest4() {
		return test4;
	}

	public void setTest4(String test4) {
		this.test4 = test4;
	}
	
}