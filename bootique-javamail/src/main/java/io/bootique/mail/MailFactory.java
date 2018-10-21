package io.bootique.mail;

import io.bootique.annotation.BQConfig;
import io.bootique.annotation.BQConfigProperty;

import javax.mail.Authenticator;
import java.util.Optional;

@BQConfig
public class MailFactory {
    private String host;
    private Integer port = 25;
    private PasswordAuthenticator authenticator;

    @BQConfigProperty("SMTP server host name")
    public void setHost(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    @BQConfigProperty("SMTP server port")
    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getPort() {
        return port;
    }

    @BQConfigProperty("SMTP server authenticator")
    public void setAuthenticator(PasswordAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    public Optional<Authenticator> createPasswordAuthenticator() {
        return Optional.ofNullable(authenticator);
    }
}
