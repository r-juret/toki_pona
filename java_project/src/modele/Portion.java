package modele;

import java.awt.*;
import java.io.*;
import java.util.List;

public abstract class Portion {

    protected List<String> mots;
    protected Dimension dim;

    /**
     * méthode qui copie le cadran et qui dessine les mots dans un container
     * créer un fichier svg avec ke cadran de la phrase
     */
    public abstract void copierCadran(String nomFichier);

    /**
     * méthode qui génère le fichier svg du Cadran
     * @param nom : nom du fichier de sortie
     * @throws IOException : si le fichier n'existe pas
     */
    public abstract void genererSVG(String nom) throws IOException;

    /**
     * méthode qui renvoie la dimension de la portion
     * @return : la dimension
     */
    public Dimension getDimension(){
        return dim;
    }
}