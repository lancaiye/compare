/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.Ktemplate;
import com.thinkgem.jeesite.modules.oa.entity.UserTemplate;
import com.thinkgem.jeesite.modules.oa.service.KtemplateService;
import com.thinkgem.jeesite.modules.oa.service.UserTemplateService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 绩效管理Controller
 *
 * @author 吴小平
 * @version 2016-09-09
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/ktemplate")
public class KtemplateController extends BaseController {

    @Autowired
    private KtemplateService ktemplateService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private OfficeService officeService;

    @Autowired
    private UserTemplateService userTemplateService;

    @ModelAttribute
    public Ktemplate get(@RequestParam(required = false) String id) {
        Ktemplate entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = ktemplateService.get(id);
        }
        if (entity == null) {
            entity = new Ktemplate();
        }
        return entity;
    }

    @RequiresPermissions("oa:ktemplate:view")
    @RequestMapping(value = {"list", ""})
    public String list(Ktemplate ktemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Ktemplate> page = ktemplateService.findPage(new Page<Ktemplate>(request, response), ktemplate);
        model.addAttribute("page", page);
        return "modules/oa/ktemplateList";
    }

    @RequiresPermissions("oa:ktemplate:view")
    @RequestMapping(value = "form")
    public String form(Ktemplate ktemplate, Model model) {
        model.addAttribute("ktemplate", ktemplate);
        return "modules/oa/ktemplateForm";
    }

    @RequiresPermissions("oa:ktemplate:edit")
    @RequestMapping(value = "save")
    public String save(Ktemplate ktemplate, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, ktemplate)) {
            return form(ktemplate, model);
        }
        ktemplateService.save(ktemplate);
        addMessage(redirectAttributes, "保存绩效模板成功");
        return "redirect:" + Global.getAdminPath() + "/oa/ktemplate/?repage";
    }

    @RequiresPermissions("oa:ktemplate:edit")
    @RequestMapping(value = "delete")
    public String delete(Ktemplate ktemplate, RedirectAttributes redirectAttributes) {
        ktemplateService.delete(ktemplate);
        addMessage(redirectAttributes, "删除绩效模板成功");
        return "redirect:" + Global.getAdminPath() + "/oa/ktemplate/?repage";
    }

    /**
     * 模板分配页面
     *
     * @param template
     * @param model
     * @return
     */
    @RequiresPermissions("oa:ktemplate:edit")
    @RequestMapping(value = "assign")
    public String assign(Ktemplate template, Model model) {
        List<User> userList = systemService.findUser(new User(new Ktemplate(template.getId())));
        model.addAttribute("userList", userList);
        return "modules/oa/assignTemplate";
    }

    /**
     * 模板分配 -- 打开模板分配对话框
     *
     * @param template
     * @param model
     * @return
     */
    @RequiresPermissions("oa:ktemplate:view")
    @RequestMapping(value = "userToTemplate")
    public String selectUserToTemplate(Ktemplate template, Model model) {
        List<User> userList = systemService.findUser(new User(new Ktemplate(template.getId())));
        model.addAttribute("template", template);
        model.addAttribute("userList", userList);
        model.addAttribute("selectIds", Collections3.extractToString(userList, "name", ","));
        model.addAttribute("officeList", officeService.findAll());
        return "modules/oa/selectUserToTemplate";
    }

    /**
     * 模板分配 -- 根据部门编号获取用户列表
     *
     * @param officeId
     * @param response
     * @return
     */
    @RequiresPermissions("oa:ktemplate:view")
    @ResponseBody
    @RequestMapping(value = "users")
    public List<Map<String, Object>> users(String officeId, HttpServletResponse response) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        User user = new User();
        user.setOffice(new Office(officeId));
        Page<User> page = systemService.findUser(new Page<User>(1, -1), user);
        for (User e : page.getList()) {
            Map<String, Object> map = Maps.newHashMap();
            map.put("id", e.getId());
            map.put("pId", 0);
            map.put("name", e.getName());
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 模板分配 -- 从模板中移除用户
     *
     * @param userId
     * @param templateId
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("oa:ktemplate:edit")
    @RequestMapping(value = "deleteUserTemplate")
    public String deleteUserTemplate(String userId, String templateId, RedirectAttributes redirectAttributes) {
        Ktemplate template = ktemplateService.get(templateId);
        User user = systemService.getUser(userId);

        int res = userTemplateService.deleteUserTemplate(user);
        if (res >= 0) {
            addMessage(redirectAttributes, "用户【" + user.getName() + "】从模板【" + template.getName() + "】中移除成功！");
        } else {
            addMessage(redirectAttributes, "用户【" + user.getName() + "】从模板【" + template.getName() + "】中移除失败！");
        }
        return "redirect:" + adminPath + "/oa/ktemplate/assign?id=" + template.getId();
    }

    /**
     * 模板分配
     *
     * @param template
     * @param idsArr
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("oa:ktemplate:edit")
    @RequestMapping(value = "assignTemplate")
    public String assignTemplate(Ktemplate template, String[] idsArr, RedirectAttributes redirectAttributes) {
        if (Global.isDemoMode()) {
            addMessage(redirectAttributes, "演示模式，不允许操作！");
            return "redirect:" + adminPath + "/oa/ktemplate/assign?id=" + template.getId();
        }
        StringBuilder msg = new StringBuilder();
        int newNum = 0;
        for (int i = 0; i < idsArr.length; i++) {
            User user = systemService.getUser(idsArr[i]);
            int res = 0;
            if (null != user) {
                user.setTemplate(template);
                UserTemplate ut = userTemplateService.getByUid(idsArr[i]);
                res = userTemplateService.assignUserToTemplate(user, ut);
            }

            if (res > 0) {
                msg.append("<br/>新增用户【" + user.getName() + "】到模板【" + template.getName() + "】！");
                newNum++;
            }
        }
        addMessage(redirectAttributes, "已成功分配 " + newNum + " 个用户" + msg);
        return "redirect:" + adminPath + "/oa/ktemplate/assign?id=" + template.getId();
    }

}