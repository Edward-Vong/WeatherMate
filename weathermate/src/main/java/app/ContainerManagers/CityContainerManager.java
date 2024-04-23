package app.ContainerManagers;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.collections.ObservableList;

public class CityContainerManager {
    private HBox cityContainer;
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

        TextField cityTextField = new TextField();
        cityTextField.setPromptText("Enter a city name");

        cityContainer.getChildren().add(cityTextField);

        createComboBoxes(stateCodes, countryCodes);

        Button SearchButton = new Button("Search");
        cityContainer.getChildren().add(SearchButton);
    }

    private void createComboBoxes(ObservableList<String> stateCodes, ObservableList<String> countryCodes) {
        ComboBox<String> stateComboBox = ComboBoxUtility.initializeComboBox(stateCodes, 0, 150);
        ComboBox<String> countryComboBox = ComboBoxUtility.initializeComboBox(countryCodes, 0, 150);
    
        // Specific behavior for the country ComboBox that disables the state ComboBox based on selection
        countryComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            stateComboBox.setDisable(!"US".equals(newVal));
        });
        countryComboBox.getSelectionModel().select("US"); // Select "US" by default
    
        // Add to the container
        cityContainer.getChildren().addAll(stateComboBox, countryComboBox);
    }
}
