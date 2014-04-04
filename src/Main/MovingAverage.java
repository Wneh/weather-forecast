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
	
	public ArrayList<TrendPoint> tempTrend;
	public ArrayList<TrendPoint> windTrend;

	
	public MovingAverage(){
		
	}
	
	public void calculate(WeatherData wd){
		//Genenarate the arraylist with moving averages for temperature
		System.out.println("Temperature average:");
		ArrayList<Double> shortAvTemp = averageTemp(wd.getTimeseries(),SHORT);
		System.out.println(shortAvTemp);
		ArrayList<Double> longAvTemp = averageTemp(wd.getTimeseries(),LONG);
		System.out.println(longAvTemp);
		
		//Wind average
		System.out.println("Wind average");
		ArrayList<Double> shortAvWind = averageWind(wd.getTimeseries(),SHORT);
		System.out.println(shortAvWind);
		ArrayList<Double> longAvWind = averageWind(wd.getTimeseries(), LONG);
		
		//Trends
		System.out.println("\nTrends:");
		tempTrend = this.detectTrends(shortAvTemp, longAvTemp);
		windTrend = this.detectTrends(shortAvWind, longAvWind);
		System.out.println("Temp:");
		System.out.println(tempTrend);
		System.out.println("Wind");
		System.out.println(windTrend);
	}
	
	private ArrayList<Double> averageTemp(ArrayList<TimeSerie> ts, int averageSize){
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
	
	private ArrayList<Double> averageWind(ArrayList<TimeSerie> ts, int averageSize){
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
	
	public ArrayList<TrendPoint> detectTrends(ArrayList<Double> shortSerie, ArrayList<Double> longSerie){
		ArrayList<TrendPoint> result = new ArrayList<TrendPoint>();
		
		boolean nextIsRise = false;
		//Must check if we should start on negative or positive
		int startPos = 0;
		for(int i = 0; i < shortSerie.size(); i++){
			if(!shortSerie.get(i).equals(longSerie.get(i))){
				System.out.println("Starting on index: " + i);
				nextIsRise = shortSerie.get(i) < longSerie.get(i) ? true : false;
				startPos = i;
				break;
			}
		}
		
		for(int i = startPos; i < shortSerie.size(); i++){
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
