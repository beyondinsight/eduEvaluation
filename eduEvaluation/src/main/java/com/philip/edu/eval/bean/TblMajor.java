package com.philip.edu.eval.bean;

import java.util.Date;

public class TblMajor {
    private Integer id;

    private String majorName;

    private String majorCode;

    private String majorClass;

    private String mainLecture;

    private String isFirstClass;

    private String memo;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName == null ? null : majorName.trim();
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode == null ? null : majorCode.trim();
    }

    public String getMajorClass() {
        return majorClass;
    }

    public void setMajorClass(String majorClass) {
        this.majorClass = majorClass == null ? null : majorClass.trim();
    }

    public String getMainLecture() {
        return mainLecture;
    }

    public void setMainLecture(String mainLecture) {
        this.mainLecture = mainLecture == null ? null : mainLecture.trim();
    }

    public String getIsFirstClass() {
        return isFirstClass;
    }

    public void setIsFirstClass(String isFirstClass) {
        this.isFirstClass = isFirstClass == null ? null : isFirstClass.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
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
}