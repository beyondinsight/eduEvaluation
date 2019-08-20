package com.philip.edu.eval.metrics;

import java.util.List;

import com.philip.edu.eval.bean.MetricsDetail;
import com.philip.edu.eval.bean.MetricsSystem;
import com.philip.edu.eval.bean.SupportMaterial;

public interface MetricsService {
	//metrics system:
	public boolean createMetricsSystem(MetricsSystem system);
	public MetricsSystem getMetricsSystemById(int id);
	public boolean updateMetricsSystem(MetricsSystem system);
	public boolean setMetricsSystemStatus(int system_id, char status);
	public List<MetricsSystem> getMetricsSystemList();
	public List<MetricsSystem> getActiveMetricsSystemList();
	
	//metrics detail:
	public MetricsDetail getMetricsDetail(int id);
	public List<MetricsDetail> getMetricsSystemDetail(int system_id);
	public boolean createMetricsDetail(MetricsDetail metrics);
	public boolean updateMetricsDetail(MetricsDetail metrics);
	public boolean deleteMetricsDetail(int metrics_id);
	public List<MetricsDetail> getChildMetricsDetail(int parent_id);
	
	//support material:
	public boolean createSupportMaterial(SupportMaterial material);
	public boolean updateSupportMaterial(SupportMaterial material);
	public boolean deleteSupportMaterial(int material_id);
}
