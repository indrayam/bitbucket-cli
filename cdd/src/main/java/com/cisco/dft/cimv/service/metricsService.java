package main.java.com.cisco.dft.cimv.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import main.java.com.cisco.dft.cimv.model.adoptionMetricsInfo;
import main.java.com.cisco.dft.cimv.model.groupInfo;
import main.java.com.cisco.dft.cimv.model.metricsInfo;
import main.java.com.cisco.dft.cimv.model.projectInfo;
import main.java.com.cisco.dft.cimv.util.callWebServiceUtil;
import main.java.com.cisco.dft.cimv.util.constantsUtil;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;

public class metricsService {
	
	private metricsService(){
		
	}
	
	private static final Logger LOG = Logger.getLogger(metricsService.class);
	
	public static metricsInfo serviceGetMetrics(String Id){
		
		if (LOG.isInfoEnabled()) {
			LOG.info("START service to get metrics for ID : " + Id);
		}
		
		metricsInfo metrics = new metricsInfo();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			URL url = new URL(constantsUtil.getMetricsURL + Id);
			String output = callWebServiceUtil.getJSONStringWebService(url);
			if (output != null) {
				metrics = mapper.readValue(output, metricsInfo.class);
			}
			else{
				return null;
			}
		} catch (JsonGenerationException e) {
			LOG.error("serviceGetMetrics : ", e);
		} catch (JsonMappingException e) {
			LOG.error("serviceGetMetrics : ", e);
		} catch (IOException e) {
			LOG.error("serviceGetMetrics : ", e);
		}
		
