package app;

import java.io.InputStream;
import java.util.HashMap;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class weatherVisualController {

    @FXML
    private Text locationText;

    @FXML
    private GridPane grid;

    private HashMap<Integer, JSONObject> weatherHashMap;
    private HashMap<String, Image> iconHashMap;

    public void initialize() {
        iconHashMap = initIconHashMap();
        weatherHashMap = App.getWeatherHashMap();

        System.out.println(App.getLocation());
        locationText.setText(App.getLocation());

        String[] times = fillArray(8, "dt_txt");
        String[] temperatures = fillArray(8, "main.temp");
        String[] feelsLikeTemperatures = fillArray(8, "main.feels_like");

        // Assuming there are 8 columns as per the FXML GridPane setup
        for (int i = 0; i < 8; i++) {
            // Time
            Label timeLabel = new Label(times[i]);
            grid.add(timeLabel, i, 0);

            // Image (weather icon)
            String weatherID = weatherHashMap.get(i).getJSONArray("weather").getJSONObject(0).getString("icon");
            ImageView weatherIcon = new ImageView(iconHashMap.get(weatherID));

            weatherIcon.setFitWidth(100); 
            weatherIcon.setFitHeight(100);
            weatherIcon.setPreserveRatio(true);
            weatherIcon.setSmooth(true);

            grid.add(weatherIcon, i, 1);

            // Temperature
            Label tempLabel = new Label(temperatures[i] + "°F");
            grid.add(tempLabel, i, 2);

            // Feels like temperature
            Label feelsLikeTempLabel = new Label(feelsLikeTemperatures[i] + "°F");
            grid.add(feelsLikeTempLabel, i, 3);
        }
    }

    private HashMap<String, Image> initIconHashMap() {
        HashMap<String, Image> map = new HashMap<String, Image>();

        try {
            putCommandFormatter(map, "01d");
            putCommandFormatter(map, "01n");
            putCommandFormatter(map, "02d");
            putCommandFormatter(map, "02n");
            putCommandFormatter(map, "03d");
            putCommandFormatter(map, "03n");
            putCommandFormatter(map, "04d");
            putCommandFormatter(map, "04n");
            putCommandFormatter(map, "09d");
            putCommandFormatter(map, "09n");
            putCommandFormatter(map, "10d");
            putCommandFormatter(map, "10n");
            putCommandFormatter(map, "11d");
            putCommandFormatter(map, "11n");
            putCommandFormatter(map, "13d");
            putCommandFormatter(map, "13n");
            putCommandFormatter(map, "50d");
            putCommandFormatter(map, "50n");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    private void putCommandFormatter(HashMap<String, Image> iconHashMap,String iconID) throws Exception {
        InputStream is = getClass().getResourceAsStream("/app/icons/" + iconID + ".png");
        if(is == null) {
            throw new Exception("Incorrect file path");
        } 

        else {
            iconHashMap.put(iconID, new Image(is));
        }
    }
    
    private String[] fillArray(int size, String responseField) {
        String[] arrayFiller = new String[size];
        String[] responseCalls = responseField.split("\\.");

        for(int i = 0; i < size; i++) {
            JSONObject currentWeather = weatherHashMap.get(i);
            if(responseCalls[0].equals("main")) {
                arrayFiller[i] = String.valueOf(currentWeather.getJSONObject("main").getDouble(responseCalls[1]));
            }

            else {
                arrayFiller[i] = currentWeather.getString(responseCalls[0]);
            }
        }
        
        return arrayFiller;
    }    
}


