package com.philip.edu.eval.bean;

public class ChosenMajor {
	private int id;
	private int school_id;
	private int major_id;
	private String major_code;
	private String major_name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSchool_id() {
		return school_id;
	}
	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}
	public int getMajor_id() {
		return major_id;
	}
	public void setMajor_id(int major_id) {
		this.major_id = major_id;
	}
	public String getMajor_code() {
		return major_code;
	}
	public void setMajor_code(String major_code) {
		this.major_code = major_code;
	}
	public String getMajor_name() {
		return major_name;
	}
	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}
}
