package main.java.com.cisco.dft.cimv.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import main.java.com.cisco.dft.cimv.model.metricsInfo;
import main.java.com.cisco.dft.cimv.model.projectInfo;
import main.java.com.cisco.dft.cimv.service.metricsService;
import main.java.com.cisco.dft.cimv.service.projectService;
import main.java.com.cisco.dft.cimv.service.trendService;
import main.java.com.cisco.dft.cimv.util.generalUtil;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class subtrackController {
	
	private static final Logger LOG = Logger.getLogger(subtrackController.class);
	
	// This function is to get the list of projects for a particular sub-track. When we click on any sub-track
	// shown in the listSubTrack.jsp page this controller is called with the groupId, groupName, trackId,  
	// trackName, sub-trackId and sub-trackName of the clicked sub-track. It returns to the listProject.jsp page.
	// This function also gets the sub-track metrics by using the subtrackId.
	@RequestMapping(value = "/projects/{groupId}/{groupName}/{trackId}/{trackName}/{subtrackId}/{subtrackName}")
	public ModelAndView showProjects(@PathVariable("groupId") String groupId, @PathVariable("groupName") String groupName, @PathVariable("trackId") String trackId, @PathVariable("trackName") String trackName, @PathVariable("subtrackId") String subtrackId, @PathVariable("subtrackName") String subtrackName, HttpServletRequest servletRequest) {
		HttpSession session = servletRequest.getSession();
		
		// name of the tiles template as argument
		ModelAndView modelAndView = new ModelAndView("listProject");

		if(session.getAttribute("userInfo") == null){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("START show Projects() ...");
		}

		// get the list of all the project
		List<projectInfo> arrProjectList = projectService.serviceGetProjectBySubTrack(groupId, trackId, subtrackId);
		if(arrProjectList == null){
			modelAndView.setViewName("error");
			return modelAndView;
		}
		
		// get the metrics for this sub-track
		metricsInfo objMetrics = metricsService.serviceGetMetrics(subtrackId);
		if(objMetrics == null){
			objMetrics = metricsService.demoGetMetrics();
		}
		
		// get the json object to be sent to the jsp to display on the google chart.
		modelAndView.addObject("jsonDataChartSubTrack", trendService.getQualityIndexLOCTimeData(subtrackId));
		
		// Quality Index to status color
		String status = generalUtil.doQualityIndexToStatus(objMetrics.getQuality_index());
				
		// adding beans to the model
		modelAndView.addObject("status", status);
		modelAndView.addObject("projectListBean", arrProjectList);
		modelAndView.addObject("metricsBean", objMetrics);
		modelAndView.addObject("name", "SubTrack");
		modelAndView.addObject("groupName", groupName);
		modelAndView.addObject("groupId", groupId);
		modelAndView.addObject("trackName", trackName);
		modelAndView.addObject("trackId", trackId);
		modelAndView.addObject("subtrackName", subtrackName);
		modelAndView.addObject("subtrackId", subtrackId);
		modelAndView.addObject("trendName", subtrackName);
		modelAndView.addObject("trendId", subtrackId);
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("END show Projects() ...");
		}
		return modelAndView;
	}

}
