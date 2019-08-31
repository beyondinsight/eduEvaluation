package com.philip.edu.eval.util;

public class EvalConstants {
	public static final char PROCESS_STATUS_NOT_INPUT = 'N';
	public static final char PROCESS_STATUS_INPUTING_INFORMATION = 'I';
	public static final char PROCESS_STATUS_SCHOOL_VERIFY = 'S';
	public static final char PROCESS_STATUS_SCHOOL_REJECT = 'R';
	public static final char PROCESS_STATUS_GOVERNMENT_VERIFY = 'G';
	public static final char PROCESS_STATUS_GOVERNMENT_REJECT = 'E';
	public static final char PROCESS_STATUS_GORVERNMENT_APPROVE = 'A';
	
	public static final String PROCESS_STATUS_DISPLAY_NOT_INPUT = "未填报";
	public static final String PROCESS_STATUS_DISPLAY_INPUTING_INFORMATION = "填报中";
	public static final String PROCESS_STATUS_DISPLAY_SCHOOL_VERIFY = "已提交，待学校审核";
	public static final String PROCESS_STATUS_DISPLAY_SCHOOL_REJECT = "学校退回，待重新填报";
	public static final String PROCESS_STATUS_DISPLAY_GOVERNMENT_VERIFY = "学校已审核,待教育厅审核";
	public static final String PROCESS_STATUS_DISPLAY_GOVERNMENT_REJECT = "教育厅退回，待重新填报";
	public static final String PROCESS_STATUS_DISPLAY_GOVERNMENT_APPROVE = "教育厅已审核入库";
	
	public static final int DEFAULT_METRICS_SYSTEM_ID = 1;
	
	public static final int TEMPLATE_PERFORMANCE_FORM_ID = 1;
	public static final int TEMPLATE_BASIC_FORM = 2;
	public static final int TEMPALTE_CAPITAL_PROGRESS_FORM_ID = 3;
	
	public static final int METRIC_BASIC_MAJOR_BASIC_ID = 10;
	public static final int METRIC_BASIC_SELF_EVAL_ID = 11;
	
	public static final char COLLECTION_STATUS_INACTIVE = 'I';
	public static final char COLLECTION_STATUS_ACTIVE = 'A';
	public static final char COLLECTION_STATUS_STOP = 'S';
	public static final String COLLECTION_STATUS_INACTIVE_DISPLAY = "未启用";
	public static final String COLLECTION_STATUS_ACTIVE_DISPLAY = "启用";
	public static final String COLLECTION_STATUS_STOP_DISPLAY = "禁用";
	
	public static final int LOGIN_STATUS_USER_NO_EXSITS = 2;
	public static final int LOGIN_STATUS_PASSWORD_NOT_RIGHT = 3;
	public static final int LOGIN_STATUS_SUCCESS = 1;
	
	public static final char MATERIAL_IS_REQUIRED = 'Y';
	public static final char MATERIAL_IS_REQUIRED_NO = 'N';
}
