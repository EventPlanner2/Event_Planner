module com.example.eventplanner {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.eventplanner to javafx.fxml;
    exports com.example.eventplanner;
}