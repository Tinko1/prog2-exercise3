package weather.ctrl;


import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient;
import tk.plogitech.darksky.forecast.APIKey;
import tk.plogitech.darksky.forecast.ForecastRequest;
import tk.plogitech.darksky.forecast.ForecastRequestBuilder;
import tk.plogitech.darksky.forecast.GeoCoordinates;
import tk.plogitech.darksky.forecast.model.DailyDataPoint;
import tk.plogitech.darksky.forecast.model.Forecast;

import java.util.ArrayList;
import java.util.List;

public class WeatherController {
    
    private String apiKey = "ab5c55091bfde0864c41b337f1c66af5";
    

    public void process(GeoCoordinates location) {
        System.out.println("process "+location); //$NON-NLS-1$
		Forecast data = getData(location);

        ArrayList<Double> highestTemp = new ArrayList<Double>();
        double result = 0;

        for (int i=0; i <data.getDaily().getData().size(); i++) {
            //System.out.println(data.getDaily().getData().get(i).getApparentTemperatureHigh());
            highestTemp.add(data.getDaily().getData().get(i).getApparentTemperatureHigh());
            result = highestTemp.get(0);
            result = result + highestTemp.get(i);
        }

        result = result/8;

		//TODO implement Error handling

		//TODO implement methods for
		// highest temperature 
		// average temperature 
		// count the daily values
		
		// implement a Comparator for the Windspeed 
		
	}
    
    
    public Forecast getData(GeoCoordinates location) {
		ForecastRequest request = new ForecastRequestBuilder()
                .key(new APIKey(apiKey))
                .location(location)
                .build();

        Forecast forecast = null;
        DarkSkyJacksonClient client = new DarkSkyJacksonClient();
        //Forecast forecast = client.forecast(request);
        try {
            forecast = client.forecast(request);
        }
        catch (Exception e) {
            System.out.println("getData error");
        }
        return forecast;
    }
}
