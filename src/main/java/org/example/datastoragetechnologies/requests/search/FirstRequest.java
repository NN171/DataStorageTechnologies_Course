package org.example.datastoragetechnologies.requests.search;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.example.datastoragetechnologies.entities.Product;
import org.hibernate.SessionFactory;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FirstRequest implements Initializable {
    private SessionFactory sessionFactory;

    private Product product;
    @FXML
    TableView<FirstDescribe> table;

    @FXML
    TableColumn<FirstDescribe, String> first_col;

    @FXML
    TableColumn<FirstDescribe, String> second_col;

    @FXML
    TableColumn<FirstDescribe, String> third_col;

    @FXML
    Button menu;

    @FXML
    public void firstRequest() {
        Platform.runLater(() -> {
            HibernateRunner.session().inTransaction(session -> {
                String textQuery = """
                             SELECT cl.fullName, pr.model, pr.productType
                         FROM Client cl, Product pr
                         WHERE EXISTS (
                             SELECT 1 FROM Product
                             WHERE pr.client.clientId = cl.clientId)
                             AND pr.client.clientId = cl.clientId
                         GROUP BY cl.fullName, pr.model, pr.productType
                         ORDER BY fullName DESC
                        """;

                List<FirstDescribe> describeList = new ArrayList<>();
                List<Object> list = session.createQuery(textQuery).getResultList();
                for (Object o : list) {
                    Object[] row = (Object[]) o;
                    describeList.add(new FirstDescribe((String) row[0], (String) row[1], (String) row[2]));
                }
                ObservableList<FirstDescribe> observe = FXCollections.observableList(describeList);
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
        first_col.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        second_col.setCellValueFactory(new PropertyValueFactory<>("model"));
        third_col.setCellValueFactory(new PropertyValueFactory<>("productType"));
    }

    protected class FirstDescribe {
        private String fullName;
        private String model;
        private String productType;

        public FirstDescribe(String fullName, String model, String productType) {
            this.fullName = fullName;
            this.model = model;
            this.productType = productType;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }
    }
}
