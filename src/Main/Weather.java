package Main;
import java.util.ArrayList;

import org.jfree.ui.RefineryUtilities;

import NLG.Basic;
import POJO.WeatherData;


public class Weather {

	
	public static void main(String[] args){
		System.out.println("Hello");
		
		DataFetcher df = new DataFetcher();
		
		WeatherData wd = df.getWeather();
		
		System.out.println(wd);
		System.out.println();
		
		MovingAverage ma = new MovingAverage();
		
		ArrayList<TrendPoint> trends = ma.calculate(wd);
		
		//Generate a forecast
		Basic b = new Basic(wd,trends);
		
		System.out.println("");
		
		System.out.println(b.generateSentence());
		
        final Chart demo = new Chart("Weather Forecast",wd);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
		
	}
}
