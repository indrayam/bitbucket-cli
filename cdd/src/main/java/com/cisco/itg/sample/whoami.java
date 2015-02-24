package main.java.com.cisco.itg.sample;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

public class whoami extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");

        out.println("Remote User: " + request.getRemoteUser());
    }
}

