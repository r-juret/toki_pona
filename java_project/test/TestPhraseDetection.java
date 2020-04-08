/*import modele.Exception.MotsInvalideException;
import modele.PhraseDetection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestPhraseDetection {

    private PhraseDetection phr;
    private static List<String> bd;

    @BeforeAll
    public static void init() {

        String str = "ala mu pi ko linja pan li luka e kule kon pi uta ale nasa e sike sona lon akesi ni pi ike kasi kasi kepeken lon toki.ijo pi pilin musi suli li pimeja ma la seli tenpo li sin sama nasa li awen toki e olin sama kalama sitelen.tenpo moku pi tan kute nasin li ken kule e jaki tawa pi pilin ilo linja tan kasi pi mute jo ko li sin tan olin tan walo kala pi ijo lawa lape.tu li nimi pi pan lawa kala la poki li pakala e insa sijelo e kala loje pi selo taso soweli lon lili pilin kepeken utala." +
                "suno pi kon sama olin li pini kepeken e monsi pana kepeken lon poka linja li kama sinpin e tenpo e tawa pi suli lape alasa.jaki li linja pi nasa mama lukin la sitelen kili pi kulupu nasin lape li ni.musi tenpo pi sina kasi mama li kili ni.ilo musi li pakala e mun pini pi tenpo suli anu e insa mu pi palisa wawa kama la lon pi lape utala kipisi li pini ijo tan jaki." +
                "lipu luka pi tomo mu kiwen li insa monsi.tenpo ni la ona laso li soweli e seme waso li awen e kute e lon pi mun pana sike lon tawa.wan pi poki pimeja jo li kepeken kute waso pi ona pakala seli.kalama anpa pi pilin telo lape li ken palisa e pana pi sewi kepeken len e mije seme pi tenpo utala suno kepeken palisa moku pi jaki ali luka sama pali kala pi lawa insa ijo." +
                "tenpo pini la waso seli li tomo e loje lili sama unpa pi mije insa lukin tan pona.sin tomo pi ale kalama jelo li tu e pipi selo pi lape musi alasa.lipu suno pi ma kon jelo li pipi pi mi ijo nanpa.mama loje pi weka insa sinpin li pini nimi li toki e nasa e nimi jaki pi kon ilo tawa." +
                "luka li sama e luka pi len ike lukin e luka lon pilin kala poka kule pipi pi ijo suno waso.moli pi oko ona uta li kepeken ala pi tenpo nanpa waso la nasa pona li ken tan tan nanpa pi lukin pakala pana kepeken musi mi pi mani wan anpa li awen pini sama moli namako pi insa seli ilo.mani pipi li kule pi luka luka nanpa.mun ni li kama tan e ma pi mute lawa mute e ala pi weka kute supa li open esun e pimeja kepeken kepeken namako sona." +
                "akesi pi lape sin moli li olin utala pi lili moli pona la tomo li kipisi e suwi jan e kepeken tan laso uta.tenpo pini la tan selo pi telo telo nasin li oko tan pan poka tu ante pi suwi oko lukin.kepeken li uta e anpa e loje toki sama wan pan pi mama lili waso.lipu pi lili supa lukin li tan suwi nasin." +
                "mama kipisi li pona sama sina pi nimi sijelo ni li alasa e mu ma pi laso lipu sona e mani ona tan awen sama lipu wawa.tenpo kama la olin li kama sike e jan pi wawa pimeja pali e ali pi tawa ike pali sama nena sama suwi.tenpo kama la ala pi sama jaki wawa li soweli e pakala pona sama lili pi sike mani supa sama esun pona pi sike esun waso.pakala li kama tan e moku mi e pali pi open kon namako la meli pi sewi pipi mu li nasa lon awen olin pi nasin nena jan." +
                "kala jelo li ken tenpo e ni kule pi nena kama luka poka tomo anu la pilin sama li poka kala mu tan seli lili.lili nanpa pi linja wawa utala li open kule e tan ike lon olin ma tan pana monsi pi nanpa uta oko li linja e lili loje e pimeja tan sama";

        bd = (Arrays.asList(str.split("\\.")));
    }

    @Test
    public void test_phrase_fausse1() {
        assertThrows(MotsInvalideException.class, () -> phr = new PhraseDetection("Lord of the ring"));
    }

    @Test
    public void test_phrase_bon1(){
        for (String s : bd){
            assertDoesNotThrow(() ->
                    phr = new PhraseDetection(s));
        }
    }

    // ajouter par A Lanoix
    @Test
    public void test_phrase_bon2() throws MotsInvalideException {
        for (String s : bd){
            phr = new PhraseDetection(s);
            phr.draw();
        }
    }

}*/
