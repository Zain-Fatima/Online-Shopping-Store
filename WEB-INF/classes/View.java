import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class View extends HttpServlet {

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
     
   
    out.println("<html>");
    out.println("<head>");
    out.println("<title>view</title>");
    out.println("</head>");
    out.println("<body>");
    
    try{

    Class.forName("com.mysql.cj.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1/db1";

    Connection con=DriverManager.getConnection(url, "root", "root");

    Statement st=con.createStatement();
    
     String query = "Select * from clothdata ";

      ResultSet rs = st.executeQuery( query );

     while(rs.next()){	 
        String cid = rs.getString("id");
        String cs = rs.getString("size");
        String cp = rs.getString("price");
        out.println("<b>id: </b>");
        out.println(cid);
        out.println("<br>");
        out.println("<b>size: </b>");
        out.println(cs);
        out.println("<br>");
        out.println("<b>price: </b>");
        out.println(cp);
        out.println("<br>");
        out.println("<br>");
        out.println("<br>");
        out.println("<form action='Addtocart' method ='POST'>");
        
        out.println("<input type='hidden' name='id' value='" + cid + "'>");
        out.println("<input type='hidden' name='size' value='" + cs + "'>");
        out.println("<input type='hidden' name='price' value='" + cp + "'>");
        
        out.println("<input type = 'submit' name= 'submit' value= 'Add to cart'>");
        out.println("</form>");
               }

       
       out.println("<form action='Viewcart' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'View cart'>");
       out.println("</form>");
       out.println("<form action='LogoutServlet' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'logout'>");
       out.println("</form>");
        
     out.println("</body></html>");

           st.close();
           con.close();

    }catch(Exception e){

      out.println(e);
    }
}
else if(type.equals("Admin")){
	 out.println("<h1>Not a page for you!</h1>");
}
}
 out.close();
	}


}