module com.example.papcreativaexpress {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires jakarta.mail;
    requires javafx.swing;


    opens com.example.papcreativaexpress to javafx.fxml;
    exports com.example.papcreativaexpress;
    opens com.example.papcreativaexpress.Controllers; // Abre el paquete Controllers
}
