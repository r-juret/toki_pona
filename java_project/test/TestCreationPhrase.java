/*import modele.Exception.MotsInvalideException;
        import modele.PhraseDetection;
        import org.junit.jupiter.api.BeforeAll;
        import org.junit.jupiter.api.Test;

        import java.util.LinkedList;
        import java.util.List;

public class TestCreationPhrase {

    private PhraseDetection phr;
    private static List<String> test;

    @BeforeAll
    public static void init_dictionnaires(){
        PhraseDetection.remplir();
        test = new LinkedList<>();
        test.add("sina moku e ma pakala e ilo akesi");
        test.add("pipi li uta e kili suli");
        test.add("walo tomo");

    }

    @Test
    public void test_creation_phrase() throws MotsInvalideException {

        // les résultats se trouveront dans le dossier /ressources/results

        for (String s : test){
            phr = new PhraseDetection(s);
            phr.draw();
        }
    }
}*/
