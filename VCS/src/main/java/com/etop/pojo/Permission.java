package com.etop.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @类名: Permission
 * @描述: 权限类，保存权限信息与对应的角色（多对一）
 * @作者 liuren-mail@163.com
 * @日期 2015年5月20日 上午11:26:31
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_permission")
public class Permission implements Serializable {

    private Integer id;
    private String permissionname;
    private Role role;
    public Permission() {
        super();
    }

    public Permission(Integer id, String permissionname, Role role) {
        super();
        this.id = id;
        this.permissionname = permissionname;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    @ManyToOne(targetEntity = Role.class)
    @JoinTable(name = "t_role_permission", joinColumns = { @JoinColumn(name = "permission_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
