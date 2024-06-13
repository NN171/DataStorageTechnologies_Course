package org.example.datastoragetechnologies;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.datastoragetechnologies.requests.FirstRequest;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        welcomeText.setText("av");
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        Parent root =
                FXMLLoader.load(getClass().getResource("first-request.fxml"));
        stage.setTitle("Request1");
        stage.setScene(new Scene(root));
        stage.show();
    }
}