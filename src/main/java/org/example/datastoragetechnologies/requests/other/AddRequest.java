package org.example.datastoragetechnologies.requests.other;

import jakarta.persistence.NonUniqueResultException;
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
import org.example.datastoragetechnologies.entities.Client;

import java.io.IOException;
import java.time.LocalDate;

public class AddRequest {

    @FXML
    Label isSuccess;

    @FXML
    TextField phone;

    @FXML
    TextField mail;

    @FXML
    TextField initials;

    @FXML
    Button menu;

    public void addRequest() {
        Platform.runLater(() -> {
            HibernateRunner.session().inTransaction(session -> {
                String addQuery = """
                             INSERT INTO Client (phoneNumber, email, registrationDate, fullName)
                             VALUES (:phone, :mail, :date, :initials)
                        """;

                Query query = session.createQuery(addQuery);
                query.setParameter("phone", phone.getText());
                query.setParameter("mail", mail.getText());
                query.setParameter("date", LocalDate.now());
                query.setParameter("initials", initials.getText());
                query.executeUpdate();

                try {
                    String checkQuery = """
                                FROM Client 
                                WHERE phoneNumber = :phone AND email = :mail AND fullName = :initials 
                         """;
                    session.createQuery(checkQuery, Client.class)
                            .setParameter("phone", phone.getText())
                            .setParameter("mail", mail.getText())
                            .setParameter("initials", initials.getText())
                            .uniqueResult();
                    isSuccess.setText("Клиент добавлен");
                }
                catch (Exception e) {
                    isSuccess.setText("Клиент уже существует");
                }
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
