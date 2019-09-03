package com.philip.edu.eval.bean;

public class TblSchoolUser {
    private Integer id;

    private Integer roleId;
    
    private String roleName;
    
    private Integer schoolId;
    
    private String schoolName;

	private String userId;
    
    private String userName;
    
    private String userChinese;
    
    
    
    public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUserChinese() {
		return userChinese;
	}

	public void setUserChinese(String userChinese) {
		this.userChinese = userChinese;
	}
    
    
    public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	@Override
	public String toString() {
		return "TblSchoolUser [id=" + id + ", roleId=" + roleId + ", roleName=" + roleName + ", userId=" + userId
				+ ", userName=" + userName + ", schoolId=" + schoolId + ", schoolName=" + schoolName + "]";
	}
    
    
    
    
}