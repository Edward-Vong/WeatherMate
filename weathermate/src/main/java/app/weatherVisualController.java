package app;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javafx.scene.layout.HBox;
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
    @FXML
    private HBox weatherCardBox;
    @FXML
    private Label currentTemp;
    @FXML
    private Label feelsLikeLabel;
    @FXML
    private Label humidityLabel;
    @FXML
    private Label pressureLabel;

    private HashMap<Integer, JSONObject> weatherHashMap;
    private HashMap<String, Image> iconHashMap;

    @FXML
    private void back() throws IOException{
        App.setRoot("home");
    }

    public void initialize() {

        iconHashMap = initIconHashMap();
        weatherHashMap = App.getWeatherHashMap();

        System.out.println(App.getLocation());
        locationText.setText(App.getLocation());

        String[] times = fillArray(8, "dt_txt");
        String[] temperatures = fillArray(8, "main.temp");
        String[] feelsLikeTemperatures = fillArray(8, "main.feels_like");
        fillCurrent();

        // Assuming there are 8 columns as per the FXML GridPane setup
        for (int i = 0; i < 8; i++) {
            // Time
            Label timeLabel = new Label(times[i].substring(10, 16));

            // Image (weather icon)
            String weatherID = weatherHashMap.get(i).getJSONArray("weather").getJSONObject(0).getString("icon");
            ImageView weatherIcon = new ImageView(iconHashMap.get(weatherID));
            weatherIcon.setFitWidth(100);
            weatherIcon.setFitHeight(100);



            // Temperature
            Label tempLabel = new Label(temperatures[i] + "°F");

            // Feels like temperature
            //Label feelsLikeTempLabel = new Label(feelsLikeTemperatures[i] + "°F");
            weatherCardBox.getChildren().add(new WeatherCard(timeLabel, weatherIcon, tempLabel));
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
    private void fillCurrent(){
        String [] tempCall = "main.temp".split("\\.");
        String [] feelsLikeCall = "main.feels_like".split("\\.");
        String [] humidCall = "main.humidity".split("\\.");
        String [] pressureCall = "main.pressure".split("\\.");
        JSONObject currentWeather = weatherHashMap.get(0);
        currentTemp.setText(String.valueOf((int)currentWeather.getJSONObject("main").getDouble(tempCall[1])) + "°");
        feelsLikeLabel.setText(String.valueOf((int)currentWeather.getJSONObject("main").getDouble(feelsLikeCall[1])) + "°");
        humidityLabel.setText(String.valueOf((int)currentWeather.getJSONObject("main").getDouble(humidCall[1])));
        pressureLabel.setText(String.valueOf((int)currentWeather.getJSONObject("main").getDouble(pressureCall[1])));
        currentTemp.setStyle("-fx-font-family: 'Arial'");
        feelsLikeLabel.setStyle("-fx-font-family: 'Arial'");
        humidityLabel.setStyle("-fx-font-family: 'Arial'");
        pressureLabel.setStyle("-fx-font-family: 'Arial'");
    }
}


