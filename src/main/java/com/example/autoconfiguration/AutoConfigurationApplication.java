package com.example.autoconfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class AutoConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoConfigurationApplication.class, args);
    }

    // @Bean
    // public CommandLineRunner initData(ClubRepository repository) {
    //     return args -> {
    //         if (repository.count() == 0) {
    //             repository.save(new Clubs(
    //                 null, 
    //                 "FC Barcelona", 
    //                 "More than a club. Legendary Spanish club with a rich history of beautiful football and academy development.", 
    //                 "contact@fcbarcelona.cat", 
    //                 LocalDateTime.now(), 
    //                 LocalDateTime.now()
    //             ));
    //             repository.save(new Clubs(
    //                 null, 
    //                 "Manchester United", 
    //                 "The Red Devils. One of the most supported and historically successful football clubs in English history.", 
    //                 "support@manutd.com", 
    //                 LocalDateTime.now(), 
    //                 LocalDateTime.now()
    //             ));
    //             repository.save(new Clubs(
    //                 null, 
    //                 "Real Madrid CF", 
    //                 "Los Blancos. Current giants of European football with an record number of Champions League titles.", 
    //                 "info@realmadrid.com", 
    //                 LocalDateTime.now(), 
    //                 LocalDateTime.now()
    //             ));
    //         }
    //     };
    // }

    // Make the nested controller static so it can be instantiated by Spring
    @RestController
    public static class TestController {

        @GetMapping("/")
        public String hello() {
            return "Hello";
        }
    }
}