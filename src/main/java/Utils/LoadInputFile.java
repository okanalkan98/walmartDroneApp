package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadInputFile {
	
	public static List<ArrayList<String>> convertInputFiletoList() throws Exception 
		  { 
		
		  	File file = new File("C:\\Users\\Qkan\\Walmart\\com.walmart.drone\\src\\test\\resources\\input.txt"); 	  

		  	List<ArrayList<String>> listOfOrders = new ArrayList<ArrayList<String>>();
		  	
            try {
    		  	BufferedReader br = new BufferedReader(new FileReader(file)); 

    		  	String stLine; 
    		  	
    		  	int j=0;
    		  	// Add each line of the file to array list splitting into order number, coordinate and order time
    	        while ((stLine = br.readLine()) != null)   {
			  		String[] splitedOrder = stLine.split(" ");
			  		listOfOrders.add(new ArrayList<String>());
			  		listOfOrders.get(j).add(splitedOrder[0]);
			  		listOfOrders.get(j).add(splitedOrder[1]);
			  		listOfOrders.get(j).add(splitedOrder[2]);
			  		j++;
			  	}

                System.out.println("Loaded Input file: "+listOfOrders);
                
    	        br.close();
		  	} catch (FileNotFoundException e) {
	            System.out.println("File is not found in the path!" + e.toString());
	        } catch (IOException e) {
	            System.out.println("Unable to read the file! " + e.toString());
	        } 
            
           return listOfOrders;
		  } 
		
//	public static void main(String[] args) throws Exception {
//		
//		convertInputFiletoList();
//	}

}
