package eshop.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import eshop.dao.IDAOPersonne;
import eshop.model.Client;
import view.Views;

@RestController
@RequestMapping("/api/client")
public class ClientRestController {

	@Autowired
	IDAOPersonne daoPersonne;

	@GetMapping
	@JsonView(Views.Client.class)
	public List<Client> afficherTous() 
	{
		return daoPersonne.findAllClient();
	}

	@GetMapping("/{id}")
	@JsonView(Views.Client.class)
	public Client afficherParId(@PathVariable Integer id) 
	{
		return (Client) daoPersonne.findById(id).orElse(null);
	}
	@GetMapping("/{id}/achats")
	@JsonView(Views.ClientWithAchats.class)
	public Client afficherParIdAvecVentes(@PathVariable Integer id) 
	{
		return daoPersonne.findByIdWithAchats(id);
	}

	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Integer id)
	{
		daoPersonne.deleteById(id);
	}

	@PostMapping
	@JsonView(Views.Client.class)
	public Client ajouter(@RequestBody Client client)
	{
		daoPersonne.save(client);
		return client;
	}

	@PutMapping("/{id}")
	@JsonView(Views.Client.class)
	public Client modifier(@PathVariable Integer id,@RequestBody Client client) 
	{
		client.setId(id);
		daoPersonne.save(client);
		return client;
	}
}
