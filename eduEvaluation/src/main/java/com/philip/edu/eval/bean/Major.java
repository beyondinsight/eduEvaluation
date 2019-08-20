package com.philip.edu.eval.bean;

import java.util.Date;

public class Major {
	private int id;
	private String major_name;
	private String major_code;
	private int major_class;
	private String main_lecture;
	public int getMajor_class() {
		return major_class;
	}
	public void setMajor_class(int major_class) {
		this.major_class = major_class;
	}
	public String getMain_lecture() {
		return main_lecture;
	}
	public void setMain_lecture(String main_lecture) {
		this.main_lecture = main_lecture;
	}
	private String memo;
	private Date create_time;
	private Date update_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMajor_name() {
		return major_name;
	}
	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}
	public String getMajor_code() {
		return major_code;
	}
	public void setMajor_code(String major_code) {
		this.major_code = major_code;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

}
