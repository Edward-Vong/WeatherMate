package app;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class HomeController {

    @FXML
    private ChoiceBox<String> locationTypeChoiceBox;

    @FXML
    public void initialize() {
        locationTypeChoiceBox.getItems().addAll("City", "Coordinate", "Location ID", "Zip");
    }
    
}
