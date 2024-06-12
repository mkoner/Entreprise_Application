package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConfigApplication implements CommandLineRunner {
    @Autowired
    ConfigData configData;
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application name: " + configData.getName());
        System.out.println("Application version: " + configData.getVersion());
        System.out.println("Application server name: " + configData.getServer().getName());
        System.out.println("Application server url: " + configData.getServer().getUrl());
        System.out.println("Application user firstName: " + configData.getUser().getFirstname());
        System.out.println("Application user lastName: " + configData.getUser().getLastname());
        System.out.println("Application user username: " + configData.getUser().getUsername());
        System.out.println("Application user password: " + configData.getUser().getPassword());
        System.out.println("Countries: ");
        for (String country : configData.getCountries()) {
            System.out.println("  - " + country);
        }
    }
}
