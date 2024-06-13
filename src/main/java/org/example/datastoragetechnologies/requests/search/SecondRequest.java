package org.example.datastoragetechnologies.requests.search;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.datastoragetechnologies.HelloApplication;
import org.example.datastoragetechnologies.HibernateRunner;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SecondRequest implements Initializable {

    @FXML
    TableView<SecondDescribe> table;

    @FXML
    TableColumn<SecondDescribe, String> first_col;

    @FXML
    TableColumn<SecondDescribe, String> second_col;

    @FXML
    Button menu;

    @FXML
    public void secondRequest() {
        Platform.runLater(() -> {
            HibernateRunner.session().inTransaction(session -> {
                String textQuery = """
                             SELECT pr.productType, pr.client.clientId
                               FROM Product pr
                               JOIN Client cl ON pr.client.clientId = cl.clientId
                               WHERE pr.producer IN ('Philips', 'Bosch')
                        """;

                List<SecondDescribe> describeList = new ArrayList<>();
                List<Object> list = session.createQuery(textQuery).getResultList();
                for (Object o : list) {
                    Object[] row = (Object[]) o;
                    describeList.add(new SecondDescribe((int) row[1], (String) row[0]));
                }
                ObservableList<SecondDescribe> observe = FXCollections.observableList(describeList);
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

    protected class SecondDescribe {
        int clientId;
        String productType;

        public SecondDescribe(int clientId, String productType) {
            this.clientId = clientId;
            this.productType = productType;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public int getClientId() {
            return clientId;
        }

        public void setClientId(int clientId) {
            this.clientId = clientId;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        first_col.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        second_col.setCellValueFactory(new PropertyValueFactory<>("productType"));
    }
}
