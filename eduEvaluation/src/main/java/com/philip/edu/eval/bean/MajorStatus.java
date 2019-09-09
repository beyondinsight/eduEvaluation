package com.philip.edu.eval.bean;

import java.util.Date;

public class MajorStatus {

	private String major_name;

	private int task_id;

	private int school_id;
	private int major_id;

	private String task_name;

	private String task_status;

	private int task_year;

	private String school_name;
	private Date update_time;

	private String major_status;
	private String major_status_key;
	
	private String  major_status_note;
	
	public String getMajor_status_note() {
		return major_status_note;
	}

	public void setMajor_status_note(String major_status_note) {
		this.major_status_note = major_status_note;
	}

	public String getMajor_status_key() {
		return major_status_key;
	}

	public void setMajor_status_key(String major_status_key) {
		this.major_status_key = major_status_key;
	}

	private int collection_school_id;

	public int getCollection_school_id() {
		return collection_school_id;
	}

	public void setCollection_school_id(int collection_school_id) {
		this.collection_school_id = collection_school_id;
	}


	public String getMajor_name() {
		return major_name;
	}

	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public String getTask_status() {
		return task_status;
	}

	public void setTask_status(String task_status) {
		this.task_status = task_status;
	}

	public int getTask_year() {
		return task_year;
	}

	public void setTask_year(int task_year) {
		this.task_year = task_year;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}

	public String getMajor_status() {
		return major_status;
	}

	public void setMajor_status(String major_status) {
		this.major_status = major_status;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
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
}
