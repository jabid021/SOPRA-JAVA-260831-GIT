package fr.formation.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloRestController {
    @GetMapping
    public String hello() {
        return "hello";
    }

    @GetMapping("/create")
    public String create() {
        return "created !";
    }

    @PostMapping
    public String createFromPost() {
        return "created from post !";
    }
}
