package main.java.com.cisco.dft.cimv.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class developerMetricsInfo {
	
	private String _id;
	private String qi; 
	private String project; 
	private String major; 
	private String comments; 
	private String dev; 
	private float duplication; 
	private String critical; 
	private String code_coverage; 
	private double blocker; 
	private String loc;
	
	
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDev() {
		return dev;
	}
	public void setDev(String dev) {
		this.dev = dev;
	}
	public float getDuplication() {
		return duplication;
	}
	public void setDuplication(float duplication) {
		this.duplication = duplication;
	}
	public String getCritical() {
		return critical;
	}
	public void setCritical(String critical) {
		this.critical = critical;
	}
	public String getCode_coverage() {
		return code_coverage;
	}
	public void setCode_coverage(String code_coverage) {
		this.code_coverage = code_coverage;
	}
	public double getBlocker() {
		return blocker;
	}
	public void setBlocker(double blocker) {
		this.blocker = blocker;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getQi() {
		return qi;
	}
	public void setQi(String qi) {
		this.qi = qi;
	}
	
	
}
