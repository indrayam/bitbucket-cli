package main.java.com.cisco.dft.cimv.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import main.java.com.cisco.dft.cimv.model.releaseInfo;
import main.java.com.cisco.dft.cimv.util.callWebServiceUtil;
import main.java.com.cisco.dft.cimv.util.constantsUtil;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;

public class releaseService {

	private releaseService(){
		
	}
	
	private static final Logger LOG = Logger.getLogger(releaseService.class);

	public static List<releaseInfo> serviceGetAllReleases() {

		if (LOG.isInfoEnabled()) {
			LOG.info("START service to get list of releases ...");
		}

		List<releaseInfo> releases = new LinkedList<releaseInfo>();
		try {

			// calling the web service to get the list of releases
			URL url = new URL(constantsUtil.getAllReleaseURL);
			JSONArray json = callWebServiceUtil.getJSONArrayWebService(url);
			if (json != null) {
				for (int i = 0; i < json.length(); i++) {
					ObjectMapper mapper = new ObjectMapper();
				
						// convert it to user class
						releaseInfo objReleaseInfo = mapper.readValue(json
								.getJSONObject(i).toString(), releaseInfo.class);	
						
						// add to the list
						releases.add(objReleaseInfo);

				}
			} else {
				return null;
			}
		} catch (MalformedURLException e) {
			LOG.error("serviceGetAllReleases : ", e);
		} catch (JsonGenerationException e) {
			LOG.error("serviceGetAllReleases : ", e);
		} catch (JsonMappingException e) {
			LOG.error("serviceGetAllReleases : ", e);
		} catch (IOException e) {
			LOG.error("serviceGetAllReleases : ", e);
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("END service to get list of releases ...");
		}
		return releases;
	}
	
	public static List<releaseInfo> serviceGetDefaultRelease() {

        if (LOG.isInfoEnabled()) {
               LOG.info("START service to get list of releases ...");
        }

        List<releaseInfo> defaultrelease = new LinkedList<releaseInfo>();
        try {

               // calling the web service to get the default release
               URL url = new URL(constantsUtil.getDefaultReleaseURL);
               JSONArray json = callWebServiceUtil.getJSONArrayWebService(url);
               if (json != null) {
                     for (int i = 0; i < json.length(); i++) {
                            ObjectMapper mapper = new ObjectMapper();
                           
                                   // convert it to user class
                                   releaseInfo objReleaseInfo = mapper.readValue(json
                                                 .getJSONObject(i).toString(), releaseInfo.class);
                    
                                   // add to the list
                                   defaultrelease.add(objReleaseInfo);
                     }
               } else {
                     return null;
               }
        } catch (MalformedURLException e) {
        	 LOG.error("serviceGetDefaultRelease : ", e);
        } catch (JsonGenerationException e) {
       	 LOG.error("serviceGetDefaultRelease : ", e);
       } catch (JsonMappingException e) {
       	LOG.error("serviceGetDefaultRelease : ", e);
       } catch (IOException e) {
       	LOG.error("serviceGetDefaultRelease : ", e);
       }

        if (LOG.isInfoEnabled()) {
               LOG.info("END service to get list of releases ...");
        }
        return defaultrelease;
 }
}
