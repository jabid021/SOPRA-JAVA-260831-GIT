package orchestre.model;

import org.springframework.stereotype.Component;

@Component
public class Guitare implements IInstrument{

	@Override
	public String son() {
		return "La guitare fait glink glink";
	}

}
