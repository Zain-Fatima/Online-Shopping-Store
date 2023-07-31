import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class Signup extends HttpServlet {

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{        
	
	
	response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String u_name = request.getParameter("uname");
    String u_email = request.getParameter("uemail");
    String pass_word = request.getParameter("upass");
    String u_address = request.getParameter("uadd");
    String u_type = request.getParameter("utype");
    
   
    out.println("<html>");
    out.println("<head>");
    out.println("<title>signup</title>");
    out.println("</head>");
    out.println("<body>");
    
    try{

    Class.forName("com.mysql.cj.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1/db1";

    Connection con=DriverManager.getConnection(url, "root", "root");

    Statement st=con.createStatement();
    
    

     
     String query = "INSERT INTO userdata(name,email,password,address,type)VALUES('"+ u_name + "','" + u_email + "','" +pass_word+ "','" + u_address+ "','" + u_type+ "') ";

     System.out.println(query);

      int rs = st.executeUpdate( query );

     if(rs==1){	 
            HttpSession session = request.getSession(true);
            session.setAttribute("user_type", u_type);
            session.setAttribute("user_name", u_name);
            session.setAttribute("user_pasw", pass_word);
            session.setAttribute("user_email", u_email);            
                        if(u_type.equals("User"))
                    {
                        
                        RequestDispatcher rd = request.getRequestDispatcher("user.html");
	                rd.forward(request,response);
                    }
                  else if(u_type.equals("Admin"))
                    {
                        RequestDispatcher rd = request.getRequestDispatcher("admin.html");
	                rd.forward(request,response);
                    }
               }
           else{
			 out.print("Record can not be inserted");  
                       RequestDispatcher rd = request.getRequestDispatcher("Login2.html");
	                rd.include(request,response);  
		}
     out.println("</body></html>");

           st.close();
           con.close();

    }catch(Exception e){

      out.println(e);
    }

	
	}


}