package main.java.com.cisco.dft.cimv.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import main.java.com.cisco.dft.cimv.model.projectInfo;
import main.java.com.cisco.dft.cimv.model.subtrackInfo;
import main.java.com.cisco.dft.cimv.util.callWebServiceUtil;
import main.java.com.cisco.dft.cimv.util.constantsUtil;
import main.java.com.cisco.dft.cimv.util.generalUtil;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressWarnings({"rawtypes","unchecked"})
public class subtrackService {

	private subtrackService(){
		
	}
	
	private static final Logger LOG = Logger.getLogger(subtrackService.class);

	public static List<subtrackInfo> serviceGetAllSubTracks() {
		List<subtrackInfo> subtracks = new LinkedList<subtrackInfo>();
		// logic to be written here
		return subtracks;
	}

	
	public static List serviceGetSubTracksByTrack(String groupId, String trackId) {

		if (LOG.isInfoEnabled()) {
			LOG.info("START service to get subtrack list for group ID : "
					+ groupId + " and track ID : " + trackId);
		}

		List<subtrackInfo> subtracks = new LinkedList<subtrackInfo>();
		List<projectInfo> projects = new LinkedList<projectInfo>();
		List subtracksANDprojects = new LinkedList<Object>();
		int countSubTrack = 0;
		int countProject = 0;
		
		try {
			// calling the web service to get the list of quality index for the
			// list of subtracks
			URL url = new URL(constantsUtil.getQualityIndexSubTracksURL + trackId);
			JSONArray json = callWebServiceUtil.getJSONArrayWebService(url);
			Map<String, JSONObject> hmQI = new HashMap<String, JSONObject>();
			if (json != null) {
				for (int i = 0; i < json.length(); i++) {
					// get the jason objects from the json array and put it in hash
					// with key as id and value as json object
					hmQI.put(json.getJSONObject(i).get("_id").toString(),
							json.getJSONObject(i));
				}
			}
			
			// calling the web service to get the list of subtracks for groupId
			url = new URL(constantsUtil.getSubTrackByTrackURL + groupId + "&trackID=" + trackId);

			json = callWebServiceUtil.getJSONArrayWebService(url);
			if (json != null) {
				for (int i = 0; i < json.length(); i++) {
					ObjectMapper mapper = new ObjectMapper();
					
						if(json.getJSONObject(i).get("_id").toString().startsWith("s_")){
							countSubTrack++; // counting no of sub-tracks
							
						// convert it to user class
						subtrackInfo objSubTrackInfo = mapper.readValue(json
								.getJSONObject(i).toString(), subtrackInfo.class);
						
						// using quality index hashmap to put the quality index
						// as per the subtrackID in the subtrackInfo object
						if (hmQI.get(objSubTrackInfo.get_id()) != null){
							// saving the quality index in subtrack info object doing : 
								// 1. Make it to two decimal place
							    // 2. Convert to float get the status color as per the index value
								// 3. Putting the color in the groupInfo object in quality index
							objSubTrackInfo
									.setQuality_index(generalUtil.doQualityIndexToStatus(Float.parseFloat(generalUtil
											.roundFloatStringTwoDecimalUnit(hmQI
													.get(objSubTrackInfo.get_id())
													.get("quality_index")
													.toString()))));
						}
						else{
							objSubTrackInfo.setQuality_index("NA");
						}
						
						// add to the list
						subtracks.add(objSubTrackInfo);
						
						}else{
							countProject++; // counting no of projects
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
				}
			} else {
				return null;
			}

		} catch (MalformedURLException e) {
			LOG.error("serviceGetSubTracksByTrack : ", e);
		} catch (JsonGenerationException e) {
			 LOG.error("serviceGetSubTracksByTrack : ", e);
		} catch (JsonMappingException e) {
			LOG.error("serviceGetSubTracksByTrack : ", e);
		} catch (IOException e) {
			LOG.error("serviceGetSubTracksByTrack : ", e);
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("END service to get subtrack list ...");
		}
		
		// to make sure sub-tracks table not shown on UI
		if(countSubTrack == 0){
			subtracks = null;
		}
		
		// to make sure projects table not shown on UI
		if(countProject == 0){
			projects = null;
		}
		
		// adding both the list to one list to pass to the controller
		subtracksANDprojects.add(subtracks);
		subtracksANDprojects.add(projects);
		
		return subtracksANDprojects;
	}

}
