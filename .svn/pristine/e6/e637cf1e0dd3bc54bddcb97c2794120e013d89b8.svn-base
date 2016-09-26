/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.web.test;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.test.entity.test.AttendanceRecord;
import com.thinkgem.jeesite.modules.test.service.test.AttendanceRecordService;


/**
 * testController
 * 
 * @author test
 * @version 2016-08-12
 */
@Controller
@RequestMapping(value = "${adminPath}/test/test/attendanceRecord")
public class AttendanceRecordController extends BaseController {

	@Autowired
	private AttendanceRecordService attendanceRecordService;

	@ModelAttribute
	public AttendanceRecord get(@RequestParam(required = false) String id) {
		AttendanceRecord entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = attendanceRecordService.get(id);
		}
		if (entity == null) {
			entity = new AttendanceRecord();
		}
		return entity;
	}

	/**
	 * 根据时间段查询
	 */
	@RequiresPermissions("test:test:attendanceRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(@RequestParam Map<String, Object> paramMap,AttendanceRecord attendanceRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AttendanceRecord> page=attendanceRecordService.findPage(new Page<AttendanceRecord>(request,response), attendanceRecord);
		model.addAttribute("page", page);
		model.addAttribute("paramMap", paramMap);
		return "modules/test/test/attendanceRecordList";
	}

	@RequiresPermissions("test:test:attendanceRecord:view")
	@RequestMapping(value = "form")
	public String form(AttendanceRecord attendanceRecord, Model model) {
		return "modules/test/test/attendanceRecordForm";
	}

	@RequiresPermissions("test:test:attendanceRecord:edit")
	@RequestMapping(value = "save")
	public String save(AttendanceRecord attendanceRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, attendanceRecord)) {
			return form(attendanceRecord, model);
		}
		attendanceRecordService.save(attendanceRecord);
		addMessage(redirectAttributes, "保存test成功");
		return "redirect:" + Global.getAdminPath() + "/test/test/attendanceRecord/?repage";
	}

	@RequiresPermissions("test:test:attendanceRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(AttendanceRecord attendanceRecord, RedirectAttributes redirectAttributes) {
		attendanceRecordService.delete(attendanceRecord);
		addMessage(redirectAttributes, "删除test成功");
		return "redirect:" + Global.getAdminPath() + "/test/test/attendanceRecord/?repage";
	}

	/**
	 * 导出用户数据
	 * 
	 * @param
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("test:test:attendanceRecord:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(AttendanceRecord attendanceRecord, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "用户数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<AttendanceRecord> page = attendanceRecordService.findUser(new Page<AttendanceRecord>(request, response, -1), attendanceRecord);
			new ExportExcel("用户数据", AttendanceRecord.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/test/test/attendanceRecord/list?repage";
	}

	/**
	 * 导入用户数据
	 */
	@RequiresPermissions("test:test:attendanceRecord:edit")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			return "redirect:" + adminPath + "/test/test/attendanceRecord?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 0, 0);
			List<AttendanceRecord> list = ei.getDataList(AttendanceRecord.class);
			for (AttendanceRecord attendanceRecord : list) {
				try {
					BeanValidators.validateWithException(validator, attendanceRecord);
					attendanceRecordService.save(attendanceRecord);
					successNum++;
				} catch (ConstraintViolationException ex) {
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条用户" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/test/test/attendanceRecord?repage";
	}

	/**
	 * 下载导入用户数据模板
	 * 
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("test:test:attendanceRecord:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "用户数据导入模板.xlsx";
			List<AttendanceRecord> list = Lists.newArrayList();
			// list.add(UserUtils.getUser());
			new ExportExcel(null, AttendanceRecord.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/test/test/attendanceRecord?repage";
	}

/*	*//**
	 * 时间段查询
	 * @param paramMap
	 * @param model
	 * @return
	 *//*
	@RequiresPermissions("test:test:attendanceRecord:view")
	@RequestMapping(value = "working")
	public String working(@RequestParam Map<String, Object> paramMap, Model model, HttpServletRequest request, HttpServletResponse response) {
//		List<AttendanceRecord> list = attendanceRecordService.working(paramMap);
		
		Page<AttendanceRecord> page = new Page<AttendanceRecord>(request, response);
		page.setList(attendanceRecordService.working(paramMap));
		model.addAttribute("page", page);
		model.addAttribute("paramMap", paramMap);
		return "modules/test/test/attendanceRecordList";
	}*/

}