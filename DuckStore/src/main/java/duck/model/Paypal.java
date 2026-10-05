package duck.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Paypal extends Paiement{

	private boolean valid;

	public Paypal() {}
	
	public Paypal(boolean valid) {
		this.valid = valid;
		this.dateCreation = LocalDate.now();
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public String toString() {
		return "Paypal [id=" + id + ", dateCreation=" + dateCreation + ", valid=" + valid + "]";
	}
	
	
}
