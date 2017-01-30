package wikipedia;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by josnyder on 1/28/17.
 */
public class WikipediaDumpContentHandler implements ContentHandler {
	private final StringBuilder simpleElementContent = new StringBuilder();
	private final Deque<String> elementStack = new ArrayDeque<>();
	private final PageHandler pageHandler;

	public WikipediaDumpContentHandler(PageHandler pageHandler) {
		this.pageHandler = pageHandler;
		elementStack.push("");
	}

	private long id;
	private String title;
	private String text;
	private int namespace;

	@Override
	public void setDocumentLocator(Locator locator) {
		
	}

	@Override
	public void startDocument() throws SAXException {

	}

	@Override
	public void endDocument() throws SAXException {

	}

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {

	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {

	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if (qName.equals("page") && elementStack.peek().equals("mediawiki")) {
			id = -1;
			namespace = -1;
			title = null;
			text = null;
		}
		elementStack.push(qName);
		simpleElementContent.setLength(0);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		elementStack.pop();
		if (elementStack.peek().equals("page")) {
			switch(qName) {
				case "id":
					id = Long.parseLong(simpleElementContent.toString(), 10);
					break;
				case "title":
					title = simpleElementContent.toString();
					break;
				case "ns":
					namespace = Integer.parseInt(simpleElementContent.toString(), 10);
					break;
			}
		} else if (elementStack.peek().equals("revision") && qName.equals("text")) {
			text = simpleElementContent.toString();
		} else if (elementStack.peek().equals("mediawiki") && qName.equals("page")) {
			pageHandler.handlePage(id, namespace, title, text);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		simpleElementContent.append(ch, start, length);

	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

	}

	@Override
	public void processingInstruction(String target, String data) throws SAXException {

	}

	@Override
	public void skippedEntity(String name) throws SAXException {

	}
}
