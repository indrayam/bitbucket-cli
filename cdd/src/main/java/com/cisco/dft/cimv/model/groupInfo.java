package main.java.com.cisco.dft.cimv.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class groupInfo {
	private String group_name;
	private String _id;
	private String quality_index;
	private String group_owner_name;
	private String group_owner_id;
	private int no_of_resources;
	private String release;
	
	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getGroup_owner_id() {
		return group_owner_id;
	}

	public void setGroup_owner_id(String group_owner_id) {
		this.group_owner_id = group_owner_id;
	}

	public String getQuality_index() {
		return quality_index;
	}

	public void setQuality_index(String quality_index) {
		this.quality_index = quality_index;
	}

	public String getGroup_owner_name() {
		return group_owner_name;
	}

	public void setGroup_owner_name(String group_owner_name) {
		this.group_owner_name = group_owner_name;
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
