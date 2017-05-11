/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mzb.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 社会组织基本信息Entity
 * @author chenww
 * @version 2016-11-14
 */
public class MzbUnitinfo extends DataEntity<MzbUnitinfo> {
	
	private static final long serialVersionUID = 1L;
	private String danwMc;		// 单位名称
	private String dengjzh;		// 登记证号
	private Date dengjRq;		// 登记日期
	private String kaibZj;		// 开办资金
	private String zhus;		// 住所
	private String danwDh;		// 单位电话
	private String fadDbr;		// 法定代表人
	private String yewzgDw;		// 业务主管单位
	private String zhongz;		// 宗旨
	private String yewFw;		// 业务范围
	private String tongyDm;		// 统一社会信用代码
	private String isself;		// 是否是社会组织自己
	
	public MzbUnitinfo() {
		super();
	}

	public MzbUnitinfo(String id){
		super(id);
	}

	@Length(min=1, max=255, message="单位名称长度必须介于 1 和 255 之间")
	public String getDanwMc() {
		return danwMc;
	}

	public void setDanwMc(String danwMc) {
		this.danwMc = danwMc;
	}
	
	@Length(min=1, max=255, message="登记证号长度必须介于 1 和 255 之间")
	public String getDengjzh() {
		return dengjzh;
	}

	public void setDengjzh(String dengjzh) {
		this.dengjzh = dengjzh;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="登记日期不能为空")
	public Date getDengjRq() {
		return dengjRq;
	}

	public void setDengjRq(Date dengjRq) {
		this.dengjRq = dengjRq;
	}
	
	public String getKaibZj() {
		return kaibZj;
	}

	public void setKaibZj(String kaibZj) {
		this.kaibZj = kaibZj;
	}
	
	@Length(min=1, max=255, message="住所长度必须介于 1 和 255 之间")
	public String getZhus() {
		return zhus;
	}

	public void setZhus(String zhus) {
		this.zhus = zhus;
	}
	
	@Length(min=1, max=255, message="单位电话长度必须介于 1 和 255 之间")
	public String getDanwDh() {
		return danwDh;
	}

	public void setDanwDh(String danwDh) {
		this.danwDh = danwDh;
	}
	
	@Length(min=1, max=255, message="法定代表人长度必须介于 1 和 255 之间")
	public String getFadDbr() {
		return fadDbr;
	}

	public void setFadDbr(String fadDbr) {
		this.fadDbr = fadDbr;
	}
	
	@Length(min=1, max=255, message="业务主管单位长度必须介于 1 和 255 之间")
	public String getYewzgDw() {
		return yewzgDw;
	}

	public void setYewzgDw(String yewzgDw) {
		this.yewzgDw = yewzgDw;
	}
	
	@Length(min=1, max=255, message="宗旨长度必须介于 1 和 255 之间")
	public String getZhongz() {
		return zhongz;
	}

	public void setZhongz(String zhongz) {
		this.zhongz = zhongz;
	}
	
	@Length(min=1, max=255, message="业务范围长度必须介于 1 和 255 之间")
	public String getYewFw() {
		return yewFw;
	}

	public void setYewFw(String yewFw) {
		this.yewFw = yewFw;
	}
	
	@Length(min=1, max=255, message="统一社会信用代码长度必须介于 1 和 255 之间")
	public String getTongyDm() {
		return tongyDm;
	}

	public void setTongyDm(String tongyDm) {
		this.tongyDm = tongyDm;
	}
	
	@Length(min=0, max=1, message="是否是社会组织自己长度必须介于 0 和 1 之间")
	public String getIsself() {
		return isself;
	}

	public void setIsself(String isself) {
		this.isself = isself;
	}
	
}