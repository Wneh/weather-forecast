import POJO.WeatherData;


public class Weather {

	
	public static void main(String[] args){
		System.out.println("Hello");
		
		DataFetcher df = new DataFetcher();
		
		WeatherData wd = df.getWeather();
		
		System.out.println(wd);
		System.out.println();
		
		MovingAverage ma = new MovingAverage();
		
		ma.calculate(wd);		
		
	}
}
