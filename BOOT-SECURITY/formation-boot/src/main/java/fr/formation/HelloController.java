package fr.formation;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public Map<String, String> hello() {
        // return "Hello";
        return Map.of("message", "Hello world!");
    }
}
