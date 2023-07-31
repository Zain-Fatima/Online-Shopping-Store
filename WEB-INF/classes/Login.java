import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class Login extends HttpServlet{

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
{    

    HttpSession session = request.getSession();
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String u_name = request.getParameter("uname");
    String pass_word = request.getParameter("upass");

    out.println("<html>");
    out.println("<head>");
    out.println("<title>Login</title>");
    out.println("</head>");
    out.println("<body>");

    
    try{
    Class.forName("com.mysql.cj.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1/db1";

    Connection con=DriverManager.getConnection(url,"root","root");

    Statement st=con.createStatement();
    
     String query="Select * from userdata where name='"+u_name+"' and password='"+pass_word+"'";
   
     ResultSet rs = st.executeQuery( query );
   
     if(rs.next()){

    	    String name = rs.getString("name");
            String typ = rs.getString("type");
            
            session.setAttribute("user_type", typ);
            session.setAttribute("user_name", name);
        
                 if(typ.equals("User"))
                    {
                        
                        RequestDispatcher rd = request.getRequestDispatcher("user.html");
	                rd.forward(request,response);
                    }
                  else if(typ.equals("Admin"))
                    {
                        RequestDispatcher rd = request.getRequestDispatcher("admin.html");
	                rd.forward(request,response);
                    }
               }
           else{
			 out.print("Sorry, username or password is wrong!");  
                       RequestDispatcher rd = request.getRequestDispatcher("Login2.html");
	                rd.include(request,response);  
		}
     
           out.println("</body></html>");
           st.close();
           con.close();
           

    }catch(Exception e){

      out.println(e);
    }
	out.close();
	}

}