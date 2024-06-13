package org.example.datastoragetechnologies;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void firstOnButtonClick() throws IOException {
        welcomeText.setText("Query 1");
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        Parent root =
                FXMLLoader.load(getClass().getResource("first-request.fxml"));
        stage.setTitle("Request1");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    protected void secondOnButtonClick() throws IOException {
        welcomeText.setText("Query 2");
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        Parent root =
                FXMLLoader.load(getClass().getResource("second-request.fxml"));
        stage.setTitle("Request2");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    protected void thirdOnButtonClick() throws IOException {
        welcomeText.setText("Query 3");
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        Parent root =
                FXMLLoader.load(getClass().getResource("third-request.fxml"));
        stage.setTitle("Request3");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    protected void countQueryOnButtonClick() throws IOException {
        welcomeText.setText("Count Query");
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        Parent root =
                FXMLLoader.load(getClass().getResource("count-request.fxml"));
        stage.setTitle("Request4");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    protected void avgQueryOnButtonClick() throws IOException {
        welcomeText.setText("Avg Query");
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        Parent root =
                FXMLLoader.load(getClass().getResource("avg-request.fxml"));
        stage.setTitle("Request5");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    protected void sumQueryOnButtonClick() throws IOException {
        welcomeText.setText("Sum Query");
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        Parent root =
                FXMLLoader.load(getClass().getResource("sum-request.fxml"));
        stage.setTitle("Request6");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    protected void addQueryOnButtonClick() throws IOException {
        welcomeText.setText("Add Query");
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        Parent root =
                FXMLLoader.load(getClass().getResource("first-request.fxml"));
        stage.setTitle("Request7");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    protected void updateOnButtonClick() throws IOException {
        welcomeText.setText("Update Query");
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        Parent root =
                FXMLLoader.load(getClass().getResource("first-request.fxml"));
        stage.setTitle("Request8");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    protected void deleteOnButtonClick() throws IOException {
        welcomeText.setText("Delete Query");
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        Parent root =
                FXMLLoader.load(getClass().getResource("first-request.fxml"));
        stage.setTitle("Request9");
        stage.setScene(new Scene(root));
        stage.show();
    }
}