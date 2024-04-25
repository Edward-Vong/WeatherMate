package app.ContainerManagers;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;
import java.io.IOException;

import app.App;
import app.ContainerManagers.APIManager.APIConnector;
import app.ContainerManagers.APIManager.URLBuilder;
import app.ContainerManagers.Utilities.ComboBoxUtility;
import javafx.collections.ObservableList;

public class ZipContainerManager {
    private HBox zipContainer;
    private ObservableList<String> countryCodes;

    private TextField zipTextField;
    private ComboBox<String> countryComboBox;

    public ZipContainerManager(ObservableList<String> countryCodes) {
        this.countryCodes = countryCodes;
        initializeZipContainer();
    }

    public HBox getContainer() {
        return zipContainer;
    }
    
    private void initializeZipContainer() {
        zipContainer = new HBox();
        zipContainer.setId("zipSearchBox");
        zipTextField = new TextField();
        zipTextField.setPromptText("Enter a ZIP code");

        countryComboBox = ComboBoxUtility.initializeComboBox(countryCodes, 0, 150);
        zipContainer.getChildren().addAll(zipTextField, countryComboBox);

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

        zipContainer.getChildren().add(searchButton);
    }

    private void performSearch() throws FileNotFoundException, IOException {
        int zip = getNumberFromTextField(zipTextField.getText());
        String country = countryComboBox.getValue();

        illegalSearchArg(country, "country", countryCodes);

        URLBuilder urlBuilder = new URLBuilder();
        String geoQueryString = urlBuilder.zipGeoUrl(zip, country);

        APIConnector connector = new APIConnector();
        String query = connector.getQuery(geoQueryString, "zip");
        App.setWeatherHashMap(connector.getWeatherHashMap(query));
    }

    private int getNumberFromTextField(String num) {
        try {
            return Integer.parseInt(num);  
        } catch (NumberFormatException e) {
            System.out.println("Error: The input is not a valid integer.");
            return 0; 
        }
    }

    private void illegalSearchArg(String input, String type, ObservableList<String> list) {
        if (input == list.get(0)) {
            throw new IllegalArgumentException("Invalid " + type + ": \"" + input + "\" is not allowed.");
        }
    }
}
