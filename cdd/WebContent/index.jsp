<%@ page  language="java" session="true" import="java.net.InetAddress" %>
<%
 String hostName=java.net.InetAddress.getLocalHost().getHostName();
 String logDir = System.getProperty("user.dir");
 String user = System.getProperty("user.name");
 String jvm = user.replaceFirst("xx","");
%>

<html>
<head><title>sample app</title></head>
<body>

This JVM(<%= jvm%>) ruuning on<b> <%= hostName%> </b> and log for this instance is <b> <%= logDir%>
</B>
<P><hr><P>

LDAP authentication/entitlement:<UL>
<LI><a href="whoami">Any authenticated user</a>
</UL><P>

<a href="Tester-New.jsp"><B>Test DB component </B> </a>

</body>
</html>
