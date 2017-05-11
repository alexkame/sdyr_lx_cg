/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 简单的一对多示例Entity
 * @author cww
 * @version 2016-05-31
 */
public class TestTableChild extends DataEntity<TestTableChild> {
	
	private static final long serialVersionUID = 1L;
	private TestTableMain testDataMainId;		// 业务主表ID 父类
	private String name;		// 名称
	
	public TestTableChild() {
		super();
	}

	public TestTableChild(String id){
		super(id);
	}

	public TestTableChild(TestTableMain testDataMainId){
		this.testDataMainId = testDataMainId;
	}

	@Length(min=0, max=64, message="业务主表ID长度必须介于 0 和 64 之间")
	public TestTableMain getTestDataMainId() {
		return testDataMainId;
	}

	public void setTestDataMainId(TestTableMain testDataMainId) {
		this.testDataMainId = testDataMainId;
	}
	
	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}