package com.thinkgem.jeesite.modules.oa.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.Pay;

/**
 * 薪水DAO接口
 * 
 * @author
 * @version
 */
@MyBatisDao
public interface PayDao extends CrudDao<Pay> {
	/**
	 * 获取信息数目
	 * 
	 * @param Pay
	 * @return
	 */
	public Long findCount(Pay pay);
}
