package duck.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class CB extends Paiement {

	@Column(name="digits",length = 4,nullable = false)
	private String lastDigit;
	
	public CB() {}

	public CB(String lastDigit) {
		this.lastDigit = lastDigit;
		this.dateCreation = LocalDate.now();
		
	}

	public String getLastDigit() {
		return lastDigit;
	}

	public void setLastDigit(String lastDigit) {
		this.lastDigit = lastDigit;
	}

	@Override
	public String toString() {
		return "CB [id=" + id + ", dateCreation=" + dateCreation + ", lastDigit=" + lastDigit + "]";
	}
	
	
}
