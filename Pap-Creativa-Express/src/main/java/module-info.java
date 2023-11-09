module com.example.papcreativaexpress {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires jakarta.mail;
    requires javafx.swing;
    requires java.sql;
    requires com.google.zxing;
    requires com.google.zxing.javase;


    opens com.example.papcreativaexpress to javafx.fxml;
    opens com.example.papcreativaexpress.Model to javafx.base;
    exports com.example.papcreativaexpress;
    exports com.example.papcreativaexpress.Model;
    opens com.example.papcreativaexpress.Controllers; // Abre el paquete Controllers
}
