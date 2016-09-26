/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.UserTemplate;
import com.thinkgem.jeesite.modules.oa.service.UserTemplateService;
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
@RequestMapping(value = "${adminPath}/oa/userTemplate")
public class UserTemplateController extends BaseController {

	@Autowired
	private UserTemplateService userTemplateService;



	@ModelAttribute
	public UserTemplate get(@RequestParam(required=false) String id) {
		UserTemplate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = userTemplateService.get(id);
		}
		if (entity == null){
			entity = new UserTemplate();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:userTemplate:view")
	@RequestMapping(value = {"list", ""})
	public String list(UserTemplate userTemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UserTemplate> page = userTemplateService.findPage(new Page<UserTemplate>(request, response), userTemplate); 
		model.addAttribute("page", page);
		return "modules/oa/userTemplateList";
	}

	@RequiresPermissions("oa:userTemplate:view")
	@RequestMapping(value = "form")
	public String form(UserTemplate userTemplate, Model model) {
		model.addAttribute("userTemplate", userTemplate);
		return "modules/oa/userTemplateForm";
	}

	@RequiresPermissions("oa:userTemplate:edit")
	@RequestMapping(value = "save")
	public String save(UserTemplate userTemplate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, userTemplate)){
			return form(userTemplate, model);
		}
		userTemplateService.save(userTemplate);
		addMessage(redirectAttributes, "保存用户绩效模板映射成功");
		return "redirect:"+Global.getAdminPath()+"/oa/userTemplate/?repage";
	}
	
	@RequiresPermissions("oa:userTemplate:edit")
	@RequestMapping(value = "delete")
	public String delete(UserTemplate userTemplate, RedirectAttributes redirectAttributes) {
		userTemplateService.delete(userTemplate);
		addMessage(redirectAttributes, "删除用户绩效模板映射成功");
		return "redirect:"+Global.getAdminPath()+"/oa/userTemplate/?repage";
	}



}