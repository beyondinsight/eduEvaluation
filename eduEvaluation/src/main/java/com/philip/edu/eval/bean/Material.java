package com.philip.edu.eval.bean;

public class Material {
	private int id;
	private String material_name;
	private String memo;
	private int metrics_id;
	
	
	public int getMetrics_id() {
		return metrics_id;
	}
	public void setMetrics_id(int metrics_id) {
		this.metrics_id = metrics_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMaterial_name() {
		return material_name;
	}
	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
