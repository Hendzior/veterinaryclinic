package com.hendzior.veterinary.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationRunnerBean implements ApplicationRunner {

    private final Environment environment;

    private final Menu menu;

    @Autowired
    public ApplicationRunnerBean(Environment environment, Menu menu) {
        this.environment = environment;
        this.menu = menu;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.debug("Current App configuration: {}",environment.getActiveProfiles());
        menu.menu();
    }
}
