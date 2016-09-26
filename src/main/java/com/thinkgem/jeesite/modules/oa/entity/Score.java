/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 绩效自我评分Entity
 * @author LLQ
 * @version 2016-09-22
 */
public class Score extends DataEntity<Score> {
	
	private static final long serialVersionUID = 1L;
	private String kpiOptionsId;		// 关联绩效类目表
	private String selfScore;		// 自评
	private String chargeScore;		// 直接上司评分
	private String reviewScore;		// 复核得分
	
	public Score() {
		super();
	}

	public Score(String id){
		super(id);
	}

	@Length(min=1, max=100, message="关联绩效类目表长度必须介于 1 和 100 之间")
	public String getKpiOptionsId() {
		return kpiOptionsId;
	}

	public void setKpiOptionsId(String kpiOptionsId) {
		this.kpiOptionsId = kpiOptionsId;
	}
	
	@Length(min=0, max=4, message="自评长度必须介于 0 和 4 之间")
	public String getSelfScore() {
		return selfScore;
	}

	public void setSelfScore(String selfScore) {
		this.selfScore = selfScore;
	}
	
	@Length(min=0, max=4, message="直接上司评分长度必须介于 0 和 4 之间")
	public String getChargeScore() {
		return chargeScore;
	}

	public void setChargeScore(String chargeScore) {
		this.chargeScore = chargeScore;
	}
	
	@Length(min=0, max=4, message="复核得分长度必须介于 0 和 4 之间")
	public String getReviewScore() {
		return reviewScore;
	}

	public void setReviewScore(String reviewScore) {
		this.reviewScore = reviewScore;
	}
	
}