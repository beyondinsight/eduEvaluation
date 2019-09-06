package com.philip.edu.eval.bean;

import java.util.ArrayList;

public class MetricsAdd {
	private MetricsDetail parentMetrics;
	private ArrayList childrenMetrics = new ArrayList();
	public MetricsDetail getParentMetrics() {
		return parentMetrics;
	}
	public void setParentMetrics(MetricsDetail parentMetrics) {
		this.parentMetrics = parentMetrics;
	}
	public ArrayList getChildrenMetrics() {
		return childrenMetrics;
	}
	public void setChildrenMetrics(ArrayList childrenMetrics) {
		this.childrenMetrics = childrenMetrics;
	}
}
