package ListByDataBaseTableStructure;

import java.io.Serializable;

public class Planets implements Serializable{
	 private static final long serialVersionUID = 1L;
	 
	 private int id;
	 private String ValuesPlanets;
	 private String Title;
	 
	 public Planets(String ValuesPlanets, String Title)
	 {
		 this.ValuesPlanets = ValuesPlanets;
		 this.Title = Title;
	 }
	 
	 public Planets(int id, String ValuesPlanets, String Title)
	 {
		 this.id = id;
		 this.ValuesPlanets = ValuesPlanets;
		 this.Title = Title;
	 }
	 
	 public int getId() {
	        return id;
	    }
	 
	 public String getValuesPlanets()
	 {
		 return ValuesPlanets;
	 }
	 
	 public void setValuesPlanets(String ValuesPlanets)
	 {
		 this.ValuesPlanets = ValuesPlanets;
	 }
	 
	 public String getTitle()
	 {
		 return Title;
	 }
	 
	 public void setTitle(String Title)
	 {
		 this.Title = Title;
	 }
}

