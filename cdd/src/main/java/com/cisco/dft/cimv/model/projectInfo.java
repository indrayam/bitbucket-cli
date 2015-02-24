package main.java.com.cisco.dft.cimv.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class projectInfo {
	private String _id;
	private String project_name;
	private String dept_number;
	private String provisioning_pm;
	private String clarity_id;
	private String admin_ad_grp;
	private String dev_ad_grp;
	private String sonar_key;
	private String start_date;
	private String end_date;
	private String release;
	private String prog_context;
	private String service_asset;
	private Long current_build_number;
	private String quality_index;
	int no_of_resources;
	
	private String unit_test_coverage;
	private String last_build_timestamp;
	private String last_svn_checkin_timestamp;
	private String last_sonar_code_analysis_timestamp;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getDept_number() {
		return dept_number;
	}
	public void setDept_number(String dept_number) {
		this.dept_number = dept_number;
	}
	public String getProvisioning_pm() {
		return provisioning_pm;
	}
	public void setProvisioning_pm(String provisioning_pm) {
		this.provisioning_pm = provisioning_pm;
	}
	public String getClarity_id() {
		return clarity_id;
	}
	public void setClarity_id(String clarity_id) {
		this.clarity_id = clarity_id;
	}
	public String getAdmin_ad_grp() {
		return admin_ad_grp;
	}
	public void setAdmin_ad_grp(String admin_ad_grp) {
		this.admin_ad_grp = admin_ad_grp;
	}
	public String getDev_ad_grp() {
		return dev_ad_grp;
	}
	public void setDev_ad_grp(String dev_ad_grp) {
		this.dev_ad_grp = dev_ad_grp;
	}
	public String getSonar_key() {
		return sonar_key;
	}
	public void setSonar_key(String sonar_key) {
		this.sonar_key = sonar_key;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public String getProg_context() {
		return prog_context;
	}
	public void setProg_context(String prog_context) {
		this.prog_context = prog_context;
	}
	public String getService_asset() {
		return service_asset;
	}
	public void setService_asset(String service_asset) {
		this.service_asset = service_asset;
	}
	public Long getCurrent_build_number() {
		return current_build_number;
	}
	public void setCurrent_build_number(Long current_build_number) {
		this.current_build_number = current_build_number;
	}
	public String getQuality_index() {
		return quality_index;
	}
	public void setQuality_index(String quality_index) {
		this.quality_index = quality_index;
	}
	public int getNo_of_resources() {
		return no_of_resources;
	}
	public void setNo_of_resources(int no_of_resources) {
		this.no_of_resources = no_of_resources;
	}
	public String getUnit_test_coverage() {
		return unit_test_coverage;
	}
	public void setUnit_test_coverage(String unit_test_coverage) {
		this.unit_test_coverage = unit_test_coverage;
	}
	public String getLast_build_timestamp() {
		return last_build_timestamp;
	}
	public void setLast_build_timestamp(String last_build_timestamp) {
		this.last_build_timestamp = last_build_timestamp;
	}
	public String getLast_svn_checkin_timestamp() {
		return last_svn_checkin_timestamp;
	}
	public void setLast_svn_checkin_timestamp(String last_svn_checkin_timestamp) {
		this.last_svn_checkin_timestamp = last_svn_checkin_timestamp;
	}
	public String getLast_sonar_code_analysis_timestamp() {
		return last_sonar_code_analysis_timestamp;
	}
	public void setLast_sonar_code_analysis_timestamp(
			String last_sonar_code_analysis_timestamp) {
		this.last_sonar_code_analysis_timestamp = last_sonar_code_analysis_timestamp;
	}
}
