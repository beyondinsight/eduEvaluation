package com.philip.edu.eval.bean;

public class TblSchoolMajor {
    
	private Integer id;

    private Integer roleId;
    
    private String roleName;

	private String userId;
    
    private String userName;
    
    private String userChinese;
    
    private Integer schoolId;
    
    private String schoolName;
    
    private  Integer majorId;
    
    private String majorName;

    
	public String getUserChinese() {
		return userChinese;
	}

	public void setUserChinese(String userChinese) {
		this.userChinese = userChinese;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Integer getMajorId() {
		return majorId;
	}

	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}


	public String toString() {
		return "TblSchoolMajor [id=" + id + ", roleId=" + roleId + ", roleName=" + roleName + ", userId=" + userId
				+ ", userName=" + userName + ", schoolId=" + schoolId + ", schoolName=" + schoolName + ", majorId="
				+ majorId + ", majorName=" + majorName + "]";
	}
    
}