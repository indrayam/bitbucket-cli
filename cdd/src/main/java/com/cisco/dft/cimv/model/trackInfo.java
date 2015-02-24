package main.java.com.cisco.dft.cimv.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class trackInfo {
	private String track_name;
	private String _id;
	private String quality_index;
	private String track_owner_name;
	private String track_owner_id;
	int no_of_resources;
	private String release;
	
	public String getTrack_name() {
		return track_name;
	}
	public void setTrack_name(String track_name) {
		this.track_name = track_name;
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
	public String getTrack_owner_name() {
		return track_owner_name;
	}
	public void setTrack_owner_name(String track_owner_name) {
		this.track_owner_name = track_owner_name;
	}
	public String getTrack_owner_id() {
		return track_owner_id;
	}
	public void setTrack_owner_id(String track_owner_id) {
		this.track_owner_id = track_owner_id;
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
