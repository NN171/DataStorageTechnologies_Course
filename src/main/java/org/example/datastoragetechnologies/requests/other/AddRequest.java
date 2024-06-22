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
import org.example.datastoragetechnologies.entities.Client;
import org.example.datastoragetechnologies.entities.Employee;

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
    TextField post;

    @FXML
    Button menu;

    public void addRequest() {
        Platform.runLater(() -> {
            HibernateRunner.session().inTransaction(session -> {

                    String checkQuery = """
                                   FROM Employee
                                   WHERE phoneNumber = :phone AND email = :mail AND post = :post AND fullName = :initials 
                            """;
                    long records = session.createQuery(checkQuery, Employee.class)
                            .setParameter("phone", phone.getText())
                            .setParameter("mail", mail.getText())
                            .setParameter("post", post.getText())
                            .setParameter("initials", initials.getText())
                            .getResultCount();

                    if (records != 1) {
                    String addQuery = """
                             INSERT INTO Employee (phoneNumber, email, post, fullName)
                             VALUES (:phone, :mail, :post, :initials)
                        """;

                    Query query = session.createQuery(addQuery);
                    query.setParameter("phone", phone.getText());
                    query.setParameter("mail", mail.getText());
                    query.setParameter("post", post.getText());
                    query.setParameter("initials", initials.getText());
                    query.executeUpdate();

                    isSuccess.setText("Сотрудник добавлен");
                } else {
                    isSuccess.setText("Сотрудник уже существует");
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
