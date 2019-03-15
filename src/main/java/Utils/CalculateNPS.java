package Utils;
import javafx.util.Pair; 
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CalculateNPS {

	public static Pair<List<ArrayList<String>>,Integer> calculateNPS(List<ArrayList<String>> listoforders) {
		
		//Get sorted list
		List<ArrayList<String>> listOfOrders=listoforders;
		
		//initialize operation start-time and end-time
		LocalTime startTime = LocalTime.parse("06:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
		LocalTime startTime1 = LocalTime.parse("05:59:59", DateTimeFormatter.ofPattern("HH:mm:ss"));
		LocalTime endTime = LocalTime.parse("22:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
		
		//initialize order-time for the first order
		LocalTime orderTime=LocalTime.parse(listOfOrders.get(0).get(2), DateTimeFormatter.ofPattern("HH:mm:ss"));
		
		//initialize departure-time for the first order:
		LocalTime departureTime;
			//if first order-time is before start-time then set first departure-time as start time
			if(orderTime.isBefore(startTime)) {
				departureTime=startTime;
			//if first order-time is after end-time then set first departure-time as end-time
			}else if (orderTime.isAfter(endTime)) {
				departureTime=endTime;
			//if first order-time is after start-time then set first departure-time as order-time
			}else {
				departureTime=orderTime;
			}
		
		//initialize delivery-time for the first order:
		LocalTime deliveryTime;	
		deliveryTime = departureTime
                .plusHours(LocalTime.parse(listOfOrders.get(0).get(3)).getHour())
                .plusMinutes(LocalTime.parse(listOfOrders.get(0).get(3)).getMinute())
                .plusSeconds(LocalTime.parse(listOfOrders.get(0).get(3)).getSecond());	
		
		//initialize drone arrival time to Warehouse for the first order:
		LocalTime droneArrivalTimeToWH;
		droneArrivalTimeToWH = deliveryTime
                .plusHours(LocalTime.parse(listOfOrders.get(0).get(3)).getHour())
                .plusMinutes(LocalTime.parse(listOfOrders.get(0).get(3)).getMinute())
                .plusSeconds(LocalTime.parse(listOfOrders.get(0).get(3)).getSecond());	
		
		LocalTime nextOrderDeliveryTime = startTime;
		
		int prompters=0;
		int detracters=0;
		int neutrals = 0;
		Integer NPS=0;
		
	  	int j=0;
	  	//Iterate until the last order for those warehouse arrival time is between start-time and end-time
		while(listOfOrders.size()>j && droneArrivalTimeToWH.isBefore(endTime) && droneArrivalTimeToWH.isAfter(startTime1)){
	
			//add order departure time to the list
			listOfOrders.get(j).add(String.valueOf(departureTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
			
			//Delivery Time = Departure Time + Time to Customer
            deliveryTime = departureTime
                    .plusHours(LocalTime.parse(listOfOrders.get(j).get(3)).getHour())
                    .plusMinutes(LocalTime.parse(listOfOrders.get(j).get(3)).getMinute())
                    .plusSeconds(LocalTime.parse(listOfOrders.get(j).get(3)).getSecond());
            
            //Calculate duration between order-time and delivery-time
            Duration duration = Duration.between(LocalTime.parse(listOfOrders.get(j).get(2)), deliveryTime);
            
            //Convert duration time to hours
            double durationHours=(double)duration.toMinutes()/(double)60;

            //if the order is delivered up to 1 hour then increase prompters 1
			if(0<durationHours && durationHours<=1) {
				prompters++;
			//if the order is delivered after 1 hour up to 3 hours then increase neutrals 1
			}else if(1<durationHours && durationHours<=3) {
				neutrals++;	
			//if the order is delivered after 3 hours then increase detracters 1
			}else {
				detracters++;
			}
			
			//Next Order Departure Time = Previous Order Delivery Time + Time back to the Warehouse
            departureTime = deliveryTime
                    .plusHours(LocalTime.parse(listOfOrders.get(j).get(3)).getHour())
                    .plusMinutes(LocalTime.parse(listOfOrders.get(j).get(3)).getMinute())
                    .plusSeconds(LocalTime.parse(listOfOrders.get(j).get(3)).getSecond());  
            
            //Calculate drone arrival time to WH after deliver next order
            if(listOfOrders.size()>j+1) {
            	
            	orderTime= LocalTime.parse(listOfOrders.get(j+1).get(2));
            	
				/*if next order departure time is before next order time then 
            	  set next order departure time as next order time*/
				if(departureTime.isBefore(orderTime)) {
					departureTime=orderTime;
				}
				
				//Next Order Delivery Time = Next Order Departure Time + Time to Customer
	            nextOrderDeliveryTime = departureTime
	                    .plusHours(LocalTime.parse(listOfOrders.get(j+1).get(3)).getHour())
	                    .plusMinutes(LocalTime.parse(listOfOrders.get(j+1).get(3)).getMinute())
	                    .plusSeconds(LocalTime.parse(listOfOrders.get(j+1).get(3)).getSecond());	
	            
	            //Drone Arrival Time to WH = Next Order Delivery Time + Time back to the Warehouse
	            droneArrivalTimeToWH = nextOrderDeliveryTime
	                    .plusHours(LocalTime.parse(listOfOrders.get(j+1).get(3)).getHour())
	                    .plusMinutes(LocalTime.parse(listOfOrders.get(j+1).get(3)).getMinute())
	                    .plusSeconds(LocalTime.parse(listOfOrders.get(j+1).get(3)).getSecond());    
            }
			
			j++;
		};
		
		System.out.println("Calculating NPS...");
		
		//Calculate number of deliveries
		int totalDelivery=prompters+detracters+neutrals;
		System.out.println("Prompters: "+prompters);
		System.out.println("Detracters: "+detracters);
		System.out.println("Neutrals: "+neutrals);
		System.out.println("Total number of orders delivered: "+totalDelivery);
		
		//Calculate NPS
		NPS=Integer.valueOf(((int)((double)prompters/totalDelivery*100)-(int)((double)detracters/totalDelivery*100)));
		System.out.println("NPS: "+NPS);
		
		//Return updated order list and NPS together
		System.out.println("Output File: "+listOfOrders+"\n");
		return  new Pair<List<ArrayList<String>>,Integer>(listOfOrders, NPS);
	}
	
//	public static void main(String[] args) throws Exception {
//		
//		Pair<List<ArrayList<String>>,Integer> p =calculateNPS(SortOrders.sortLIFO());
//		System.out.println(p.getKey());
//		System.out.println(p.getValue());
//	}

}


