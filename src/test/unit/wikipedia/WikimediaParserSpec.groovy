package wikipedia

import spock.lang.Specification
/**
 * Created by josnyder on 1/26/17.
 */
class WikimediaParserSpec extends Specification {

    def "it works"() {
        expect:
        (new WikimediaParser()).parsePlainText("This is a [[Hello World]] '''example'''") == "\nThis is a Hello World example";
    }


    def "it gets links"() {
        when:
        def text = "===Spin-offs===" +
                "The sound of the Doctor's TARDIS featured in the final scene of the ''[[Torchwood]]'' episode &quot;" +
                "[[End of Days (Torchwood)|End of Days]]&quot; (2007). As Torchwood Three's hub is situated at a rift" +
                " of temporal energy, the Doctor often appears on [[Roald Dahl Plass]] directly above it in order to " +
                "recharge the TARDIS.{{citation needed|date=February 2016}} In the episode, [[Jack Harkness]] hears the " +
                "tell-tale sound of the engines, smiles and afterwards is nowhere to be found; the scene picks up in the " +
                "[[cold open]] of the ''Doctor Who'' episode &quot;[[Utopia (Doctor Who)|Utopia]]&quot; (2007) in which " +
                "Jack runs to and holds onto the TARDIS just before it disappears."
        def subject = new WikimediaParser();

        then:
        subject.parsePlainText(text) != ""
        subject.getLinks().size() == 7
    }

    def "it handles info boxes"() {
        when:
        def subject = new WikimediaParser()
        def text = "{{Infobox fictional artifact\n" +
                "| name            = TARDIS\n" +
                "| colour          = \n" +
                "| colour text     = \n" +
                "| image           = TARDIS1.jpg\n" +
                "| caption         = The Doctor's TARDIS as it looked between 2005 and 2010 on display at [[BBC Television Centre]]\n" +
                "| source          = [[Doctor Who]]\n" +
                "| source_type     = TV\n" +
                "| company         = [[BBC]]\n" +
                "| first           = [[An Unearthly Child]]\n" +
                "| date            = 23 November 1963\n" +
                "| creator         = {{unbulleted list|[[Sydney Newman]]|[[C. E. Webber]]|[[Donald Wilson (writer and producer)|Donald Wilson]]}}\n" +
                "| episode_creator = [[Anthony Coburn]]\n" +
                "| genre           = [[Science fiction]]\n" +
                "| type            = [[Time machine]]/[[spacecraft]]\n" +
                "| uses            = Travel through time and space\n" +
                "| traits          = Can change its outer dimensions and inner layout, impregnable, telepathic \n" +
                "| affiliation     = [[Time Lord]]s &lt;br/&gt; [[The Doctor (Doctor Who)|The Doctor]]\n" +
                "}}";

        then:
        subject.parsePlainText(text) == "\nTemplate:Infobox fictional artifact";
        subject.getLinks().size() == 1

    }
}
