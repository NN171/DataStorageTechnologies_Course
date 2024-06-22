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

import java.io.IOException;

public class UpdateRequest {

    @FXML
    TextField phone;

    @FXML
    TextField mail;

    @FXML
    TextField post;

    @FXML
    TextField initials;

    @FXML
    TextField id;

    @FXML
    Label isSuccess;

    @FXML
    Button menu;

    public void updateRequest() {
        Platform.runLater(() -> {
            HibernateRunner.session().inTransaction(session -> {
                try {
                    String idEmployee = id.getText();
                    if (idEmployee.isEmpty()) {
                        throw new IllegalArgumentException();
                    }

                    StringBuilder updateQuery = getStringBuilder();

                    Query query = session.createQuery(updateQuery.toString());
                    if (!phone.getText().isBlank())
                        query.setParameter("phone", phone.getText());
                    if (!mail.getText().isBlank())
                        query.setParameter("mail", mail.getText());
                    if (!post.getText().isBlank())
                        query.setParameter("post", post.getText());
                    if (!initials.getText().isBlank())
                        query.setParameter("initials", initials.getText());
                    query.setParameter("idEmployee", Integer.parseInt(id.getText()));
                    int result = query.executeUpdate();

                    if (result != 0)
                        isSuccess.setText("Информация о сотруднике " + id.getText() + " была изменена");
                    else
                        isSuccess.setText("Сотрудник отсутствует в базе");
                }
                catch (Exception e) {
                    isSuccess.setText("Произошла ошибка изменения информации о сотруднике");
                }
            });
        });
    }

    private StringBuilder getStringBuilder() {
        StringBuilder updateQuery = new StringBuilder("UPDATE Employee " +
                     "SET ");

        if (!phone.getText().isBlank())
            updateQuery.append("phoneNumber = :phone, ");
        if (!mail.getText().isBlank())
            updateQuery.append("email = :mail, ");
        if (!post.getText().isBlank())
            updateQuery.append("post = :post, ");
        if (!initials.getText().isBlank())
            updateQuery.append("fullName = :initials");

        if (updateQuery.substring(updateQuery.length() - 2).equals(", ")) {
            updateQuery.deleteCharAt(updateQuery.length() - 1);
            updateQuery.deleteCharAt(updateQuery.length() - 1);
        }

        updateQuery.append(" WHERE employeeId = :idEmployee");
        return updateQuery;
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
