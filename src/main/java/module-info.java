module com.example.numconv {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.numconv to javafx.fxml;
    exports com.example.numconv;
}