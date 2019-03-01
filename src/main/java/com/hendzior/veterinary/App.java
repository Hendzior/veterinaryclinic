package com.hendzior.veterinary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

//      ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(Menu.class, args);
//      Menu menu = configurableApplicationContext.getBean(Menu.class);
//      menu.menu();

    }

}
