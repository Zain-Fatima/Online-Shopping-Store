import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class toLogin extends HttpServlet{

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
{    

    HttpSession session = request.getSession();
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    if(session == null  || session.getAttribute("user_type")==null)
    {
     RequestDispatcher rd = request.getRequestDispatcher("Login2.html");
	                rd.forward(request,response);  
     }
   else{
String type = (String)session.getAttribute("user_type");
    if(type.equals("User"))
                    {
                        
                        RequestDispatcher rd = request.getRequestDispatcher("user.html");
	                rd.forward(request,response);
                    }
                  else if(type.equals("Admin"))
                    {
                        RequestDispatcher rd = request.getRequestDispatcher("admin.html");
	                rd.forward(request,response);
                    }
           }
out.close();
}

}