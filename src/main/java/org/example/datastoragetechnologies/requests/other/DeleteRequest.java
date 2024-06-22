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
import org.example.datastoragetechnologies.entities.Employee;

import java.io.IOException;

public class DeleteRequest {

    @FXML
    TextField id;

    @FXML
    Label isSuccess;

    @FXML
    Button menu;

    public void deleteRequest() {
        Platform.runLater(() -> {
            HibernateRunner.session().inTransaction(session -> {
                String addQuery = """
                             DELETE FROM Employee
                             WHERE employeeId = :employeeId
                        """;

                try {
                    Query query = session.createQuery(addQuery);
                    query.setParameter("employeeId", Integer.parseInt(id.getText()));
                    int amount = query.executeUpdate();

                    if (amount != 0) {
                        String checkQuery = """
                                       FROM Employee
                                       WHERE employeeId = :employeeId
                                """;
                        Query isDeleted = session.createQuery(checkQuery, Employee.class)
                                .setParameter("employeeId", Integer.parseInt(id.getText()));
                        if (isDeleted.getResultList().isEmpty())
                            isSuccess.setText("Сотрудник успешно удален");
                    } else isSuccess.setText("Сотрудник отсутствует в базе");
                } catch (Exception e) {
                    isSuccess.setText("Сотрудник привязан к заказу");
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
