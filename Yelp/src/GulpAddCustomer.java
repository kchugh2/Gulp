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
 * Servlet implementation class GulpAddCustomer
 */
@WebServlet("/GulpAddCustomer")
public class GulpAddCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String custname,custemail,custzip;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GulpAddCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		custname = request.getParameter("name");
		custemail = request.getParameter("email");
		custzip = request.getParameter("zip");
		UpdateCustomer();
		out.println("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"utf-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><title>Add Restaurant</title>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\" integrity=\"sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==\"crossorigin=\"anonymous\">");
		out.println("</head><body><nav class=\"navbar navbar-inverse navbar-fixed-top\"	style=\"background-color: #141452\"><div class=\"container\">");
		out.println("<div class=\"navbar-header\"><button type=\"button\" class=\"navbar-toggle collapsed\"	data-toggle=\"collapse\" data-target=\"#navbar\" aria-expanded=\"false\" aria-controls=\"navbar\"><span class=\"sr-only\">Toggle navigation</span> <span>class=\"icon-bar\"></span> <span class=\"icon-bar\"></span> <span class=\"icon-bar\"></span></button></div>");
		out.println("<div id=\"navbar\" class=\"collapse navbar-collapse\"><ul class=\"nav navbar-nav\"><li><a href=\"#Home\">Home</a></li><li  class=\"active\"><a href=\"AddCustomer.html\">New Customer</a></li><li><a href=\"AddRestaurant\">Add a Restaurant</a></li><li><a href=\"RestRestaurant\">Rate a Restaurant</a></li><li><a href=\"AllRest\">All Restaurants</a></li><li><a href=\"CustList\">Customer List</a></li></ul></div><div class=\"jumbotron\">");
		out.println("<p>New Customer has been created</p></div></div></nav><br/><br/><script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script><script	src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"	integrity=\"sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==\" crossorigin=\"anonymous\"></script></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public static void UpdateCustomer() {

		try {
			// URL of Oracle database server
			String url = "jdbc:oracle:thin:system/password@localhost";
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// properties for creating connection to Oracle database
			Properties props = new Properties();
			props.setProperty("user", "TestUserDB");
			props.setProperty("password", "password");

			// creating connection to Oracle database using JDBC
			Connection conn = DriverManager.getConnection(url, props);

			String sql = "Insert into GULPCUSTOMER (CUSTID,CUSTNAME,CUSTEMAIL,CUSTZIP) values (seq_cust.nextval"
					+ ",'" + custname + "','" + custemail + "','" + custzip +"')";

			// creating PreparedStatement object to execute query
			PreparedStatement preStatement = conn.prepareStatement(sql);

			ResultSet result = preStatement.executeQuery();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
