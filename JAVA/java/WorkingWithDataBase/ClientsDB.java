package WorkingWithDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ListByDataBaseTableStructure.Clients;
import ListByDataBaseTableStructure.Operators;

public class ClientsDB {
	
	public ClientsDB() {
        super();
    }

 //SELECT
    
    public static List<Clients> select(){
		List<Clients> clients = new ArrayList<Clients>();
		try(Connection con = new ConnectionDB().getConnection()) { 	
			   Statement statement = con.createStatement();
               ResultSet resultSet = statement.executeQuery("SELECT * FROM clients");
               while(resultSet.next()){  
            	   clients.add(setValues(resultSet));
                                      }
		    }
             catch(Exception ex){
        	    System.out.println(ex);
                                }
		   return clients;
		}
    
    public static List<Clients> selectStatus(int idStatus) {
    	List<Clients> clients = new ArrayList<Clients>();
		try(Connection con = new ConnectionDB().getConnection()) { 	
			   String sql = "SELECT * FROM clients where id_ApplicationStatuses=?";
			   PreparedStatement s = con.prepareStatement(sql);
			   s.setInt(1, idStatus);
			   ResultSet rs = s.executeQuery();
               while(rs.next()){  
            	   clients.add(setValues(rs));
                                      }
		    }
             catch(Exception ex){
        	    System.out.println(ex);
                                }
		   return clients;
    }
    
    public static String selectOne(String tell) {
    	String rezalt = "false";
        try(Connection con = new ConnectionDB().getConnection()){	
        		String sql = "SELECT * FROM clients WHERE tell=? and (id_ApplicationStatuses=1 or id_ApplicationStatuses=2)";
        		int col = 0;
        		try(PreparedStatement preparedStatement = con.prepareStatement(sql)){
                    preparedStatement.setString(1, tell);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                    	col++;
                    }
                    if(col>0) rezalt = "true";
                    else rezalt = "false";
             }
           }
            catch(Exception ex){
               System.out.println(ex);
                               }
        return rezalt;
    }
    
    public static Clients setValues(ResultSet resultSet) throws SQLException
	{
    	int id = resultSet.getInt(1);
    	String Name = resultSet.getString(2);
    	String SurName = resultSet.getString(3);
        String Tell = resultSet.getString(4);
    	String Profession = resultSet.getString(5);
    	int id_planets = resultSet.getInt(6);
    	int id_ApplicationStatuses = resultSet.getInt(7);
    	int id_Operators = resultSet.getInt(8);
        Clients client = new Clients (id, Name, SurName, Tell, Profession, id_planets, id_ApplicationStatuses, id_Operators);
        return client;
	}
    
    
   //SELECT (selection of unoccupied operators)
    
    public static List<Operators> selectionOfUnoccupiedOperators() {
    	List<Operators> operators = new ArrayList<Operators>();
		try(Connection con = new ConnectionDB().getConnection()) { 	
			   Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from operators where id NOT IN (SELECT clients.id_Operators from clients where clients.id_ApplicationStatuses = 2)");
            while(resultSet.next()){  
            	operators.add(setValuesOperators(resultSet));
                                   }
		    }
             catch(Exception ex){
        	    System.out.println(ex);
                                }
		   return operators;
    }
    
    public static Operators setValuesOperators(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt(1);
        String PIB = resultSet.getString(2);
        String accessIdentifier = resultSet.getString(3);
        Operators operator = new Operators (id, PIB, accessIdentifier);
        return operator;
	}
    
  //UPDATE
    
    public static int update(Clients client)
    {
 	   try(Connection con = new ConnectionDB().getConnection())
 	   {
 		   String sqlUpdate = "UPDATE clients SET id_ApplicationStatuses = ?, id_Operators = ? WHERE id = ?";
 		   try(PreparedStatement preparedStatement = con.prepareStatement(sqlUpdate)){
                preparedStatement.setInt(1, client.getid_ApplicationStatuses());
                preparedStatement.setInt(2, client.getid_Operators());
                preparedStatement.setInt(3, client.getId());
                return  preparedStatement.executeUpdate();
            }
 	   }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
    
  //INSERT
    
    public static int insert(Clients client)
    {
 	   try(Connection con = new ConnectionDB().getConnection())
 	   {
 		   String sqlInsert = "INSERT INTO clients (Name, SurName, Tell, Profession, id_planets, id_ApplicationStatuses)"
 		   		+ " Values (?, ?, ?, ?, ?, ?)";
 		   try(PreparedStatement preparedStatement = con.prepareStatement(sqlInsert)){
                preparedStatement.setString(1, client.getName());
                preparedStatement.setString(2, client.getSurName());
                preparedStatement.setString(3, client.getTell());
                preparedStatement.setString(4, client.getProfession());
                preparedStatement.setInt(5, client.getid_planets());
                preparedStatement.setInt(6, client.getid_ApplicationStatuses());
                return  preparedStatement.executeUpdate();
            }
 	   }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
    
  //DELETE
    
    public static int delete(int id)
    {
 	   try(Connection con = new ConnectionDB().getConnection())
 	   {
 		   String sqlDelete = "DELETE FROM clients WHERE id = ?";
 		   try(PreparedStatement preparedStatement = con.prepareStatement(sqlDelete)){
 			   preparedStatement.setInt(1, id);
                return  preparedStatement.executeUpdate();
            }
 	   }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
}
