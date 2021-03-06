/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.Score;

/**
 * 绩效自我评分DAO接口
 * @author LLQ
 * @version 2016-09-22
 */
@MyBatisDao
public interface ScoreDao extends CrudDao<Score> {
	
}