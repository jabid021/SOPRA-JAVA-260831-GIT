package duck.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "seqPaiement",sequenceName = "sequence_paiement")
public abstract class Paiement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqPaiement")
	protected Integer id;
	
	@Column(name="date_creation",nullable = false)
	protected LocalDate dateCreation;
	
	public Paiement() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	

}
