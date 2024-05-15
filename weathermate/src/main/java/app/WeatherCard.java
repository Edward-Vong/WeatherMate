package app;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class WeatherCard extends VBox {

    private BorderPane time = new BorderPane();
    private BorderPane image = new BorderPane();
    private BorderPane temp = new BorderPane();

    public WeatherCard(Label time, ImageView image, Label temp){
        this.time.setCenter(time);
        this.image.setCenter(image);
        this.temp.setCenter(temp);
        this.getChildren().addAll(this.time, this.image, this.temp);
    }

}
