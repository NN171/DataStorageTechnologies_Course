package org.example.datastoragetechnologies.requests.aggregation;

import jakarta.persistence.Query;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.datastoragetechnologies.HelloApplication;
import org.example.datastoragetechnologies.HibernateRunner;

import java.io.IOException;

public class AvgRequest {

    @FXML
    Label content;

    @FXML
    Button menu;

    public void avgRequest() {
        Platform.runLater(() -> {
            HibernateRunner.session().inTransaction(session -> {
                String textQuery = """
                             SELECT AVG(bex.repairCost) FROM BookingExecution bex
                             INNER JOIN Booking bk ON bex.booking.bookingId = bk.bookingId
                             WHERE bex.repairCost > 0 AND bk.warranty IS FALSE 
                        """;
                Query query = session.createQuery(textQuery, Double.class);
                Double avg = (Double) query.getSingleResult();
                content.setText(String.format("%.2f", avg) + " руб.");
            });
        });
    }

    @FXML
    void menuReturn(ActionEvent event) throws IOException {
        Stage stage = (Stage) menu.getScene().getWindow();
        Parent root =
                FXMLLoader.load(HelloApplication.class.getResource("hello-view.fxml"));
        stage.setTitle("Main");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
