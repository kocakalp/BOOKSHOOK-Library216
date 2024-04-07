module Library {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.google.gson;

    opens Library to javafx.fxml,com.google.gson;
    exports Library;
}