/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.dao.UserTemplateDao;
import com.thinkgem.jeesite.modules.oa.entity.UserTemplate;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户绩效模板映射Service
 *
 * @author 吴小平
 * @version 2016-09-08
 */
@Service
@Transactional(readOnly = true)
public class UserTemplateService extends CrudService<UserTemplateDao, UserTemplate> {

    @Autowired
    private UserTemplateDao userTemplateDao;

    public UserTemplate get(String id) {
        return super.get(id);
    }

    public UserTemplate getByUid(String uid) {
        return userTemplateDao.getByUid(uid);
    }

    public List<UserTemplate> findList(UserTemplate userTemplate) {
        return super.findList(userTemplate);
    }

    public Page<UserTemplate> findPage(Page<UserTemplate> page, UserTemplate userTemplate) {
        return super.findPage(page, userTemplate);
    }

    @Transactional(readOnly = false)
    public void save(UserTemplate userTemplate) {
        super.save(userTemplate);
    }

    @Transactional(readOnly = false)
    public void delete(UserTemplate userTemplate) {
        super.delete(userTemplate);
    }

    @Transactional(readOnly = false)
    public int deleteUserTemplate(User user) {
        return userTemplateDao.deleteUserTemplate(user);
    }

    @Transactional(readOnly = false)
    public int assignUserToTemplate(User user, UserTemplate ut) {
        if (ut != null && user != null) {
            if (ut.getTemplateId().equals(user.getTemplate().getId())) {
                return 0;
            }
        }
        return userTemplateDao.assignUserToTemplate(user);
    }

}