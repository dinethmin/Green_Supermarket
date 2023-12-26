package green_supermarket;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class EmailSender {

    public static void sendEmailWithTable(String recipientEmail, String subject, String[][] tableData) {
        String senderEmail = "greensupermarketonline@gmail.com";
        String appSpecificPassword = "qzautvytkowxiqjo";
        

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.password", appSpecificPassword);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, appSpecificPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);

            // Constructing the HTML table in the email body
            StringBuilder body = new StringBuilder("<html><body><h2>Your Table:</h2><table border='1'>");

            // Adding table headers
            body.append("<tr>");
            for (String header : tableData[0]) {
                body.append("<th>").append(header).append("</th>");
            }
            body.append("</tr>");

            // Adding table rows
            for (int i = 1; i < tableData.length; i++) {
                body.append("<tr>");
                for (String cell : tableData[i]) {
                    body.append("<td>").append(cell).append("</td>");
                }
                body.append("</tr>");
            }

            body.append("</table></body></html>");

            message.setContent(body.toString(), "text/html");

            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}
