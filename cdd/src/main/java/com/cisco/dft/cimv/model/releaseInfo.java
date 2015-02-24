package main.java.com.cisco.dft.cimv.model;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class releaseInfo implements Serializable{
	
	private String _id;
	private Date end_date;
	private boolean default_view;
	
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date){
		this.end_date = end_date;
	}
		

	public boolean getDefault_view() {
		return default_view;
	}

	public void setDefault_view(boolean default_view) {
		this.default_view = default_view;
	}

}