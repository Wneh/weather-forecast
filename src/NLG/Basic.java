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
		TimeSerie start = this.wd.getTimeseries().get(0);
		//Some basic text
		sb.append("At ").append(start.getClockTime()).append(" the temperature will be ").append(start.getT());
		//Add if it will increase or decrease in temperature
		TrendPoint firstTrend = this.trends.get(0);
		if(firstTrend.getTrend() == TrendPoint.Trend.POSITIVE){
			sb.append(" and increase to ").append(wd.getTimeseries().get(firstTrend.getIndex()).getT())
			  .append(" at ").append(wd.getTimeseries().get(firstTrend.getIndex()).getClockTime());
		}
		else {
			sb.append(" and decrease to ").append(wd.getTimeseries().get(firstTrend.getIndex()).getT())
			  .append(" at ").append(wd.getTimeseries().get(firstTrend.getIndex()).getClockTime());
		}
		return sb.toString();		
	}
}