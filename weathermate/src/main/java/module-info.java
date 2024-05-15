module app {
    requires javafx.fxml;
    requires java.net.http;
    
    requires transitive org.json;
    requires transitive javafx.controls;
    requires transitive javafx.graphics;

    opens app to javafx.fxml;
    exports app;
}
 