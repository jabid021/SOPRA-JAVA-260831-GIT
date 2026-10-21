package quest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import quest.dao.IDAOMatiere;
import quest.model.Matiere;


@Controller
@RequestMapping("/matiere")
public class MatiereController {

	@Autowired
	IDAOMatiere daoMatiere;
	
	@GetMapping("/filter")
	@ResponseBody
	public String filtrerAjax(String texte) {
		List<Matiere> matieres = daoMatiere.findByLibelleContaining(texte);
		String affichage="";
		if(matieres.isEmpty()) 
		{
			affichage+="<tr><td align='center' colspan='3'>Aucune matiere</td></tr>";
		}
	
		for(Matiere m : matieres) {
			affichage+="<tr>";
			affichage+="<td>"+m.getId()+"</td>";
			affichage+="<td>"+m.getLibelle()+"</td>";
			affichage+="<td>";
			affichage+="<a class='btn btn-warning' href='matiere?id="+m.getId()+"'>Modifier</a>";
			affichage+="<a class='btn btn-danger' href='matiere?id="+m.getId()+"&delete'>Supprimer</a>";
			affichage+="</td>";
			affichage+="</tr>"	;	
		}
		return affichage;
	}
	 
	@GetMapping
	public String afficherTous(Model model) 
	{
		model.addAttribute("matieres", daoMatiere.findAll());
		return "/WEB-INF/matieres.jsp";
	}

	@GetMapping("/{id}")
	public String afficherParId(@PathVariable Integer id, Model model) 
	{
		Matiere matiere = daoMatiere.findById(id).orElse(null);
		model.addAttribute("matiere", matiere);
		model.addAttribute("matieres", daoMatiere.findAll());
		return "/WEB-INF/matieres.jsp";
	}
	
	@GetMapping("/{id}/delete")
	public String supprimer(@PathVariable Integer id)
	{
		daoMatiere.deleteById(id);
		return "redirect:/matiere";
	}

	@PostMapping
	public String ajouter(@RequestParam(required = true) String libelle)
	{
		Matiere matiere = new Matiere(null,libelle);
		daoMatiere.save(matiere);
		return "redirect:/matiere";
	}
	
	@PostMapping("/{id}")
	public String modifier(@PathVariable Integer id,@RequestParam(required = true) String libelle) 
	{
		Matiere matiere = new Matiere(id,libelle);
		daoMatiere.save(matiere);
		return "redirect:/matiere";
	}

}
