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
import ListByDataBaseTableStructure.Queue_Clients_Operators_Planets;
import WorkingWithDataBase.ClientsDB;
import WorkingWithDataBase.QueueWithDB;

@WebServlet("/QueueServlet")
public class QueueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QueueServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
			PrintWriter writer = response.getWriter();
			List<Operators> oper =  ClientsDB.selectionOfUnoccupiedOperators();
			String json = new Gson().toJson(oper);
	    	writer.println(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
		
		printResume(response, Integer.parseInt(request.getParameter("idOperatorsQueueForm")));
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
			int id = Integer.parseInt(params.get("idQ").toString());
			int idClientQ = Integer.parseInt(params.get("idClientQ").toString());
			QueueWithDB.updateStatuses(idClientQ);
			QueueWithDB.assignmentOfNewTicketsToOperators();
		}
		
		
	}
	
	private void printResume(HttpServletResponse response, int id) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		Queue_Clients_Operators_Planets r = QueueWithDB.selectOne(id);
        PrintWriter writer = response.getWriter();

        writer.println("<!DOCTYPE html>\n"
        		+ "<html lang=\"en\">\n"
        		+ "<head>\n"
        		+ "<meta charset=\"utf-8\">\n"
        		+ "<title>Поточна заявка для обраного Оператора</title>\n"
        		+ "</head>\n"
        		+ "<body>\n" +
        		"<p>Поточна заявка для обраного Оператора</p>\n" +
        		"<table>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<th>id</th>\n" +
                "<th>Name</th>\n" +
                "<th>SurName</th>\n" +
                "<th>Tell</th>\n" +
                "<th>Profession</th>\n" +
                "<th>Title</th>\n" +
                "<th>PIB Operators</th>\n" +
                "</tr>");
        
            writer.println("<tr>");
            writer.println("<td>" + r.getId() + "</td>");
            writer.println("<td>" + r.getName() + "</td>");
            writer.println("<td>" + r.getSurName() + "</td>");
            writer.println("<td>" + r.getTell() + "</td>");
            writer.println("<td>" + r.getProfession() + "</td>");
            writer.println("<td>" + r.getTitle() + "</td>");
            writer.println("<td>" + r.getPIB() + "</td>");
            writer.println("</tr>");

            writer.println("</tbody\n" +
                           "</table>\n" + 
            		       "<form>\n"+
            		       "<input type=\"submit\" onclick=\"ConfirmRequest("+id+","+r.getId()+")\" name=\"que\" value=\"Підтвердити зявку (Змінити на статус: Виповнено-оброблено)\"> "
            		    + "</form>\n" +
            		       "<p> <a href=\"admin.html\">Відмінити обробку (повернутися до меню)</a></p>\n" +
            		       "<script src=\"./JSforAdmin.js\"></script>" +
            		       "</body>\n" +
            		       "</html>");
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
