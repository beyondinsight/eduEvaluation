package com.philip.edu.eval.bean;

import java.util.Date;

public class School {
	private int id;
	private String school_name;
	private String school_code;
	private String build_year;
	private String build_year_s;
	private String city;
	private String type;
	private String type_s;
	private String properties;
	public String getBuild_year_s() {
		return build_year_s;
	}
	public void setBuild_year_s(String build_year_s) {
		this.build_year_s = build_year_s;
	}
	public String getType_s() {
		return type_s;
	}
	public void setType_s(String type_s) {
		this.type_s = type_s;
	}
	private int level;
	private Date create_time;
	private Date update_time;
	private String memo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getSchool_code() {
		return school_code;
	}
	public void setSchool_code(String school_code) {
		this.school_code = school_code;
	}

	public String getBuild_year() {
		return build_year;
	}
	public void setBuild_year(String build_year) {
		this.build_year = build_year;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
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
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
