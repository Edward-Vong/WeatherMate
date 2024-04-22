module app {
   
    requires javafx.controls;
    requires javafx.fxml;
    
    requires json.simple; //unstable need fix later

    opens app to javafx.fxml;
    exports app;
}
