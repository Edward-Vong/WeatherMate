package app.ContainerManagers;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.collections.ObservableList;

public class ZipContainerManager {
    private HBox zipContainer;
    private ObservableList<String> countryCodes;

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
        TextField zipTextField = new TextField();
        zipTextField.setPromptText("Enter a ZIP code");

        ComboBox<String> countryComboBox = ComboBoxUtility.initializeComboBox(countryCodes, 0, 150);
        zipContainer.getChildren().addAll(zipTextField, countryComboBox);

        Button SearchButton = new Button("Search");
        zipContainer.getChildren().add(SearchButton);
    }
}
