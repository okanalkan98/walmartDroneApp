package TestCases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.*;

import Utils.CalculateNPS;
import Utils.SortOrders;
import Utils.WriteToOutputFile;
import javafx.util.Pair;

public class TestSchedules {
	
	Integer NPSforFIFO;
	Integer NPSforLIFO;
	Integer NPSforSDTFO;
	Integer NPSforLDTFO;
	static HashMap<String, Integer> map = new HashMap();;
	
	@Test
    public void testFIFO() throws Exception {	
		System.out.println("********************************************FIFO*******************************************");
		List<ArrayList<String>> sortedOrders = SortOrders.sortFIFO();
		Pair<List<ArrayList<String>>,Integer> pair = CalculateNPS.calculateNPS(sortedOrders);
		NPSforFIFO=pair.getValue();
		WriteToOutputFile.writeToOutputFile(pair,"FIFO");	
		map.put("FIFO", NPSforFIFO);
    }	
	
	@Test
    public void testLIFO() throws Exception {	
		System.out.println("********************************************LIFO*******************************************");
		List<ArrayList<String>> sortedOrders = SortOrders.sortLIFO();
		Pair<List<ArrayList<String>>,Integer> pair = CalculateNPS.calculateNPS(sortedOrders);
		NPSforLIFO=pair.getValue();
		WriteToOutputFile.writeToOutputFile(pair,"LIFO");
		map.put("LIFO", NPSforLIFO);
    }	
	
	@Test
    public void testSDTFO() throws Exception {	
		System.out.println("********************************************SDTFO*******************************************");
		List<ArrayList<String>> sortedOrders = SortOrders.sortShortestDeliveryTimeFO();
		Pair<List<ArrayList<String>>,Integer> pair = CalculateNPS.calculateNPS(sortedOrders);
		NPSforSDTFO=pair.getValue();
		WriteToOutputFile.writeToOutputFile(pair,"SDTFO");	
		map.put("SDTFO", NPSforSDTFO);
    }	
	
	@Test
    public void testLDTFO() throws Exception {	
		System.out.println("********************************************LDTFO*******************************************");
		List<ArrayList<String>> sortedOrders = SortOrders.sortLongestDeliveryTimeFO();
		Pair<List<ArrayList<String>>,Integer> pair = CalculateNPS.calculateNPS(sortedOrders);
		NPSforLDTFO=pair.getValue();
		WriteToOutputFile.writeToOutputFile(pair,"LDTFO");
		map.put("LDTFO", NPSforLDTFO);
    }	
	
	@AfterClass
	public static void  findBestNPS() {
		System.out.println("********************************************Sorted NPSs*******************************************");
		List<Map.Entry<String, Integer>> list=new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
		
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() { 
	            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
	            return (o1.getValue()).compareTo(o2.getValue()); 
	        }
        });
        
        System.out.println(list);
	}
	

}
