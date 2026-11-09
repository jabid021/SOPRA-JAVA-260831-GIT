package fr.bibliotek.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.bibliotek.api.request.AuthRequest;
import fr.bibliotek.api.response.AuthResponse;
import fr.bibliotek.security.service.SecurityService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthApiController {
    private static final Logger log = LoggerFactory.getLogger(AuthApiController.class);
    private final SecurityService service;

    public AuthApiController(SecurityService service) {
        this.service = service;
    }

    @PostMapping
    public AuthResponse auth(@Valid @RequestBody AuthRequest request) {
        log.debug("Authentification ...");

        return this.service.auth(request);
    }
}
