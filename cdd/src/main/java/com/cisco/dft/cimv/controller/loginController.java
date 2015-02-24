package main.java.com.cisco.dft.cimv.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import main.java.com.cisco.dft.cimv.model.*;
import main.java.com.cisco.dft.cimv.service.favoriteService;
import main.java.com.cisco.dft.cimv.service.groupService;
import main.java.com.cisco.dft.cimv.service.loginService;
import main.java.com.cisco.dft.cimv.service.metricsService;
import main.java.com.cisco.dft.cimv.service.releaseService;
import main.java.com.cisco.dft.cimv.util.constantsUtil;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class loginController{
	
	private static final Logger LOG = Logger.getLogger(loginController.class);
	
	// the first page of the webapp
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	// This function is called when we submit the login page. It do the following :
	// 1. LDAP authentication and put user info in session.
	// 2. Get list of favorites and put it in session.
	// 3. Go to the favorite default view page if any.
	//4. Get the releases available and the default release
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public ModelAndView validate(@RequestParam("userId") String userId, @RequestParam("password") String password, HttpServletRequest servletRequest) {
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("START validate() ...");
		}
		
		ModelAndView modelAndView = new ModelAndView("listGroup"); // name of the tiles template as argument
		
		// validate the user and form an object to set user info into session
		userInfo objUserInfo = loginService.getUserInfo(userId, password);
		
		// if user credentials are incorrect null is returned
		if(objUserInfo == null){
			modelAndView.setViewName("login");
			modelAndView.addObject("errorMessage", "Invalid User Id or Password");
			return modelAndView;
		}
		
		// put user info in session
		HttpSession session = servletRequest.getSession();
		session.setAttribute("userInfo", objUserInfo.getUserId());
		session.setAttribute("userName", objUserInfo.getFullName());
		session.setAttribute("userMail", objUserInfo.getMail());
		
		// get the list of favorites put it in session
		List<favoriteInfo> arrFavoriteList = favoriteService.serviceGetFavoriteList(objUserInfo.getUserId());

		// size of the favorite list to restrict user from saving more then four favorites
		if(arrFavoriteList == null){
			session.setAttribute("favoriteListBeanSize", 0);
		}else{
			session.setAttribute("favoriteListBeanSize", arrFavoriteList.size());
			// sometimes service returns [], i.e. size zero for list but in jsp to display no favorites
			// available we compare with null so we make it null if size zero.
			if(arrFavoriteList.size() == 0){
				arrFavoriteList = null;
			}
		}
		session.setAttribute("favoriteListBean", arrFavoriteList);
		
		// selected release
		List<releaseInfo> arrDefaultReleaseList = releaseService.serviceGetDefaultRelease();
		if(arrDefaultReleaseList == null){
			modelAndView.setViewName("error");
			return modelAndView;
		}
		releaseInfo DefaultReleaseList = arrDefaultReleaseList.get(0);
		String release =DefaultReleaseList.get_id();
		session.setAttribute("selected", release);
		List<releaseInfo> arrReleaseList = releaseService.serviceGetAllReleases();
		session.setAttribute("releaseListBean", arrReleaseList);
					
		// get the list of all the groups		
		String newSelectedRelease = (String) session.getAttribute("selected");
		List<groupInfo> arrGroupList = groupService.serviceGetAllGroups(newSelectedRelease);
		
		if(arrGroupList == null){
			modelAndView.setViewName("error");
			return modelAndView;
		}
		
		// add objects to the model
		modelAndView.addObject("groupListBean", arrGroupList);
		modelAndView.addObject("name", "null"); // this name is for the sticky note (eg: group, track, etc.)
		
		// showing some details of adoption metrics on home page below the image
		/*List<adoptionMetricsInfo> arrAdoptionMetrics = metricsService.serviceGetAdoptionMetrics(newSelectedRelease);
		
		long longTotalProjects = 0;
		long longTotalJAVAProjects = 0;
		long longTotalPLSQLProjects = 0;
		if(arrAdoptionMetrics != null){
			// looping the adoption metrics array to add up the total number of projects and number of java/PLSQL projects
			for(int i = 0; i < arrAdoptionMetrics.size(); i++){
				longTotalProjects = longTotalProjects + arrAdoptionMetrics.get(i).getNo_of_projects();
				longTotalJAVAProjects = longTotalJAVAProjects + arrAdoptionMetrics.get(i).getProjectType().get(constantsUtil.strJAVAProject);
				longTotalPLSQLProjects = longTotalPLSQLProjects + arrAdoptionMetrics.get(i).getProjectType().get(constantsUtil.strPLSQLProject);
			}
		}*/
		
		// add objects to the model
		session.setAttribute("totalProjects", 88 /*longTotalProjects*/);
		session.setAttribute("totalJAVAProjects", 40 /*longTotalJAVAProjects*/);
		session.setAttribute("totalPLSQLProjects", 40 /*longTotalPLSQLProjects*/);
		
		// get the user name from the object
		String userName = objUserInfo.getFullName();
				
		// get the time stamp of the user logging in
		Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		String timestamp = currentTimestamp.toString();
				
		// updating uses logs
		loginService.serviceSaveUsesLogs(userId, userName, timestamp);
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("END validate() ...");
		}
		return modelAndView;
	}
	
	// Displays the page with the list of groups.
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest servletRequest){
		HttpSession session = servletRequest.getSession();
		String finalSelectedRelease = servletRequest.getParameter("selectedRelease");
		List<groupInfo> arrGroupList;
		// name of the tiles template as argument
		ModelAndView modelAndView = new ModelAndView("listGroup");
		
		if(session.getAttribute("userInfo") == null){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
		if(finalSelectedRelease!=null){
		session.setAttribute("selected", finalSelectedRelease);
		}
		String newSelectedRelease = (String) session.getAttribute("selected");
		arrGroupList = groupService.serviceGetAllGroups(newSelectedRelease);
		
		// add objects to the model
		modelAndView.addObject("groupListBean", arrGroupList);
		modelAndView.addObject("name", "null");
				
		return modelAndView;
			
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest servletRequest){
	
		HttpSession session = servletRequest.getSession();
		session.invalidate();
		
		// name of the tiles template as argument
		return new ModelAndView("login"); 
	}

}
