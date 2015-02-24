package main.java.com.cisco.dft.cimv.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import main.java.com.cisco.dft.cimv.model.favoriteInfo;
import main.java.com.cisco.dft.cimv.util.callWebServiceUtil;
import main.java.com.cisco.dft.cimv.util.constantsUtil;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

public class favoriteService {
	
	private favoriteService(){
		
	}
	
	private static final Logger LOG = Logger.getLogger(favoriteService.class);

	public static List<favoriteInfo> serviceGetFavoriteList(String userId) {

		if (LOG.isInfoEnabled()) {
			LOG.info("START service to get list of favorites ...");
		}

		List<favoriteInfo> favorites = new LinkedList<favoriteInfo>();
		try {

			// calling the web service to get the list of favorites for the
			// particular user
			URL url = new URL(constantsUtil.getFavoriteListURL + userId);
			String strjson = callWebServiceUtil.getJSONStringWebService(url);
			if(strjson == null){
				if (LOG.isInfoEnabled()) {
					LOG.info("No list of favorites ...");
				}
				return null;
			}
			JSONObject objjson = new JSONObject(strjson);

			JSONArray arrjson = objjson.getJSONArray("favoriteList");
			if(arrjson != null){
				for (int i = 0; i < arrjson.length(); i++){
					ObjectMapper mapper = new ObjectMapper();
					
						// convert it to user class
						favoriteInfo objFavoriteInfo = mapper.readValue(arrjson
							.getJSONObject(i).toString(), favoriteInfo.class);
						
						// add to the list
						favorites.add(objFavoriteInfo);
						
				}
			}else{
				return null;
			}
			
		} catch (MalformedURLException e) {
			LOG.error("serviceGetFavoriteList : ", e);
		} catch (IOException e) {
			LOG.error("serviceGetFavoriteList : ", e);
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("END service to get list of favorites ...");
		}
		return favorites;
	}
	
	public static String serviceAddFavorite(String userId, String favName, String favURL, String favDefault) {

		if (LOG.isInfoEnabled()) {
			LOG.info("START service to add a favorite to database ...");
		}	
		URL url;
		String serviceStatus = "error";
		try {
			// Favorite name is encoded to take into account the favorite names with spaces
			url = new URL(constantsUtil.addFavoriteListURL + userId + "&favName=" + URLEncoder.encode(favName,"UTF-8") + "&favUrl=" + favURL + "&favDefault=" + favDefault);
			
			// calling the service to save the favorite and also getting in return success if inserted in database
			serviceStatus = callWebServiceUtil.sendDataWebService(url);	
		} catch (MalformedURLException e) {
			LOG.error("serviceAddFavorite : ", e);
		} catch (UnsupportedEncodingException e) {
			LOG.error("serviceAddFavorite : ", e);
		}
		
		return serviceStatus;
	}
	
	public static String serviceDeleteFavorite(String userId, String favName) {

		if (LOG.isInfoEnabled()) {
			LOG.info("START service to delete a favorite from database ...");
		}
		URL url;
		String serviceStatus = "error";
		try {
			// Favorite name is encoded to take into account the favorite names with spaces
			url = new URL(constantsUtil.deleteFavoriteListURL + userId + "&favName=" + URLEncoder.encode(favName, "UTF-8"));
			// calling the service to delete the favorite and also getting in return success if deleted in database
			serviceStatus = callWebServiceUtil.sendDataWebService(url);
			
		} catch (MalformedURLException e) {
			LOG.error("serviceDeleteFavorite : ", e);
		} catch (UnsupportedEncodingException e) {
			LOG.error("serviceDeleteFavorite : ", e);
		}
		
		return serviceStatus;	
	}
	
	//for checking already existing favorites return this json object to the view to check in the javascript dynamically
	public static JSONObject getFavoriteAsJSONObject(){
		
		return null;		
	}
	
}
