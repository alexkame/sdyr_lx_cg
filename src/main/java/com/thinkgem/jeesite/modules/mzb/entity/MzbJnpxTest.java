/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mzb.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 技能培训Entity
 * @author 陈威伟
 * @version 2016-11-28
 */
public class MzbJnpxTest extends DataEntity<MzbJnpxTest> {
	
	private static final long serialVersionUID = 1L;
	private MzbJnpxMain mzbJnpxMain;		// 测试题 父类
	private String question;		// 问题
	private String answer;		// 答案
	private String status;		// 状态
	private String test1;		// 测试一
	private String test2;		// 测试二
	private String test3;		// 测试三
	private String test4;		// 测试四
	
	public MzbJnpxTest() {
		super();
	}

	public MzbJnpxTest(String id){
		super(id);
	}

	public MzbJnpxTest(MzbJnpxMain mzbJnpxMain){
		this.mzbJnpxMain = mzbJnpxMain;
	}

	@Length(min=0, max=255, message="测试题长度必须介于 0 和 255 之间")
	public MzbJnpxMain getMzbJnpxMain() {
		return mzbJnpxMain;
	}

	public void setMzbJnpxMain(MzbJnpxMain mzbJnpxMain) {
		this.mzbJnpxMain = mzbJnpxMain;
	}
	
	@Length(min=0, max=255, message="问题长度必须介于 0 和 255 之间")
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	@Length(min=0, max=1, message="答案长度必须介于 0 和 1 之间")
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=255, message="测试一长度必须介于 0 和 255 之间")
	public String getTest1() {
		return test1;
	}

	public void setTest1(String test1) {
		this.test1 = test1;
	}
	
	@Length(min=0, max=255, message="测试二长度必须介于 0 和 255 之间")
	public String getTest2() {
		return test2;
	}

	public void setTest2(String test2) {
		this.test2 = test2;
	}
	
	@Length(min=0, max=255, message="测试三长度必须介于 0 和 255 之间")
	public String getTest3() {
		return test3;
	}

	public void setTest3(String test3) {
		this.test3 = test3;
	}
	
	@Length(min=0, max=255, message="测试四长度必须介于 0 和 255 之间")
	public String getTest4() {
		return test4;
	}

	public void setTest4(String test4) {
		this.test4 = test4;
	}
	
}