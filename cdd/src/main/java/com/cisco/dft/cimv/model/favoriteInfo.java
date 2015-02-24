package main.java.com.cisco.dft.cimv.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class favoriteInfo implements Serializable{
	private String userId;
	private String fav_url;
	private String fav_default;
	private String fav_name;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFav_url() {
		return fav_url;
	}
	public void setFav_url(String fav_url) {
		this.fav_url = fav_url;
	}
	public String getFav_default() {
		return fav_default;
	}
	public void setFav_default(String fav_default) {
		this.fav_default = fav_default;
	}
	public String getFav_name() {
		return fav_name;
	}
	public void setFav_name(String fav_name) {
		this.fav_name = fav_name;
	}
	
}
