package org.example.datastoragetechnologies.requests.aggregation;

import jakarta.persistence.Query;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.datastoragetechnologies.HelloApplication;
import org.example.datastoragetechnologies.HibernateRunner;

import java.io.IOException;

public class CountRequest {

    @FXML
    Label content;

    @FXML
    Button menu;

    @FXML
    TextField input;

    @FXML
    CheckBox check;

    public void countRequest() {
        Platform.runLater(() -> {
            HibernateRunner.session().inTransaction(session -> {
                String textQuery = """
                             SELECT COUNT(pr.productType) FROM Product pr
                             INNER JOIN Booking bk ON pr.productId = bk.product.productId
                             WHERE pr.producer = :producer AND bk.warranty = :isEnabled
                        """;
                Query query = session.createQuery(textQuery, Long.class);
                query.setParameter("producer", input.getText());
                query.setParameter("isEnabled", check.isSelected());
                long count = (long) query.getSingleResult();
                content.setText(count + " штук");
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
