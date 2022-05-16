package TestDBConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WorkingWithDataBase.ConnectionDB;

@WebServlet("/ConnectionDBServlet")
public class ConnectionDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConnectionDBServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try(Connection con = new ConnectionDB().getConnection()){ 
                 writer.println("Connection to ProductDB succesfull!");
        }
        catch(Exception ex){
            writer.println("Connection failed...");
            writer.println(ex);
        }
        finally {
            writer.close();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
