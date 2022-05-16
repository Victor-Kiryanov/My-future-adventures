package WorkingWithDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ListByDataBaseTableStructure.Clients;
import ListByDataBaseTableStructure.Operators;
import ListByDataBaseTableStructure.Queue_Clients_Operators_Planets;

public class QueueWithDB {
	
	public QueueWithDB() {
        super();
    }

 //For Queue revision (if there is a free operator, then the queue is shifted)
	public static void assignmentOfNewTicketsToOperators()
	{
		List<Operators> oper =  ClientsDB.selectionOfUnoccupiedOperators();
		List<Clients> client =  ClientsDB.selectStatus(1);
		
		if(oper.isEmpty() == false && client.isEmpty() == false)
		{		
			for(Operators operator : oper)
			{
				if(client.isEmpty() == false)
				{
				   int id = operator.getId();
				   int idClients = client.get(0).getId();
				   Clients cl = new Clients (idClients , 2, id);
				   ClientsDB.update(cl);
				   client.remove(0);
				}
			}
		}
	}
	
	public static Queue_Clients_Operators_Planets selectOne(int idOperators) {
		Queue_Clients_Operators_Planets queue = null;
        try(Connection con = new ConnectionDB().getConnection()){	
        		String sql = "SELECT clients.id, clients.Name, clients.SurName,"
        				+ "       clients.Tell, clients.Profession,"
        				+ "       planets.Title, operators.PIB FROM clients, planets, operators where clients.id_ApplicationStatuses = 2"
        				+ "         and clients.id_planets = planets.id"
        				+ "         and clients.id_Operators = operators.id and operators.id =?";
        		try(PreparedStatement preparedStatement = con.prepareStatement(sql)){
                    preparedStatement.setInt(1, idOperators);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                    	queue = setValues(resultSet);
                    }
             }
           }
            catch(Exception ex){
               System.out.println(ex);
                               }
        return queue;
    } 
	
	public static Queue_Clients_Operators_Planets setValues(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt(1);
    	String Name = resultSet.getString(2);
    	String SurName = resultSet.getString(3);
        String Tell = resultSet.getString(4);
    	String Profession = resultSet.getString(5);
    	String Title = resultSet.getString(6);
    	String PIB = resultSet.getString(7);
    	Queue_Clients_Operators_Planets queue = new Queue_Clients_Operators_Planets (id, Name, SurName, Tell, Profession, Title, PIB);
        return queue;
	}
	
  //UPDATEStatuses
    
    public static int updateStatuses(int id)
    {
 	   try(Connection con = new ConnectionDB().getConnection())
 	   {
 		   String sqlUpdate = "UPDATE clients SET id_ApplicationStatuses = 3 WHERE id = ?";
 		   try(PreparedStatement preparedStatement = con.prepareStatement(sqlUpdate)){
                preparedStatement.setInt(1,id);;
                return preparedStatement.executeUpdate();
            }
 	   }
        catch(Exception ex){
            System.out.println(ex);
        }
 	   return 0;
    }
}
