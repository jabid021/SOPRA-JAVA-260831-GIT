package fr.formation.restcontroller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloRestController {
    @GetMapping
    // @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    // @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String hello() {
        return "hello";
    }

    @GetMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public String create() {
        return "created !";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String createFromPost() {
        return "created from post !";
    }
}
