

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
 * Servlet implementation class CustList
 */
@WebServlet("/CustList")
public class CustList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "jdbc:oracle:thin:system/password@localhost";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// properties for creating connection to Oracle database
		Properties props = new Properties();
		props.setProperty("user", "testuserdb");
		props.setProperty("password", "password");

		// creating connection to Oracle database using JDBC
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, props);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		ResultSet result = null;
		String resttype = null, restname = null;
		int restrating = 0;
		int restid = 0, i = 0, avg = 0, id;
		String dat = null;

		String sql2 = " select * from gulpcustomer";
		// creating PreparedStatement object to execute query
		PreparedStatement preStatement2 = null;

		try {
			preStatement2 = conn.prepareStatement(sql2);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			result = preStatement2.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}

		out.println("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"utf-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><title>Add Restaurant</title>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\" integrity=\"sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==\"crossorigin=\"anonymous\">");
		out.println("</head><body><nav class=\"navbar navbar-inverse navbar-fixed-top\"	style=\"background-color: #141452\"><div class=\"container\">");
		out.println("<div class=\"navbar-header\"><button type=\"button\" class=\"navbar-toggle collapsed\"	data-toggle=\"collapse\" data-target=\"#navbar\" aria-expanded=\"false\" aria-controls=\"navbar\"><span class=\"sr-only\">Toggle navigation</span> <span>class=\"icon-bar\"></span> <span class=\"icon-bar\"></span> <span class=\"icon-bar\"></span></button></div>");
		out.println("<div id=\"navbar\" class=\"collapse navbar-collapse\"><ul class=\"nav navbar-nav\"><li><a href=\"#Home\">Home</a></li><li><a href=\"AddCustomer.html\">New Customer</a></li><li><a href=\"AddRestaurant.html\">Add a Restaurant</a></li><li><a href=\"RestRestaurant.html\">Rate a Restaurant</a></li><li class=\"active\"><a href=\"AllRest\">All Restaurants</a></li><li><a href=\"CustList\">Customer List</a></li></ul></div><div class=\"jumbotron\">");
		out.println("<table class=\"table table-striped\">");
		out.println("<tr><th>Restaurant</th><th>Cuisine</th><th>Rating</th><tr>");
		try {
			while (result.next()) {
				restname = result.getString(2);
				restid = result.getInt(1);
				resttype = result.getString(3);
				restrating=result.getInt(4);

				out.println("<tr><td><a href='RestDetails?restid=" + restid
						+ "'>" + restname + "</a>" + "</td><td>" + resttype + "</td><td>" + restrating + "</td>"
						+ "<tr>");

			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.println("</table>");
		// out.println("<h4 style='text-align:left'>Average Grade: " + avg +
		// "</h4>");
		out.println("<div class = 'container' align='center'>");
		out.println("<a href='AddCustomer.html' class='btn btn-info' role='button'>Add Customer</a>");
		out.println("<a href='AddRestaurant.html' class='btn btn-info' role='button'>Add Restaurant</a>");
		out.println("</div>");
		out.println("</div>");
		out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>");
		out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\" integrity=\"sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==\" crossorigin=\"anonymous\"></script>");
		out.println("</body>");
		out.println("</html>");

	}

}