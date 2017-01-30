package kopi

import spock.lang.Specification

/**
 * Created by josnyder on 1/25/17.
 */
class LuceneTokenizerSpec extends Specification {

    def "it works"() {
        when:
        def subject = new LuceneTokenizer();

        then:
        subject.tokenize("I like cats") == ["i","like","cats"]
        subject.tokenize('He said, "Hello my good sir!" (He sounded like an idiot.)') ==
        ["he","said","hello","my","good","sir","he","sounded","like","an","idiot"]
    }
}
