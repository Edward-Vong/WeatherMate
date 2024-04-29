package app.ContainerManagers;

import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import app.App;

public abstract class BaseContainerManager {
    protected HBox container;
    protected Button searchButton;

    public BaseContainerManager() {
        container = new HBox();
        container.setId(getClass().getSimpleName() + "Container");
    }

    public HBox getContainer() {
        return container;
    }

    protected abstract void initializeComponents();

    protected void setupSearchButton() {
        searchButton = new Button("Search");
        container.getChildren().add(searchButton);
        searchButton.setOnAction(event -> {
            try {
                performSearch();
                App.setRoot("weatherVisual");


            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    protected abstract void performSearch() throws Exception;
}
