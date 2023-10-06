module com.example.papcreativaexpress {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.papcreativaexpress to javafx.fxml;
    exports com.example.papcreativaexpress;
}