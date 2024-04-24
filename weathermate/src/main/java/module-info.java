module app {
   
    requires javafx.controls;
    requires javafx.fxml;
    
    requires org.json; //unstable need fix later
    requires java.net.http;

    opens app to javafx.fxml;
    exports app;
}
