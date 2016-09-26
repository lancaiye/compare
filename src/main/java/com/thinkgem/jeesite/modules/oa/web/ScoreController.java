/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.entity.Ktemplate;
import com.thinkgem.jeesite.modules.oa.entity.Score;
import com.thinkgem.jeesite.modules.oa.service.ScoreService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 绩效自我评分Controller
 * @author LLQ
 * @version 2016-09-22
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/score")
public class ScoreController extends BaseController {

	@Autowired
	private ScoreService scoreService;
	
	@ModelAttribute
	public Score get(@RequestParam(required=false) String id) {
		Score entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = scoreService.get(id);
		}
		if (entity == null){
			entity = new Score();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:score:view")
	@RequestMapping(value = {"list", ""})
	public String list(Score score, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Score> page = scoreService.findPage(new Page<Score>(request, response), score); 
		model.addAttribute("page", page);
		return "modules/oa/scoreList";
	}

	@RequiresPermissions("oa:score:view")
	@RequestMapping(value = "form")
	public String form(Score score, Model model) {
		User loginUser = UserUtils.getUser();
		Office office = loginUser.getOffice();
		
//		Ktemplate ktemplate =office.getName(); 
		
//		model.addAttribute("ktemplate",ktemplate);
		model.addAttribute("office", office);
		model.addAttribute("score", score);
		return "modules/oa/scoreForm";
	}

	@RequiresPermissions("oa:score:edit")
	@RequestMapping(value = "save")
	public String save(Score score, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, score)){
			return form(score, model);
		}
		scoreService.save(score);
		addMessage(redirectAttributes, "保存自评得分成功");
		return "redirect:"+Global.getAdminPath()+"/oa/score/?repage";
	}
	
	@RequiresPermissions("oa:score:edit")
	@RequestMapping(value = "delete")
	public String delete(Score score, RedirectAttributes redirectAttributes) {
		scoreService.delete(score);
		addMessage(redirectAttributes, "删除自评得分成功");
		return "redirect:"+Global.getAdminPath()+"/oa/score/?repage";
	}

}