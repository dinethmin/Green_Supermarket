package green_supermarket;

import Connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javax.swing.JOptionPane;

public class EmailSender {

    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    String column1Value;
    String column2Value;
    String column3Value;
    String column4Value;
    String column5Value;
    String column6Value;
    String emailBody;

    public static void sendEmailWithTable(String UserName, String recipientEmail, String subject, String SubTotal, String Status) throws SQLException {
        String senderEmail = "greensupermarketonline@gmail.com";
        String appSpecificPassword = "";

        PreparedStatement ps;
        Statement st;
        ResultSet rs;

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

            String sql = "select PurchaseID, ProductID, ProductName, Quantity, Price, total from purchasedetails where UserName = ? and Status = ?";

            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, UserName);
            ps.setString(2, Status);
            rs = ps.executeQuery();
            StringBuilder htmlTable = new StringBuilder("<html><body><h3>Hi " + UserName + "</h3><h3>Your bill</h3><table border='1'><tr><th>PurchaseID</th><th>ProductID</th><th>ProductName</th><th>Quantity</th><th>Price</th><th>Total</th></tr>");

            while (rs.next()) {
                String column1Value = String.valueOf(rs.getInt("PurchaseID"));
                String column2Value = String.valueOf(rs.getInt("ProductID"));
                String column3Value = rs.getString("ProductName");
                String column4Value = String.valueOf(rs.getInt("Quantity"));
                String column5Value = String.valueOf(rs.getDouble("Price"));
                String column6Value = String.valueOf(rs.getDouble("total"));

                htmlTable.append("<tr>");
                htmlTable.append("<td>").append(column1Value).append("</td>");
                htmlTable.append("<td>").append(column2Value).append("</td>");
                htmlTable.append("<td>").append(column3Value).append("</td>");
                htmlTable.append("<td>").append(column4Value).append("</td>");
                htmlTable.append("<td>").append(column5Value).append("</td>");
                htmlTable.append("<td>").append(column6Value).append("</td>");
                htmlTable.append("</tr>");
            }

            htmlTable.append("</table><h4>Total Net Ammount  ").append(SubTotal).append(" </h4></body></html>");

            message.setContent(htmlTable.toString(), "text/html");

            Transport.send(message);

            JOptionPane.showMessageDialog(null, "Thank you for the Purchase");
        } catch (MessagingException e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
    
    public static void sendCancelEmailWithTable(String UserName, String recipientEmail, String subject, String SubTotal, String Status, int PurchaseID) throws SQLException {
        String senderEmail = "greensupermarketonline@gmail.com";
        String appSpecificPassword = "qzautvytkowxiqjo";

        PreparedStatement ps;
        Statement st;
        ResultSet rs;

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

            String sql = "select PurchaseID, ProductID, ProductName, Quantity, Price, total from purchasedetails where PurchaseID = ? and Status = ?";

            Connection con = MyConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, PurchaseID);
            ps.setString(2, Status);
            rs = ps.executeQuery();
            StringBuilder htmlTable = new StringBuilder("<html><body><h3>Hi " + UserName + "</h3><h3>Sorry your This Purchase was canceled</h3><table border='1'><tr><th>PurchaseID</th><th>ProductID</th><th>ProductName</th><th>Quantity</th><th>Price</th><th>Total</th></tr>");

            while (rs.next()) {
                String column1Value = String.valueOf(rs.getInt("PurchaseID"));
                String column2Value = String.valueOf(rs.getInt("ProductID"));
                String column3Value = rs.getString("ProductName");
                String column4Value = String.valueOf(rs.getInt("Quantity"));
                String column5Value = String.valueOf(rs.getDouble("Price"));
                String column6Value = String.valueOf(rs.getDouble("total"));

                htmlTable.append("<tr>");
                htmlTable.append("<td>").append(column1Value).append("</td>");
                htmlTable.append("<td>").append(column2Value).append("</td>");
                htmlTable.append("<td>").append(column3Value).append("</td>");
                htmlTable.append("<td>").append(column4Value).append("</td>");
                htmlTable.append("<td>").append(column5Value).append("</td>");
                htmlTable.append("<td>").append(column6Value).append("</td>");
                htmlTable.append("</tr>");
            }

            htmlTable.append("</table><h4>Total Net Ammount  ").append(SubTotal).append(" </h4></body></html>");

            message.setContent(htmlTable.toString(), "text/html");

            Transport.send(message);
        } catch (MessagingException e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }

}
