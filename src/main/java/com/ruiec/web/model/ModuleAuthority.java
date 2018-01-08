package com.ruiec.web.model;

public class ModuleAuthority {
    /**版块ID*/
    private Integer moduleId;

    /**部门ID*/
    private Integer departmentId;

    /**权限类型（read/create/reply/delete）*/
    private String type;

    /**版块ID*/
    public Integer getModuleId() {
        return moduleId;
    }

    /**版块ID*/
    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    /**部门ID*/
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**部门ID*/
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**权限类型（read/create/reply/delete）*/
    public String getType() {
        return type;
    }

    /**权限类型（read/create/reply/delete）*/
    public void setType(String type) {
        this.type = type;
    }
}