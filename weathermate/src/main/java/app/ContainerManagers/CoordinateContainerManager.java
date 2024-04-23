package app.ContainerManagers;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class CoordinateContainerManager {
    private HBox coordinateContainer;

    public CoordinateContainerManager() {
        initializeCoordinateContainer();
    }

    public HBox getContainer() {
        return coordinateContainer;
    }

    private void initializeCoordinateContainer() {
        coordinateContainer = new HBox();
        coordinateContainer.setId("coordinateSearchBox");
        TextField latitudeTextField = new TextField();
        latitudeTextField.setPromptText("Enter latitude");
        TextField longitudeTextField = new TextField();
        longitudeTextField.setPromptText("Enter longitude");
        coordinateContainer.getChildren().addAll(latitudeTextField, longitudeTextField);

        Button SearchButton = new Button("Search");
        coordinateContainer.getChildren().add(SearchButton);
    }
}
