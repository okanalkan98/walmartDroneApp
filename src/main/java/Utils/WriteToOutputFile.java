package Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.util.Pair;

public class WriteToOutputFile {
	
	public static void writeToOutputFile(Pair<List<ArrayList<String>>,Integer> pair, String sortType) {
		
		Pair<List<ArrayList<String>>,Integer> p = pair;
		List<ArrayList<String>> listOfOrders = p.getKey();
		int NPS= p.getValue();
	
		Path basePath = Paths.get("C:\\Users\\Qkan\\Walmart\\com.walmart.drone\\Test Outputs");
		
		String outputFileAbsolutePath = basePath + System.getProperty("file.separator") 
										+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +"_"
										+ sortType + "_output.txt";
		
        File outputFile = new File (outputFileAbsolutePath);
        
        try {
			outputFile.createNewFile();
			
            FileWriter fw = new FileWriter(outputFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            
            for(ArrayList<String> order: listOfOrders) {
            	
            	String departureTime;
            	
            	if(order.size()==5) {
            		departureTime=" ";
            	}else {
            		departureTime=order.get(5);
            	}
 		
            	bw.write(order.get(0)+" "+departureTime+ System.getProperty("line.separator"));
            }
            
            bw.write("..."+ System.getProperty("line.separator"));
            bw.write("NPS "+NPS);
            bw.close();
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
//	public static void main(String[] args) throws Exception {
//	
//		List<ArrayList<String>> sortedOrders = SortOrders.sortFIFO();
//		Pair<List<ArrayList<String>>,Integer> pair = CalculateNPS.calculateNPS(sortedOrders);
//		writeToOutputFile(pair,"FIFO");
//	}
}
