package com.thinkgem.jeesite.modules.record.entiy;

import java.util.Date;


import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 考勤Entity
 * @author LLQ
 *
 */

public class Attendance extends DataEntity<Attendance> {
	private static final long serialVersionUID=1L;
	private String id; // id编号
	private String office; // 部门
	private String name; // 姓名
	private String attendance_no; // 考勤号
	private Date working_time; // 打卡时间
	private String record_status;// 记录状态
	private String machine_id;// 机器号
	private String num_id;// 编号
	private String trades_id;// 工种
	private String compare_mode;// 对比方式
	private String card_number;// 卡号
	private String remarks;// 备注信息
	
	public Attendance() {
		super();
	}

	public Attendance(String id) {
		super(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAttendance_no() {
		return attendance_no;
	}

	public void setAttendance_no(String attendance_no) {
		this.attendance_no = attendance_no;
	}

	public Date getWorking_time() {
		return working_time;
	}

	public void setWorking_time(Date working_time) {
		this.working_time = working_time;
	}

	public String getRecord_status() {
		return record_status;
	}

	public void setRecord_status(String record_status) {
		this.record_status = record_status;
	}

	public String getMachine_id() {
		return machine_id;
	}

	public void setMachine_id(String machine_id) {
		this.machine_id = machine_id;
	}

	public String getNum_id() {
		return num_id;
	}

	public void setNum_id(String num_id) {
		this.num_id = num_id;
	}

	public String getTrades_id() {
		return trades_id;
	}

	public void setTrades_id(String trades_id) {
		this.trades_id = trades_id;
	}

	public String getCompare_mode() {
		return compare_mode;
	}

	public void setCompare_mode(String compare_mode) {
		this.compare_mode = compare_mode;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
