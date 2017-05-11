/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 简单的一对多示例Entity
 * @author cww
 * @version 2016-05-31
 */
public class TestTableMain extends DataEntity<TestTableMain> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 归属用户
	private Office office;		// 归属部门
	private Area area;		// 归属区域
	private String name;		// 名称
	private String sex;		// 性别（字典类型：sex）
	private Date inDate;		// 加入日期
	private List<TestTableChild> testTableChildList = Lists.newArrayList();		// 子表列表
	private List<TestTableChild2> testTableChild2List = Lists.newArrayList();		// 子表列表
	
	public TestTableMain() {
		super();
	}

	public TestTableMain(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1, message="性别（字典类型：sex）长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	
	public List<TestTableChild> getTestTableChildList() {
		return testTableChildList;
	}

	public void setTestTableChildList(List<TestTableChild> testTableChildList) {
		this.testTableChildList = testTableChildList;
	}
	public List<TestTableChild2> getTestTableChild2List() {
		return testTableChild2List;
	}

	public void setTestTableChild2List(List<TestTableChild2> testTableChild2List) {
		this.testTableChild2List = testTableChild2List;
	}
}