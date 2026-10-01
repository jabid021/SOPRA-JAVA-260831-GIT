package duck.model;

import java.util.Set;

public class Jeu {

	private Integer id;
	private String titre;
	private Console console;
	private Boutique boutique;
	private Set<Genre> genres;
	
	
	public Jeu(Integer id, String titre, Console console,Boutique boutique, Set<Genre> genres) {
		
		this.id = id;
		this.titre = titre;
		this.boutique = boutique;
		this.console = console;
		this.genres = genres;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public Boutique getBoutique() {
		return boutique;
	}


	public void setBoutique(Boutique boutique) {
		this.boutique = boutique;
	}


	public Console getConsole() {
		return console;
	}


	public void setConsole(Console console) {
		this.console = console;
	}


	public Set<Genre> getGenres() {
		return genres;
	}


	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}


	public String toString() {
		return "Jeu [id=" + id + ", titre=" + titre + ", boutique=" + boutique + ", console=" + console + ", genres="
				+ genres + "]";
	}
	
	
	
}
