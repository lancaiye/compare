package com.thinkgem.jeesite.modules.sys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.modules.sys.entity.Payroll;

/**
 * 薪资DAO接口
 * 
 * @author Administrator
 *
 */

public interface PayrollDao extends CrudDao<Payroll> {

	/**
	 * 查询所有用户
	 * @param payroll
	 * @return
	 */
	public long findAllCount(Payroll payroll);
	
	/**
	 * 根据姓名查询用户
	 */
	public Payroll getByName();
	
	/**
	 * 删除信息
	 */
	public int deletePayroll();

	/**
	 * 插入用户薪资数据
	 * 
	 */
	public double insertUserPayroll(Payroll payroll);

	/**
	 * 更新用户薪资数据
	 * 
	 */
	public double updateUserPayroll(Payroll payroll);

}
