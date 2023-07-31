import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;
public class Viewcart extends HttpServlet {

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
        for (Map.Entry<String, List<String>> entry : cart.entrySet()) {
        String productId = entry.getKey();
        List<String> productInfo = entry.getValue();
        String productSize = productInfo.get(1);
        String productPrice = productInfo.get(2);

       out.println("cloth ID: " + productId);
       out.println("cloth Size: " + productSize);
       out.println("cloth Price: " + productPrice);
       out.println("<br><br>");
        }
    }
      else{out.println("No item");}
       
       out.println("<br><br>");
       out.println("<form action='View' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'Add more items'>");
       out.println("</form>");
       out.println("<form action='Checkout' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'Checkout'>");
       out.println("</form>");
       out.println("<form action='Order' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'Place order'>");
       out.println("</form>");
       out.println("<form action='LogoutServlet' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'logout'>");
       out.println("</form>");
        
     out.println("</body></html>") ;       
    }
else if(type.equals("Admin")){
	 out.println("<h1>Not a page for you!</h1>");
}
}
 out.close();
	}


}