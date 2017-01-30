package kopi;

import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by josnyder on 1/25/17.
 */
public class LuceneTokenizer {

	private final DiacriticMarkRemover diacriticMarkRemover;

	public LuceneTokenizer(DiacriticMarkRemover diacriticMarkRemover) {
		this.diacriticMarkRemover = diacriticMarkRemover;
	}

	public List<String> tokenize(String input) throws IOException {
		StringReader reader = new StringReader(diacriticMarkRemover.removeDiacriticMarks(input));
		StandardTokenizer tokenizer = new StandardTokenizer(Version.LUCENE_36, reader);

		CharTermAttribute charTermAttrib = tokenizer.getAttribute(CharTermAttribute.class);

		List<String> tokens = new ArrayList<String>();
		tokenizer.reset();
		while (tokenizer.incrementToken()) {
			tokens.add(charTermAttrib.toString().toLowerCase());
		}
		tokenizer.end();
		tokenizer.close();

		return tokens;
	}
}
