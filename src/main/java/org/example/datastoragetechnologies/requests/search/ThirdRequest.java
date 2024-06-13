package org.example.datastoragetechnologies.requests.search;

import jakarta.persistence.Query;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.datastoragetechnologies.HelloApplication;
import org.example.datastoragetechnologies.HibernateRunner;
import org.example.datastoragetechnologies.entities.Client;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ThirdRequest implements Initializable {

    @FXML
    TableView<Client> table;

    @FXML
    TableColumn<Client, Integer> first_col;

    @FXML
    TableColumn<Client, String> second_col;

    @FXML
    TableColumn<Client, String> third_col;

    @FXML
    TableColumn<Client, Date> fourth_col;

    @FXML
    TableColumn<Client, String> fifth_col;

    @FXML
    Button menu;

    @FXML
    TextField idField;

    @FXML
    public void thirdRequest() {
        Platform.runLater(() -> {
            HibernateRunner.session().inTransaction(session -> {
                String textQuery = """
                             FROM Client
                             WHERE clientId = :idClient
                        """;
                Query query = session.createQuery(textQuery, Client.class);
                query.setParameter("idClient", Integer.parseInt(idField.getText()));
                List<Client> list = query.getResultList();

                ObservableList<Client> observe = FXCollections.observableList(list);
                table.setItems(observe);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        first_col.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        second_col.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        third_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        fourth_col.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        fifth_col.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
    }
}
