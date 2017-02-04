package wikipedia;

import kopi.ArticleProcessor;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by josnyder on 1/28/17.
 */
public class PageHandler {

	private final WikimediaParser wikimediaParser;
	private final ArticleProcessor<String> articleProcessor;
	private final BufferedWriter linkWriter;

	public PageHandler(WikimediaParser wikimediaParser, ArticleProcessor<String> articleProcessor, BufferedWriter linkWriter) {
		this.wikimediaParser = wikimediaParser;
		this.articleProcessor = articleProcessor;
		this.linkWriter = linkWriter;
	}

	public void handlePage(long id, int namespace, String title, String text) throws IOException {
		if (namespace != 0) {
			return;
		}

		String plainText = wikimediaParser.parsePlainText(text);
		articleProcessor.processArticle(title, text);
		wikimediaParser.getLinks()
	}
}
