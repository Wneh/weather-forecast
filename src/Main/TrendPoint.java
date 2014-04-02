package Main;
import java.util.Date;


public class TrendPoint {
	private int index;
	private Trend trend;
	
	public static enum Trend { POSITIVE, NEGATIVE };
	
	public TrendPoint(Trend trend,int index){
		this.trend = trend;
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Trend getTrend() {
		return trend;
	}

	public void setTrend(Trend trend) {
		this.trend = trend;
	}
	
	public String toString(){
		return "index: " + this.index + ", trend: " + this.trend + "\n";
	}
	
}
