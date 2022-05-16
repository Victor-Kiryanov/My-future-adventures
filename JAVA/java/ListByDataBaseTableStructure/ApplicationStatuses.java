package ListByDataBaseTableStructure;

import java.io.Serializable;

public class ApplicationStatuses implements Serializable{
	 private static final long serialVersionUID = 1L;

	 private int id;
	 private String TitleStatuses;
	 private String ValuesStatuses;
	 
	 public ApplicationStatuses(String TitleStatuses, String ValuesStatuses)
	 {
		 this.TitleStatuses = TitleStatuses;
		 this.ValuesStatuses = ValuesStatuses;
	 }
	 
	 public ApplicationStatuses(int id, String TitleStatuses, String ValuesStatuses)
	 {
		 this.id = id;
		 this.TitleStatuses = TitleStatuses;
		 this.ValuesStatuses = ValuesStatuses;
	 }
	 
	 public int getId() {
	        return id;
	    }
	 
	 public String getValuesStatuses()
	 {
		 return ValuesStatuses;
	 }
	 
	 public void setValuesStatuses(String ValuesStatuses)
	 {
		 this.ValuesStatuses = ValuesStatuses;
	 }
	 
	 public String getTitleStatuses()
	 {
		 return TitleStatuses;
	 }
	 
	 public void setTitleStatuses(String TitleStatuses)
	 {
		 this.TitleStatuses = TitleStatuses;
	 }

}
