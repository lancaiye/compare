package com.thinkgem.jeesite.modules.oa.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 薪水
 * @author
 * @version
 */
public class PayRecord extends DataEntity<PayRecord> {
	private static final long serialVersionUID = 1L;
	private Pay pay; // 薪水信息id
	private User user; // 接收人
	private String readFlag;// 阅读标记
	private Date readDate;// 阅读时间

	public PayRecord() {
		super();
	}

	public PayRecord(String id) {
		super(id);
	}

	public PayRecord(Pay pay) {
		this.pay = pay;
	}

	public Pay getPay() {
		return pay;
	}

	public void setPay(Pay pay) {
		this.pay = pay;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Length(min = 0, max = 1, message = " 阅读标记（0：未读，1：已读）长度介于0和1之间")
	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
