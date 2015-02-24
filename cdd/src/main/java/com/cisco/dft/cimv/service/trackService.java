package main.java.com.cisco.dft.cimv.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import main.java.com.cisco.dft.cimv.model.trackInfo;
import main.java.com.cisco.dft.cimv.util.callWebServiceUtil;
import main.java.com.cisco.dft.cimv.util.constantsUtil;
import main.java.com.cisco.dft.cimv.util.generalUtil;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

public class trackService {

	private trackService(){
		
	}
	
	private static final Logger LOG = Logger.getLogger(trackService.class);

	public static List<trackInfo> serviceGetAllTracks() {
		List<trackInfo> tracks = new LinkedList<trackInfo>();
		// logic to be written here
		return tracks;
	}

	public static List<trackInfo> serviceGetTracksByGroup(String groupId) {

		if (LOG.isInfoEnabled()) {
			LOG.info("START service to get track list for group ID : "
					+ groupId);
		}

		List<trackInfo> tracks = new LinkedList<trackInfo>();
		try {
			
			// calling the web service to get the list of quality index for the
			// list of tracks
			URL url = new URL(constantsUtil.getQualityIndexTracksURL + groupId);
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
			// calling the web service to get the list of tracks for groupId
			url = new URL(constantsUtil.getTrackByGroupURL + groupId);

			json = callWebServiceUtil.getJSONArrayWebService(url);
			if (json != null) {
				for (int i = 0; i < json.length(); i++) {
					ObjectMapper mapper = new ObjectMapper();
					
						// convert it to user class
						trackInfo objTrackInfo = mapper.readValue(json
								.getJSONObject(i).toString(), trackInfo.class);
						
						// using quality index hashmap to put the quality index
						// as per the trackID in the trackInfo object
						if (hmQI.get(objTrackInfo.get_id()) != null){
							// saving the quality index in track info object doing : 
								// 1. Make it to two decimal place
							    // 2. Convert to float get the status color as per the index value
								// 3. Putting the color in the groupInfo object in quality index
							objTrackInfo
									.setQuality_index(generalUtil.doQualityIndexToStatus(Float.parseFloat(generalUtil
											.roundFloatStringTwoDecimalUnit(hmQI
													.get(objTrackInfo.get_id())
													.get("quality_index")
													.toString()))));
						}
						else{
							objTrackInfo.setQuality_index("NA");
						}
						
						// add to the list
						tracks.add(objTrackInfo);
					
				}
			} else {
				return null;
			}

		} catch (MalformedURLException e) {
			LOG.error("serviceGetTracksByGroup : ", e);
		} catch (JsonGenerationException e) {
			LOG.error("serviceGetTracksByGroup : ", e);
		} catch (JsonMappingException e) {
			LOG.error("serviceGetTracksByGroup : ", e);
		} catch (IOException e) {
			LOG.error("serviceGetTracksByGroup : ", e);
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("END service to get track list ...");
		}
		return tracks;
	}

}
