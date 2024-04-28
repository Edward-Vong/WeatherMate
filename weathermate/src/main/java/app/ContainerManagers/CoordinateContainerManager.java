package app.ContainerManagers;

import javafx.scene.control.TextField;
import java.io.FileNotFoundException;
import java.io.IOException;
import app.ContainerManagers.APIManager.URLBuilder;
import app.App;
import app.ContainerManagers.APIManager.APIConnector;

public class CoordinateContainerManager extends BaseContainerManager {
    private TextField latitudeTextField;
    private TextField longitudeTextField;

    public CoordinateContainerManager() {
        super();
        initializeComponents();
        setupSearchButton();
    }

    @Override
    protected void initializeComponents() {
        latitudeTextField = new TextField();
        latitudeTextField.setPromptText("Enter latitude");
        longitudeTextField = new TextField();
        longitudeTextField.setPromptText("Enter longitude");

        container.getChildren().addAll(latitudeTextField, longitudeTextField);
    }

    @Override
    protected void performSearch() throws FileNotFoundException, IOException {
        double lat = Double.parseDouble(latitudeTextField.getText());
        double lon = Double.parseDouble(longitudeTextField.getText());

        URLBuilder urlBuilder = new URLBuilder();
        String query = urlBuilder.getQueryUrl(lat, lon);

        APIConnector connector = new APIConnector();
        App.setWeatherHashMap(connector.getWeatherHashMap(query));
    }
}
