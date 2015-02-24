package main.java.com.cisco.dft.cimv.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import main.java.com.cisco.dft.cimv.model.adoptionMetricsInfo;
import main.java.com.cisco.dft.cimv.service.loginService;
import main.java.com.cisco.dft.cimv.service.metricsService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class generalController {
	
	private static final Logger LOG = Logger.getLogger(generalController.class);
	
	@RequestMapping(value = "/standards")
	public ModelAndView standards(HttpServletRequest servletRequest){
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("START standards() ...");
		}
		
		HttpSession session = servletRequest.getSession();
		// name of the tiles template as argument
		ModelAndView modelAndView = new ModelAndView("standards");
		if(session.getAttribute("userInfo") == null){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("END standards() ...");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/usageMetrics")
	public ModelAndView usageMetrics(HttpServletRequest servletRequest) {

		if (LOG.isDebugEnabled()) {
			LOG.debug("START usageMetrics() ...");
		}
		
		// get the list of all the groups
		HttpSession session = servletRequest.getSession();
		// name of the tiles template as argument
		ModelAndView modelAndView = new ModelAndView("usageMetrics");

		if(session.getAttribute("userInfo") == null){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		String newSelectedRelease = (String) session.getAttribute("selected");
		List<adoptionMetricsInfo> arrAdoptionMetrics = metricsService.serviceGetAdoptionMetrics(newSelectedRelease);

		// add objects to the model
		modelAndView.addObject("adoptionMetricsBean", arrAdoptionMetrics);

		if (LOG.isDebugEnabled()) {
			LOG.debug("END usageMetrics() ...");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/mailReport", method = RequestMethod.POST)
	public ModelAndView mailReport(@RequestParam("groupId") String groupId, @RequestParam("groupName") String groupName, HttpServletRequest servletRequest){
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("START mailReport() ...");
		}
		
		// get the mail id from the session
		HttpSession session = servletRequest.getSession();
		// name of the tiles template as argument
		ModelAndView modelAndView = new ModelAndView("defaultRedirect");
		if(session.getAttribute("userInfo") == null){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		String mail = session.getAttribute("userMail").toString();
		
		// call the web service to send the mail id and send the report
		loginService.sendReportByMail(mail, groupId, groupName);
		
		modelAndView.addObject("redirectURL", servletRequest.getHeader("referer"));
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("END mailReport() ...");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/shareReport", method = RequestMethod.POST)
	public ModelAndView shareReport(@RequestParam("groupId") String groupId, @RequestParam("groupName") String groupName, @RequestParam("mails") String mails, HttpServletRequest servletRequest){
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("START shareReport() ...");
		}
		
		// get the mail id from the session
		HttpSession session = servletRequest.getSession();
		// name of the tiles template as argument
		ModelAndView modelAndView = new ModelAndView("defaultRedirect");
		if(session.getAttribute("userInfo") == null){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
		// call the web service to send the mail id and send the report
		loginService.sendReportByMail(mails, groupId, groupName);
		
		modelAndView.addObject("redirectURL", servletRequest.getHeader("referer"));
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("END shareReport() ...");
		}
		
		return modelAndView;
	}
}
