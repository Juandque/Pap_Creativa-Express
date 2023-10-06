module com.example.papcreativaexpress {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.papcreativaexpress to javafx.fxml;
    exports com.example.papcreativaexpress;
}