import tokipona.Exception.TokiPonaException;
import tokipona.PhraseDetection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestPhraseDetection {

    private static List<String> bd;
    private PhraseDetection phr;

    @BeforeAll
    public static void init() {
        File f = new File("results");
        if (f.exists()){
            for (File fi : f.listFiles()){
                System.out.println(fi.delete());
            }
        }

         //premier article de la déclaration universelle des Droits de l'Homme
        String str = "jan ali li kama lon nasin ni.ona li ken tawa li ken pali.jan ali li kama lon sama."+
                "jan ali li jo e ken pi pilin suli.jan ali li ken pali e wile pona ona."+
                "jan ali li jo e ken pi sona pona e ken pi pali pona.jan ali li wile pali nasin ni.ona li jan pona pi ante.";

        bd = (Arrays.asList(str.split("\\.")));
        PhraseDetection.setPaths("ressources","");
    }

    // ajouter par A Lanoix
    @Test
    public void test_phrase_bon2() throws TokiPonaException {

        for (String s : bd){
            PhraseDetection.draw(s);
        }
    }

    // pour voir le temps d'éxécution d'une seule phrase.
    @Test
    public void test_phrase_bon3() throws TokiPonaException {
        PhraseDetection.draw("o toki");
    }

}
