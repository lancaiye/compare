/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户绩效模板映射Entity
 * @author 吴小平
 * @version 2016-09-08
 */
public class UserTemplate extends DataEntity<UserTemplate> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 用户编号
	private String templateId;		// 模板编号
	
	public UserTemplate() {
		super();
	}

	public UserTemplate(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=1, max=64, message="模板编号长度必须介于 1 和 64 之间")
	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	
}