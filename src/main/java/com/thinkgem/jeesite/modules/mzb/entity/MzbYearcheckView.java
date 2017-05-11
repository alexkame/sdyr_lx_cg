/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mzb.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 年报公示预警Entity
 * @author 陈威伟
 * @version 2016-12-04
 */
public class MzbYearcheckView extends DataEntity<MzbYearcheckView> {
	
	private static final long serialVersionUID = 1L;
	private String offiName;		// 类别
	private String userName;		// 组织名称
	private String mobile;		// 手机
	private String openid;		// 用户微信标识
	private User user;		// 用户id
	private String offceId;		// 部门id
	
	public MzbYearcheckView() {
		super();
	}

	public MzbYearcheckView(String id){
		super(id);
	}

	@Length(min=0, max=100, message="类别长度必须介于 0 和 100 之间")
	public String getOffiName() {
		return offiName;
	}

	public void setOffiName(String offiName) {
		this.offiName = offiName;
	}
	
	@Length(min=1, max=100, message="组织名称长度必须介于 1 和 100 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=200, message="手机长度必须介于 0 和 200 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=1000, message="用户微信标识长度必须介于 0 和 1000 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@NotNull(message="用户id不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=64, message="部门id长度必须介于 0 和 64 之间")
	public String getOffceId() {
		return offceId;
	}

	public void setOffceId(String offceId) {
		this.offceId = offceId;
	}
	
}