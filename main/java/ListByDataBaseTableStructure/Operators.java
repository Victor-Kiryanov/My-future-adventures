package ListByDataBaseTableStructure;

import java.io.Serializable;

public class Operators implements Serializable{
	 private static final long serialVersionUID = 1L;
	 
	 private int id;
	 private String PIB;
	 private String accessIdentifier;
	 
	 public Operators(String PIB, String accessIdentifier)
	 {
		 this.PIB = PIB;
		 this.accessIdentifier = accessIdentifier;
	 }
	 
	 public Operators(int id, String PIB, String accessIdentifier)
	 {
		 this.id = id;
		 this.PIB = PIB;
		 this.accessIdentifier = accessIdentifier;
	 }
	 
	 public Operators(int id, String accessIdentifier)
	 {
		 this.id = id;
		 this.accessIdentifier = accessIdentifier;
	 }
	 
	 public int getId() {
	        return id;
	    }
	 
	 public String getPIB()
	 {
		 return PIB;
	 }
	 
	 public void setPIB(String PIB)
	 {
		 this.PIB = PIB;
	 }
	 
	 public String getAccessIdentifier()
	 {
		 return accessIdentifier;
	 }
	 
	 public void setAccessIdentifier(String accessIdentifier)
	 {
		 this.accessIdentifier = accessIdentifier;
	 }
}
