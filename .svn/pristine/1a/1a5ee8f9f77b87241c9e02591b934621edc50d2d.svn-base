package com.thinkgem.jeesite.modules.oa.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.dao.PayDao;
import com.thinkgem.jeesite.modules.oa.dao.PayRecordDao;
import com.thinkgem.jeesite.modules.oa.entity.PayRecord;
import com.thinkgem.jeesite.modules.oa.entity.Pay;

/**
 * 薪水添加Service
 * 
 * @author
 * @version
 */
@Service
@Transactional(readOnly = true)
public class PayService extends CrudService<PayDao, Pay> {

	@Autowired
	private PayRecordDao payRecordDao;

	public Pay get(String id) {
		Pay entity = dao.get(id);
		return entity;
	}

	/**
	 * 获取薪水信息发送记录
	 * 
	 * @param Pay
	 * @return
	 */
	public Pay getRecordList(Pay pay) {
		pay.setPayRecordList(payRecordDao.findList(new PayRecord(pay)));
		return pay;
	}

	public Page<Pay> find(Page<Pay> page, Pay pay) {
		pay.setPage(page);
		page.setList(dao.findList(pay));
		return page;
	}

	/**
	 * 获取薪资信息数目
	 * 
	 * @param Pay
	 * @return
	 */
	public Long findCount(Pay pay) {
		return dao.findCount(pay);
	}

	@Transactional(readOnly = false)
	public void save(Pay pay) {
		super.save(pay);
		System.out.println("pay:"+pay);
		// 更新发送接收人记录
		payRecordDao.deleteByPayId(pay.getId());
		if (pay.getPayRecordList().size() > 0) {
			payRecordDao.insertAll(pay.getPayRecordList());
		}
	}

	/**
	 * 更新阅读状态
	 */
	@Transactional(readOnly = false)
	public void updateReadFlag(Pay pay) {
		PayRecord payRecord = new PayRecord(pay);
		payRecord.setUser(pay.getCurrentUser());
		payRecord.setReadDate(new Date());
		payRecord.setReadFlag("1");
		payRecordDao.update(payRecord);

	}

}
