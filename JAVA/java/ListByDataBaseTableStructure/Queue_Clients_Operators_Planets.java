package ListByDataBaseTableStructure;

import java.io.Serializable;

public class Queue_Clients_Operators_Planets implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String Name;
	private String SurName;
	private String Tell;
	private String Profession;
	private String Title;
	private String PIB;
	private int idOperators;
	
	public Queue_Clients_Operators_Planets(int id, String Name, String SurName, String Tell, String Profession, String Title, String PIB, int idOperators)
	{
		this.id = id;
		this.Name = Name;
		this.SurName = SurName;
		this.Tell = Tell;
		this.Profession = Profession;
		this.Title = Title;
		this.PIB = PIB;
		this.idOperators = idOperators;
	}
	
	public Queue_Clients_Operators_Planets(int id, String Name, String SurName, String Tell, String Profession, String Title, String PIB)
	{
		this.id = id;
		this.Name = Name;
		this.SurName = SurName;
		this.Tell = Tell;
		this.Profession = Profession;
		this.Title = Title;
		this.PIB = PIB;
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
	 
	 public String getTitle()
	 {
		 return Title;
	 }
	 
	 public void setTitle(String Title)
	 {
		 this.Title = Title;
	 }
	 
	 public String getPIB()
	 {
		 return PIB;
	 }
	 
	 public void setPIB(String PIB)
	 {
		 this.PIB = PIB;
	 }
	 
	 public int getIdOperators()
	 {
		 return idOperators;
	 }
	 
	 public void setIdOperators(int idOperators)
	 {
		 this.idOperators = idOperators;
	 }
}
