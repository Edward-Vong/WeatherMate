package app.ContainerManagers;

import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.collections.ObservableList;
import app.ContainerManagers.Utilities.ComboBoxUtility;
import java.io.FileNotFoundException;
import java.io.IOException;
import app.ContainerManagers.APIManager.URLBuilder;
import app.App;
import app.ContainerManagers.APIManager.APIConnector;

public class CityContainerManager extends BaseContainerManager {
    private TextField cityTextField;
    private ComboBox<String> stateComboBox;
    private ComboBox<String> countryComboBox;

    private ObservableList<String> stateCodes;
    private ObservableList<String> countryCodes;

    public CityContainerManager(ObservableList<String> stateCodes, ObservableList<String> countryCodes) {
        super();
        this.stateCodes = stateCodes;
        this.countryCodes = countryCodes;
        initializeComponents();
        setupSearchButton();
    }

    @Override
    protected void initializeComponents() {
        cityTextField = new TextField();
        cityTextField.setPromptText("Enter a city name");
        container.getChildren().add(cityTextField);

        stateComboBox = ComboBoxUtility.initializeComboBox(stateCodes, 0, 150);
        countryComboBox = ComboBoxUtility.initializeComboBox(countryCodes, 0, 150);

        countryComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            stateComboBox.setDisable(!"US".equals(newVal));
        });

        container.getChildren().addAll(stateComboBox, countryComboBox);
    }

    @Override
    protected void performSearch() throws FileNotFoundException, IOException {
        String city = cityTextField.getText();
        String state = stateComboBox.getValue();
        String country = countryComboBox.getValue();

        URLBuilder urlBuilder = new URLBuilder();
        String geoQueryString = urlBuilder.cityGeoURL(city, state, country);

        APIConnector connector = new APIConnector();
        String query = connector.getQuery(geoQueryString, "city");
        App.setWeatherHashMap(connector.getWeatherHashMap(query));
    }
}
