<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<link href="http://wwwin.cisco.com/cec/common_2/c/cec_base.css" rel="stylesheet" type="text/css" />

<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="javax.naming.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="oracle.jdbc.driver.*" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<TITLE>Eman Monitor Test</TITLE>
</HEAD>
<BODY>
<%
        //String lifecycle = Utilities.getLifecycle();
        String lifecycle = System.getProperty("cisco.life");
        String jndiName = "jdbc/newapp_" + lifecycle;        
        //String jndiName = "jdbc/newapp_dev";

        Connection conn = null;
        out.print("<table align=center>");
        try {
                //InitialContext context = new InitialContext();
                Context initContext = new InitialContext();
                Context envContext  = (Context)initContext.lookup("java:/comp/env");
                DataSource dataSource = (DataSource) envContext.lookup(jndiName);
                //DataSource dataSource = (DataSource) context.lookup(jndiName);
                conn = dataSource.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select 'success' from dual");
                while(rs.next()){
                   out.print("<tr><td><h2>"+rs.getString(1)+"<h2></td></tr>");
                }
                rs.close();
                stmt.close();
        }//end try
        catch (Exception e) {
                out.print("<tr><td>" + e.toString() + "</td></tr>");
        }//end catch
        finally {
                if (conn != null) {
                        try {
                                conn.close();
                        }//end try
                        catch (Exception e) {}
                }//end if
        }//end finally
        out.print("</table>");
%>
</BODY>
</HTML>

