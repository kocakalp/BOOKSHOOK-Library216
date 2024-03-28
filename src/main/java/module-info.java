module com.example.bookshooklibrary216 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.google.gson;

    opens com.example.bookshooklibrary216 to javafx.fxml;
    exports com.example.bookshooklibrary216;
}