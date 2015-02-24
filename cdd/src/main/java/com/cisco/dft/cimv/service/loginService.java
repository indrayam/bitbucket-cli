package main.java.com.cisco.dft.cimv.service;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import main.java.com.cisco.dft.cimv.ldap.ldapAuthentication;
import main.java.com.cisco.dft.cimv.model.userInfo;
import main.java.com.cisco.dft.cimv.util.callWebServiceUtil;
import main.java.com.cisco.dft.cimv.util.constantsUtil;

import org.apache.log4j.Logger;

public class loginService {
	
	private loginService(){
		
	}
	
	private static final Logger LOG = Logger.getLogger(loginService.class);
	
	@SuppressWarnings("rawtypes")
	public static userInfo getUserInfo(String userId, String password){
		
		if (LOG.isInfoEnabled()) {
			LOG.info("START service to get user info with userId : " + userId);
		}
		
		userInfo objUserInfo = new userInfo();
		Map employeeMap = ldapAuthentication.authenticateUser(userId, password);
		
		if("success".equals(employeeMap.get("message"))){
			objUserInfo.setUserId((String) employeeMap.get("uid"));
			objUserInfo.setMail((String) employeeMap.get("mail"));
			objUserInfo.setManagerId((String) employeeMap.get("manageruid"));
			objUserInfo.setRoleInCisco((String) employeeMap.get("title"));
			objUserInfo.setFullName((String) employeeMap.get("cn"));
			
			if (LOG.isInfoEnabled()) {
				LOG.info("END service successfully to get user info ...");
			}
			return objUserInfo;
		}
		else{
			if (LOG.isInfoEnabled()) {
				LOG.info("END service with user info not found return null ...");
			}
			return null;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static userInfo getUserInfo(String userId){
		
		if (LOG.isInfoEnabled()) {
			LOG.info("START service to get user info with userId : " + userId);
		}
		
		userInfo objUserInfo = new userInfo();
		Map employeeMap = ldapAuthentication.getUserDetails(userId);
		
		if("success".equals(employeeMap.get("message"))){
			objUserInfo.setUserId((String) employeeMap.get("uid"));
			objUserInfo.setMail((String) employeeMap.get("mail"));
			objUserInfo.setManagerId((String) employeeMap.get("manageruid"));
			objUserInfo.setRoleInCisco((String) employeeMap.get("title"));
			objUserInfo.setFullName((String) employeeMap.get("cn"));
			
			if (LOG.isInfoEnabled()) {
				LOG.info("END service successfully to get user info ...");
			}
			return objUserInfo;
		}
		else{
			if (LOG.isInfoEnabled()) {
				LOG.info("END service with user info not found return null ...");
			}
			return null;
		}
	}
	
	public static String sendReportByMail(String mail, String groupId, String groupName){
		if (LOG.isInfoEnabled()) {
			LOG.info("START service to send report by mail ...");
		}
		URL url;
		String serviceStatus = "error";
		try {
			// Group name is encoded to take into account the group names with spaces
			url = new URL(constantsUtil.sendReportByMailURL + groupId + "&groupName=" + URLEncoder.encode(groupName, "UTF-8") + "&emailId=" + mail);
			
			// calling the service to send the report and also getting in return success if sent successfully to user
			serviceStatus = callWebServiceUtil.sendDataWebService(url);
			
		} catch (MalformedURLException e) {
			LOG.error("sendReportByMail : ", e);
		} catch (UnsupportedEncodingException e) {
			LOG.error("sendReportByMail : ", e);
		}
		
		return serviceStatus;	
	}
	
	public static String serviceSaveUsesLogs(String userId, String userName, String timestamp) {

		if (LOG.isInfoEnabled()) {
			LOG.info("START service to save user logs to database ...");
		}	
		URL url;
		String serviceStatus = "error";
		try {
			// Favorite name is encoded to take into account the favorite names with spaces
			url = new URL(constantsUtil.addUsesLogsURL + userId + "&userName=" + URLEncoder.encode(userName,"UTF-8") + "&timestamp=" + URLEncoder.encode(timestamp,"UTF-8"));
			
			// calling the service to save the favorite and also getting in return success if inserted in database
			serviceStatus = callWebServiceUtil.sendDataWebService(url);	
		} catch (MalformedURLException e) {
			LOG.error("serviceSaveUsesLogs : ", e);
		} catch (UnsupportedEncodingException e) {
			LOG.error("serviceSaveUsesLogs : ", e);
		}
		
		return serviceStatus;
	}
}
