package com.thinkgem.jeesite.modules.oa.entity;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.List;

/**
 * 薪水Entity
 * 
 * @author
 * @version
 * 
 */
public class Pay extends DataEntity<Pay> {
	private static final long serialVersionUID = 1L;
	private String title; // 标题
	private String content; // 内容
	private String files; // 上传文件
	private String status; // 状态

	private String readNum; // 已读
	private String unReadNum;// 未读

	private boolean isSelf;// 是否只查询自己的通知

	private String readFlag; // 本人阅读状态

	private List<PayRecord> payRecordList = Lists.newArrayList();

	public Pay() {
		super();
	}

	public Pay(String id) {
		super(id);
	}

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

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReadNum() {
		return readNum;
	}

	public void setReadNum(String readNum) {
		this.readNum = readNum;
	}

	public String getUnReadNum() {
		return unReadNum;
	}

	public void setUnReadNum(String unReadNum) {
		this.unReadNum = unReadNum;
	}

	public boolean isSelf() {
		return isSelf;
	}

	public void setSelf(boolean isSelf) {
		this.isSelf = isSelf;
	}

	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<PayRecord> getPayRecordList() {
		return payRecordList;
	}

	public void setPayRecordList(List<PayRecord> payRecordList) {
		this.payRecordList = payRecordList;
	}

	/**
	 * 获取通知发送记录用户ID
	 * 
	 * @return
	 */
	public String getPayRecordIds() {
		return Collections3.extractToString(payRecordList, "user.id", ",");
	}

	/**
	 * 设置通知发送记录用户ID
	 * 
	 * @return
	 */
	public void setPayRecordIds(String PayRecord) {
		this.payRecordList = Lists.newArrayList();
		for (String id : StringUtils.split(PayRecord, ",")) {
			PayRecord entity = new PayRecord();
			entity.setId(IdGen.uuid());
			entity.setPay(this);
			entity.setUser(new User(id));
			entity.setReadFlag("0");
			this.payRecordList.add(entity);
		}
	}

	/**
	 * 获取通知发送记录用户Name
	 * 
	 * @return
	 */
	public String getPayRecordNames() {
		return Collections3.extractToString(payRecordList, "user.name", ",");
	}

	/**
	 * 设置通知发送记录用户Name
	 * 
	 * @return
	 */
	private void setPayRecordNames(String PayRecord) {
		//
	}

}
