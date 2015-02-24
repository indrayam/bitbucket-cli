package main.java.com.cisco.dft.cimv.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import main.java.com.cisco.dft.cimv.model.developerMetricsInfo;
import main.java.com.cisco.dft.cimv.model.userInfo;
import main.java.com.cisco.dft.cimv.util.callWebServiceUtil;
import main.java.com.cisco.dft.cimv.util.constantsUtil;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;

public class developerService {

	private developerService(){
		
	}
	
	private static final Logger LOG = Logger.getLogger(developerService.class);
	
	public static List<developerMetricsInfo> serviceGetProjectWiseDeveloperMetrics(String project) {

		if (LOG.isInfoEnabled()) {
			LOG.info("START service to get developer metrics of all developers of project : "
					+ project);
		}
		// developer metrics for a developer of the project
		List<developerMetricsInfo> devMetrics = new LinkedList<developerMetricsInfo>();
		try {
			
			// calling the web service to get developer metrics of all developers of a project
			URL url = new URL(constantsUtil.getProjectWiseDeveloperMetricsURL + project);
			JSONArray json = callWebServiceUtil.getJSONArrayWebService(url);
			if(json != null){
				for(int i = 0 ; i < json.length() ; i++){
					// convert it to user class
					ObjectMapper mapper = new ObjectMapper();
					developerMetricsInfo objdeveloperMetricsInfo = mapper.readValue(json
							.getJSONObject(i).toString(), developerMetricsInfo.class);
					
					// get the details of the developer and form an object to set user info
					userInfo objUserInfo = loginService.getUserInfo(objdeveloperMetricsInfo.getDev().split("@")[0]);
					if(objUserInfo != null){
						objdeveloperMetricsInfo.setDev(objUserInfo.getFullName());
					}
					devMetrics.add(objdeveloperMetricsInfo);
				}
			}
			
		}catch (MalformedURLException e) {
			LOG.error("serviceGetProjectWiseDeveloperMetrics : ", e);
		} catch (JsonParseException e) {
			LOG.error("serviceGetProjectWiseDeveloperMetrics : ", e);
		} catch (JsonMappingException e) {
			LOG.error("serviceGetProjectWiseDeveloperMetrics : ", e);
		} catch (JSONException e) {
			LOG.error("serviceGetProjectWiseDeveloperMetrics : ", e);
		} catch (IOException e) {
			LOG.error("serviceGetProjectWiseDeveloperMetrics : ", e);
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("END service to get developer metrics of project ...");
		}
		
		return devMetrics;

	}
	
	public static List<developerMetricsInfo> serviceGetAllDeveloperAllMetrics() {

		if (LOG.isInfoEnabled()) {
			LOG.info("START service to get developer metrics of all developers ");
		}
		// developer metrics for a developer
		List<developerMetricsInfo> devMetrics = new LinkedList<developerMetricsInfo>();
		try {
			
			// calling the web service to get developer metrics of all developers of a project
			URL url = new URL(constantsUtil.getAllDeveloperAllMetricsURL);
			JSONArray json = callWebServiceUtil.getJSONArrayWebService(url);
			if(json != null){
				for(int i = 0 ; i < json.length() ; i++){
					// convert it to user class
					ObjectMapper mapper = new ObjectMapper();
					developerMetricsInfo objdeveloperMetricsInfo = mapper.readValue(json
							.getJSONObject(i).toString(), developerMetricsInfo.class);
					
					// get the details of the developer and form an object to set user info
					userInfo objUserInfo = loginService.getUserInfo(objdeveloperMetricsInfo.get_id().split("@")[0]);
					if(objUserInfo != null){
						objdeveloperMetricsInfo.setDev(objUserInfo.getFullName());
					}
					else{
						objdeveloperMetricsInfo.setDev(objdeveloperMetricsInfo.get_id());
					}
					devMetrics.add(objdeveloperMetricsInfo);
				}
			}
			
		}catch (MalformedURLException e) {
			LOG.error("serviceGetProjectWiseDeveloperMetrics : ", e);
		} catch (JsonParseException e) {
			LOG.error("serviceGetProjectWiseDeveloperMetrics : ", e);
		} catch (JsonMappingException e) {
			LOG.error("serviceGetProjectWiseDeveloperMetrics : ", e);
		} catch (JSONException e) {
			LOG.error("serviceGetProjectWiseDeveloperMetrics : ", e);
		} catch (IOException e) {
			LOG.error("serviceGetProjectWiseDeveloperMetrics : ", e);
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("END service to get all developer metrics ...");
		}
		
		return devMetrics;

	}
}
