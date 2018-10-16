package com.epam.audiomanager.util.mail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;
import java.util.ResourceBundle;


public class SessionCreator {

    private String userName;
    private String userPass;
    private Properties sessionProperties;

    private static final Logger LOG = LogManager.getLogger(SessionCreator.class);

    public SessionCreator(ResourceBundle resourceBundle) {
        String smtpHost = resourceBundle.getString("mail.smtp.host");
        String smtpPort = resourceBundle.getString("mail.smtp.port");
        userName = resourceBundle.getString("mail.user.name");
        userPass = resourceBundle.getString("mail.user.password");

        LOG.debug(smtpHost + "\n"
                + smtpPort + "\n"
                + userName + "\n"
                + userPass + "\n");

        sessionProperties = new Properties();

        sessionProperties.setProperty("mail.transport.protocol", "smtp");
        sessionProperties.setProperty("mail.host", smtpHost);

        sessionProperties.put("mail.smtp.auth", "true");
        sessionProperties.put("mail.smtp.port", smtpPort);
        sessionProperties.put("mail.smtp.socketFactory.port", smtpPort);
        sessionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        sessionProperties.put("mail.smtp.socketFactory.fallback", "false");

        sessionProperties.setProperty("mail.smtp.quitwait", "false");
    }

    public Session createSession() {
        return Session.getDefaultInstance(sessionProperties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, userPass);
                    }
                });
    }

}
