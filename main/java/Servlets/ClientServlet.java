package Servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ListByDataBaseTableStructure.Clients;
import ListByDataBaseTableStructure.Planets;
import WorkingWithDataBase.ClientsDB;
import WorkingWithDataBase.PlanetsDB;
import WorkingWithDataBase.QueueWithDB;

@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ClientServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
		if(request.getParameter("StatusIDC") == null)
		{
		PrintWriter writer = response.getWriter();
		List<Clients> oper =  ClientsDB.select();
		String json = new Gson().toJson(oper);
    	writer.println(json);
		}
		else{
			PrintWriter writer = response.getWriter();
			List<Clients> oper =  ClientsDB.selectStatus(Integer.parseInt(request.getParameter("StatusIDC")));
			String json = new Gson().toJson(oper);
	    	writer.println(json);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
		
    	String Name = request.getParameter("name");
    	String SurName = request.getParameter("surname");
        String Tell = request.getParameter("phone");
    	String Profession = request.getParameter("profession");
    	int id_ApplicationStatuses = 1;
    	
    	Planets foo =  PlanetsDB.selectOne(request.getParameter("select"));
    	int id_planets = foo.getId();
    	
    	String rezalt =  ClientsDB.selectOne(Tell);
    	
    	if(rezalt.equals("false"))
    	{
        	Clients cl = new Clients(Name, SurName, Tell, Profession, id_planets, id_ApplicationStatuses);
        	ClientsDB.insert(cl);
        	
        	//Queue revision (if there is a free operator, then the queue is shifted)
        	QueueWithDB.assignmentOfNewTicketsToOperators();
        	
        	PrintWriter writer = response.getWriter();
        	writer.println("Ви записалися у чергу, чекайте з Вами зв'яжуться!");
    	}
    	else {
        	PrintWriter writer = response.getWriter();
        	writer.println("Заявка з таким номером чекає на обробку або оброблюється!");
    	}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
		
		String body = inputStreamToString(request.getInputStream());
		Map<?, ?> params = parseBody(body);
		
		if(params.get("status").equals("DELETE"))
		{
			int id = Integer.parseInt(params.get("id").toString());
			ClientsDB.delete(id);
		}
	}
	
	protected static String inputStreamToString(InputStream inputStream) {
		try (Scanner scanner = new Scanner(inputStream, "UTF-8")) {
			return scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
		}
	}

	
	protected static Map<String, String> parseBody(String body) {

		Map<String, String> map = new HashMap<String, String>();

		body = body.replaceAll("\\\"", "");
		body = body.replaceAll("\\{", "");
		body = body.replaceAll("\\}", "");

		String[] lines = body.split(",");
		for (String s : lines) {
			String[] item = s.split(":");
			map.put(item[0], item[1]);
		}

		return map;
	}

}
