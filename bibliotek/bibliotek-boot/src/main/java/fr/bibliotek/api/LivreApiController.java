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

import fr.bibliotek.api.request.CreateOrUpdateLivreRequest;
import fr.bibliotek.api.response.EntityCreatedResponse;
import fr.bibliotek.api.response.EntityUpdatedResponse;
import fr.bibliotek.api.response.LivreResponse;
import fr.bibliotek.service.LivreService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/livre")
public class LivreApiController {
    private final LivreService service;

    public LivreApiController(LivreService service) {
        this.service = service;
    }

    @GetMapping
    public List<LivreResponse> findAll() {
        return this.service.findAll().stream().map(LivreResponse::convert).toList();
    }

    @GetMapping("/{id}")
    public LivreResponse findById(@PathVariable String id) {
        return LivreResponse.convert(this.service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityCreatedResponse create(@Valid @RequestBody CreateOrUpdateLivreRequest request) {
        return new EntityCreatedResponse(this.service.save(request).getId());
    }

    @PutMapping("/{id}")
    public EntityUpdatedResponse update(@PathVariable String id, @Valid @RequestBody CreateOrUpdateLivreRequest request) {
        this.service.save(id, request);

        return new EntityUpdatedResponse(id, true);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable String id) {
        this.service.deleteById(id);
    }
}
