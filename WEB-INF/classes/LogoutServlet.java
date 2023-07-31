import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class LogoutServlet extends HttpServlet{

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
{    
    
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
   
    HttpSession session=request.getSession(false);  
     session.invalidate();  
              
            out.print("You are successfully logged out!");  
              
    RequestDispatcher rd = request.getRequestDispatcher("Login2.html");
	   rd.include(request,response);
            out.close();  
   
	
}	


}