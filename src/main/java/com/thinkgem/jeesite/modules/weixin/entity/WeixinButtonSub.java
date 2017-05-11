/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 微信菜单类Entity
 * @author cww
 * @version 2016-06-08
 */
public class WeixinButtonSub extends DataEntity<WeixinButtonSub> {
	
	private static final long serialVersionUID = 1L;
	private WeixinButtonMain weixinButtonMainId;		// 编号 父类
	private String type;		// 类型
	private String name;		// 名称
	private String key;		// 键值
	private String url;		// 跳转路径
	private String mediaId;		// 媒体id
	
	public WeixinButtonSub() {
		super();
	}

	public WeixinButtonSub(String id){
		super(id);
	}

	public WeixinButtonSub(WeixinButtonMain weixinButtonMainId){
		this.weixinButtonMainId = weixinButtonMainId;
	}

	@Length(min=1, max=64, message="编号长度必须介于 1 和 64 之间")
	public WeixinButtonMain getWeixinButtonMainId() {
		return weixinButtonMainId;
	}

	public void setWeixinButtonMainId(WeixinButtonMain weixinButtonMainId) {
		this.weixinButtonMainId = weixinButtonMainId;
	}
	
	@Length(min=0, max=20, message="类型长度必须介于 0 和 20 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=1, max=20, message="名称长度必须介于 1 和 20 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=20, message="键值长度必须介于 0 和 20 之间")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	@Length(min=0, max=200, message="跳转路径长度必须介于 0 和 200 之间")
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
	
}