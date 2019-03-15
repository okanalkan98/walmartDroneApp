package Utils;

import java.time.LocalTime;

public class CalculateDeliveryTime {

	public static LocalTime calculateDeliveryTime(String coordinate) {
		
		int xAxis;
		int yAxis;

		// Get x and y coordinates
		if(coordinate.contains("W")) {
			String[] splitedCoordinate = coordinate.split("W");
			yAxis=Integer.parseInt(splitedCoordinate[1]);
			if(splitedCoordinate[0].contains("N")) {
				String[] splitedXaxis = splitedCoordinate[0].split("N");
				xAxis=Integer.parseInt(splitedXaxis[1]);
			}else{
				String[] splitedXaxis = splitedCoordinate[0].split("S");
				xAxis=Integer.parseInt(splitedXaxis[1]);
			}		
		}else {
			String[] splitedCoordinate = coordinate.split("E");
			yAxis=Integer.parseInt(splitedCoordinate[1]);
			if(splitedCoordinate[0].contains("N")) {
				String[] splitedXaxis = splitedCoordinate[0].split("N");
				xAxis=Integer.parseInt(splitedXaxis[1]);
			}else{
				String[] splitedXaxis = splitedCoordinate[0].split("S");
				xAxis=Integer.parseInt(splitedXaxis[1]);
			}
		}
		
		//Calculate delivery time (minutes)
		double hypotenuse= Math.sqrt(xAxis * xAxis + yAxis * yAxis);
		
        int hour = 0;
        int min = (int) hypotenuse;

        //if delivery time is more than 60 minutes then split it into hours and minutes
        if (min > 60) {
            hour =  min / 60;
            min = min - hour * 60;
        }

        //Covert delivery time decimals to seconds
        double seconds = hypotenuse - Math.floor(hypotenuse);
        int sec = (int) Math.ceil(60 * seconds) ;
        
        return LocalTime.of(hour, min, sec);	
			
	}

}
