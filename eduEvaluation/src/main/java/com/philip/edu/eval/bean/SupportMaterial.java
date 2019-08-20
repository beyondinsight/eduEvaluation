package com.philip.edu.eval.bean;

public class SupportMaterial {
	private int id;
	private int metrics_id;
	private int material_name;
	private char type;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMetrics_id() {
		return metrics_id;
	}
	public void setMetrics_id(int metrics_id) {
		this.metrics_id = metrics_id;
	}
	public int getMaterial_name() {
		return material_name;
	}
	public void setMaterial_name(int material_name) {
		this.material_name = material_name;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
