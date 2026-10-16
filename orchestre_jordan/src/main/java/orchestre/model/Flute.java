package orchestre.model;

import org.springframework.stereotype.Component;

@Component
public class Flute implements IInstrument{

	@Override
	public String son() {
		return "La flute fait flink flink";
	}

}
