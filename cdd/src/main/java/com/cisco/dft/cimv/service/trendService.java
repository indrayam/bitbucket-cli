package main.java.com.cisco.dft.cimv.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import main.java.com.cisco.dft.cimv.model.trendInfo;
import main.java.com.cisco.dft.cimv.util.callWebServiceUtil;
import main.java.com.cisco.dft.cimv.util.constantsUtil;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

/* The function basically takes the trend model and from that taking the time stamp and quality index and using this 
 * forming this json object which is passed to the UI to form the graph.
 */

public class trendService {
	
	private trendService(){
		
	}
	
	private static final Logger LOG = Logger.getLogger(trendService.class);
	
	// function to create the cols part of the json for the chart with one y-axis
	public static JSONArray createColsForChartJsonOneAxis(String colsLabel1, String colsLabel2){
		
		JSONObject objjson = new JSONObject();
		JSONArray arrjson = new JSONArray();
		
		objjson.put("label", colsLabel1);
		objjson.put("type", "string");
		arrjson.put(objjson);
		objjson = new JSONObject();
		objjson.put("label", colsLabel2);
		objjson.put("type", "number");
		arrjson.put(objjson);
		
		return arrjson;
	}
	
	// function to create the cols part of the json for the chart with one y-axis
		public static JSONArray createColsForChartJsonTwoAxis(String colsLabel1, String colsLabel2, String colsLabel3){
			
			JSONObject objjson = new JSONObject();
			JSONArray arrjson = new JSONArray();
			
			objjson.put("id", "");
			objjson.put("label", colsLabel1);
			objjson.put("type", "string");
			arrjson.put(objjson);
			objjson = new JSONObject();
			objjson.put("id", "0");
			objjson.put("label", colsLabel2);
			objjson.put("type", "number");
			arrjson.put(objjson);
			objjson = new JSONObject();
			objjson.put("id", "1");
			objjson.put("label", colsLabel3);
			objjson.put("type", "number");
			arrjson.put(objjson);
			
			return arrjson;
		}
	
	public static JSONObject getQualityIndexLOCTimeData(String Id){
		
		JSONObject objjson = new JSONObject();
		
		if (LOG.isInfoEnabled()) {
			LOG.info("START service to get quality index time data for ID : "
					+ Id);
		}

		try {
			// calling the web service to get the trends model
			URL url = new URL(constantsUtil.getQualityIndexAndTimeDataURL
					+ Id);
			JSONArray arrjson = callWebServiceUtil.getJSONArrayWebService(url);

			if (arrjson != null) {
				
				objjson.put("cols", createColsForChartJsonTwoAxis("Time Stamp", "Quality Index", "Lines Of Code"));
				JSONArray arrtempjason2 = new JSONArray();
				
				// to filter only the latest 10 records to be showed on the chart
				int difference = 1;
				if(arrjson.length() > 10){
					difference = arrjson.length() / 10;
				}
				
				// iterating 10 times to take the latest data for the json object of the chart
				for (int i = 0; i < arrjson.length();) {
					ObjectMapper mapper = new ObjectMapper();
					
					JSONObject tempjason1 = new JSONObject();
					JSONArray arrtempjason1 = new JSONArray();
					JSONObject tempjason2 = new JSONObject();
					
						// convert it to user class
						trendInfo objTrendInfo = mapper
								.readValue(arrjson.getJSONObject(i).toString(),
										trendInfo.class);
						
						tempjason1.put("v", objTrendInfo.getTimestamp());
						arrtempjason1.put(tempjason1);
						tempjason1 = new JSONObject();
						tempjason1.put("v", (Math.round(objTrendInfo.getQuality_index() * 100)/100.0));
						arrtempjason1.put(tempjason1);
						tempjason1 = new JSONObject();
						tempjason1.put("v", objTrendInfo.getLines_of_code());
						arrtempjason1.put(tempjason1);
						tempjason2.put("c", arrtempjason1);
						arrtempjason2.put(tempjason2);
						
						// changing the index so as to get random values between all the values
						i = i + difference;
		
				}
				objjson.put("rows", arrtempjason2);
			} else {
				return null;
			}
		} catch (MalformedURLException e) {
			LOG.error("getQualityIndexLOCTimeData : ", e);
		} catch (JsonGenerationException e) {
			LOG.error("getQualityIndexLOCTimeData : ", e);
		} catch (JsonMappingException e) {
			LOG.error("getQualityIndexLOCTimeData : ", e);
		} catch (IOException e) {
			LOG.error("getQualityIndexLOCTimeData : ", e);
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("END service to get project list ...");
		}
		return objjson;
	}
	
