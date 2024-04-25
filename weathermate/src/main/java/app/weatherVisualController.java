package app;

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

        locationText.setText(App.getLocation());

        String[] times = fillArray(8, "list.dt_txt");
        String[] temperatures = fillArray(8, "list.main.temp");
        String[] feelsLikeTemperatures = fillArray(8, "list.main.feels_like");

        // Assuming there are 8 columns as per the FXML GridPane setup
        for (int i = 0; i < 8; i++) {
            // Time
            Label timeLabel = new Label(times[i]);
            grid.add(timeLabel, i, 0);

            // Image (weather icon)
            ImageView weatherIcon = new ImageView(new Image("path/to/your/image.png"));
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
        
        iconHashMap.put("01d", new Image("/icons/01d.png"));
        iconHashMap.put("01n", new Image("/icons/01n.png"));
        iconHashMap.put("02d", new Image("/icons/02d.png"));
        iconHashMap.put("02n", new Image("/icons/02n.png"));
        iconHashMap.put("03d", new Image("/icons/03d.png"));
        iconHashMap.put("03n", new Image("/icons/03n.png"));
        iconHashMap.put("04d", new Image("/icons/04d.png"));
        iconHashMap.put("04n", new Image("/icons/04n.png"));
        iconHashMap.put("09d", new Image("/icons/09d.png"));
        iconHashMap.put("09n", new Image("/icons/09n.png"));
        iconHashMap.put("10d", new Image("/icons/10d.png"));
        iconHashMap.put("10n", new Image("/icons/10n.png"));
        iconHashMap.put("11d", new Image("/icons/11d.png"));
        iconHashMap.put("11n", new Image("/icons/11n.png"));
        iconHashMap.put("13d", new Image("/icons/13d.png"));
        iconHashMap.put("13n", new Image("/icons/13n.png"));
        iconHashMap.put("50d", new Image("/icons/50d.png"));
        iconHashMap.put("50n", new Image("/icons/50n.png"));


        return map;
    }
    
    private String[] fillArray(int size, String responseField) {
        String[] arrayFiller = new String[size];

        for(int i = 0; i < size; i++) {
            JSONObject obj = weatherHashMap.get(i);
            arrayFiller[i] = obj.getString(responseField);
        }
        
        return arrayFiller;
    }    
}
