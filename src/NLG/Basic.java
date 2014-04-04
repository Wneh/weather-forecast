package NLG;

import java.util.ArrayList;

import Main.TrendPoint;
import POJO.TimeSerie;
import POJO.WeatherData;

public class Basic {

	private WeatherData wd;
	private ArrayList<TrendPoint> trends;
	
	public Basic(WeatherData wd, ArrayList<TrendPoint> trends){
		this.wd = wd;
		this.trends = trends;
	}
	
	public String generateSentence(){
		StringBuilder sb = new StringBuilder();
		TrendPoint firstTrend = this.trends.get(0);
		
		TimeSerie first = this.wd.getTimeseries().get(0);
		
		System.out.println("First: " + first);
		//TimeSerie second = this.wd.getTimeseries().get(firstTrend.getIndex());
		//Some basic text
		sb.append("At ").append(first.getClockTime()).append(" the temperature will be ").append(first.getT()).append(" ° Celsius");
		//Add if it will increase or decrease in temperature
		if(firstTrend.getTrend() == TrendPoint.Trend.POSITIVE){
			int maxMinTrendIndex = wd.getMaxMinTempTrendIndex(true,0, firstTrend.getIndex());
			sb.append(" and increase to ").append(wd.getTimeseries().get(maxMinTrendIndex).getT())
			  .append(" ° Celsius at ").append(wd.getTimeseries().get(maxMinTrendIndex).getClockTime());
		}
		else {
			int maxMinTrendIndex = wd.getMaxMinTempTrendIndex(true,0, firstTrend.getIndex());
			sb.append(" and decrease to ").append(wd.getTimeseries().get(maxMinTrendIndex).getT())
			  .append(" ° Celsius at ").append(wd.getTimeseries().get(maxMinTrendIndex).getClockTime());
		}
		return sb.toString();		
	}
}