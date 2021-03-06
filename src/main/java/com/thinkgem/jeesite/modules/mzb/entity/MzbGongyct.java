/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mzb.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 公益创投的表Entity
 * @author 陈威伟
 * @version 2016-11-24
 */
public class MzbGongyct extends DataEntity<MzbGongyct> {
	
	private static final long serialVersionUID = 1L;
	private String status;		// 状态
	private String title;		// 标题
	private Double cash;		// 申请金额
	private String summary;		// 申请内容
	private String files;		// 附件
	private String test2;		// test2
	private String test3;		// test3
	private String test4;		// test4
	private String test5;		// test5
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	
	public MzbGongyct() {
		super();
	}

	public MzbGongyct(String id){
		super(id);
	}

	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=255, message="标题长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Double getCash() {
		return cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}
	
	@Length(min=0, max=255, message="申请内容长度必须介于 0 和 255 之间")
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	@Length(min=0, max=2000, message="附件长度必须介于 0 和 2000 之间")
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
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
	
	@Length(min=0, max=255, message="test5长度必须介于 0 和 255 之间")
	public String getTest5() {
		return test5;
	}

	public void setTest5(String test5) {
		this.test5 = test5;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
}