package wikipedia;

import info.bliki.wiki.filter.PlainTextConverter;
import info.bliki.wiki.model.WikiModel;

import java.io.IOException;
import java.util.Set;

/**
 * Created by josnyder on 1/26/17.
 */
public class WikimediaParser {

	private final WikiModel wikiModel = new WikiModel("https://www.mywiki.com/wiki/${image}", "https://www.mywiki.com/wiki/${title}");

	public String parsePlainText(String input) throws IOException {
		String plainText = wikiModel.render(new PlainTextConverter(), input);
		//plainText = StringEscapeUtils.unescapeHtml4(plainText);
		//plainText = plainText.replace("Template:","");
		System.out.println(wikiModel.getLinks());
		return plainText;
	}

	public Set<String> getLinks() {
		return wikiModel.getLinks();
	}
}
