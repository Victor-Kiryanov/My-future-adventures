package WorkingWithDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ListByDataBaseTableStructure.ApplicationStatuses;

  public class ApplicationStatusesDB {
	
    public ApplicationStatusesDB(){
    	super();
    }

      public static List<ApplicationStatuses> select(){
		
		List<ApplicationStatuses> statuses = new ArrayList<ApplicationStatuses>();
		
		try(Connection con = new ConnectionDB().getConnection()) { 	
			   Statement statement = con.createStatement();
               ResultSet resultSet = statement.executeQuery("SELECT * FROM applicationstatuses");
               while(resultSet.next()){  
            	   statuses .add(setValues(resultSet));
                                      }
		    }
             catch(Exception ex){
        	    System.out.println(ex);
                                }
		   return statuses;
		}
      
      public static ApplicationStatuses selectOne(int id) {
    	  ApplicationStatuses statuses = null;
          try(Connection con = new ConnectionDB().getConnection()){	
          		String sql = "SELECT * FROM applicationstatuses WHERE id=?";
          		try(PreparedStatement preparedStatement = con.prepareStatement(sql)){
                      preparedStatement.setInt(1, id);
                      ResultSet resultSet = preparedStatement.executeQuery();
                      if(resultSet.next()){
                    	  statuses = setValues(resultSet);
                      }
               }
             }
              catch(Exception ex){
                 System.out.println(ex);
                                 }
          return statuses;
      }
      
      public static ApplicationStatuses setValues(ResultSet resultSet) throws SQLException
  	  {
  		int id = resultSet.getInt(1);
        String TitleStatuses = resultSet.getString(2);
        String ValuesStatuses = resultSet.getString(3);
        ApplicationStatuses status = new ApplicationStatuses(id, TitleStatuses, ValuesStatuses);
        return status;
  	  }
}
