package fr.bibliotek.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.bibliotek.api.request.CreateOrUpdateAvisRequest;
import fr.bibliotek.api.response.AvisResponse;
import fr.bibliotek.api.response.EntityCreatedResponse;
import fr.bibliotek.api.response.EntityUpdatedResponse;
import fr.bibliotek.service.AvisService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/avis")
public class AvisApiController {
    private final AvisService service;

    public AvisApiController(AvisService service) {
        this.service = service;
    }

    @GetMapping
    public List<AvisResponse> findAll() {
        return this.service.findAll().stream().map(AvisResponse::convert).toList();
    }

    @GetMapping("/{id}")
    public AvisResponse findById(@PathVariable String id) {
        return AvisResponse.convert(this.service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityCreatedResponse create(@Valid @RequestBody CreateOrUpdateAvisRequest request) {
        return new EntityCreatedResponse(this.service.save(request).getId());
    }

    @PutMapping("/{id}")
    public EntityUpdatedResponse update(@PathVariable String id, @Valid @RequestBody CreateOrUpdateAvisRequest request) {
        this.service.save(id, request);

        return new EntityUpdatedResponse(id, true);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable String id) {
        this.service.deleteById(id);
    }
}
