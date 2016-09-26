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
import com.thinkgem.jeesite.modules.oa.dao.KtemplateDao;
import com.thinkgem.jeesite.modules.oa.entity.Options;
import com.thinkgem.jeesite.modules.oa.dao.OptionsDao;

/**
 * 绩效管理Service
 * @author 吴小平
 * @version 2016-09-09
 */
@Service
@Transactional(readOnly = true)
public class KtemplateService extends CrudService<KtemplateDao, Ktemplate> {

	@Autowired
	private OptionsDao optionsDao;
	
	public Ktemplate get(String id) {
		Ktemplate ktemplate = super.get(id);
		ktemplate.setOptionsList(optionsDao.findList(new Options(ktemplate)));
		return ktemplate;
	}
	
	public List<Ktemplate> findList(Ktemplate ktemplate) {
		return super.findList(ktemplate);
	}
	
	public Page<Ktemplate> findPage(Page<Ktemplate> page, Ktemplate ktemplate) {
		return super.findPage(page, ktemplate);
	}
	
	@Transactional(readOnly = false)
	public void save(Ktemplate ktemplate) {
		super.save(ktemplate);
		for (Options options : ktemplate.getOptionsList()){
			if (options.getId() == null){
				continue;
			}
			if (Options.DEL_FLAG_NORMAL.equals(options.getDelFlag())){
				if (StringUtils.isBlank(options.getId())){
					options.setKpi_template(ktemplate);
					options.preInsert();
					optionsDao.insert(options);
				}else{
					options.preUpdate();
					optionsDao.update(options);
				}
			}else{
				optionsDao.delete(options);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Ktemplate ktemplate) {
		super.delete(ktemplate);
		optionsDao.delete(new Options(ktemplate));
	}
	
}