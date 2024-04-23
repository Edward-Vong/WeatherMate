package app;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HomeController {

    @FXML
    private VBox mainContainer;
    @FXML
    private ChoiceBox<String> locationTypeChoiceBox;

    private HBox cityContainer;
    private HBox zipContainer;
    private HBox coordinateContainer;

    private ObservableList<String> stateCodes = FXCollections.observableArrayList(
        "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA",
        "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD",
        "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
        "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC",
        "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"
    );

    private ObservableList<String> countryCodes = FXCollections.observableArrayList(
        "US", "CA", "MX", "GB", "FR", "DE", "JP", "CN", "IN", "BR"
    );

    @FXML
    public void initialize() {
        locationTypeChoiceBox.getItems().addAll("City", "Zip", "Coordinate");
        initializeContainers();
        setupChoiceBoxListeners();
    }

    private void initializeContainers() {
        initializeCityContainer();
        initializeZipContainer();
        initializeCoordinateContainer();
    }

    private void initializeCityContainer() {
        cityContainer = new HBox();
        cityContainer.setId("citySearchBox");
        TextField cityTextField = new TextField();
        cityTextField.setPromptText("Enter a city name");
        
        ChoiceBox<String> stateChoiceBox = new ChoiceBox<>(stateCodes);
        ChoiceBox<String> countryChoiceBox = new ChoiceBox<>(countryCodes);
        
        countryChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            stateChoiceBox.setDisable(!"US".equals(newVal));
        });
        countryChoiceBox.getSelectionModel().select("US");

        cityContainer.getChildren().addAll(cityTextField, stateChoiceBox, countryChoiceBox);
    }

    private void initializeZipContainer() {
        zipContainer = new HBox();
        zipContainer.setId("zipSearchBox");
        TextField zipTextField = new TextField();
        zipTextField.setPromptText("Enter a ZIP code");

        ChoiceBox<String> zipCountryChoiceBox = new ChoiceBox<>(countryCodes);
        zipContainer.getChildren().addAll(zipTextField, zipCountryChoiceBox);
    }

    private void initializeCoordinateContainer() {
        coordinateContainer = new HBox();
        coordinateContainer.setId("coordinateSearchBox");
        TextField latitudeTextField = new TextField();
        latitudeTextField.setPromptText("Enter latitude");
        TextField longitudeTextField = new TextField();
        longitudeTextField.setPromptText("Enter longitude");
        coordinateContainer.getChildren().addAll(latitudeTextField, longitudeTextField);
    }

    private void setupChoiceBoxListeners() {
        locationTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            mainContainer.getChildren().removeIf(child -> child instanceof HBox && !child.getId().equals("searchTypeBar"));
            switch (newValue) {
                case "City":
                    mainContainer.getChildren().add(cityContainer);
                    break;
                case "Zip":
                    mainContainer.getChildren().add(zipContainer);
                    break;
                case "Coordinate":
                    mainContainer.getChildren().add(coordinateContainer);
                    break;
            }
        });
    }
}
