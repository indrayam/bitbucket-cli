package main.java.com.cisco.dft.cimv.model;

import java.util.Map;

public class adoptionMetricsInfo {
	
	private String group_name;
	private String group_id;
	private String group_owner;
	private int no_of_projects;
	private String total_lines_of_code;
	private String total_no_of_builds;
	private float group_QI;
	private Map<String, Integer> projectType;
	
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getGroup_owner() {
		return group_owner;
	}
	public void setGroup_owner(String group_owner) {
		this.group_owner = group_owner;
	}
	public int getNo_of_projects() {
		return no_of_projects;
	}
	public void setNo_of_projects(int no_of_projects) {
		this.no_of_projects = no_of_projects;
	}
	public String getTotal_lines_of_code() {
		return total_lines_of_code;
	}
	public void setTotal_lines_of_code(String total_lines_of_code) {
		this.total_lines_of_code = total_lines_of_code;
	}
	public String getTotal_no_of_builds() {
		return total_no_of_builds;
	}
	public void setTotal_no_of_builds(String total_no_of_builds) {
		this.total_no_of_builds = total_no_of_builds;
	}

	public Map<String, Integer> getProjectType() {
		return projectType;
	}
	public void setProjectType(Map<String, Integer> projectType) {
		this.projectType = projectType;
	}
	public float getGroup_QI() {
		return group_QI;
	}
	public void setGroup_QI(float group_QI) {
		this.group_QI = group_QI;
	}
	
	
}
