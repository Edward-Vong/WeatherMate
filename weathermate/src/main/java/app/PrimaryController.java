package app;

import java.io.IOException;
import java.nio.charset.MalformedInputException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class PrimaryController {
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    
}
