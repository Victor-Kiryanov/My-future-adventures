package ListByDataBaseTableStructure;

import java.io.Serializable;

public class Clients implements Serializable{

	private static final long serialVersionUID = 1L;
	 
	private int id;
	private String Name;
	private String SurName;
	private String Tell;
	private String Profession;
	private int id_planets;
	private int id_ApplicationStatuses;
	private int id_Operators;
	
	public Clients(String Name, String SurName, String Tell, String Profession, int id_planets, int id_ApplicationStatuses, int id_Operators)
	 {
		this.Name = Name;
		this.SurName = SurName;
		this.Tell = Tell;
		this.Profession = Profession;
		this.id_planets = id_planets;
		this.id_ApplicationStatuses = id_ApplicationStatuses;
		this.id_Operators = id_Operators;
	 }
	
	public Clients(String Name, String SurName, String Tell, String Profession, int id_planets, int id_ApplicationStatuses)
	 {
		this.Name = Name;
		this.SurName = SurName;
		this.Tell = Tell;
		this.Profession = Profession;
		this.id_planets = id_planets;
		this.id_ApplicationStatuses = id_ApplicationStatuses;
	 }
	 
	 public Clients(int id, String Name, String SurName, String Tell, String Profession, int id_planets, int id_ApplicationStatuses, int id_Operators)
	 {
		 this.id = id;
		 this.Name = Name;
		 this.SurName = SurName;
		 this.Tell = Tell;
		 this.Profession = Profession;
		 this.id_planets = id_planets;
		 this.id_ApplicationStatuses = id_ApplicationStatuses;
		 this.id_Operators = id_Operators;
	 }
	 
	 public Clients(int id, int id_ApplicationStatuses, int id_Operators)
	 {
		 this.id = id;
		 this.id_ApplicationStatuses = id_ApplicationStatuses;
		 this.id_Operators = id_Operators;
	 }
	 
	 public Clients(int id, int id_ApplicationStatuses)
	 {
		 this.id = id;
		 this.id_ApplicationStatuses = id_ApplicationStatuses;
	 }
	 
	 public int getId() {
	        return id;
	    }
	 
	 public String getName()
	 {
		 return Name;
	 }
	 
	 public void setName(String Name)
	 {
		 this.Name = Name;
	 }
	 
	 public String getSurName()
	 {
		 return SurName;
	 }
	 
	 public void setSurName(String SurName)
	 {
		 this.SurName = SurName;
	 }
	 
	 public String getTell()
	 {
		 return Tell;
	 }
	 
	 public void setTell(String Tell)
	 {
		 this.Tell = Tell;
	 }
	 
	 public String getProfession()
	 {
		 return Profession;
	 }
	 
	 public void setProfession(String Profession)
	 {
		 this.Profession = Profession;
	 }
	 
	 public int getid_planets()
	 {
		 return id_planets;
	 }
	 
	 public void setid_planets(int id_planets)
	 {
		 this.id_planets = id_planets;
	 }
	 
	 public int getid_ApplicationStatuses()
	 {
		 return id_ApplicationStatuses;
	 }
	 
	 public void setid_ApplicationStatuses(int id_ApplicationStatuses)
	 {
		 this.id_ApplicationStatuses = id_ApplicationStatuses;
	 }
	 
	 public int getid_Operators()
	 {
		 return id_Operators;
	 }
	 
	 public void setid_Operators(int id_Operators)
	 {
		 this.id_Operators = id_Operators;
	 }
}
