module com.example.papcreativaexpress {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires jakarta.mail;

    opens com.example.papcreativaexpress to javafx.fxml;
    opens com.example.papcreativaexpress.Model to javafx.base;
    exports com.example.papcreativaexpress;
    opens com.example.papcreativaexpress.Controllers; // Abre el paquete Controllers
}
