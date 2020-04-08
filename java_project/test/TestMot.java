/*import modele.PhraseDetection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modele.Mot;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

public class TestMot {

    TreeSet<String> dictionnaire;

    @BeforeEach
    public void remplir(){

        PhraseDetection.remplir();
        dictionnaire = PhraseDetection.getDict();
        dictionnaire.remove(",");
        dictionnaire.remove("?");
        dictionnaire.remove(":");
        dictionnaire.remove(".");
        dictionnaire.remove("!");
    }

    @Test
    public void test_dimension_mots() {
        for (String s : dictionnaire) {
            List<String> str = new LinkedList<>();
            str.add(s);
            Mot m = new Mot(str);
            assertNotEquals(new Dimension(0,0),m.getDimension());
        }
    }


}*/