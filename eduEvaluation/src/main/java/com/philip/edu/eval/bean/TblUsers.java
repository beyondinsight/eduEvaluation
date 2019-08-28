package com.philip.edu.eval.bean;

import java.util.Date;

public class TblUsers {
    private Integer id;

    private String userName;

    private String chineseName;

    private String password;

    private String salt;

    private String institution;

    private String major;
    
    private Integer roleId;
    
	private String roleName;
    
    public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

    public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	private String status;

    private Integer creator;

    private Date createTime;

    private Date updateTime;

    private Date lastOperation;

    private String position;

    private String qq;

    private String fixPhone;

    private String mobilePhone;

    private String email;

    private String memo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName == null ? null : chineseName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution == null ? null : institution.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
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

    public Date getLastOperation() {
        return lastOperation;
    }

    public void setLastOperation(Date lastOperation) {
        this.lastOperation = lastOperation;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getFixPhone() {
        return fixPhone;
    }

    public void setFixPhone(String fixPhone) {
        this.fixPhone = fixPhone == null ? null : fixPhone.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

	@Override
	public String toString() {
		return "TblUsers [id=" + id + ", userName=" + userName + ", chineseName=" + chineseName + ", password="
				+ password + ", salt=" + salt + ", institution=" + institution + ", majot=" + major + ", status="
				+ status + ", creator=" + creator + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", lastOperation=" + lastOperation + ", position=" + position + ", qq=" + qq + ", fixPhone="
				+ fixPhone + ", mobilePhone=" + mobilePhone + ", email=" + email + ", memo=" + memo + "]";
	}
    
}