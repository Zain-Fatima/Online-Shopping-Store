import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class Update extends HttpServlet {

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
     

    String c_id = request.getParameter("cID");
    String n_id = request.getParameter("ID");
    String n_size = request.getParameter("csize");
    String n_price = request.getParameter("cprice");

    out.println("<html>");
    out.println("<head>");
    out.println("<title>add</title>");
    out.println("</head>");
    out.println("<body>");
    
    try{

    Class.forName("com.mysql.cj.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1/db1";

    Connection con=DriverManager.getConnection(url, "root", "root");

    PreparedStatement st=con.prepareStatement("Select * from clothdata where id='"+c_id+"'",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
    
 
   
     ResultSet rs = st.executeQuery();
   
     if(rs.next()){
       rs.updateString("id",n_id);
       rs.updateString("size",n_size);
       rs.updateString("price",n_price);
       rs.updateRow();
       out.println("<h1>Record updated successfully</h1>"); 

       out.println("<form action='admin.html' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'Home'>");
       out.println("</form>");

       out.println("<form action='update.html' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'Update more clothes'>");
       out.println("</form>");
       
      
       out.println("<form action='LogoutServlet' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'logout'>");
       out.println("</form>");
               }
           else{
			 out.print("<h1>Record can not be updated</h1>");  
                       RequestDispatcher rd = request.getRequestDispatcher("update.html");
	                rd.include(request,response);  
		}
     out.println("</body></html>");

           st.close();
           con.close();

    }catch(Exception e){

      out.println(e);
    }
}
else if(type.equals("User")){
	 out.println("<h1>Not a page for you!</h1>");
}

 out.close();
	}
}

}