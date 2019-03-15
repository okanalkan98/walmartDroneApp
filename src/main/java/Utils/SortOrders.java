package Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class SortOrders {
	
	// 1.Sort orders ascending by order time
	public static List<ArrayList<String>> sortFIFO() throws Exception {
		
		List<ArrayList<String>> listOfOrders=LoadInputFile.convertInputFiletoList();
		
		
		for(ArrayList<String> order: listOfOrders) {	
			order.add(String.valueOf(CalculateDeliveryTime.calculateDeliveryTime(order.get(1))));
		}
		
		Collections.sort(listOfOrders, new Comparator<ArrayList<String>>() {    
		        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
		            return o1.get(2).compareTo(o2.get(2));
		        }               
			}
		);
		
		int i=listOfOrders.size();
		for(ArrayList<String> order: listOfOrders) {	
			order.add(String.valueOf(i));
			i--;
		}
		
		System.out.println("Sorted Input File by FIFO: "+listOfOrders);
			return listOfOrders;
	}
	
	// 2.Sort orders descending by order time
	public static List<ArrayList<String>> sortLIFO() throws Exception {

		List<ArrayList<String>> listOfOrders=sortFIFO();
		
		Collections.sort(listOfOrders, new Comparator<ArrayList<String>>() {    
		        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
		            return o1.get(4).compareTo(o2.get(4));
		        }               
			}
		);
		System.out.println("Sorted Input File by LIFO: "+listOfOrders);
			return listOfOrders;
	}
	
	// 3.Sort orders ascending by delivery time
	public static List<ArrayList<String>> sortShortestDeliveryTimeFO() throws Exception {

		List<ArrayList<String>> listOfOrders=LoadInputFile.convertInputFiletoList();
		
		for(ArrayList<String> order: listOfOrders) {	
			order.add(String.valueOf(CalculateDeliveryTime.calculateDeliveryTime(order.get(1))));
		}
		
		Collections.sort(listOfOrders, new Comparator<ArrayList<String>>() {    
		        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
		            return o1.get(3).compareTo(o2.get(3));
		        }               
			}
		);
		
		int i=listOfOrders.size();
		for(ArrayList<String> order: listOfOrders) {	
			order.add(String.valueOf(i));
			i--;
		}
		System.out.println("Sorted Input File by SDTFO: "+listOfOrders);
			return listOfOrders;
	}
	
	// 3.Sort orders descending by delivery time
	public static List<ArrayList<String>> sortLongestDeliveryTimeFO() throws Exception {
		
		List<ArrayList<String>> listOfOrders=sortShortestDeliveryTimeFO();
		
		Collections.sort(listOfOrders, new Comparator<ArrayList<String>>() {    
		        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
		            return o1.get(4).compareTo(o2.get(4));
		        }               
			}
		);
		System.out.println("Sorted Input File by LDTFO: "+listOfOrders);
			return listOfOrders;
	}

}
