/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.entity.Ktemplate;
import com.thinkgem.jeesite.modules.oa.entity.Options;
import com.thinkgem.jeesite.modules.oa.entity.Score;
import com.thinkgem.jeesite.modules.oa.dao.OptionsDao;
import com.thinkgem.jeesite.modules.oa.dao.ScoreDao;

/**
 * 绩效自我评分Service
 * @author LLQ
 * @version 2016-09-22
 */
@Service
@Transactional(readOnly = true)
public class ScoreService extends CrudService<ScoreDao, Score> {

	
	public Score get(String id) {
		Score score = super.get(id);
		return score;
	}
		
	
	public List<Score> findList(Score score) {
		return super.findList(score);
	}
	
	public Page<Score> findPage(Page<Score> page, Score score) {
		return super.findPage(page, score);
	}
	
	@Transactional(readOnly = false)
	public void save(Score score) {
		super.save(score);
	}
	
	@Transactional(readOnly = false)
	public void delete(Score score) {
		super.delete(score);
	}
	
}