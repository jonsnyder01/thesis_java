package kopi;

import java.io.*;

/**
 * Created by josnyder on 1/25/17.
 */
public class Program {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

		ArticleProcessor<String> articleProcessor =
				new ArticleTokenizer(
						new LuceneTokenizer(new DiacriticMarkRemover()),
						StopwordFactory.buildStopwords(),
						new ArticleWriter(writer, 3));
		KopiProcessor processor = new KopiProcessor();
		processor.process(reader, articleProcessor);
		writer.close();
		System.out.println("Done!");
	}
}
