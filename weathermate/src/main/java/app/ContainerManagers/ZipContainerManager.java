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

public class ZipContainerManager extends BaseContainerManager {
    private TextField zipTextField;
    private ComboBox<String> countryComboBox;

    private ObservableList<String> countryCodes;

    public ZipContainerManager(ObservableList<String> countryCodes) {
        super();
        this.countryCodes = countryCodes;
        initializeComponents();
        setupSearchButton();
    }

    @Override
    protected void initializeComponents() {
        zipTextField = new TextField();
        zipTextField.setPromptText("Enter a ZIP code");
        countryComboBox = ComboBoxUtility.initializeComboBox(countryCodes, 0, 150);

        container.getChildren().addAll(zipTextField, countryComboBox);
    }

    @Override
    protected void performSearch() throws FileNotFoundException, IOException {
        int zip = Integer.parseInt(zipTextField.getText());
        String country = countryComboBox.getValue();

        URLBuilder urlBuilder = new URLBuilder();
        String geoQueryString = urlBuilder.zipGeoUrl(zip, country);

        APIConnector connector = new APIConnector();
        String query = connector.getQuery(geoQueryString, "zip");
        App.setWeatherHashMap(connector.getWeatherHashMap(query));
    }
}
