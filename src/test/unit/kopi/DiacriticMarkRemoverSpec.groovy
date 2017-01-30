package kopi

import spock.lang.Specification

/**
 * Created by josnyder on 1/26/17.
 */
class DiacriticMarkRemoverSpec extends Specification {

    def "it works"() {
        expect: (new DiacriticMarkRemover()).removeDiacriticMarks("orčpžsíáýd\n==") == "orcpzsiayd\n==";
    }
}
