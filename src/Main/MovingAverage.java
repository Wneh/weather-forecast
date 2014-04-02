package Main;
import java.util.ArrayList;

import POJO.TimeSerie;
import POJO.WeatherData;


public class MovingAverage {

	
	/*
	 * How many step forward and backward should we calculate the average with
	 */
	public static final int SHORT = 2;
	public static final int LONG = 3;

	
	public MovingAverage(){
		
	}
	
	public ArrayList<TrendPoint> calculate(WeatherData wd){
		//Genenarate the arraylist with moving averages
		ArrayList<Double> shortAv = average(wd.getTimeseries(),SHORT);
		System.out.println(shortAv);
		ArrayList<Double> longAv = average(wd.getTimeseries(),LONG);
		System.out.println(longAv);
		
		//Trends
		System.out.println("Trends:");
		ArrayList<TrendPoint> trends = this.detectTrends(shortAv, longAv);
		System.out.println(trends);
		
		return trends;
	}
	
	private ArrayList<Double> average(ArrayList<TimeSerie> ts, int averageSize){
		ArrayList<Double> result = new ArrayList<Double>();
		
		//Iterate for each measure point
		for(int i = 0; i < ts.size(); i++){
			//Take the data for this point
			double tempResult = 0;
			int t;
//			System.out.print("#" + i + ": ");
			for(t = 0; t < averageSize; t++){
				//Check if we out of bounds in the main arrary
				if(i - t >= 0){
//					System.out.print(i-t + ", ");
					tempResult += ts.get(i-t).getT();
				} 
				else {
					break;
				}
			}
//			System.out.println();
			//Add the result to the main array
			result.add(i, (tempResult/t));
		}
		
		return result;
	}
	
	public ArrayList<TrendPoint> detectTrends(ArrayList<Double> shortSerie, ArrayList<Double> longSerie){
		ArrayList<TrendPoint> result = new ArrayList<TrendPoint>();
		
		boolean nextIsRise = false;
		//Must check if we should start on negative or positive
		for(int i = 0; i < shortSerie.size(); i++){
			if(!shortSerie.get(i).equals(longSerie.get(i))){
				System.out.println("Starting on index: " + i);
				nextIsRise = shortSerie.get(i) < longSerie.get(i) ? true : false;
				break;
			}
		}
		
		for(int i = 0; i < shortSerie.size(); i++){
			if(nextIsRise && (shortSerie.get(i) > longSerie.get(i))){
				result.add(new TrendPoint(TrendPoint.Trend.NEGATIVE,i));
				nextIsRise = false;
			} 
			else if(!nextIsRise && (shortSerie.get(i) < longSerie.get(i))){
				result.add(new TrendPoint(TrendPoint.Trend.POSITIVE,i));
				nextIsRise = true;
			}
		}
		
		return result;
	}
}
