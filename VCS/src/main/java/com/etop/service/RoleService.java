package com.etop.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etop.dao.RoleDAO;
import com.etop.dto.RoleDto;
import com.etop.pojo.Role;
import com.etop.utils.PageUtil;
/**
 * @类名: RoleService 
 * @描述: 角色服务和DAO对接
 * @作者 liuren-mail@163.com
 * @日期 2015年5月26日 下午5:20:03
 */
@SuppressWarnings("serial")
@Service
public class RoleService implements Serializable{
    
    @Autowired
     RoleDAO roleDAO;
    
    @Transactional
    public List<Role> getAllRole() {
        return roleDAO.find("from Role r");
    }
    
    @Transactional
    public PageUtil<RoleDto> findAllRole() {
        return roleDAO.findBySql("select * from t_role", RoleDto.class, false);
    }

    public Role findById(int id) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id", id);
        return roleDAO.findUniqueResult("from Role r where r.id= :id", params);
    }

    public void updateRole(Role role) {
        roleDAO.saveOrUpdate(role);
    }

    public void saveRole(Role role) {
        roleDAO.save(role);
    }

    public void deleteRole(int id) {
        roleDAO.deleteById(id);
    }

    public String getNameById(int role_id) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id", role_id);
        Role role =roleDAO.findUniqueResult("from Role r where r.id= :id", params);
        String rolename = role.getRolename();
        return rolename;
    }
}
