package WorkingWithDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ListByDataBaseTableStructure.Planets;

public class PlanetsDB { 
	
    public PlanetsDB() {
        super();
    }
	
	public static List<Planets> select(){
		List<Planets> planets = new ArrayList<Planets>();
		try(Connection con = new ConnectionDB().getConnection()) { 	
			   Statement statement = con.createStatement();
               ResultSet resultSet = statement.executeQuery("SELECT * FROM planets");
               while(resultSet.next()){  
                    planets.add(setValues(resultSet));
                                      }
		    }
             catch(Exception ex){
        	    System.out.println(ex);
                                }
		   return planets;
		}
	
	public static Planets selectOne(String ValuesPlanets) {
        Planets planets = null;
        try(Connection con = new ConnectionDB().getConnection()){	
        		String sql = "SELECT * FROM planets WHERE ValuesPlanets=?";
        		try(PreparedStatement preparedStatement = con.prepareStatement(sql)){
                    preparedStatement.setString(1, ValuesPlanets);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                    	planets = setValues(resultSet);
                    }
             }
           }
            catch(Exception ex){
               System.out.println(ex);
                               }
        return planets;
    }
	
	public static Planets setValues(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt(1);
        String ValuesPlanets = resultSet.getString(2);
        String Title = resultSet.getString(3);
        Planets planet = new Planets(id, ValuesPlanets, Title);
        return planet;
	}
}
