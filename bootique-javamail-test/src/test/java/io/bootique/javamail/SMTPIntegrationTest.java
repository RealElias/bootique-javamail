package io.bootique.javamail;

import io.bootique.BQCoreModule;
import io.bootique.BQRuntime;
import io.bootique.mail.MailModule;
import io.bootique.test.junit.BQTestFactory;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;

public class SMTPIntegrationTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SMTPIntegrationTest.class);

    @ClassRule
    public static GenericContainer smtpServer = new GenericContainer("namshi/smtp:latest")
            .withExposedPorts(25)
            .waitingFor(new LogMessageWaitStrategy().withRegEx(".*listening for SMTP on port.*\\s"));


    @ClassRule
    public static BQTestFactory testFactory = new BQTestFactory();

    @Test
    public void test() {
        LOGGER.info("SMTP server started at {}:{}", smtpServer.getContainerIpAddress(),  smtpServer.getMappedPort(25));

        BQRuntime runtime = testFactory.app("--config=classpath:smtp-test.yml")
                .module(MailModule.class)
                .module((binder) -> {
                    BQCoreModule.extend(binder)
                            .setProperty("bq.mail.port", String.valueOf(smtpServer.getMappedPort(25)));
                    binder.bind(TestSMTPClient.class);
                }).createRuntime();

        runtime.getInstance(TestSMTPClient.class).init();
    }
}