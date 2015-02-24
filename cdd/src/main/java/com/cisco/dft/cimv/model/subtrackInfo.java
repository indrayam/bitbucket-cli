package main.java.com.cisco.dft.cimv.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class subtrackInfo {
	private String subtrack_name;
	private String _id;
	private String quality_index;
	private String subtrack_owner_name;
	private String subtrack_owner_id;
	private int no_of_resources;
	private String release;
	
	public String getSubtrack_name() {
		return subtrack_name;
	}
	public void setSubtrack_name(String subtrack_name) {
		this.subtrack_name = subtrack_name;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getQuality_index() {
		return quality_index;
	}
	public void setQuality_index(String quality_index) {
		this.quality_index = quality_index;
	}
	public String getSubtrack_owner_name() {
		return subtrack_owner_name;
	}
	public void setSubtrack_owner_name(String subtrack_owner_name) {
		this.subtrack_owner_name = subtrack_owner_name;
	}
	public String getSubtrack_owner_id() {
		return subtrack_owner_id;
	}
	public void setSubtrack_owner_id(String subtrack_owner_id) {
		this.subtrack_owner_id = subtrack_owner_id;
	}
	public int getNo_of_resources() {
		return no_of_resources;
	}
	public void setNo_of_resources(int no_of_resources) {
		this.no_of_resources = no_of_resources;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	
}
