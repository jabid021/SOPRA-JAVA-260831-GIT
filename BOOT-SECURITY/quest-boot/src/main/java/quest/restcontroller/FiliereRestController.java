package quest.restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import quest.dao.IDAOFiliere;
import quest.dao.IDAOSalle;
import quest.dto.request.CreateFiliereRequest;
import quest.dto.response.FiliereResponse;
import quest.model.Filiere;
import quest.model.Salle;

@RestController
@RequestMapping("/api/filiere")
public class FiliereRestController {
    private final IDAOFiliere dao;
    private final IDAOSalle daoSalle;

    public FiliereRestController(IDAOFiliere dao, IDAOSalle daoSalle) {
        this.dao = dao;
        this.daoSalle = daoSalle;
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

    @PostMapping
    public FiliereResponse create(@RequestBody CreateFiliereRequest request) {
        Filiere filiere = new Filiere();

        filiere.setLibelle(request.libelle());

        // Salle salle = new Salle();
        // filiere.setSalle(salle);
        // salle.setId(request.salleId());

        filiere.setSalle(this.daoSalle.getReferenceById(request.salleId()));

        return null;
    }
}
