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
public class TestTableChild2 extends DataEntity<TestTableChild2> {
	
	private static final long serialVersionUID = 1L;
	private TestTableMain testTableMainId;		// 编号 父类
	private String name;		// name
	
	public TestTableChild2() {
		super();
	}

	public TestTableChild2(String id){
		super(id);
	}

	public TestTableChild2(TestTableMain testTableMainId){
		this.testTableMainId = testTableMainId;
	}

	@Length(min=1, max=64, message="编号长度必须介于 1 和 64 之间")
	public TestTableMain getTestTableMainId() {
		return testTableMainId;
	}

	public void setTestTableMainId(TestTableMain testTableMainId) {
		this.testTableMainId = testTableMainId;
	}
	
	@Length(min=0, max=64, message="name长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}