package main.java.com.cisco.dft.cimv.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import main.java.com.cisco.dft.cimv.model.groupInfo;
import main.java.com.cisco.dft.cimv.util.callWebServiceUtil;
import main.java.com.cisco.dft.cimv.util.constantsUtil;
import main.java.com.cisco.dft.cimv.util.generalUtil;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

public class groupService {

	private groupService(){
		
	}
	
	private static final Logger LOG = Logger.getLogger(groupService.class);

	public static List<groupInfo> serviceGetAllGroups(String release) {

		if (LOG.isInfoEnabled()) {
			LOG.info("START service to get list of groups ...");
		}

		List<groupInfo> groups = new LinkedList<groupInfo>();
		try {

			// calling the web service to get the list of quality index for the
			// list of groups
			URL url = new URL(constantsUtil.getQualityIndexGroupsURL);
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
			// calling the web service to get the list of groups
			url = new URL(constantsUtil.getAllGroupURL + release);
			json = callWebServiceUtil.getJSONArrayWebService(url);
			if (json != null) {
				for (int i = 0; i < json.length(); i++) {
					ObjectMapper mapper = new ObjectMapper();
					
						// convert it to user class
						groupInfo objGroupInfo = mapper.readValue(json
								.getJSONObject(i).toString(), groupInfo.class);

						// using quality index hashmap to put the quality index
						// as per the groupID in the groupInfo object
						if (hmQI.get(objGroupInfo.get_id()) != null){
							// saving the quality index in group info object doing : 
								// 1. Make it to two decimal place
							    // 2. Convert to float get the status color as per the index value
								// 3. Putting the color in the groupInfo object in quality index
							objGroupInfo
									.setQuality_index(generalUtil.doQualityIndexToStatus(Float.parseFloat(generalUtil
											.roundFloatStringTwoDecimalUnit(hmQI
													.get(objGroupInfo.get_id())
													.get("quality_index")
													.toString()))));
						}
						else{
							objGroupInfo.setQuality_index("NA");
						}
						
						// add to the list
						groups.add(objGroupInfo);

				}
			} else {
				return null;
			}
		} catch (MalformedURLException e) {
			LOG.error("serviceGetAllGroups : ", e);
		} catch (JsonGenerationException e) {
			LOG.error("serviceGetAllGroups : ", e);
		} catch (JsonMappingException e) {
			LOG.error("serviceGetAllGroups : ", e);
		} catch (IOException e) {
			LOG.error("serviceGetAllGroups : ", e);
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("END service to get list of groups ...");
		}
		return groups;
	}

}
