package app.ContainerManagers;

import javafx.scene.layout.HBox;

public interface SearchContainerManager {
    HBox getContainer();
    void performSearch() throws Exception;
}
