package main.java.com.cisco.dft.cimv.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import main.java.com.cisco.dft.cimv.model.developerMetricsInfo;
import main.java.com.cisco.dft.cimv.model.metricsInfo;
import main.java.com.cisco.dft.cimv.service.developerService;
import main.java.com.cisco.dft.cimv.service.metricsService;
import main.java.com.cisco.dft.cimv.service.trendService;
import main.java.com.cisco.dft.cimv.util.generalUtil;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class projectController {
	
	private static final Logger LOG = Logger.getLogger(projectController.class);
	
	// This function is used to get the metrics of the particular project using projectId. When we click on any project
	// shown in the listProject.jsp page this controller is called with the groupId, groupName, trackId,  
	// trackName, sub-trackId, sub-trackName, projectId and projectName of the clicked project.
	// It returns to the viewProject.jsp page.
	@RequestMapping(value = "/projectInfo/{groupId}/{groupName}/{trackId}/{trackName}/{subtrackId}/{subtrackName}/{projectId}/{projectName}")
	public ModelAndView showProjectInfo(@PathVariable("groupId") String groupId, @PathVariable("groupName") String groupName, @PathVariable("trackId") String trackId, @PathVariable("trackName") String trackName, @PathVariable("projectId") String projectId, @PathVariable("projectName") String projectName, @PathVariable("subtrackId") String subtrackId, @PathVariable("subtrackName") String subtrackName, HttpServletRequest servletRequest) {
		HttpSession session = servletRequest.getSession();
		
		// name of the tiles template as argument
		ModelAndView modelAndView = new ModelAndView("viewProject");

		if(generalUtil.validateLoginSession(session)){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("START show ProjectInfo() ...");
		}

		// get the metrics for this project
		metricsInfo objMetrics = metricsService.serviceGetMetrics(projectId);
		if(objMetrics == null){
			objMetrics = metricsService.demoGetMetrics();
		}
		
		// get the json object to be sent to the jsp to display on the google chart.
		modelAndView.addObject("jsonDataChartProject", trendService.getQualityIndexLOCTimeData(projectId));
		
		// Quality Index to status color
		String status = generalUtil.doQualityIndexToStatus(objMetrics.getQuality_index());
		
		// developer metrics
		List<developerMetricsInfo> arrDevMetrics = developerService.serviceGetProjectWiseDeveloperMetrics(projectId);
		modelAndView.addObject("developerMetricsBean", arrDevMetrics);
		
		// adding beans to the model
		modelAndView.addObject("status", status);
		modelAndView.addObject("metricsBean", objMetrics);
		modelAndView.addObject("name", "Project");
		modelAndView.addObject("groupName", groupName);
		modelAndView.addObject("groupId", groupId);
		modelAndView.addObject("trackName", trackName);
		modelAndView.addObject("trackId", trackId);
		modelAndView.addObject("subtrackName", subtrackName);
		modelAndView.addObject("subtrackId", subtrackId);
		modelAndView.addObject("projectName", projectName);
		modelAndView.addObject("projectId", projectId);
		modelAndView.addObject("trendName", projectName);
		modelAndView.addObject("trendId", projectId);
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("END show ProjectInfo() ...");
		}
		return modelAndView;
	}

	// This function is used to get the metrics of the particular project using projectId which do not have any sub-track.
	// When we click on any project shown in the listSubTrack.jsp page this controller is called with the groupId, 
	// groupName, trackId, trackName, projectId and projectName of the clicked project.
	// It returns to the viewProject.jsp page.
	@RequestMapping(value = "/projectInfoOrphan/{groupId}/{groupName}/{trackId}/{trackName}/{projectId}/{projectName}")
	public ModelAndView showProjectInfoOrphan(@PathVariable("groupId") String groupId, @PathVariable("groupName") String groupName, @PathVariable("trackId") String trackId, @PathVariable("trackName") String trackName, @PathVariable("projectId") String projectId, @PathVariable("projectName") String projectName, HttpServletRequest servletRequest) {
		HttpSession session = servletRequest.getSession();
		
		// name of the tiles template as argument
		ModelAndView modelAndView = new ModelAndView("viewProjectOrphan");

		if(generalUtil.validateLoginSession(session)){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("START show ProjectInfo() ...");
		}

		// get the metrics for this project
		metricsInfo objMetrics = metricsService.serviceGetMetrics(projectId);
		if(objMetrics == null){
			objMetrics = metricsService.demoGetMetrics();
		}
		
		// get the json object to be sent to the jsp to display on the google chart.
		modelAndView.addObject("jsonDataChartProject", trendService.getQualityIndexLOCTimeData(projectId));
		
		// Quality Index to status color
		String status = generalUtil.doQualityIndexToStatus(objMetrics.getQuality_index());
				
		// developer metrics
		List<developerMetricsInfo> arrDevMetrics = developerService.serviceGetProjectWiseDeveloperMetrics(objMetrics.getSonar_key());
		modelAndView.addObject("developerMetricsBean", arrDevMetrics);
		
		// adding beans to the model
		modelAndView.addObject("status", status);
		modelAndView.addObject("metricsBean", objMetrics);
		modelAndView.addObject("name", "Project");
		modelAndView.addObject("groupName", groupName);
		modelAndView.addObject("groupId", groupId);
		modelAndView.addObject("trackName", trackName);
		modelAndView.addObject("trackId", trackId);
		modelAndView.addObject("projectName", projectName);
		modelAndView.addObject("projectId", projectId);
		modelAndView.addObject("trendName", projectName);
		modelAndView.addObject("trendId", projectId);
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("END show ProjectInfo() ...");
		}
		return modelAndView;
	}

}
