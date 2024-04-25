package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * JavaFX App
 */
public class App extends Application {

    private static HashMap<Integer, JSONObject> weatherHashMap;
    private static String location;

    private static Scene scene;

    @SuppressWarnings("exports")
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("home"), 640, 480);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("WeatherMate");
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void setWeatherHashMap( HashMap<Integer, JSONObject> weatherHashMap) {
        App.weatherHashMap = weatherHashMap;
    }

    public static HashMap<Integer, JSONObject> getWeatherHashMap() {
        return weatherHashMap;
    }

    public static void setLocation(String location) {
        App.location = location;
    }

    public static String getLocation() {
        return location;
    }

}