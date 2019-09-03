package com.philip.edu.eval.bean;

import java.util.Date;

public class PerformanceForm {
	private int id;
	private int collection_major_id;
	private int metrics_id;
	private String metrics_name;
	private Date create_time;
	private Date update_time;
	private int op_user;
	private char process_status;
	private int m_system_id;
	private double current_value;
	private double target_value;
	private double actual_value;
	private double score;
	private double self_evaluate;
	private String self_introduction;
	private String unit;
	private String memo;
	private String material_num;
	private int for_template;
	
	
	public int getFor_template() {
		return for_template;
	}
	public void setFor_template(int for_template) {
		this.for_template = for_template;
	}
	public String getMaterial_num() {
		return material_num;
	}
	public void setMaterial_num(String material_num) {
		this.material_num = material_num;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getMetrics_name() {
		return metrics_name;
	}
	public void setMetrics_name(String metrics_name) {
		this.metrics_name = metrics_name;
	}
	public double getCurrent_value() {
		return current_value;
	}
	public void setCurrent_value(double current_value) {
		this.current_value = current_value;
	}
	public double getTarget_value() {
		return target_value;
	}
	public void setTarget_value(double target_value) {
		this.target_value = target_value;
	}
	public double getActual_value() {
		return actual_value;
	}
	public void setActual_value(double actual_value) {
		this.actual_value = actual_value;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public double getSelf_evaluate() {
		return self_evaluate;
	}
	public void setSelf_evaluate(double self_evaluate) {
		this.self_evaluate = self_evaluate;
	}
	public String getSelf_introduction() {
		return self_introduction;
	}
	public void setSelf_introduction(String self_introduction) {
		this.self_introduction = self_introduction;
	}
	public int getM_system_id() {
		return m_system_id;
	}
	public void setM_system_id(int m_system_id) {
		this.m_system_id = m_system_id;
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
	public int getOp_user() {
		return op_user;
	}
	public void setOp_user(int op_user) {
		this.op_user = op_user;
	}
	public char getProcess_status() {
		return process_status;
	}
	public void setProcess_status(char process_status) {
		this.process_status = process_status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCollection_major_id() {
		return collection_major_id;
	}
	public void setCollection_major_id(int collection_major_id) {
		this.collection_major_id = collection_major_id;
	}
	public int getMetrics_id() {
		return metrics_id;
	}
	public void setMetrics_id(int metrics_id) {
		this.metrics_id = metrics_id;
	}
	
}
