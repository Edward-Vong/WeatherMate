package app.ContainerManagers;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class ComboBoxUtility {
    public static ComboBox<String> initializeComboBox(ObservableList<String> options, int defaultIndex, double prefWidth) {
        ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.setPrefWidth(prefWidth);
        comboBox.setVisibleRowCount(10);
        comboBox.getSelectionModel().select(defaultIndex);

        comboBox.setOnAction(event -> {
            if (comboBox.getSelectionModel().getSelectedIndex() == defaultIndex) {
                comboBox.getSelectionModel().clearSelection();
            }
        });

        return comboBox;
    }
}
