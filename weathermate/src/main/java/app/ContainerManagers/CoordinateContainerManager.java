package app.ContainerManagers;

import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;

import app.App;
import app.ContainerManagers.APIManager.APIConnector;
import app.ContainerManagers.APIManager.URLBuilder;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class CoordinateContainerManager {
    private HBox coordinateContainer;
    private TextField latitudeTextField;
    private TextField longitudeTextField;

    public CoordinateContainerManager() {
        initializeCoordinateContainer();
    }

    public HBox getContainer() {
        return coordinateContainer;
    }

    private void initializeCoordinateContainer() {
        coordinateContainer = new HBox();
        coordinateContainer.setId("coordinateSearchBox");

        latitudeTextField = new TextField();
        latitudeTextField.setPromptText("Enter latitude");
        longitudeTextField = new TextField();
        longitudeTextField.setPromptText("Enter longitude");

        coordinateContainer.getChildren().addAll(latitudeTextField, longitudeTextField);

        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            try {
                performSearch();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        coordinateContainer.getChildren().add(searchButton);
    }

    private void performSearch() throws FileNotFoundException, IOException {
        //get all values from fields
        String lat = latitudeTextField.getText();
        String lon = longitudeTextField.getText();

        //create the geo coordinate query URL based off of entered data
        URLBuilder urlBuilder = new URLBuilder();
        String query = urlBuilder.getQueryUrl(getDoubleFromTextField(lat), getDoubleFromTextField(lon));

        //have the data coordinates create a HashMap for forecast data
        APIConnector connector = new APIConnector();
        App.setWeatherHashMap(connector.getWeatherHashMap(query));
    }

    private double getDoubleFromTextField(String num) {
        try {
            return Double.parseDouble(num);  // Use Double.parseDouble(text) for decimal numbers
        } catch (NumberFormatException e) {
            System.out.println("Error: The input is not a valid integer.");
            return 0;  // or any default value, or rethrow the exception or handle it as necessary
        }
    }
}
