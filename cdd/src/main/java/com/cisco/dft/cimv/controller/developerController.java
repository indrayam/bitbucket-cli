package main.java.com.cisco.dft.cimv.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import main.java.com.cisco.dft.cimv.model.developerMetricsInfo;
import main.java.com.cisco.dft.cimv.service.developerService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class developerController {

	private static final Logger LOG = Logger.getLogger(developerController.class);
	
	@RequestMapping(value = "/getDeveloperMetrics/{page}")
	public ModelAndView getDeveloperWiseMetrics(@PathVariable("page") int page, HttpServletRequest servletRequest) {
		
		ModelAndView modelAndView = new ModelAndView("developerMetrics");
		HttpSession session = servletRequest.getSession();
		
		if(session.getAttribute("userInfo") == null){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
		List<developerMetricsInfo> arrDevMetrics = developerService.serviceGetAllDeveloperAllMetrics();
		modelAndView.addObject("developerMetricsBean", arrDevMetrics);
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("START getDeveloperMetrics() ...");
		}
		
		return modelAndView;
	}
	
	public static void mainDemo(String[] args){
		List<developerMetricsInfo> arrDevMetrics = developerService.serviceGetProjectWiseDeveloperMetrics("CC_CS_ASS_Q4FY14_JULY");
		for(int i = 0 ; i < arrDevMetrics.size() ; i++){
			System.out.println(arrDevMetrics.get(i).getProject() + " and " + arrDevMetrics.get(i).getDev() + " and " + arrDevMetrics.get(i).getDuplication() + " and " + arrDevMetrics.get(i).getBlocker());
		}
	}
}
