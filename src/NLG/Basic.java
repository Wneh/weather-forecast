package NLG;

import java.util.ArrayList;

import Main.TrendPoint;
import POJO.TimeSerie;
import POJO.WeatherData;

public class Basic {

	private WeatherData wd;
	private ArrayList<TrendPoint> trends;
	
	public Basic(WeatherData wd, ArrayList<TrendPoint> trends, ArrayList<TrendPoint> windTrend){
		this.wd = wd;
		this.trends = trends;
	}
	
	public String generateSentenceBasic(){
		StringBuilder sb = new StringBuilder();
		TrendPoint firstTrend = this.trends.get(0);
		
		TimeSerie first = this.wd.getTimeseries().get(0);
		
		System.out.println("First: " + first);
		//TimeSerie second = this.wd.getTimeseries().get(firstTrend.getIndex());
		//Some basic text
		sb.append("At ").append(first.getClockTime()).append(" o'clock we will have a temperature of ").append(first.getT()).append(" ° C");
		//Add if it will increase or decrease in temperature
		if(firstTrend.getTrend() == TrendPoint.Trend.POSITIVE){
			int maxMinTrendIndex = wd.getMaxMinTempTrendIndex(true,0, firstTrend.getIndex());
			sb.append(" steady rising until about ").append(wd.getTimeseries().get(maxMinTrendIndex).getClockTime())
			.append(" o'clock where it will reach it's maximum temperature, about ")
			.append(wd.getTimeseries().get(maxMinTrendIndex).getT())
			.append(" ° C.");	
		}
		else {
			int maxMinTrendIndex = wd.getMaxMinTempTrendIndex(true,0, firstTrend.getIndex());
			sb.append(" slowly decreasing until about ").append(wd.getTimeseries().get(maxMinTrendIndex).getClockTime())
			.append(" o'clock where it will reach it's lowest temperature, about ")
			.append(wd.getTimeseries().get(maxMinTrendIndex).getT())
			.append(" ° C.");	
		}
		return sb.toString();		
	}
	
	public String generateSentenceAdvanced(){
		StringBuilder sb = new StringBuilder();
		TrendPoint firstTrend = this.trends.get(0);
		
		TimeSerie first = this.wd.getTimeseries().get(0);
		
		return sb.toString();
	}
}