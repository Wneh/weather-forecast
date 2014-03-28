import java.util.ArrayList;

import POJO.TimeSerie;
import POJO.WeatherData;


public class MovingAverage {

	
	/*
	 * How many step forward and backward should we calculate the average with
	 */
	public static final int SHORT = 1;
	public static final int LONG = 2;
	
	public MovingAverage(){
		
	}
	
	public void calculate(WeatherData wd){
		ArrayList<Double> shortAv = average(wd.getTimeseries(),SHORT);
		System.out.println(shortAv);
		ArrayList<Double> longAv = average(wd.getTimeseries(),LONG);
		System.out.println(longAv);
	}
	
	private ArrayList<Double> average(ArrayList<TimeSerie> ts, int averageSize){
		ArrayList<Double> result = new ArrayList<Double>();
		
		//Iterate for each measure point
		for(int i = 0; i < ts.size(); i++){
			//Take the data for this point
			double tempResult = 0;
			int t;
			for(t = 0; t < averageSize; t++){
				//Check if we out of bounds in the main arrary
				if(i - t >= 0){
					tempResult += ts.get(i-t).getT();
				} 
				else {
					break;
				}
			}
			//Add the result to the main array
			result.add(i, (tempResult/t));
		}
		
		return result;
	}
}
