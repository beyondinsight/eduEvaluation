package com.philip.edu.eval.bean;

import java.util.Date;

public class TblRoles {
    private Integer id;

    private String roleName;

    private String roleCode;

    private Integer defineYear;

    private String description;

    private String roleDef;

    private Integer pid;

    private String status;

    private Date createTime;

    private Date updateTime;
    
    private Integer counts;
    
    
    public Integer getCounts() {
		return counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public Integer getDefineYear() {
        return defineYear;
    }

    public void setDefineYear(Integer defineYear) {
        this.defineYear = defineYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRoleDef() {
        return roleDef;
    }

    public void setRoleDef(String roleDef) {
        this.roleDef = roleDef == null ? null : roleDef.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	@Override
	public String toString() {
		return "TblRoles [id=" + id + ", roleName=" + roleName + ", roleCode=" + roleCode + ", defineYear=" + defineYear
				+ ", description=" + description + ", roleDef=" + roleDef + ", pid=" + pid + ", status=" + status
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", counts=" + counts + "]";
	}
    
}