import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;
public class Addtocart extends HttpServlet {

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
      String cid = request.getParameter("id");
      String cs = request.getParameter("size");
      String cp = request.getParameter("price");
   
    out.println("<html>");
    out.println("<head>");
    out.println("<title>addtocart</title>");
    out.println("</head>");
    out.println("<body>");

    HashMap<String, List<String>> cart = (HashMap<String, List<String>>) session.getAttribute("cart");

if (cart == null) {
cart = new HashMap<String, List<String>>();
}

List<String> product = new ArrayList<String>();
product.add(cid);
product.add(cs);
product.add(cp);

cart.put(cid, product);

session.setAttribute("cart", cart);
    
       out.println("Added item successfully");
       out.println("<form action='View' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'Add more items'>");
       out.println("</form>");
       out.println("<form action='Viewcart' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'View cart'>");
       out.println("</form>");
       out.println("<form action='Checkout' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'Checkout'>");
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