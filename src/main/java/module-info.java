module org.example.datastoragetechnologies {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens org.example.datastoragetechnologies.entities to org.hibernate.orm.core, javafx.base;

    opens org.example.datastoragetechnologies to javafx.fxml;
    exports org.example.datastoragetechnologies;
    opens org.example.datastoragetechnologies.requests.aggregation to javafx.base, javafx.fxml;
    opens org.example.datastoragetechnologies.requests.search to javafx.base, javafx.fxml;
    opens org.example.datastoragetechnologies.requests.other to javafx.base, javafx.fxml;
}