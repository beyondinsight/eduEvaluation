package com.philip.edu.eval.bean;

import java.util.ArrayList;

public class PerformanceAdd {
	private PerformanceForm parent = null;
	private ArrayList children = new ArrayList();
	public PerformanceForm getParent() {
		return parent;
	}
	public void setParent(PerformanceForm parent) {
		this.parent = parent;
	}
	public ArrayList getChildren() {
		return children;
	}
	public void setChildren(ArrayList children) {
		this.children = children;
	}
	
	
}
