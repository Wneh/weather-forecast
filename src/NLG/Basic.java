package NLG;

import java.util.ArrayList;

import Main.TrendPoint;
import POJO.TimeSerie;
import POJO.WeatherData;

public class Basic {

	private WeatherData wd;
	private ArrayList<TrendPoint> trendsTemp;
	private ArrayList<TrendPoint> trendsWind;
	
	public Basic(WeatherData wd, ArrayList<TrendPoint> trendsTemp, ArrayList<TrendPoint> windTrend){
		this.wd = wd;
		this.trendsTemp = trendsTemp;
		this.trendsWind = windTrend;		
	}
	
	public String generateSentenceTemp(){
		StringBuilder sb = new StringBuilder();
		TrendPoint firstTrend = this.trendsTemp.get(0);
		
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
	
	public String generateSentenceWind(){
		StringBuilder sb = new StringBuilder();
		TrendPoint firstTrend = this.trendsWind.get(0);
		
		TimeSerie first = this.wd.getTimeseries().get(0);
		
		//sb.append("At ").append(first.getClockTime()).append(" o'clock we will have a temperature of ").append(first.getT()).append(" ° C");
		sb.append("At ").append(first.getClockTime()).append(" o'clock this ").append(first.getDayZone()).append(" we'll see wind speeds of up to ")
		.append(first.getWs()).append(" m/s blowing in ").append(first.getWindDirection()).append(" direction,");
		//Add if it will increase or decrease in temperature
		int maxMinTrendIndex;
		if(firstTrend.getTrend() == TrendPoint.Trend.POSITIVE){
			maxMinTrendIndex = wd.getMaxMinTempTrendIndex(true,0, firstTrend.getIndex());
			sb.append("the wind speed will then build up until about ").append(wd.getTimeseries().get(maxMinTrendIndex).getClockTime()).append(" o'clock");
		}
		else {
			maxMinTrendIndex = wd.getMaxMinTempTrendIndex(true,0, firstTrend.getIndex());
			sb.append("the wind speed will then build up until about ").append(wd.getTimeseries().get(maxMinTrendIndex).getClockTime()).append(" o'clock");	
		}
		sb.append(" with speeds of up to ").append(wd.getTimeseries().get(maxMinTrendIndex).getWs()).append(" m/s blowing in the ").append(wd.getTimeseries().get(maxMinTrendIndex).getWindDirection())
		.append(" direction");
		
		
		return sb.toString();
	}
}