	public static JSONObject getTechnicalDebtTimeData(String Id){
		
		JSONObject objjson = new JSONObject();
		
		if (LOG.isInfoEnabled()) {
			LOG.info("START service to get quality index time data for ID : "
					+ Id);
		}

		try {
			// calling the web service to get the trends model
			URL url = new URL(constantsUtil.getQualityIndexAndTimeDataURL
					+ Id);
			JSONArray arrjson = callWebServiceUtil.getJSONArrayWebService(url);

			if (arrjson != null) {
				
				objjson.put("cols", createColsForChartJsonOneAxis("Time Stamp", "Technical Debt"));
				JSONArray arrtempjason2 = new JSONArray();
				
				for (int i = 0; i < arrjson.length(); i++) {
					ObjectMapper mapper = new ObjectMapper();
					
					JSONObject tempjason1 = new JSONObject();
					JSONArray arrtempjason1 = new JSONArray();
					JSONObject tempjason2 = new JSONObject();
				
						// convert it to user class
						trendInfo objTrendInfo = mapper
								.readValue(arrjson.getJSONObject(i).toString(),
										trendInfo.class);
						
						tempjason1.put("v", objTrendInfo.getTimestamp());
						arrtempjason1.put(tempjason1);
						tempjason1 = new JSONObject();
						tempjason1.put("v", Math.round(objTrendInfo.getTechnical_debt() * 100)/100.0);
						arrtempjason1.put(tempjason1);
						tempjason2.put("c", arrtempjason1);
						arrtempjason2.put(tempjason2);

				}
				objjson.put("rows", arrtempjason2);
			} else {
				return null;
			}
		} catch (MalformedURLException e) {
			LOG.error("getTechnicalDebtTimeData : ", e);
		} catch (JsonGenerationException e) {
			LOG.error("getTechnicalDebtTimeData : ", e);
		} catch (JsonMappingException e) {
			LOG.error("getTechnicalDebtTimeData : ", e);
		} catch (IOException e) {
			LOG.error("getTechnicalDebtTimeData : ", e);
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("END service to get project list ...");
		}
		return objjson;
	}
	
public static JSONObject getRulesComplianceTimeData(String Id){
		
		JSONObject objjson = new JSONObject();
		
		if (LOG.isInfoEnabled()) {
			LOG.info("START service to get quality index time data for ID : "
					+ Id);
		}

		try {
			// calling the web service to get the trends model
			URL url = new URL(constantsUtil.getQualityIndexAndTimeDataURL
					+ Id);
			JSONArray arrjson = callWebServiceUtil.getJSONArrayWebService(url);

			if (arrjson != null) {
				
				JSONObject tempjason = new JSONObject();
				JSONArray arrtempjason = new JSONArray();
				
				tempjason.put("label", "Time Stamp");
				tempjason.put("type", "string");
				arrtempjason.put(tempjason);
				tempjason = new JSONObject();
				tempjason.put("label", "Rules Compliance");
				tempjason.put("type", "number");
				arrtempjason.put(tempjason);
				
				objjson.put("cols", arrtempjason);
				JSONArray arrtempjason2 = new JSONArray();
				
				for (int i = 0; i < arrjson.length(); i++) {
					ObjectMapper mapper = new ObjectMapper();
					
					JSONObject tempjason1 = new JSONObject();
					JSONArray arrtempjason1 = new JSONArray();
					JSONObject tempjason2 = new JSONObject();
					
						// convert it to user class
						trendInfo objTrendInfo = mapper
								.readValue(arrjson.getJSONObject(i).toString(),
										trendInfo.class);
						
						tempjason1.put("v", objTrendInfo.getTimestamp());
						arrtempjason1.put(tempjason1);
						tempjason1 = new JSONObject();
						tempjason1.put("v", Math.round(objTrendInfo.getRules_compliance() * 100)/100.0);
						arrtempjason1.put(tempjason1);
						tempjason2.put("c", arrtempjason1);
						arrtempjason2.put(tempjason2);

				}
				objjson.put("rows", arrtempjason2);
			} else {
				return null;
			}
		} catch (MalformedURLException e) {
			LOG.error("getRulesComplianceTimeData : ", e);
		} catch (JsonGenerationException e) {
			LOG.error("getRulesComplianceTimeData : ", e);
		} catch (JsonMappingException e) {
			LOG.error("getRulesComplianceTimeData : ", e);
		} catch (IOException e) {
			LOG.error("getRulesComplianceTimeData : ", e);
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("END service to get project list ...");
		}
		return objjson;
	}

public static JSONObject getLinesOfCodeTimeData(String Id){
	
	JSONObject objjson = new JSONObject();
	
	if (LOG.isInfoEnabled()) {
		LOG.info("START service to get quality index time data for ID : "
				+ Id);
	}

	try {
		// calling the web service to get the trends model
		URL url = new URL(constantsUtil.getQualityIndexAndTimeDataURL
				+ Id);
		JSONArray arrjson = callWebServiceUtil.getJSONArrayWebService(url);

		if (arrjson != null) {
			
			JSONObject tempjason = new JSONObject();
			JSONArray arrtempjason = new JSONArray();
			
			tempjason.put("label", "Time Stamp");
			tempjason.put("type", "string");
			arrtempjason.put(tempjason);
			tempjason = new JSONObject();
			tempjason.put("label", "Lines Of Code");
			tempjason.put("type", "number");
			arrtempjason.put(tempjason);
			
			objjson.put("cols", arrtempjason);
			JSONArray arrtempjason2 = new JSONArray();
			
			for (int i = 0; i < arrjson.length(); i++) {
				ObjectMapper mapper = new ObjectMapper();
				
				JSONObject tempjason1 = new JSONObject();
				JSONArray arrtempjason1 = new JSONArray();
				JSONObject tempjason2 = new JSONObject();
				
					// convert it to user class
					trendInfo objTrendInfo = mapper
							.readValue(arrjson.getJSONObject(i).toString(),
									trendInfo.class);
					
					tempjason1.put("v", objTrendInfo.getTimestamp());
					arrtempjason1.put(tempjason1);
					tempjason1 = new JSONObject();
					tempjason1.put("v", objTrendInfo.getLines_of_code());
					arrtempjason1.put(tempjason1);
					tempjason2.put("c", arrtempjason1);
					arrtempjason2.put(tempjason2);
					
			}
			objjson.put("rows", arrtempjason2);
		} else {
			return null;
		}
	} catch (MalformedURLException e) {
		LOG.error("getLinesOfCodeTimeData : ", e);
	} catch (JsonGenerationException e) {
		LOG.error("getLinesOfCodeTimeData : ", e);
	} catch (JsonMappingException e) {
		LOG.error("getLinesOfCodeTimeData : ", e);
	} catch (IOException e) {
		LOG.error("getLinesOfCodeTimeData : ", e);
	}

	if (LOG.isInfoEnabled()) {
		LOG.info("END service to get project list ...");
	}
	return objjson;
	}

public static JSONObject getBlockerIssueTimeData(String Id){
	
	JSONObject objjson = new JSONObject();
	
	if (LOG.isInfoEnabled()) {
		LOG.info("START service to get quality index time data for ID : "
				+ Id);
	}

	try {
		// calling the web service to get the trends model
		URL url = new URL(constantsUtil.getQualityIndexAndTimeDataURL
				+ Id);
		JSONArray arrjson = callWebServiceUtil.getJSONArrayWebService(url);

		if (arrjson != null) {
			
			JSONObject tempjason = new JSONObject();
			JSONArray arrtempjason = new JSONArray();
			
			tempjason.put("label", "Time Stamp");
			tempjason.put("type", "string");
			arrtempjason.put(tempjason);
			tempjason = new JSONObject();
			tempjason.put("label", "Blocker Issues");
			tempjason.put("type", "number");
			arrtempjason.put(tempjason);
			
			objjson.put("cols", arrtempjason);
			JSONArray arrtempjason2 = new JSONArray();
			
			for (int i = 0; i < arrjson.length(); i++) {
				ObjectMapper mapper = new ObjectMapper();
				
				JSONObject tempjason1 = new JSONObject();
				JSONArray arrtempjason1 = new JSONArray();
				JSONObject tempjason2 = new JSONObject();
				
					// convert it to user class
					trendInfo objTrendInfo = mapper
							.readValue(arrjson.getJSONObject(i).toString(),
									trendInfo.class);
					
					tempjason1.put("v", objTrendInfo.getTimestamp());
					arrtempjason1.put(tempjason1);
					tempjason1 = new JSONObject();
					tempjason1.put("v", objTrendInfo.getBlocker_issues());
					arrtempjason1.put(tempjason1);
					tempjason2.put("c", arrtempjason1);
					arrtempjason2.put(tempjason2);
					
			}
			objjson.put("rows", arrtempjason2);
		} else {
			return null;
		}
	} catch (MalformedURLException e) {
		LOG.error("getBlockerIssueTimeData : ", e);
	} catch (JsonGenerationException e) {
		LOG.error("getBlockerIssueTimeData : ", e);
	} catch (JsonMappingException e) {
		LOG.error("getBlockerIssueTimeData : ", e);
	} catch (IOException e) {
		LOG.error("getBlockerIssueTimeData : ", e);
	}

	if (LOG.isInfoEnabled()) {
		LOG.info("END service to get project list ...");
	}
	return objjson;
}
}
