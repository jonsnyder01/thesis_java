package wikipedia

import org.xml.sax.InputSource
import org.xml.sax.XMLReader
import spock.lang.Specification

import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory

/**
 * Created by josnyder on 1/28/17.
 */
class WikipediaDumpContentHandlerSpec extends Specification {

    def "it works"() {
        given:
        def content =
                '<mediawiki xmlns="http://www.mediawiki.org/xml/export-0.10/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.mediawiki.org/xml/expo\n' +
                        'rt-0.10/ http://www.mediawiki.org/xml/export-0.10.xsd" version="0.10" xml:lang="en">' +
                "  <page>\n" +
                "    <title>The Answer</title>\n" +
                "    <ns>11</ns>\n" +
                "    <id>42</id>\n" +
                "    <revision>\n" +
                "      <id>759448062</id>\n" +
                "      <parentid>759443824</parentid>\n" +
                "      <timestamp>2017-01-11T05:42:08Z</timestamp>\n" +
                "      <contributor>\n" +
                "        <username>Jmcgnh</username>\n" +
                "        <id>28223823</id>\n" +
                "      </contributor>\n" +
                "      <comment>Undid revision 759443824 by [[Special:Contributions/2607:FCC8:A003:3C00:396D:E056:53EC:3800|2607:FCC8:A003:3C00:396D:E056:53EC:3800]] Experimental edits or vandalism.</comment>\n" +
                "      <model>wikitext</model>\n" +
                "      <format>text/x-wiki</format>\n" +
                "      <text xml:space=\"preserve\">to life\nthe universe and  &lt;everything&gt;!</text>" +
                "</revision></page></mediawiki>";
        PageHandler pageHandler = Mock(PageHandler)
        WikipediaDumpContentHandler subject = new WikipediaDumpContentHandler(pageHandler)

        when:
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setContentHandler(subject);
        xmlReader.parse(new InputSource(new StringReader(content)));

        then:
        1 * pageHandler.handlePage(42,11,"The Answer","to life\nthe universe and  <everything>!");
    }
}
