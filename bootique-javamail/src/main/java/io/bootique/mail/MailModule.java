package io.bootique.mail;

import com.google.inject.Binder;
import com.google.inject.Provides;
import io.bootique.ConfigModule;
import io.bootique.config.ConfigurationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Authenticator;
import javax.mail.Session;
import java.util.Optional;
import java.util.Properties;

public class MailModule extends ConfigModule {

    private static final Logger LOG = LoggerFactory.getLogger(MailModule.class);

    @Override
    public void configure(Binder binder) {

    }

    @Provides
    public MailFactory provideClientFactory(ConfigurationFactory configurationFactory) {
        return configurationFactory.config(MailFactory.class, configPrefix);
    }

    @Provides
    public Session provideSession(MailFactory config) {
        Optional<Authenticator> authenticator = config.createPasswordAuthenticator();
        LOG.debug("Building SMTP session for {}. Using authentication: {}", config.toString(), authenticator.isPresent());
        Properties properties = new Properties();
        properties.put("mail.smtp.host", config.getHost());
        properties.put("mail.smtp.port", config.getPort());

        if (authenticator.isPresent()) {
            properties.put("mail.smtp.auth", Boolean.TRUE.toString());
            return Session.getInstance(properties, authenticator.get());
        }

        return Session.getInstance(properties);
    }
}
