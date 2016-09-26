/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.entity.Options;
import com.thinkgem.jeesite.modules.oa.dao.OptionsDao;

/**
 * 用户绩效模板映射Service
 * @author 吴小平
 * @version 2016-09-08
 */
@Service
@Transactional(readOnly = true)
public class OptionsService extends CrudService<OptionsDao, Options> {

	public Options get(String id) {
		return super.get(id);
	}
	
	public List<Options> findList(Options options) {
		return super.findList(options);
	}
	
	public Page<Options> findPage(Page<Options> page, Options options) {
		return super.findPage(page, options);
	}
	
	@Transactional(readOnly = false)
	public void save(Options options) {
		super.save(options);
	}
	
	@Transactional(readOnly = false)
	public void delete(Options options) {
		super.delete(options);
	}
	
}