import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;
public class Order extends HttpServlet {

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
   
    if(type.equals("User")){
      
       HashMap<String, List<String>> cart = (HashMap<String, List<String>>)session.getAttribute("cart");

        if (cart != null) {
       session.removeAttribute("cart");
       out.println("Order placed successfully");
}
else{out.println("No item");
     out.println("<form action='View' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'Add items'>");
       out.println("</form>");}

       out.println("<br><br>");
       out.println("<form action='user.html' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'Home'>");
       out.println("</form>");
       
       out.println("<form action='LogoutServlet' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'logout'>");
       out.println("</form>");
        
     out.println("</body></html>");

           
}
else if(type.equals("Admin")){
	 out.println("<h1>Not a page for you!</h1>");
}
}
 out.close();
	}

}