package io.bootique.mail;

import com.google.inject.Module;
import io.bootique.BQModule;
import io.bootique.BQModuleProvider;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;

public class MailModuleProvider implements BQModuleProvider {

    @Override
    public Module module() {
        return new MailModule();
    }

    @Override
    public Map<String, Type> configs() {
        return Collections.singletonMap("mail", MailFactory.class);
    }

    @Override
    public BQModule.Builder moduleBuilder() {
        return BQModuleProvider.super
                .moduleBuilder()
                .description("Provides integration with Java Mail.");
    }
}
