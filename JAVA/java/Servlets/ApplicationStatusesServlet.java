package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ListByDataBaseTableStructure.ApplicationStatuses;
import WorkingWithDataBase.ApplicationStatusesDB;


@WebServlet("/ApplicationStatusesServlet")
public class ApplicationStatusesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ApplicationStatusesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
		if(request.getParameter("idStatus") == null)
		{
		 PrintWriter writer = response.getWriter();
		 List<ApplicationStatuses> st =  ApplicationStatusesDB.select();
		 String json = new Gson().toJson(st);
	     writer.println(json);
		}
		else {
			PrintWriter writer = response.getWriter();
			ApplicationStatuses st = ApplicationStatusesDB.selectOne(Integer.parseInt(request.getParameter("idStatus")));
			 String json = new Gson().toJson(st);
		     writer.println(json);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
