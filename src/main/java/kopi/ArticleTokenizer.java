package kopi;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by josnyder on 1/26/17.
 */
public class ArticleTokenizer implements ArticleProcessor<String> {

	private final LuceneTokenizer tokenizer;
	private final Set<String> stopwords;
	private final ArticleProcessor<Map<String,Integer>> nextProcessor;

	public ArticleTokenizer(LuceneTokenizer tokenizer, Set<String> stopwords, ArticleProcessor<Map<String,Integer>> nextProcessor) {
		this.tokenizer = tokenizer;
		this.stopwords = stopwords;
		this.nextProcessor = nextProcessor;
	}

	@Override
	public void processArticle(String title, String text) throws IOException {
		List<String> tokens = tokenizer.tokenize(title + "\n" + text);
		Map<String,Integer> tokenFrequencies = new HashMap<String, Integer>();
		for (String token : tokens) {
			if (!stopwords.contains(token)) {
				Integer count = tokenFrequencies.containsKey(token) ? tokenFrequencies.get(token) : 0;
				tokenFrequencies.put(token,count + 1);
			}
		}
		nextProcessor.processArticle(title, tokenFrequencies);

	}


}
