package com.thinkgem.jeesite.modules.oa.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.PayRecord;

/**
 * 薪水记录DAO接口
 * 
 * @author
 * @version
 */
@MyBatisDao
public interface PayRecordDao extends CrudDao<PayRecord> {

	/**
	 * 插入通知记录
	 * @param PayRecordList
	 * @return
	 */
	public int insertAll(List<PayRecord> payRecordsList);

	/**
	 * 根据通知ID删除通知记录
	 * 
	 * @param PayId
	 * @return
	 */
	public int deleteByPayId(String payId);

}
