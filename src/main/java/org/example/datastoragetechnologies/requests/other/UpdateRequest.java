package org.example.datastoragetechnologies.requests.other;

import jakarta.persistence.Query;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.datastoragetechnologies.HelloApplication;
import org.example.datastoragetechnologies.HibernateRunner;
import org.example.datastoragetechnologies.entities.Booking;
import org.example.datastoragetechnologies.entities.Client;

import java.io.IOException;

public class UpdateRequest {

    @FXML
    TextField id;

    @FXML
    TextField status;

    @FXML
    Label isSuccess;

    @FXML
    Button menu;

    public void updateRequest() {
        Platform.runLater(() -> {
            HibernateRunner.session().inTransaction(session -> {
                String updateQuery = """
                             UPDATE Booking bo
                             SET status = :status
                             WHERE bo.product.productId = :productId
                        """;

                Query query = session.createQuery(updateQuery);
                query.setParameter("status", status.getText());
                query.setParameter("productId", Integer.parseInt(id.getText()));
                int result = query.executeUpdate();

                if (result != 0)
                    isSuccess.setText("Статус заказа " + id.getText() + " изменен");
                else
                    isSuccess.setText("Заказ отсутствует в базе");
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
