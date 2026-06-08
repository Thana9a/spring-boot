
package com.example.autoconfiguration.actuator;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloWorld {

    private static final String template = "Hello, %s";
    
    private final AtomicLong counter = new AtomicLong();
    
    @GetMapping("/Hello_Dom")
    public RestFull sayHello (@RequestParam(name = "name", required = false, defaultValue = "Stranger")String name){
        return new RestFull(counter.incrementAndGet(),String.format(template, name));
    }

}
