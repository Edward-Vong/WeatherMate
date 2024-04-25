package app;

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

    public void initialize() {
        // Setting the location name dynamically
        locationText.setText("San Jose");

        // Simulated data - in a real application, this data would come from a model or external source
        String[] times = {"09:00", "12:00", "15:00", "18:00", "21:00", "00:00", "03:00", "06:00"};
        String[] temperatures = {"15°C", "17°C", "16°C", "14°C", "13°C", "12°C", "11°C", "10°C"};
        String[] feelsLikeTemperatures = {"14°C", "16°C", "15°C", "13°C", "12°C", "11°C", "10°C", "9°C"};

        // Assuming there are 8 columns as per the FXML GridPane setup
        for (int i = 0; i < 8; i++) {
            // Time
            Label timeLabel = new Label(times[i]);
            grid.add(timeLabel, i, 0);

            // Image (weather icon)
            ImageView weatherIcon = new ImageView(new Image("path/to/your/image.png"));
            grid.add(weatherIcon, i, 1);

            // Temperature
            Label tempLabel = new Label(temperatures[i]);
            grid.add(tempLabel, i, 2);

            // Feels like temperature
            Label feelsLikeTempLabel = new Label(feelsLikeTemperatures[i]);
            grid.add(feelsLikeTempLabel, i, 3);
        }
    }

    
}
