module com.example.ticktacktoe {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.ticktacktoe to javafx.fxml;
    exports com.example.ticktacktoe;
    exports com.example.ticktacktoe.models;
    opens com.example.ticktacktoe.models to javafx.fxml;
    exports com.example.ticktacktoe.controllers;
    opens com.example.ticktacktoe.controllers to javafx.fxml;
}