package app.ContainerManagers;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.IOException;

import app.ContainerManagers.APIManager.URLBuilder;
import app.ContainerManagers.Utilities.ComboBoxUtility;
import app.App;
import app.ContainerManagers.APIManager.APIConnector;

public class CityContainerManager {
    private HBox cityContainer;
    private TextField cityTextField;
    private ComboBox<String> stateComboBox;
    private ComboBox<String> countryComboBox;

    private ObservableList<String> stateCodes;
    private ObservableList<String> countryCodes;

    public CityContainerManager(ObservableList<String> stateCodes, ObservableList<String> countryCodes) {
        this.stateCodes = stateCodes;
        this.countryCodes = countryCodes;
        initializeCityContainer();
    }

    public HBox getContainer() {
        return cityContainer;
    }

    private void initializeCityContainer() {
        cityContainer = new HBox();
        cityContainer.setId("citySearchBox");

        cityTextField = new TextField();
        cityTextField.setPromptText("Enter a city name");

        cityContainer.getChildren().add(cityTextField);

        createComboBoxes(stateCodes, countryCodes);

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
        cityContainer.getChildren().add(searchButton);
    }

    private void createComboBoxes(ObservableList<String> stateCodes, ObservableList<String> countryCodes) {
        stateComboBox = ComboBoxUtility.initializeComboBox(stateCodes, 0, 150);
        countryComboBox = ComboBoxUtility.initializeComboBox(countryCodes, 0, 150);
    
        countryComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            stateComboBox.setDisable(!"US".equals(newVal));
        });
        countryComboBox.getSelectionModel().select("US");
    
        // Add to the container
        cityContainer.getChildren().addAll(stateComboBox, countryComboBox);
    }

    private void performSearch() throws FileNotFoundException, IOException {
        //get all values from fields
        String city = cityTextField.getText();
        String state = stateComboBox.getValue();
        String country = countryComboBox.getValue();

        //check if something is being search that should not be
        illegalSearchArg(state, "state", stateCodes);
        illegalSearchArg(country, "country", countryCodes);

        //create the geo coordinate query URL based off of entered data
        URLBuilder urlBuilder = new URLBuilder();
        String geoQueryString = urlBuilder.cityGeoURL(city, state, country);

        //have the data coordinates create a HashMap for forecast data
        APIConnector connector = new APIConnector();
        String query = connector.getQuery(geoQueryString, "city");
        App.setWeatherHashMap(connector.getWeatherHashMap(query));
    }

    private void illegalSearchArg(String input, String type, ObservableList<String> list) {
        if (input == list.get(0)) {
            throw new IllegalArgumentException("Invalid " + type + ": \"" + input + "\" is not allowed.");
        }
    }
}
