package com.philip.edu.eval.bean;

import java.util.Date;

public class TblCollectionMaterial {
    private Integer id;

    private Integer formPerformanceId;

    private Integer metricsId;

    private Integer materialId;

    private String required;

    private String doc;

    private String description;

    private Date createTime;

    private Date updateTime;
    
    private Integer materialcount;

    public Integer getMaterialcount() {
		return materialcount;
	}

	public void setMaterialcount(Integer materialcount) {
		this.materialcount = materialcount;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFormPerformanceId() {
        return formPerformanceId;
    }

    public void setFormPerformanceId(Integer formPerformanceId) {
        this.formPerformanceId = formPerformanceId;
    }

    public Integer getMetricsId() {
        return metricsId;
    }

    public void setMetricsId(Integer metricsId) {
        this.metricsId = metricsId;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required == null ? null : required.trim();
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc == null ? null : doc.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	@Override
	public String toString() {
		return "TblCollectionMaterial [id=" + id + ", formPerformanceId=" + formPerformanceId + ", metricsId="
				+ metricsId + ", materialId=" + materialId + ", required=" + required + ", doc=" + doc
				+ ", description=" + description + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", materialcount=" + materialcount + "]";
	}
    
}