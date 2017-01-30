package kopi;

import java.io.IOException;
import java.util.Set;

/**
 * Created by josnyder on 1/26/17.
 */
public class ArticleFilter implements ArticleProcessor<String> {

	private final Set<String> validTitles;
	private final ArticleProcessor<String> nextProcessor;

	public ArticleFilter(Set<String> validTitles, ArticleProcessor<String> nextProcessor) {
		this.validTitles = validTitles;
		this.nextProcessor = nextProcessor;
	}


	@Override
	public void processArticle(String title, String text) throws IOException {
		if (validTitles.contains(title)) {
			nextProcessor.processArticle(title, text);
		}
	}
}
