package WorkingWithDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ListByDataBaseTableStructure.Operators;

public class OperatorBD {
	
    public OperatorBD() {
        super();
    }

    //SELECT ALL AND ONE
    
    public static List<Operators> select(){
		List<Operators> operators = new ArrayList<Operators>();
		try(Connection con = new ConnectionDB().getConnection()) { 	
			   Statement statement = con.createStatement();
               ResultSet resultSet = statement.executeQuery("SELECT * FROM operators");
               while(resultSet.next()){  
            	   operators.add(setValues(resultSet));
                                      }
		    }
             catch(Exception ex){
        	    System.out.println(ex);
                                }
		   return operators;
		}
    
	public static Operators selectOne(String accessIdentifier) {
		Operators operators = null;
        try(Connection con = new ConnectionDB().getConnection()){	
        		String sql = "SELECT * FROM operators WHERE accessIdentifier=?";
        		try(PreparedStatement preparedStatement = con.prepareStatement(sql)){
                    preparedStatement.setString(1, accessIdentifier);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                    	operators = setValues(resultSet);
                    }
             }
           }
            catch(Exception ex){
               System.out.println(ex);
                               }
        return operators;
    }
    
    public static Operators setValues(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt(1);
        String PIB = resultSet.getString(2);
        String accessIdentifier = resultSet.getString(3);
        Operators operator = new Operators (id, PIB, accessIdentifier);
        return operator;
	}
    
   //INSERT
    
   public static int insert(Operators operat)
   {
	   try(Connection con = new ConnectionDB().getConnection())
	   {
		   String sqlInsert = "INSERT INTO operators (PIB, accessIdentifier) Values (?, ?)";
		   try(PreparedStatement preparedStatement = con.prepareStatement(sqlInsert)){
               preparedStatement.setString(1, operat.getPIB());
               preparedStatement.setString(2, operat.getAccessIdentifier());
               return  preparedStatement.executeUpdate();
           }
	   }
       catch(Exception ex){
           System.out.println(ex);
       }
       return 0;
   }
   
   //UPDATE
   
   public static int update(Operators operat)
   {
	   try(Connection con = new ConnectionDB().getConnection())
	   {
		   String sqlUpdate = "UPDATE operators SET accessIdentifier = ? WHERE id = ?";
		   try(PreparedStatement preparedStatement = con.prepareStatement(sqlUpdate)){
               preparedStatement.setString(1, operat.getAccessIdentifier());
               preparedStatement.setInt(2, operat.getId());
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
		   String sqlDelete = "DELETE FROM operators WHERE id = ?";
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
