/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.dao.test;


import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.test.entity.test.AttendanceRecord;

/**
 * testDAO接口
 * 
 * @author test
 * @version 2016-08-12
 */
@MyBatisDao
public interface AttendanceRecordDao extends CrudDao<AttendanceRecord> {
	
	
}