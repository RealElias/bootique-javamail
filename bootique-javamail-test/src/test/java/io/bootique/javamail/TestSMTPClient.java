package io.bootique.javamail;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class TestSMTPClient {
    private static final Logger LOG = LoggerFactory.getLogger(TestSMTPClient.class);
    private Session session;

    @Inject
    public TestSMTPClient(Session session) {
        this.session = session;
    }

    public void init() {
        try {
            MimeMessage message = buildMessage();

            Transport.send(message);
        } catch (MessagingException e) {
            LOG.error("Message wasn't sent: {}", e.getMessage());
        }
    }

    private MimeMessage buildMessage() throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom("from@bootique.mail");
        //change this address for test
        message.setRecipients(Message.RecipientType.TO, "to@bootique.test");
        message.setSubject("bootique-javamail test");

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText("Hello from bootique-javamail test!");

        MimeMultipart content = new MimeMultipart("related");
        content.addBodyPart(bodyPart);

        message.setContent(content);

        return message;
    }
}
