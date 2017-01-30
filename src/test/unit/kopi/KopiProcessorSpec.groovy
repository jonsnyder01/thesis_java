package kopi

import spock.lang.Specification

/**
 * Created by josnyder on 1/25/17.
 */
class KopiProcessorSpec extends Specification {

    def "it works"() {
        when:
        def input = "[[Foo]]\n" +
                "Foo is a funny name Foo Foo funny funny\n" +
                "Foo is is is\n" +
                "[[Bar]]\n" +
                "Bar is a funny name Bar Bar Bar\n" +
                "cool cool cool"
        def reader = new BufferedReader(new StringReader(input))
        def stringWriter = new StringWriter()
        def writer = new BufferedWriter(stringWriter)

        def processor = new KopiProcessor(new LuceneTokenizer(), StopwordFactory.buildStopwords())
        processor.process(reader, writer);
        writer.close();

        then:
        stringWriter.toString() == "[[Foo]] foo 4 funny 3\n" +
                "[[Bar]] bar 4 cool 3\n";
    }
}
