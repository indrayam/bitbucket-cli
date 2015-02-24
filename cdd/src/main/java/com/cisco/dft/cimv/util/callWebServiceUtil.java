package main.java.com.cisco.dft.cimv.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.json.JSONArray;

public class callWebServiceUtil {

	private static final Logger LOG = Logger.getLogger(callWebServiceUtil.class);
	private static HttpURLConnection conn;
	private static BufferedReader br;
	
	private callWebServiceUtil(){
		
	}
	
	// calls the web service and in return gets the jsonarray
	public static JSONArray getJSONArrayWebService(URL url){

		if (LOG.isInfoEnabled()) {
			LOG.info("START util getJSONArrayWebService for URL : " + url);
		}

		JSONArray json = null;
		
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				conn.disconnect();
				if (LOG.isInfoEnabled()) {
					LOG.info("Web service called unsuccessfully and return null ...");
				}
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			if (LOG.isInfoEnabled()) {
				LOG.info("Web service called successfully ...");
			}
			br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			String output = br.readLine();
			if (output != null) {
				json = new JSONArray(output);
			}
			br.close();
			
			if (LOG.isInfoEnabled()) {
				LOG.info("JSON array created ...");			
			}
		} catch (MalformedURLException e) {
			LOG.error("getJSONArrayWebService : ", e);

		} catch (IOException e) {
			LOG.error("getJSONArrayWebService : ", e);
		} finally {
			conn.disconnect();
		//	return json;
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("END util getJSONArrayWebService ...");
		}
		return json;
	}

	// calls the web service and in return gets the json string
	public static String getJSONStringWebService(URL url){

		if (LOG.isInfoEnabled()) {
			LOG.info("START util getJSONStringWebService for URL : " + url);
		}

		String output = null;
		
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				conn.disconnect();
				if (LOG.isInfoEnabled()) {
					LOG.info("Web service called unsuccessfully and return null ...");
				}
				return null;
			}

			if (LOG.isInfoEnabled()) {
				LOG.info("Web service called successfully ...");
			}
			br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			if(br != null){
				output = br.readLine();
				br.close();
			}

			if (LOG.isInfoEnabled()) {
				LOG.info("JSON string created ...");
			}

		} catch (MalformedURLException e) {
			LOG.error("getJSONStringWebService : ", e);

		} catch (IOException e) {
			LOG.error("getJSONStringWebService : ", e);
			
		} finally {
			conn.disconnect();
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("END util getJSONStringWebService ...");
		}
		return output;
	}

	// calls the web service and sends some data and receive success or null
	public static String sendDataWebService(URL url){

		if (LOG.isInfoEnabled()) {
			LOG.info("START util sendDataWebService for URL : " + url);
		}

		String output = null;
		
		try {
			conn = (HttpURLConnection) url.openConnection();
			if (conn.getResponseCode() != 200) {
				conn.disconnect();
				if (LOG.isInfoEnabled()) {
					LOG.info("Web service called unsuccessfully and return null ...");
				}
				return null;
			}

			if (LOG.isInfoEnabled()) {
				LOG.info("Web service called successfully ...");
			}
			br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			output = br.readLine();

			if (LOG.isInfoEnabled()) {
				LOG.info("Data sent successfully ...");
			}

			br.close();
		} catch (MalformedURLException e) {
			LOG.error("sendDataWebService : ", e);

		} catch (IOException e) {
			LOG.error("sendDataWebService : ", e);
			
		} finally {
			conn.disconnect();
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("END util sendDataWebService ...");
		}
		return output;
	}
}
