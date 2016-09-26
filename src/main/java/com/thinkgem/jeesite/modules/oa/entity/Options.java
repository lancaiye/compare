/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 绩效管理Entity
 * @author 吴小平
 * @version 2016-09-09
 */
public class Options extends DataEntity<Options> {
	
	private static final long serialVersionUID = 1L;
	private Ktemplate ktemplate;		// 关联绩效模板表 父类
	private String name;		// 绩效项目
	private String type;		// 绩效类别
	private String definition;		// 指标定义
	private String totalScore;		// 总分
	private String scope;		// 计算方式
	
	public Options() {
		super();
	}

	public Options(String id){
		super(id);
	}

	public Options(Ktemplate ktemplate){
		this.ktemplate = ktemplate;
	}

	@Length(min=1, max=100, message="关联绩效模板表长度必须介于 1 和 100 之间")
	public Ktemplate getKpi_template() {
		return ktemplate;
	}

	public void setKpi_template(Ktemplate ktemplate) {
		this.ktemplate = ktemplate;
	}
	
	@Length(min=1, max=100, message="绩效项目长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=1, message="绩效类别长度必须介于 1 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="指标定义长度必须介于 0 和 255 之间")
	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	@Length(min=0, max=4, message="总分长度必须介于 0 和 4 之间")
	public String getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}
	
	@Length(min=0, max=255, message="计算方式长度必须介于 0 和 255 之间")
	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
}