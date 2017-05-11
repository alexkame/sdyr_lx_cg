/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.entity;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 微信菜单类Entity
 * @author cww
 * @version 2016-06-08
 */
public class WeixinButtonMain extends DataEntity<WeixinButtonMain> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String type;		// 类型
	private String key;		// 键值
	private String url;		// 跳转路径
	private String mediaId;		// 媒体id
	private List<WeixinButtonSub> weixinButtonSubList = Lists.newArrayList();		// 子表列表
	
	public WeixinButtonMain() {
		super();
	}

	public WeixinButtonMain(String id){
		super(id);
	}

	@Length(min=1, max=20, message="名称长度必须介于 1 和 20 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=20, message="类型长度必须介于 0 和 20 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=20, message="键值长度必须介于 0 和 20 之间")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	@Length(min=0, max=2000, message="跳转路径长度必须介于 0 和 200 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=200, message="媒体id长度必须介于 0 和 200 之间")
	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	public List<WeixinButtonSub> getWeixinButtonSubList() {
		return weixinButtonSubList;
	}

	public void setWeixinButtonSubList(List<WeixinButtonSub> weixinButtonSubList) {
		this.weixinButtonSubList = weixinButtonSubList;
	}
}