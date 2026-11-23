package fr.formation.servicesecurity.api;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/.well-known/jwks.json")
@RequiredArgsConstructor
public class JwksApiController {
    private final RSAKey rsaKey;

    @GetMapping
    public Map<String, Object> jwks() {
        return new JWKSet(rsaKey).toJSONObject();
    }
}
