package io.bootique.mail;

import io.bootique.test.junit.BQModuleProviderChecker;
import org.junit.Test;

public class MailModuleProviderTest {

    @Test
    public void testAutoLoadable() {
        BQModuleProviderChecker.testAutoLoadable(MailModuleProvider.class);
    }

    @Test
    public void testMetadata() {
        BQModuleProviderChecker.testMetadata(MailModuleProvider.class);
    }
}
