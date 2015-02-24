package main.java.com.cisco.dft.cimv.ldap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import main.java.com.cisco.dft.cimv.service.favoriteService;

import org.apache.log4j.Logger;

@SuppressWarnings({"unchecked", "rawtypes", "unused"})
public class ldapAuthentication {
	private static final Logger LOG = Logger.getLogger(ldapAuthentication.class);
	
	private static final String LDAP_PROPERTIES = "ldap-ext.properties";
	private static final String INIT_CONTEXT = "com.sun.jndi.ldap.LdapCtxFactory";
	private static final String LDAP_HOST = "ldap://ldap.cisco.com:389";
	private static final String BASE = "ou=active, ou=employees, ou=people, o=cisco.com";
	private static final String AUTHENTICATION_SECURITY = "simple";
	
	public static final String MANAGER_ID = "manageruid";
	public static final String EMP_ID = "uid";
	public static final String EMP_NAME = "cn";
	public static final String DEPT_NO = "departmentNumber";
	public static final String MESSAGE = "message";
	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";
	public static final String LDAP_FAILURE = "LDAP server not reachable";
	public static final String MAIL = "mail";
	public static final String IMAGE = "image";
	public static final String TITLE = "title";
	public static final String IMAGE_URL = "http://wwwin.cisco.com/dir/photo/std/";
	public static final String IMAGE_TYPE = ".jpg";
	
	private ldapAuthentication(){
		
	}
	
	public static Map authenticateUser(String userId, String password) {

		Hashtable table = new Hashtable();

		table.put(Context.INITIAL_CONTEXT_FACTORY, INIT_CONTEXT);
		table.put(Context.PROVIDER_URL, LDAP_HOST);
		table.put(Context.SECURITY_AUTHENTICATION, AUTHENTICATION_SECURITY);
		table.put(Context.SECURITY_PRINCIPAL, userId);
		table.put(Context.SECURITY_CREDENTIALS, password);
			
		Map userMap = null;
				
		userMap = validateUser(table, userId);

		return userMap;
	}
	
	public static Map getUserDetails(String userId) {

		Hashtable table = new Hashtable();

		table.put(Context.INITIAL_CONTEXT_FACTORY, INIT_CONTEXT);
		table.put(Context.PROVIDER_URL, LDAP_HOST);
		table.put(Context.SECURITY_AUTHENTICATION, AUTHENTICATION_SECURITY);
		table.put(Context.SECURITY_PRINCIPAL, userId);
			
		Map userMap = null;
				
		userMap = validateUser(table, userId);

		return userMap;
	}
	
	public static Map validateUser(Hashtable table, String userName) {

		Map userMap = new HashMap();
		try {

			String filter = "";
			DirContext context = null;
			SearchControls searchControls = null;
			NamingEnumeration namingEnumeration = null;
			context = new InitialDirContext(table);
			searchControls = new SearchControls();
			searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			filter = "(name=" + userName + ")";
			namingEnumeration = context.search(BASE, filter, searchControls);
			while (namingEnumeration.hasMoreElements()) {
				SearchResult searchResult = (SearchResult) namingEnumeration
						.nextElement();
				Attributes attributes = searchResult.getAttributes();
				if (attributes.get(MANAGER_ID) != null) {
					userMap.put(MANAGER_ID, attributes.get(MANAGER_ID).get()
							.toString());
				}
				if (attributes.get(EMP_NAME) != null) {
					userMap.put(EMP_NAME, attributes.get(EMP_NAME).get()
							.toString());
				}
				if (attributes.get(EMP_ID) != null) {
					userMap.put(EMP_ID, attributes.get(EMP_ID).get().toString());
				}
				if (attributes.get(DEPT_NO) != null) {
					userMap.put(DEPT_NO, attributes.get(DEPT_NO).get()
							.toString());
				}
				if (attributes.get(MAIL) != null) {
					userMap.put(MAIL, attributes.get(MAIL).get().toString());
				}
				if (attributes.get(TITLE) != null) {
					userMap.put(TITLE, attributes.get(TITLE).get().toString());
				}
				if (attributes.get("location") != null) {
					userMap.put("l", attributes.get("location").get()
							.toString());
				}
				userMap.put(MESSAGE, SUCCESS);

			}

		} catch (AuthenticationException e) {
			userMap.put(MESSAGE, FAILURE);
			LOG.error("validateUser : ", e);
			return userMap;

		} catch (NamingException e) {
			userMap.put(MESSAGE, LDAP_FAILURE);
			LOG.error("validateUser : ", e);
			return userMap;
		}
		return userMap;
	}
}
