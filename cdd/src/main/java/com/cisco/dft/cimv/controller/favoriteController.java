package main.java.com.cisco.dft.cimv.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import main.java.com.cisco.dft.cimv.model.favoriteInfo;
import main.java.com.cisco.dft.cimv.service.favoriteService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class favoriteController {
	
	private static final Logger LOG = Logger.getLogger(favoriteController.class);
	
	@RequestMapping(value = "/saveFavorite", method = RequestMethod.POST)
	public ModelAndView saveFavorite(@RequestParam("favName") String favName, HttpServletRequest servletRequest) {
		
		ModelAndView modelAndView = new ModelAndView("defaultRedirect");
		HttpSession session = servletRequest.getSession();
		
		if(session.getAttribute("userInfo") == null){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("START save favorite() ...");
		}
		
		// get the user Id from the session
		String userId = session.getAttribute("userInfo").toString();
		
		// getting the url of the page from which the save favorite link was clicked so as to save it in
		// database as favorite link and also to redirect to the same page after saving favorite. 
		String favURL = servletRequest.getHeader("referer");

		// service to add the favorite to the database
		favoriteService.serviceAddFavorite(userId, favName, favURL, "false");
		
		// populating the list of favorites after addition of the new one. So as to reflect the change on reload
		List<favoriteInfo> arrFavoriteList = favoriteService.serviceGetFavoriteList(userId);
		
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
				
		modelAndView.setViewName("defaultRedirect");
		modelAndView.addObject("redirectURL", favURL);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/deleteFavorite", method = RequestMethod.POST)
	public ModelAndView deleteFavorite(@RequestParam("favName") String favName, HttpServletRequest servletRequest) {
		
		ModelAndView modelAndView = new ModelAndView("defaultRedirect");
		HttpSession session = servletRequest.getSession();
		
		if(session.getAttribute("userInfo") == null){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("START save favorite() ...");
		}
		
		// get the user Id from the session
		String userId = session.getAttribute("userInfo").toString();
		
		// getting the url of the page from which the save favorite link was clicked so as to
		// redirect to the same page after saving favorite. 
		String favURL = servletRequest.getHeader("referer");
		
		// service to delete the favorite from the database
		favoriteService.serviceDeleteFavorite(userId, favName);
		
		// populating the list of favorites after addition of the new one. So as to reflect the change on reload
		List<favoriteInfo> arrFavoriteList = favoriteService.serviceGetFavoriteList(userId);
		
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
		
		modelAndView.setViewName("defaultRedirect");
		modelAndView.addObject("redirectURL", favURL);
		
		return modelAndView;
	}
	
}
