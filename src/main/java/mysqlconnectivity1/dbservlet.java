package mysqlconnectivity1;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






@WebServlet("/connect")
public class dbservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter out= response.getWriter();
		
		
		try {
			int num=Integer.parseInt(request.getParameter("id"));
			if(num<=10) {
				out.print("<title>product details</title>");
		Properties props= new Properties();
		InputStream in=getServletContext().getResourceAsStream("/WEB-INF/application.properties");
		props.load(in);
		
		Connection conn= dbconfig.getConnection(props);
		response.setContentType("text/html");
		if(conn!=null) {
		try {
			String sql="select *from eproduct where id = ? ;";
			PreparedStatement smt=conn.prepareStatement(sql);
			smt.setInt(1, num);
			
	
	
			ResultSet rs =smt.executeQuery();
			out.print("product details:<br>");
			while(rs.next()) {
				out.print("product Id:"+rs.getInt(1)+"<br>"+"product name:"+rs.getString(2)+"<br>"+"product price:"+rs.getInt(3)+"<br>"+"product added time:"+rs.getDate(4));
			}
		
			
			smt.close();
			conn.close();
		
	
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		else
			out.print("Error while connecting connection");
			}else
				out.print("id not found int the records");
				
	}catch (Exception e) {
		// TODO: handle exception
		out.print("invalid product id");
	}
		
	}
	
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
