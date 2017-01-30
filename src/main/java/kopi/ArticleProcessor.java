package kopi;

import java.io.IOException;

/**
 * Created by josnyder on 1/26/17.
 */
public interface ArticleProcessor<T> {

	void processArticle(String title, T text) throws IOException;
}
