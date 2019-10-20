package com.cssg.citcscheduler.util;

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

	public static void main(String[] args) {
		try {
			StringTokenizer st ;
	        BufferedReader TSVFile = new BufferedReader(new FileReader("ExampleForm.tsv"));
	        String dataRow;

	        // Read first line (header), do nothing
	        dataRow = TSVFile.readLine();
	        
	        // Read second line, execute while loop on it
	        dataRow = TSVFile.readLine();
	
	        while (dataRow != null){
	            st = new StringTokenizer(dataRow,"\t");
	            List<String>dataArray = new ArrayList<String>() ;
	            while(st.hasMoreElements()){
	                dataArray.add(st.nextElement().toString() + "  ");
	            }
	            for (String item:dataArray) { 
	                System.out.print(item); 
	            }
	            System.out.println(); // Print the data line.
	            dataRow = TSVFile.readLine(); // Read next line of data.
	        }
	        // Close the file once all data has been read.
	        TSVFile.close();
	
	        // End the printout with a blank line.
	        System.out.println();
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

    } //main()
}
