<%= request.getRemoteUser() %>
<%
out.println("<ul>");
java.util.Enumeration names = request.getHeaderNames();
while (names.hasMoreElements()) {
   String name = (String) names.nextElement();
   String value = request.getHeader(name);
   out.println(" <li>     <b>" + name + "=</b>" + value +"</li>");
}
out.println("</ul>");
%>
