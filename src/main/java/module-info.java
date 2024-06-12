module org.example.datastoragetechnologies {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;


    opens org.example.datastoragetechnologies to javafx.fxml;
    exports org.example.datastoragetechnologies;
}