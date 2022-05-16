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

import ListByDataBaseTableStructure.Operators;
import WorkingWithDataBase.OperatorBD;

@WebServlet("/OperatorsServlet")
public class OperatorsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OperatorsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
		if(request.getParameter("accessIdentifier") == null)
		{
		PrintWriter writer = response.getWriter();
		List<Operators> oper =  OperatorBD.select();
		String json = new Gson().toJson(oper);
    	writer.println(json);
		}
		else {
			PrintWriter writer = response.getWriter();
			Operators oper =  OperatorBD.selectOne(request.getParameter("accessIdentifier"));
			String json = new Gson().toJson(oper);
	    	writer.println(json);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
		
		String body = inputStreamToString(request.getInputStream());
		Map<?, ?> params = parseBody(body);

		if(params.get("status").equals("INSERT"))
			{
			  String PIB = params.get("PIBs").toString();
    	      String accessIdentifier = params.get("accessIdentifierS").toString();
    	      Operators operator = new Operators (PIB, accessIdentifier);
    	      OperatorBD.insert(operator);
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
			OperatorBD.delete(id);
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
		
		String body = inputStreamToString(request.getInputStream());
		Map<?, ?> params = parseBody(body);
		
		if(params.get("status").equals("UPDATE"))
		{
			int id = Integer.parseInt(params.get("id").toString());
			String accessIdentifier = params.get("accessIdentifier").toString();
    	    Operators operator = new Operators (id, accessIdentifier);
    	    OperatorBD.update(operator);
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
