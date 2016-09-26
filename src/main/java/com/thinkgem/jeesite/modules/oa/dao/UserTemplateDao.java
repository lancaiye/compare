/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.UserTemplate;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 用户绩效模板映射DAO接口
 *
 * @author 吴小平
 * @version 2016-09-08
 */
@MyBatisDao
public interface UserTemplateDao extends CrudDao<UserTemplate> {
    /**
     * 获取模板编号
     *
     * @param user_id
     * @return
     */
    public UserTemplate getByUid(String user_id);
    /**
     * 删除用户模板关联数据
     *
     * @param user
     * @return
     */
    public int deleteUserTemplate(User user);

    /**
     * 插入用户模板关联数据
     *
     * @param user
     * @return
     */
    public int assignUserToTemplate(User user);
}