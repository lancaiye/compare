/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.entity.test;

import org.hibernate.validator.constraints.Length;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * testEntity
 * 
 * @author test
 * @version 2016-08-12
 */
public class AttendanceRecord extends DataEntity<AttendanceRecord> {

	private static final long serialVersionUID = 1L;
	private String office; // 部门
	private String name; // 姓名
	private int attendanceNo; // 考勤号
	private Date workingTime; // 上班时间
	private String recordStatus; // 记录状态
	private String machineId; // 机器号
	private String numId; // 编号
	private String tradesId; // 工种代码
	private String compareMode; // 对比方式
	private String cardNumber; // 卡号

	private Date beginDate; // 开始时间
	private Date endDate; // 结束时间

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public AttendanceRecord() {
		super();
	}

	public AttendanceRecord(String id) {
		super(id);
	}

	@JsonIgnore
	@Length(min = 1, max = 100, message = "部门长度必须介于 1 和 100 之间")
	@ExcelField(title = "部门", align = 2, sort = 40)
	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	@JsonIgnore
	@Length(min = 1, max = 100, message = "姓名长度必须介于 1 和 100 之间")
	@ExcelField(title = "姓名", align = 2, sort = 41)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	@Length(min = 0, max = 100, message = "考勤号长度必须介于 0 和 100 之间")
	@ExcelField(title = "考勤号", align = 2, sort = 42)
	public int getAttendanceNo() {
		return attendanceNo;
	}

	public void setAttendanceNo(int attendanceNo) {
		this.attendanceNo = attendanceNo;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "上班时间不能为空")
	@ExcelField(title = "上班时间", type = 0, align = 2, sort = 43)
	public Date getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(Date workingTime) {
		this.workingTime = workingTime;
	}

	@Length(min = 1, max = 100, message = "记录状态长度必须介于 1 和 100 之间")
	@ExcelField(title = "记录状态", align = 2, sort = 44)
	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	@Length(min = 1, max = 64, message = "机器号长度必须介于 1 和 64 之间")
	@ExcelField(title = "机器号", align = 2, sort = 45)
	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	@ExcelField(title = "编号", align = 2, sort = 46)
	public String getNumId() {
		return numId;
	}

	public void setNumId(String numId) {
		this.numId = numId;
	}

	@Length(min = 1, max = 64, message = "工种代码长度必须介于 1 和 64 之间")
	@ExcelField(title = "工种代码", align = 2, sort = 47)
	public String getTradesId() {
		return tradesId;
	}

	public void setTradesId(String tradesId) {
		this.tradesId = tradesId;
	}

	@Length(min = 1, max = 100, message = "对比方式长度必须介于 1 和 100 之间")
	@ExcelField(title = "对比方式", align = 2, sort = 48)
	public String getCompareMode() {
		return compareMode;
	}

	public void setCompareMode(String compareMode) {
		this.compareMode = compareMode;
	}

	@ExcelField(title = "卡号", align = 2, sort = 49)
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

}