		if (LOG.isInfoEnabled()) {
			LOG.info("END service to get metrics ...");
		}
		return metrics;
	}
	
	// gets all the details regarding adoption metrics calling different web services
	public static List<adoptionMetricsInfo> serviceGetAdoptionMetrics(String release){
		
		if (LOG.isInfoEnabled()) {
			LOG.info("START service to get list of groups and other adoption metrics...");
		}

		List<adoptionMetricsInfo> adoptionmetrics = new LinkedList<adoptionMetricsInfo>();
		try {

			// calling the web service to get the list of groups
			URL url = new URL(constantsUtil.getAllGroupURL + release);
			JSONArray json = callWebServiceUtil.getJSONArrayWebService(url);
			if (json != null) {
				metricsInfo objMetrics;
				List<projectInfo> arrProject;
				for (int i = 0; i < json.length(); i++) {
					Map<String, Integer> hmProjectType = new HashMap<String, Integer>();
					ObjectMapper mapper = new ObjectMapper();
					
						// convert it to user class
						groupInfo objGroupInfo = mapper.readValue(json
								.getJSONObject(i).toString(), groupInfo.class);
						adoptionMetricsInfo objAdoptionMetrics = new adoptionMetricsInfo();
						
						// get all the projects for this groupID
						arrProject = projectService.serviceGetAllProjects(objGroupInfo.get_id());
						
						// insert relevant information in adoption metrics
						// group information
						objAdoptionMetrics.setGroup_id(objGroupInfo.get_id());
						objAdoptionMetrics.setGroup_name(objGroupInfo.getGroup_name());
						objAdoptionMetrics.setGroup_owner(objGroupInfo.getGroup_owner_name());
						
						// getting the metrics info for each group by calling getMetrics function and passing group id
						objMetrics = serviceGetMetrics(objGroupInfo.get_id());
						
						// group quality index for showing average QI on view
						objAdoptionMetrics.setGroup_QI(objMetrics.getQuality_index());
						
						// total lines of code for the group
						objAdoptionMetrics.setTotal_lines_of_code(objMetrics.getLines_of_code() + "");
						
						// total no of projects in this group
						objAdoptionMetrics.setNo_of_projects(arrProject.size());
						
						// count the total no of builds for all the projects in this group
						long totalBuilds = 0;
						
						hmProjectType.put(constantsUtil.strJAVAProject, 0);
						hmProjectType.put(constantsUtil.strPLSQLProject, 0);
						hmProjectType.put(constantsUtil.strOtherProject, 0);
						
						for(int j = 0; j < arrProject.size(); j++){
							
							// adding all the builds per project
							if(!(arrProject.get(j).getCurrent_build_number() == null)){
								totalBuilds = totalBuilds + arrProject.get(j).getCurrent_build_number();
							}
							
							// get the metrics for each project check its project type and make a count of all
							objMetrics = serviceGetMetrics(arrProject.get(j).get_id());
							if(objMetrics == null || objMetrics.getProject_type() == null){}
							else{
								if(constantsUtil.strJAVAProject.equals(objMetrics.getProject_type()) || constantsUtil.strPLSQLProject.equals(objMetrics.getProject_type())){
									hmProjectType.put(objMetrics.getProject_type(), hmProjectType.get(objMetrics.getProject_type()) + 1);
								}
								else{
									hmProjectType.put(constantsUtil.strOtherProject, hmProjectType.get(constantsUtil.strOtherProject) + 1);
								}
							}
						}
						// total no of projects for each type
						objAdoptionMetrics.setProjectType(hmProjectType);
						
						// total no of builds
						objAdoptionMetrics.setTotal_no_of_builds(totalBuilds + "");
						
						// adoption percentage
						
						
						// add to the list
						adoptionmetrics.add(objAdoptionMetrics);
						
				}
			} else {
				return null;
			}
		} catch (MalformedURLException e) {
			LOG.error("serviceGetAdoptionMetrics : ", e);
		} catch (JsonGenerationException e) {
			LOG.error("serviceGetAdoptionMetrics : ", e);
		} catch (JsonMappingException e) {
			LOG.error("serviceGetAdoptionMetrics : ", e);
		} catch (IOException e) {
			LOG.error("serviceGetAdoptionMetrics : ", e);
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("END service to get list of groups and other adoption metrics ...");
		}
		return adoptionmetrics;
	}
	
	// dummy function for local testing
	public static metricsInfo demoGetMetrics(){
		metricsInfo metrics = new metricsInfo();
		metrics.set_id("0");
		metrics.setParent_id("0");
		metrics.setLines(0);
		metrics.setLines_of_code(0);
		metrics.setStatements("0");
		metrics.setFiles(0);
		metrics.setClasses(0);
		metrics.setPackages(0);
		metrics.setFunctions(0);
		metrics.setAccessors("0");
		metrics.setTechnical_debt("0");
		metrics.setRules_compliance("0");
		metrics.setIssues("0");
		metrics.setBlocker_issues(0);
		metrics.setCritical_issues(0);
		metrics.setMajor_issues("0");
		metrics.setComments("0");
		metrics.setComment_lines(0);
		metrics.setBlank_comments("0");
		metrics.setCommented_out_loc(0);
		metrics.setDuplication("0");
		metrics.setDuplicated_lines("0");
		metrics.setComplexity("0");
		metrics.setComplexity_per_file("0");
		metrics.setComplexity_per_class("0");
		metrics.setComplexity_per_function("0");
		metrics.setLines_to_cover("0");
		metrics.setUncovered_lines("0");
		metrics.setConditions_to_cover("0");
		metrics.setUncovered_conditions("0");
		metrics.setUnit_test_coverage("0");
		metrics.setLine_coverage("0");
		metrics.setBranch_coverage("0");
		metrics.setUnit_test_success("0");
		metrics.setNo_of_tests("0");
		metrics.setUnit_test_errors("0");
		metrics.setUnit_test_failures("0");
		metrics.setTotal_imp_issues("0");
		metrics.setQuality_index((float) 0.0);
		metrics.setCoding_qi(1);
		metrics.setCoverage_qi(1);
		metrics.setComplexity_qi(1);
		metrics.setDuplicates_qi(1);
		metrics.setComments_qi(1);
		metrics.setSonar_key("0");
		
		return metrics;
	}

}
