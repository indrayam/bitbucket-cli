package main.java.com.cisco.dft.cimv.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import main.java.com.cisco.dft.cimv.model.metricsInfo;
import main.java.com.cisco.dft.cimv.service.metricsService;
import main.java.com.cisco.dft.cimv.service.subtrackService;
import main.java.com.cisco.dft.cimv.service.trendService;
import main.java.com.cisco.dft.cimv.util.generalUtil;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings("rawtypes")
@Controller
public class trackController {
	
	private static final Logger LOG = Logger.getLogger(trackController.class);
	
	// This function is to get the list of sub-tracks for a particular track. When we click on any track
	// shown in the listTrack.jsp page this controller is called with the groupId, groupName, trackId and trackName of 
	// the clicked track. It returns to the listSubTrack.jsp page.
	// This function also gets the track metrics by using the trackId.
	@RequestMapping(value = "/subtracks/{groupId}/{groupName}/{trackId}/{trackName}")
	public ModelAndView showSubTracks(@PathVariable("groupId") String groupId, @PathVariable("groupName") String groupName, @PathVariable("trackId") String trackId, @PathVariable("trackName") String trackName, HttpServletRequest servletRequest) {
		HttpSession session = servletRequest.getSession();
		
		// name of the tiles template as argument
		ModelAndView modelAndView = new ModelAndView("listSubTrack");

		if(session.getAttribute("userInfo") == null){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("START show Subtracks() ...");
		}
		
		// get the list of all the sub-track
		List subtracksANDprojects = subtrackService.serviceGetSubTracksByTrack(groupId, trackId);
		
		if(subtracksANDprojects == null){
			modelAndView.setViewName("error");
			return modelAndView;
		}
		
		// get the metrics for this track
		metricsInfo objMetrics = metricsService.serviceGetMetrics(trackId);
		if(objMetrics == null){
			objMetrics = metricsService.demoGetMetrics();
		}
		
		// get the json object to be sent to the jsp to display on the google chart.
		modelAndView.addObject("jsonDataChartTrack", trendService.getQualityIndexLOCTimeData(trackId));
		
		// Quality Index to status color
		String status = generalUtil.doQualityIndexToStatus(objMetrics.getQuality_index());
			
		// if orphan project exist set it to true and add the project list
		String projectStatus = "false";
		if(subtracksANDprojects.get(1) != null){
			projectStatus = "true";
			modelAndView.addObject("projectListBean", subtracksANDprojects.get(1));
		}
		
		// if there is no sub-track under the track
		String subtrackStatus = "false";
		if(subtracksANDprojects.get(0) != null){
			subtrackStatus = "true";
			modelAndView.addObject("subtrackListBean", subtracksANDprojects.get(0));
		}
		
		// adding beans to the model
		modelAndView.addObject("status", status);
		modelAndView.addObject("projectTableShow", projectStatus);
		modelAndView.addObject("subtrackTableShow", subtrackStatus);
		modelAndView.addObject("metricsBean", objMetrics);
		modelAndView.addObject("name", "Track");
		modelAndView.addObject("groupName", groupName);
		modelAndView.addObject("groupId", groupId);
		modelAndView.addObject("trackName", trackName);
		modelAndView.addObject("trackId", trackId);
		modelAndView.addObject("trendName", trackName);
		modelAndView.addObject("trendId", trackId);
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("END show Subtracks() ...");
		}
		return modelAndView;
	}

}
