package com.philip.edu.eval.bean;

public class CapitalProgressInfo {
	private String item_name;
	private int performance_id;
	private String materials_num;
	private String metrics_name;
	private double actural_num;
	private String unit;
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getPerformance_id() {
		return performance_id;
	}
	public void setPerformance_id(int performance_id) {
		this.performance_id = performance_id;
	}
	public String getMaterials_num() {
		return materials_num;
	}
	public void setMaterials_num(String materials_num) {
		this.materials_num = materials_num;
	}
	public String getMetrics_name() {
		return metrics_name;
	}
	public void setMetrics_name(String metrics_name) {
		this.metrics_name = metrics_name;
	}
	public double getActural_num() {
		return actural_num;
	}
	public void setActural_num(double actural_num) {
		this.actural_num = actural_num;
	}
	
	
}
