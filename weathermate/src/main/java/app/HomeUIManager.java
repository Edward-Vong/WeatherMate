package app;

import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import app.ContainerManagers.CityContainerManager;
import app.ContainerManagers.CoordinateContainerManager;
import app.ContainerManagers.ZipContainerManager;

public class HomeUIManager {

    @FXML
    private VBox mainContainer;
    @FXML
    private ChoiceBox<String> locationTypeChoiceBox;

    private CityContainerManager cityContainerManager;
    private ZipContainerManager zipContainerManager;
    private CoordinateContainerManager coordinateContainerManger;

    private ObservableList<String> stateCodes = FXCollections.observableArrayList(
        "Please Select State", "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA",
        "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD",
        "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
        "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC",
        "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"
    );

    private ObservableList<String> countryCodes = FXCollections.observableArrayList(
        "Please Select Country", "US", "CA", "MX", "GB", "FR", "DE", "JP", "CN", "IN", "BR"
    );

    @FXML
    public void initialize() {
        locationTypeChoiceBox.getItems().addAll("City", "Zip", "Coordinate");
        
        cityContainerManager = new CityContainerManager(stateCodes, countryCodes);
        zipContainerManager = new ZipContainerManager(countryCodes);
        coordinateContainerManger = new CoordinateContainerManager();

        setupChoiceBoxListeners();
    }

    private void setupChoiceBoxListeners() {
        locationTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            mainContainer.getChildren().removeIf(child -> child instanceof HBox && !child.getId().equals("searchTypeBar"));
            switch (newValue) {
                case "City":
                    mainContainer.getChildren().add(cityContainerManager.getContainer());
                    break;
                case "Zip":
                    mainContainer.getChildren().add(zipContainerManager.getContainer());
                    break;
                case "Coordinate":
                    mainContainer.getChildren().add(coordinateContainerManger.getContainer());
                    break;
            }
        });
    }
}
