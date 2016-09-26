/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.service.test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.test.entity.test.AttendanceRecord;
import com.thinkgem.jeesite.modules.test.dao.test.AttendanceRecordDao;

/**
 * testService
 * 
 * @author test
 * @version 2016-08-12
 */
@Service
@Transactional(readOnly = true)
public class AttendanceRecordService extends CrudService<AttendanceRecordDao, AttendanceRecord> {

	@Autowired
	private AttendanceRecordDao attendanceRecordDao;

	public AttendanceRecord get(String id) {
		return super.get(id);
	}

	public List<AttendanceRecord> findList(AttendanceRecord attendanceRecord) {
		return super.findList(attendanceRecord);
	}

	public Page<AttendanceRecord> findPage( Map<String, Object> paramMap, Page<AttendanceRecord> page, AttendanceRecord attendanceRecord) {
		
		Date beginDate = DateUtils.parseDate(get("beginDate"));
		if (beginDate == null) {
			beginDate = DateUtils.setDays(new Date(), 1);
			paramMap.put("beginDate", DateUtils.formatDate(beginDate, "yyyy-MM-dd HH:mm"));
		}
		attendanceRecord.setBeginDate(beginDate);
		Date endDate = DateUtils.parseDate(get("endDate"));
		if (endDate == null) {
			endDate = DateUtils.addDays(DateUtils.addMonths(beginDate, 1), -1);
			paramMap.put("endDate", DateUtils.formatDate(endDate, "yyyy-MM-dd HH:mm"));
		}
		attendanceRecord.setEndDate(endDate);
		
		page.setList(dao.findAllList(attendanceRecord));
		
		return page;
	}

	@Transactional(readOnly = false)
	public void save(AttendanceRecord attendanceRecord) {
		super.save(attendanceRecord);
	}

	@Transactional(readOnly = false)
	public void delete(AttendanceRecord attendanceRecord) {
		super.delete(attendanceRecord);
	}

	public Page<AttendanceRecord> findUser(Page<AttendanceRecord> page, AttendanceRecord attendanceRecord) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		attendanceRecord.getSqlMap().put("dsf", dataScopeFilter(attendanceRecord.getCurrentUser(), "o", "a"));
		// 设置分页参数
		attendanceRecord.setPage(page);
		// 执行分页查询
		page.setList(attendanceRecordDao.findList(attendanceRecord));
		return page;
	}

	/*public List<AttendanceRecord> working(Map<String, Object> paramMap) {

		AttendanceRecord attendanceRecord = new AttendanceRecord();

		Date beginDate = DateUtils.parseDate(paramMap.get("beginDate"));
		if (beginDate == null) {
			beginDate = DateUtils.setDays(new Date(), 1);
			paramMap.put("beginDate", DateUtils.formatDate(beginDate, "yyyy-MM-dd HH:mm"));
		}
		attendanceRecord.setBeginDate(beginDate);
		Date endDate = DateUtils.parseDate(paramMap.get("endDate"));
		if (endDate == null) {
			endDate = DateUtils.addDays(DateUtils.addMonths(beginDate, 1), -1);
			paramMap.put("endDate", DateUtils.formatDate(endDate, "yyyy-MM-dd HH:mm"));
		}
		attendanceRecord.setEndDate(endDate);
		List<AttendanceRecord> list = attendanceRecordDao.findList(attendanceRecord);
		return list;
	}
*/
}