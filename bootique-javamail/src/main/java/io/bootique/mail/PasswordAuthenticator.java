package io.bootique.mail;

import io.bootique.annotation.BQConfigProperty;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class PasswordAuthenticator  extends Authenticator {
    private String username;
    private String password;

    @BQConfigProperty("SMTP server authenticator username")
    public void setUsername(String username) {
        this.username = username;
    }

    @BQConfigProperty("SMTP server authenticator password")
    public void setPassword(String password) {
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
