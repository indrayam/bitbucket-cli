package main.java.com.cisco.dft.cimv.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import main.java.com.cisco.dft.cimv.model.metricsInfo;
import main.java.com.cisco.dft.cimv.model.trackInfo;
import main.java.com.cisco.dft.cimv.service.metricsService;
import main.java.com.cisco.dft.cimv.service.trackService;
import main.java.com.cisco.dft.cimv.service.trendService;
import main.java.com.cisco.dft.cimv.util.generalUtil;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class groupController {
	
	private static final Logger LOG = Logger.getLogger(groupController.class);
	
	// This function is to get the list of tracks for a particular group. When we click on any group
	// shown in the listGroup.jsp page this controller is called with the groupId and groupName of 
	// the clicked group. It returns to the listTrack.jsp page.
	// This function also gets the group metrics by using the groupId.
	@RequestMapping(value = "/tracks/{groupId}/{groupName}")
	public ModelAndView showTracks(@PathVariable("groupId") String groupId, @PathVariable("groupName") String groupName, HttpServletRequest servletRequest) {
		HttpSession session = servletRequest.getSession();
		
		// name of the tiles template as argument
		ModelAndView modelAndView = new ModelAndView("listTrack");

		if(session.getAttribute("userInfo") == null){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("START show Tracks() ...");
		}
		
		// get the list of all the tracks
		List<trackInfo> arrTrackList = trackService.serviceGetTracksByGroup(groupId);
		if(arrTrackList == null){
			modelAndView.setViewName("error");
			return modelAndView;
		}
		
		// get the json object to be sent to the jsp to display on the google chart. Put json in session
		modelAndView.addObject("jsonDataChartGroup", trendService.getQualityIndexLOCTimeData(groupId));
		
		
		// get the metrics for this group
		metricsInfo objMetrics = metricsService.serviceGetMetrics(groupId);
		if(objMetrics == null){
			objMetrics = metricsService.demoGetMetrics();
		}
		
		
		// Quality Index to status color
		String status = generalUtil.doQualityIndexToStatus(objMetrics.getQuality_index());
		
		// adding beans to the model
		modelAndView.addObject("status", status);
		modelAndView.addObject("trackListBean", arrTrackList);
		modelAndView.addObject("metricsBean", objMetrics);
		modelAndView.addObject("name", "Group");
		modelAndView.addObject("groupName", groupName);
		modelAndView.addObject("groupId", groupId);
		modelAndView.addObject("trendName", groupName);
		modelAndView.addObject("trendId", groupId);
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("END show Tracks() ...");
		}
		return modelAndView;
	}
}
