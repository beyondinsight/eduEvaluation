package com.philip.edu.eval.bean;

import java.util.List;

public class MetricsDetail {
	private int id;
	private int m_system_id;
	private String metrics_name;
	private int pid;
	private int level;
	private String description;
	private int order;
	private String unit;
	private String metrics_code;
	private String level1_name;
	private String material_num;
	 
	public String getLevel1_name() {
		return level1_name;
	}
	public void setLevel1_name(String level1_name) {
		this.level1_name = level1_name;
	}
	public String getMaterial_num() {
		return material_num;
	}
	public void setMaterial_num(String material_num) {
		this.material_num = material_num;
	}
	public String getMetrics_code() {
		return metrics_code;
	}
	public void setMetrics_code(String metrics_code) {
		this.metrics_code = metrics_code;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	private List<SupportMaterial> materials;
	
	
	public List<SupportMaterial> getMaterials() {
		return materials;
	}
	public void setMaterials(List<SupportMaterial> materials) {
		this.materials = materials;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getM_system_id() {
		return m_system_id;
	}
	public void setM_system_id(int m_system_id) {
		this.m_system_id = m_system_id;
	}
	public String getMetrics_name() {
		return metrics_name;
	}
	public void setMetrics_name(String metrics_name) {
		this.metrics_name = metrics_name;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
}
