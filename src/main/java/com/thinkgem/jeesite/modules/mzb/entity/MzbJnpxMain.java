/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mzb.entity;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 技能培训Entity
 * @author 陈威伟
 * @version 2016-11-28
 */
public class MzbJnpxMain extends DataEntity<MzbJnpxMain> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String content;		// 内容
	private String files;		// 附件
	private String status;		// 类别
	private String test2;		// 分数
	private String test3;		// test3
	private String test4;		// test4
	private String test5;		// test5
	private List<MzbJnpxTest> mzbJnpxTestList = Lists.newArrayList();		// 子表列表
	private List<MzbJnpxUser> mzbJnpxUserList = Lists.newArrayList();		// 子表列表
	
	public MzbJnpxMain() {
		super();
	}

	public MzbJnpxMain(String id){
		super(id);
	}

	@Length(min=0, max=255, message="标题长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=255, message="附件长度必须介于 0 和 255 之间")
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}
	
	@Length(min=0, max=100, message="类别长度必须介于 0 和 100 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	public List<MzbJnpxTest> getMzbJnpxTestList() {
		return mzbJnpxTestList;
	}

	public void setMzbJnpxTestList(List<MzbJnpxTest> mzbJnpxTestList) {
		this.mzbJnpxTestList = mzbJnpxTestList;
	}
	public List<MzbJnpxUser> getMzbJnpxUserList() {
		return mzbJnpxUserList;
	}

	public void setMzbJnpxUserList(List<MzbJnpxUser> mzbJnpxUserList) {
		this.mzbJnpxUserList = mzbJnpxUserList;
	}

	/**
	 * 获取通知发送记录用户ID
	 * @return
	 */
	public String getMzbJnpxUserIds() {
		return Collections3.extractToString(mzbJnpxUserList, "user.id", ",") ;
	}


	/**
	 * 设置通知发送记录用户ID
	 * @return
	 */
	public void setMzbJnpxUserIds(String mzbJnpxUser) {
		this.mzbJnpxUserList = Lists.newArrayList();
		for (String id : StringUtils.split(mzbJnpxUser, ",")){
			MzbJnpxUser entity = new MzbJnpxUser();
			entity.setId(IdGen.uuid());
			entity.setMzbJnpxMainId(this);
			entity.setUser(new User(id));
			entity.setIsDone("0");
			this.mzbJnpxUserList.add(entity);
		}
	}

	/**
	 * 获取通知发送记录用户Name
	 * @return
	 */
	public String getMzbJnpxUserNames() {
		return Collections3.extractToString(mzbJnpxUserList, "user.name", ",") ;
	}
}