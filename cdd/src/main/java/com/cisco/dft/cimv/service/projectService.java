package main.java.com.cisco.dft.cimv.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import main.java.com.cisco.dft.cimv.model.projectInfo;
import main.java.com.cisco.dft.cimv.util.callWebServiceUtil;
import main.java.com.cisco.dft.cimv.util.constantsUtil;
import main.java.com.cisco.dft.cimv.util.generalUtil;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

public class projectService {

	private projectService(){
		
	}
	
	private static final Logger LOG = Logger.getLogger(projectService.class);

	public static List<projectInfo> serviceGetAllProjects(String groupId) {

		if (LOG.isInfoEnabled()) {
			LOG.info("START service to get project list for group ID : "
					+ groupId);
		}
		List<projectInfo> projects = new LinkedList<projectInfo>();
		try {

			URL url = new URL(constantsUtil.getAllProjectsByGroupURL + groupId);
			JSONArray json = callWebServiceUtil.getJSONArrayWebService(url);
			if (json != null) {

				for (int i = 0; i < json.length(); i++) {
					ObjectMapper mapper = new ObjectMapper();
					
					// convert it to user class
					projectInfo objProjectInfo = mapper.readValue(json
							.getJSONObject(i).toString(), projectInfo.class);

					// add to the list
					projects.add(objProjectInfo);
				}
			} else {
				return null;
			}
		} catch (JsonGenerationException e) {
			LOG.error("serviceGetAllProjects : ", e);
		} catch (JsonMappingException e) {
			LOG.error("serviceGetAllProjects : ", e);
		} catch (IOException e) {
			LOG.error("serviceGetAllProjects : ", e);
		}
		return projects;
	}

	public static List<projectInfo> serviceGetProjectBySubTrack(String groupId,
			String trackId, String subtrackId) {

		if (LOG.isInfoEnabled()) {
			LOG.info("START service to get project list for group ID : "
					+ groupId + " and track ID : " + trackId + " and subtrack ID : " + subtrackId);
		}

		List<projectInfo> projects = new LinkedList<projectInfo>();

		try {
			// calling the web service to get the list of quality index for the
			// list of projects
			URL url = new URL(constantsUtil.getQualityIndexProjectsURL
					+ subtrackId);
			JSONArray json = callWebServiceUtil.getJSONArrayWebService(url);
			Map<String, JSONObject> hmQI = new HashMap<String, JSONObject>();
			if (json != null) {
				for (int i = 0; i < json.length(); i++) {
					// get the json objects from the json array and put it in hash
					// with key as id and value as json object
					hmQI.put(json.getJSONObject(i).get("_id").toString(),
							json.getJSONObject(i));
				}
			}
			
			// calling the web service to get the list of projects
			url = new URL(constantsUtil.getProjectBySubTrackURL + groupId
					+ "&trackID=" + trackId + "&subtrackID=" + subtrackId);
			json = callWebServiceUtil.getJSONArrayWebService(url);
			if (json != null) {
				for (int i = 0; i < json.length(); i++) {
					ObjectMapper mapper = new ObjectMapper();
					
						// convert it to user class
						projectInfo objProjectInfo = mapper
								.readValue(json.getJSONObject(i).toString(),
										projectInfo.class);

						// using quality index hashmap to put the quality index
						// as per the projectID in the projectInfo object
						if (hmQI.get(objProjectInfo.get_id()) != null) {
							// saving the quality index in project info object doing :
							// 1. Make it to two decimal place
							// 2. Convert to float get the status color as per the index value
							// 3. Putting the color in the projectInfo object in quality index
							objProjectInfo
									.setQuality_index(generalUtil.doQualityIndexToStatus(Float.parseFloat(generalUtil
											.roundFloatStringTwoDecimalUnit(hmQI.get(objProjectInfo.get_id())
											.get("quality_index").toString()))));
							
							
							// for plsql project set the unit test coverage as NA
							if("plsql".equalsIgnoreCase(hmQI.get(objProjectInfo.get_id()).get("project_type").toString())){
								objProjectInfo.setUnit_test_coverage("NA");
							}
							// else the value is stored
							else{
								objProjectInfo.setUnit_test_coverage(hmQI.get(objProjectInfo.get_id()).get("unit_test_coverage").toString() + "%");
							}
							
							// adding the other values directly from the json which was stored in map and put into projectInfo model
							objProjectInfo.setLast_build_timestamp(generalUtil.timestampReformation(hmQI.get(objProjectInfo.get_id()).get("last_build_timestamp").toString()));
							objProjectInfo.setLast_svn_checkin_timestamp(generalUtil.timestampReformation(hmQI.get(objProjectInfo.get_id()).get("last_svn_checkin_timestamp").toString()));
							objProjectInfo.setLast_sonar_code_analysis_timestamp(generalUtil.timestampReformation(hmQI.get(objProjectInfo.get_id()).get("last_sonar_code_analysis_timestamp").toString()));
							
						} else {
							objProjectInfo.setQuality_index("NA");
							objProjectInfo.setLast_build_timestamp("NA");
							objProjectInfo.setLast_svn_checkin_timestamp("NA");
							objProjectInfo.setLast_sonar_code_analysis_timestamp("NA");
							objProjectInfo.setUnit_test_coverage("NA");
						}

						// add to the list
						projects.add(objProjectInfo);
	
				}
			} else {
				return null;
			}
		} catch (MalformedURLException e) {
			LOG.error("serviceGetProjectBySubTrack : ", e);
		} catch (JsonGenerationException e) {
			LOG.error("serviceGetProjectBySubTrack : ", e);
		} catch (JsonMappingException e) {
			LOG.error("serviceGetProjectBySubTrack : ", e);
		} catch (IOException e) {
			LOG.error("serviceGetProjectBySubTrack : ", e);
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("END service to get project list ...");
		}
		return projects;
	}

}
