package fr.formation.restcontroller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloRestController {
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    // @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    // @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public String hello(Authentication auth) {
        System.out.println(auth);
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
