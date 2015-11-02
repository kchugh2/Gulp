

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RateRest
 */
@WebServlet("/RateRest")
public class RateRest extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	static String restname,review;
	static String rate;
	   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RateRest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		restname = request.getParameter("restname");
		rate = request.getParameter("rate");
		review = request.getParameter("review");
		
		
		
			try {
				UpdateRestaurant(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}
	
	public static void UpdateRestaurant(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {

		try {
			// URL of Oracle database server
			String url = "jdbc:oracle:thin:system/password@localhost";
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// properties for creating connection to Oracle database
			Properties props = new Properties();
			props.setProperty("user", "TestUserDB");
			props.setProperty("password", "password");

			// creating connection to Oracle database using JDBC
			Connection conn1 = DriverManager.getConnection(url, props);
			

			String sql1 = "select * from gulprestaurant where restname = '"+restname+"'" ; //request.getParameter("restname") + "'";
			
			
			// creating PreparedStatement object to execute query
			PreparedStatement preStatement1 = conn1.prepareStatement(sql1);

			ResultSet result1 = preStatement1.executeQuery();
			
			result1.next();
			int rid = result1.getInt(4);
			
			conn1.close();
			
			
			
			
			Connection conn = DriverManager.getConnection(url, props);
			

			String sql = "Insert into GULPPREVIEW (RID,Rating,Review) values (" +rid
					+"," +rate +",'" + review +"')";
			
			
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"utf-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><title>Add Restaurant</title>");
			out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\" integrity=\"sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==\"crossorigin=\"anonymous\">");
			out.println("</head><body><nav class=\"navbar navbar-inverse navbar-fixed-top\"	style=\"background-color: #141452\"><div class=\"container\">");
			out.println("<div class=\"navbar-header\"><button type=\"button\" class=\"navbar-toggle collapsed\"	data-toggle=\"collapse\" data-target=\"#navbar\" aria-expanded=\"false\" aria-controls=\"navbar\"><span class=\"sr-only\">Toggle navigation</span> <span>class=\"icon-bar\"></span> <span class=\"icon-bar\"></span> <span class=\"icon-bar\"></span></button></div>");
			out.println("<div id=\"navbar\" class=\"collapse navbar-collapse\"><ul class=\"nav navbar-nav\"><li><a href=\"#Home\">Home</a></li><li><a href=\"AddCustomer.html\">New Customer</a></li><li><a href=\"AddRestaurant.html\">Add a Restaurant</a></li><li class=\"active\"><a href=\"RestRestaurant.html\">Rate a Restaurant</a></li><li><a href=\"AllRest\">All Restaurants</a></li><li><a href=\"CustList\">Customer List</a></li></ul></div><div class=\"jumbotron\">");
			out.println("<p>Restaurant has been rated</p></div></div></nav><br/><br/><script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script><script	src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"	integrity=\"sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==\" crossorigin=\"anonymous\"></script></body></html>");
		
			// creating PreparedStatement object to execute query
			PreparedStatement preStatement = conn.prepareStatement(sql);

			ResultSet result = preStatement.executeQuery();
			conn.close();
		}  catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	

}
