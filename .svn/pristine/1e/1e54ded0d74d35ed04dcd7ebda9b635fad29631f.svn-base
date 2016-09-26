package com.thinkgem.jeesite.modules.record.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.record.entiy.Attendance;

/**
 * 考勤DAO接口
 * 
 * @author LLQ
 *
 */

@MyBatisDao
public interface AttendanceDao extends CrudDao<AttendanceDao> {
	
	
	/**
	 *  导入数据
	 */
	 
	

	/**
	 * 获取用户名信息
	 */
	public List<Attendance> getByName(Attendance attendance);

	/**
	 * 根据部门获取信息
	 */
	public List<Attendance> getByOffice(Attendance attendance);

	/**
	 * 根据时间查找信息
	 */
	public List<Attendance> getByTime(Attendance attendance);

}
