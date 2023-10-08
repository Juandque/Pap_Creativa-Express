package com.example.papcreativaexpress.Utils;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EnviarCorreo {
    private Properties props;

    public EnviarCorreo() {
        // Configura las propiedades del servidor SMTP
        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Reemplaza con el servidor SMTP que deseas utilizar
        props.put("mail.smtp.port", "465"); // Reemplaza con el puerto SMTP, generalmente 25 o 587
        props.put("mail.smtp.auth", "true"); // Habilita la autenticación SMTP
        props.put("mail.smtp.ssl.enable", "true");

    }

    public void enviarCorreo(String remitente, String destinatario, String asunto, String cuerpo) {
        // Crea una sesión de correo electrónico
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("papcreativaexpress@gmail.com", "tbpw dvuz ldds mzrw");
            }
        });

        try {
            // Crea un objeto de mensaje de correo
            Message message = new MimeMessage(session);

            // Establece el remitente
            message.setFrom(new InternetAddress(remitente));

            // Establece el destinatario
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));

            // Establece el asunto
            message.setSubject(asunto);

            // Establece el contenido del correo
            message.setText(cuerpo);

            // Envía el correo
            Transport.send(message);

            System.out.println("Correo electrónico enviado con éxito.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
