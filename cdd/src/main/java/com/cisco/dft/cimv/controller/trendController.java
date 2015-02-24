package main.java.com.cisco.dft.cimv.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import main.java.com.cisco.dft.cimv.service.trendService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class trendController {
	
	private static final Logger LOG = Logger.getLogger(trendController.class);
	
	@RequestMapping(value = "/trends/{trendId}/{trendName}")
	public ModelAndView trends(@PathVariable("trendId") String trendId, @PathVariable("trendName") String trendName, HttpServletRequest servletRequest){
		HttpSession session = servletRequest.getSession();
		
		// name of the tiles template as argument
		ModelAndView modelAndView = new ModelAndView("trends");
				
		if(session.getAttribute("userInfo") == null){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("START show Trends() ...");
		}
		
		// get the url of the page from which this controller was called
		String trendURL = servletRequest.getHeader("referer");
		
		// use the ID to get the trends
		modelAndView.addObject("jsonDataChartTrend", trendService.getTechnicalDebtTimeData(trendId));
		modelAndView.addObject("jsonDataChartTrend1", trendService.getRulesComplianceTimeData(trendId));
		modelAndView.addObject("jsonDataChartTrend2", trendService.getBlockerIssueTimeData(trendId));
		modelAndView.addObject("jsonDataChartTrend3", trendService.getLinesOfCodeTimeData(trendId));
		
		// add objects to the model
		modelAndView.addObject("trendId", trendId);
		modelAndView.addObject("trendName", trendName);
		modelAndView.addObject("trendRedirect", trendURL);
		modelAndView.addObject("name", "null");
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("END show Trends() ...");
		}
		return modelAndView;
	}
}
