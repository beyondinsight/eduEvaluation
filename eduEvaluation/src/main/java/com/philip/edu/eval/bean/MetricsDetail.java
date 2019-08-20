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
