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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.entity.Pay;
import com.thinkgem.jeesite.modules.oa.service.PayService;

/**
 * 薪水信息通知
 * 
 * @author Administrator
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/pay")
public class PayController extends BaseController {

	@Autowired
	private PayService payService;

	@ModelAttribute
	public Pay get(@RequestParam(required = false) String id) {
		Pay entity = null;

		if (StringUtils.isBlank(id)) {
			entity = payService.get(id);
		}
		if (entity == null) {
			entity = new Pay();
		}
		return entity;
	}

	@RequiresPermissions("oa:pay:view")
	@RequestMapping(value = { "list", "" })
	public String list(Pay pay, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Pay> page = payService.find(new Page<Pay>(request, response), pay);
		model.addAttribute("page", page);
		return "modules/oa/payList";
	}

	@RequiresPermissions("oa:pay:view")
	@RequestMapping(value = "form")
	public String form(Pay pay, Model model) {
		if (StringUtils.isBlank(pay.getId())) {
			pay = payService.getRecordList(pay);
		}
		model.addAttribute("pay", pay);
		return "modules/oa/payForm";
	}

	@RequiresPermissions("oa:pay:edit")
	@RequestMapping(value = "save")
	public String save(Pay pay, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pay)) {
			return form(pay, model);
		}
		// 如果是修改，则状态为已发布，则不能再进行操作
		if (StringUtils.isNotBlank(pay.getId())) {
			Pay e = payService.get(pay.getId());
			if ("1".equals(e.getStatus())) {
				addMessage(redirectAttributes, "已发布，不能操作！");
				return "redirect:" + adminPath + "/oa/pay/form?id=" + pay.getId();
			}
		}
		payService.save(pay);
		addMessage(redirectAttributes, "保存信息'" + pay.getTitle() + "'成功");
		return "redirect:" + adminPath + "/oa/pay/?repage";
	}

	@RequiresPermissions("oa:pay:edit")
	@RequestMapping(value = "delete")
	public String delete(Pay pay, RedirectAttributes redirectAttributes) {

		payService.delete(pay);
		addMessage(redirectAttributes, "delete成功");
		return "redirect:" + adminPath + "/oa/pay/?repage";
	}

	/**
	 * 我的信息列表
	 */
	@RequestMapping(value = "self")
	public String selfList(Pay pay, HttpServletRequest request, HttpServletResponse response, Model model) {
		pay.setSelf(true);
		Page<Pay> page = payService.find(new Page<Pay>(request, response), pay);
		model.addAttribute("page", page);
		return "modules/oa/payList";
	}

	/**
	 * 我的信息列表-数据
	 */
	@RequiresPermissions("oa:pay:view")
	@RequestMapping(value = "selfData")
	@ResponseBody
	public Page<Pay> listData(Pay pay, HttpServletRequest request, HttpServletResponse response, Model model) {
		pay.setSelf(true);
		Page<Pay> page = payService.find(new Page<Pay>(request, response), pay);
		return page;
	}

	/**
	 * 查看我的信息
	 */
	@RequestMapping(value = "view")
	public String view(Pay pay, Model model) {
		if (StringUtils.isNotBlank(pay.getId())) {
			payService.updateReadFlag(pay);
			pay = payService.get(pay);
			model.addAttribute("pay", pay);
		/*	System.out.println("!!!!!!!!!!!!!!!!!!!!!" + pay + "!!!!!!!!!!!!!!");
			System.out.println("pay.id" + pay.getId());*/
			return "modules/oa/payForm";
		}
		return "redirect:" + adminPath + "/oa/pay/self?repage";
	}

	/**
	 * 查看我的通知-数据
	 */
	@RequestMapping(value = "viewData")
	@ResponseBody
	public Pay viewData(Pay pay, Model model) {
		if (StringUtils.isNotBlank(pay.getId())) {
			payService.updateReadFlag(pay);
			return pay;
		}
		return null;
	}

	/**
	 * 查看我的信息-发送记录
	 */
	@RequestMapping(value = "viewRecordData")
	@ResponseBody
	public Pay viewRecordData(Pay pay, Model model) {
		if (StringUtils.isNotBlank(pay.getId())) {
			pay = payService.getRecordList(pay);
			return pay;
		}
		return null;
	}

	/**
	 * 获取我的信息数目
	 */
	@RequestMapping(value = "self/count")
	@ResponseBody
	public String selfCount(Pay pay, Model model) {
		pay.setSelf(true);
		pay.setReadFlag("0");
		return String.valueOf(payService.findCount(pay));
	}

}
