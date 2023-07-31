import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class Delete extends HttpServlet {

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
       rs.deleteRow();
       out.println("<h1>Record deleted successfully</h1>"); 

       out.println("<form action='admin.html' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'Home'>");
       out.println("</form>");

       out.println("<form action='delete.html' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'Delete more clothes'>");
       out.println("</form>");
        
       
       out.println("<form action='LogoutServlet' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'logout'>");
       out.println("</form>");
               }
           else{
			 out.print("<h1>Record can not be deleted</h1>");  
                       RequestDispatcher rd = request.getRequestDispatcher("delete.html");
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