package kopi;

import java.text.Normalizer;

/**
 * Created by josnyder on 1/26/17.
 */
public class DiacriticMarkRemover {

	public String removeDiacriticMarks(String input) {
		return Normalizer.normalize(input, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
}
