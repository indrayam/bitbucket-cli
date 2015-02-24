package main.java.com.cisco.dft.cimv.util;

public class constantsUtil {
	
	// URL to the web service dft-mongo
	public static final String webURLmongo = "http://dft-mongo.cisco.com:8080/CIMVService/webapi/";
	
	// URL to the web service LAE
	public static final String webURLlae = "http://cddserv-dev.cloudapps.cisco.com/webapi/";
	
	// URL to the web service local
	public static final String webURLlocal = "http://localhost:8080/CIMVService/webapi/";
	
	// URL to the web service to be used
	public static final String webURL = webURLlae;
	
	// URL to get the metrics for a particular ID(groupId/trackId/projectId)
	public static final String getMetricsURL = webURL + "getResourceMetrics?resourceKey=";
	
	// URL to get the list of group/track/project(passing full hierarchy of ID)
	public static final String getAllReleaseURL = webURL + "listResources/releases";
	public static final String getDefaultReleaseURL = webURL + "listResources/defaultRelease";
	public static final String getAllGroupURL = webURL + "listResources/groups?release=";
	public static final String getTrackByGroupURL = webURL + "listResources/groupResources?groupID=";
	public static final String getSubTrackByTrackURL = webURL + "listResources/trackResources?groupID=";
	public static final String getProjectBySubTrackURL = webURL + "listResources/subtrackResources?groupID=";
	
	// URL to get the list of projects(passing groupID)
	public static final String getAllProjectsByGroupURL = webURL + "listResources/groupResourcesAllProjects?groupID=";
	
	// URl to get the quality index to show with the list in the table(passing immediate ID)
	public static final String getQualityIndexGroupsURL = webURL + "listResources/groupsAttributes";
	public static final String getQualityIndexTracksURL = webURL + "listResources/groupResourcesAttributes?groupID=";
	public static final String getQualityIndexSubTracksURL = webURL + "listResources/trackResourcesAttributes?trackID=";
	public static final String getQualityIndexProjectsURL = webURL + "listResources/subtrackResourcesAttributes?subtrackID=";
	
	//URL to get the chart data for the quality index and timestamp for each page(passing immediate ID)
	public static final String getQualityIndexAndTimeDataURL = webURL + "trends?resourceKey=";
	
	// URL to get the list of favorites(passing user cec ID)
	public static final String getFavoriteListURL = webURL + "favourites/getFavorites?userId=";
	
	// URL to add a favorite(passing favorite name, url, default, userid)
	public static final String addFavoriteListURL = webURL + "favourites/setFavorite?userId=";
	
	// URL to delete a favorite(passing favorite name, userid)
	public static final String deleteFavoriteListURL = webURL + "favourites/unsetFavorite?userId=";
	
	// URL to send the report by mail(passing groupId, groupName, mail)
	public static final String sendReportByMailURL = webURL + "sendMail?groupId=";
	
	// URL to add a favorite(passing favorite name, url, default, userid)
	public static final String addUsesLogsURL = webURL + "logs/saveUsesLogs?userId=";
	
	// constants for projects type
	public static final String strJAVAProject = "java";
	public static final String strPLSQLProject = "plsql";
	public static final String strOtherProject = "other";
	
	// for junit testing
	public static final String testUserId = "plashkar";
	public static final String testFavoriteURL = "http://cdd.cloudapps.cisco.com/tracks/g_ITCITSCustomerCareQ1FY15/IT-CITS-Customer%20Care";
	
	// for developer metrics (passing the project name)
	public static final String getProjectWiseDeveloperMetricsURL = webURL + "developerMetrics/getProjectWiseDeveloperMetrics?project=";
	public static final String getAllDeveloperAllMetricsURL = webURL + "developerMetrics/getALLDeveloperAllMetrics";
			
	private constantsUtil(){
		
	}
}
