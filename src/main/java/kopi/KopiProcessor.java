package kopi;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by josnyder on 1/25/17.
 */
public class KopiProcessor {

	public void process(BufferedReader reader, ArticleProcessor<String> processor) throws IOException {
		String title = null;
		StringBuilder text = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("[[") && line.endsWith("]]")) {
				processText(processor, title, text.toString());
				title = line.substring(2,line.length()-2);
				text.setLength(0);
			} else {
				text.append(line);
				text.append("\n");
			}
		}
		processText(processor, title, text.toString());
	}

	private void processText(ArticleProcessor<String> processor, String title, String text) throws IOException {
		if (title == null) {
			return;
		}
		processor.processArticle(title, text);
	}

}
