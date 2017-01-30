package kopi

import spock.lang.Specification

/**
 * Created by josnyder on 1/25/17.
 */
class StopwordFactorySpec extends Specification {

    def "it works"() {
        expect: StopwordFactory.buildStopwords().contains("the");
    }
}
