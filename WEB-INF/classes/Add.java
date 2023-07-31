import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class Add extends HttpServlet {

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
    String c_size = request.getParameter("csize");
    String c_price = request.getParameter("cprice");
    
   
    out.println("<html>");
    out.println("<head>");
    out.println("<title>add</title>");
    out.println("</head>");
    out.println("<body>");
    
    try{

    Class.forName("com.mysql.cj.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1/db1";

    Connection con=DriverManager.getConnection(url, "root", "root");

    Statement st=con.createStatement();
    
 
     String query = "INSERT INTO clothdata(ID,size,price)VALUES('"+ c_id + "','" + c_size + "','" +c_price+ "') ";

     System.out.println(query);

      int rs = st.executeUpdate( query );

     if(rs==1){	 
       out.println("<h1>Record added successfully</h1>"); 

       out.println("<form action='admin.html' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'Home'>");
       out.println("</form>");
        out.println("<form action='add.html' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'Add more clothes'>");
       out.println("</form>");
       
       out.println("<form action='LogoutServlet' method ='POST'>");
       out.println(" <input type = 'submit' name= 'submit' value= 'logout'>");
       out.println("</form>");
               }
           else{
			 out.print("<h1>Record can not be inserted</h1>");  
                       RequestDispatcher rd = request.getRequestDispatcher("add.html");
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
}
 out.close();
	}


}