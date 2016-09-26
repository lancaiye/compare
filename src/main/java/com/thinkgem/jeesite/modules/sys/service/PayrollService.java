package com.thinkgem.jeesite.modules.sys.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.sys.dao.PayrollDao;
import com.thinkgem.jeesite.modules.sys.entity.Payroll;

/**
 * 薪资Service
 */

@Service
@Transactional(readOnly = true)
public class PayrollService extends BaseService {
	@Autowired
	private PayrollDao payrollDao;

	/**
	 * 
	 */
	public void save(Payroll payroll, Map<String, Object> variables) {
		
	}

}
