import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class toUpdate extends HttpServlet {

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{        
    HttpSession session = request.getSession(false);
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    if(session == null || session.getAttribute("user_type")==null)
    {
     out.print("<h1>Login first</h1>");  
                       RequestDispatcher rd = request.getRequestDispatcher("Login2.html");
	                rd.include(request,response);  
    }
    else{
    String type = (String)session.getAttribute("user_type");
    
    if(type.equals("Admin")){
     
     RequestDispatcher rd = request.getRequestDispatcher("update.html");
	                rd.forward(request,response);  

    
    }
        else if(type.equals("User")){
	 out.println("<h1>Not a page for you!</h1>");
}
}
 out.close();
	}


}
    
   
