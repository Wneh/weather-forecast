package POJO;

import java.util.ArrayList;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherData {
	
	private double lat;
	private double lon;
	private Date referenceTime;
	@JsonProperty("timeseries")
	private ArrayList<TimeSerie> timeseries;
	
	public String toString(){
		return "Lat: " + lat + "\nLong: " + lon + "\nReferenceTime: " + referenceTime + "\ntimeseries: " + timeseries;
	}
	
	public int getMaxMinTempTrendIndex(boolean max,int start,int stop){
		double currentMax = 0;
		int index = 0;
		for(int i = start; i <= stop; i++){
			if(max){
				if(Math.max(currentMax, timeseries.get(i).getT()) > currentMax){
					currentMax = timeseries.get(i).getT();
					index = i;
				}
			} else {
				if(Math.min(currentMax, timeseries.get(i).getT()) < currentMax){
					currentMax = timeseries.get(i).getT();
					index = i;
				}
			}
		}
		return index;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public Date getReferenceTime() {
		return referenceTime;
	}

	public void setReferenceTime(Date referenceTime) {
		this.referenceTime = referenceTime;
	}

	public ArrayList<TimeSerie> getTimeseries() {
		return timeseries;
	}

	public void setTimeseries(ArrayList<TimeSerie> timeseries) {
		this.timeseries = timeseries;
	}
}
