package kopi;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by josnyder on 1/26/17.
 */
public class ArticleWriter implements ArticleProcessor<Map<String,Integer>> {

	private final BufferedWriter writer;
	private final Integer minimumCount;

	public ArticleWriter(BufferedWriter writer, Integer minimumCount) {
		this.writer = writer;
		this.minimumCount = minimumCount;
	}

	@Override
	public void processArticle(String title, Map<String, Integer> tokenFrequencies) throws IOException {

		List<Map.Entry<String,Integer>> tokenFrequenciesList = new ArrayList<Map.Entry<String, Integer>>();
		for (Map.Entry<String,Integer> entry : tokenFrequencies.entrySet()) {
			if (entry.getValue() >= minimumCount) {
				tokenFrequenciesList.add(entry);
			}
		}
		Collections.sort(tokenFrequenciesList, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		writer.write("[[");
		writer.write(title);
		writer.write("]]");
		for (Map.Entry<String,Integer> entry : tokenFrequenciesList) {
			writer.write(" ");
			writer.write(entry.getKey());
			writer.write(" ");
			writer.write(entry.getValue());
		}
		writer.newLine();
	}
}
