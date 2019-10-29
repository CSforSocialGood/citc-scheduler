import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Excel_Input {
	public ArrayList<Teacher> teachers = new ArrayList<Teacher>();
	public ArrayList<Volunteer> volunteers = new ArrayList<Volunteer>();
	public ArrayList<Driver> drivers = new ArrayList<Driver>();
	
	public static void makeVolunteers() {
		try {
			StringTokenizer st ;
			BufferedReader TSVFile = new BufferedReader(new FileReader("newVolunteer.tsv"));
		    String dataRow;
		    
		    dataRow = TSVFile.readLine();
		    dataRow = TSVFile.readLine();
		    
		    while (dataRow != null){
	            st = new StringTokenizer(dataRow,"\t");
	            //System.out.println(st.countTokens());
	            st.nextToken();
	            
	            ArrayList<String>dataArray = new ArrayList<String>() ;
	            
	            int column = 1;
	            //boolean print = true;
	         /* String firstName = "";
            	String lastName = "";
            	String email = "";
            	String phone = "";
            	String school = "";
            	boolean isCurry = false;
            	boolean isSpanish = false;
            	*/
	            
	            while(st.hasMoreElements()){
	                       	  
	            	String firstName = "";
	            	String lastName = "";
	            	String email = "";
	            	String phone = "";
	            	String school = "";
	            	boolean isCurry = false;
	            	boolean isSpanish = false;
	            	
	               
	                String dataToken = st.nextElement().toString();
	                dataArray.add(dataToken + column);
	                
	                switch(column) {
	                case 1:
	                	firstName = dataToken;
	                	column++;
	                	continue;
	                case 2:
	                	lastName = dataToken;
	                	column++;
	                	continue;
	                case 3:
	                	email = dataToken;
	                	column++;
	                	continue;
	                case 4: 
	                	phone = dataToken;
	                	column++;
	                	continue;
	                case 5:
	                	isCurry = dataToken.equals("No") ? false : true;
	                	column++;
	                	continue;
	                case 6:
	                	/////monday
	                	column++;
	                	continue;
	                case 7:
	                	////trues
	                	column++;
	                	continue;
	                case 8:
	                	////weds
	                	column++;
	                	continue;
	                case 9: 
	                	////
	                	column++;
	                	continue;
	                case 10:
	                	////
	                	column++;
	                	continue;
	                case 11: 
	                	///has car?
	                	column++;
	                	continue;
	                case 12:
	                	//can be Driver
	                	column++;
	                	continue;
	                case 13: 
	                	isSpanish = dataToken.equals("No") ? false : true;
	                	continue;
	                	
	                }
	            }   
	           
	            for (String item:dataArray) { 
	                System.out.print(item + "|"); 
	            }
	            System.out.println(); // Print the data line.
	            dataRow = TSVFile.readLine(); // Read next line of data.
	        }
		    
	        // Close the file once all data has been read.
	        TSVFile.close();
		    		
		}catch (FileNotFoundException e) {
			System.out.println("File not found");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//availability in 30 minute blocks
	public static void main(String[] args) {
		makeVolunteers();
}
}
