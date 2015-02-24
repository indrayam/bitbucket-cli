package main.java.com.cisco.dft.cimv.model;

import main.java.com.cisco.dft.cimv.util.generalUtil;

public class trendInfo {
	
	int lines_of_code;
	float technical_debt;
	float rules_compliance;
	int blocker_issues;
	int critical_issues;
	int major_issues;
	String comments;
	String duplication;
	String complexity;
	String unit_test_coverage;
	float quality_index;
	String resource_id;
	String timestamp;
	private String release;
	
	
	public int getLines_of_code() {
		return lines_of_code;
	}
	public void setLines_of_code(int lines_of_code) {
		this.lines_of_code = lines_of_code;
	}
	public float getTechnical_debt() {
		return technical_debt;
	}
	public void setTechnical_debt(float technical_debt) {
		this.technical_debt = technical_debt;
	}
	public float getRules_compliance() {
		return rules_compliance;
	}
	public void setRules_compliance(float rules_compliance) {
		this.rules_compliance = rules_compliance;
	}
	public int getBlocker_issues() {
		return blocker_issues;
	}
	public void setBlocker_issues(int blocker_issues) {
		this.blocker_issues = blocker_issues;
	}
	public int getCritical_issues() {
		return critical_issues;
	}
	public void setCritical_issues(int critical_issues) {
		this.critical_issues = critical_issues;
	}
	public int getMajor_issues() {
		return major_issues;
	}
	public void setMajor_issues(int major_issues) {
		this.major_issues = major_issues;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDuplication() {
		return duplication;
	}
	public void setDuplication(String duplication) {
		this.duplication = duplication;
	}
	public String getComplexity() {
		return complexity;
	}
	public void setComplexity(String complexity) {
		this.complexity = complexity;
	}
	public String getUnit_test_coverage() {
		return unit_test_coverage;
	}
	public void setUnit_test_coverage(String unit_test_coverage) {
		this.unit_test_coverage = unit_test_coverage;
	}
	public float getQuality_index() {
		return quality_index;
	}
	public void setQuality_index(float quality_index) {
		this.quality_index = quality_index;
	}
	public String getResource_id() {
		return resource_id;
	}
	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = generalUtil.trimYearAndSecondsFromTimestamp(timestamp);
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
}
