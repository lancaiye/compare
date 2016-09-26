/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.Options;
import com.thinkgem.jeesite.modules.oa.service.OptionsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户绩效模板映射Controller
 * @author 吴小平
 * @version 2016-09-08
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/options")
public class OptionsController extends BaseController {

	@Autowired
	private OptionsService optionsService;
	
	@ModelAttribute
	public Options get(@RequestParam(required=false) String id) {
		Options entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = optionsService.get(id);
		}
		if (entity == null){
			entity = new Options();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:options:view")
	@RequestMapping(value = {"list", ""})
	public String list(Options options, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Options> page = optionsService.findPage(new Page<Options>(request, response), options); 
		model.addAttribute("page", page);
		return "modules/oa/optionsList";
	}

	@RequiresPermissions("oa:options:view")
	@RequestMapping(value = "form")
	public String form(Options options, Model model) {
		model.addAttribute("options", options);
		return "modules/oa/optionsForm";
	}

	@RequiresPermissions("oa:options:edit")
	@RequestMapping(value = "save")
	public String save(Options options, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, options)){
			return form(options, model);
		}
		optionsService.save(options);
		addMessage(redirectAttributes, "保存用户绩效模板映射成功");
		return "redirect:"+Global.getAdminPath()+"/oa/options/?repage";
	}
	
	@RequiresPermissions("oa:options:edit")
	@RequestMapping(value = "delete")
	public String delete(Options options, RedirectAttributes redirectAttributes) {
		optionsService.delete(options);
		addMessage(redirectAttributes, "删除用户绩效模板映射成功");
		return "redirect:"+Global.getAdminPath()+"/oa/options/?repage";
	}

}