package quest.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import quest.dao.IDAOFiliere;
import quest.dto.response.FiliereResponse;
import quest.model.Filiere;

@RestController
@RequestMapping("/api/filiere")
public class FiliereRestController {
    private final IDAOFiliere dao;

    public FiliereRestController(IDAOFiliere dao) {
        this.dao = dao;
    }

    // @GetMapping
    // @JsonView(Views.FiliereView.class)
    // public List<Filiere> findAll() {
    //     return this.dao.findAll();
    // }

    // @GetMapping
    // public List<FiliereResponse> findAll() {
    //     List<FiliereResponse> response = new ArrayList<>();

    //     for (Filiere filiere : this.dao.findAll()) {
    //         response.add(FiliereResponse.convert(filiere));
    //     }

    //     return response;
    // }

    @GetMapping
    @Transactional
    public List<FiliereResponse> findAll() {
        return this.dao.findAll().stream()
            // .map(filiere -> FiliereResponse.convert(filiere))
            .map(FiliereResponse::convert)
            .toList()
        ;
    }
}
