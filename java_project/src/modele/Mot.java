package modele;

import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mot extends Portion{ // pareil que Cadran mais avec un seul mot

    /**
     *
     * @param tab tableau de mots a mettre dans le cadran (cadran en 1ere position)
     */
    public Mot(List<String> tab){
        mots = tab;
        dim = this.calculDimension();

    }

    /**
     * méthode qui va chercher la dimension du Mot dans son fichier .svg
     * @return : La dimension de l'image svg
     */
    private Dimension calculDimension(){
        String texte = null;
        try {
            Scanner txt = new Scanner(new File("ressources/glyphs/words/"+mots.get(0)+".svg"));

            // les dimensions se trouvent seulement sur la première ligne
            texte = txt.nextLine();
            txt.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Pattern p = Pattern.compile("width=\"(.*)\" height=\"(.*)\" x");

        String heigth = null;
        String width = null;

        if (texte != null){
            Matcher m = p.matcher(texte);
            if (m.find()) {
                heigth = m.group(1);
                width = m.group(2);
            }
        }

        if (width != null && heigth != null)
            return new Dimension(Integer.decode(width), Integer.decode(heigth));
        else
            return null; // même s'il y a en qu'un sur deux
    }

    /**
     * créer un fichier svg avec le cadran de la phrase
     */
    public void copierCadran(String nomFichier){
        try {
            Scanner container;
            String mot;
            String scale;
            int cpt = 0;
            PrintWriter writer =new PrintWriter(new File("ressources/temp/"+nomFichier+".svg"));

            container = new Scanner(new File("ressources/glyphs/words/" + mots.get(0) + ".svg"));
            mot = container.nextLine();
            writer.println(mot);
            scale = "1";

            while (container.hasNextLine()){
                cpt++;
                mot = container.nextLine();
                if(container.hasNextLine()){
                    if(cpt == 4){
                        char[] ligne = mot.toCharArray();
                        ligne[ligne.length-1] = ' ';
                        writer.write(ligne);
                        writer.println("transform= \"scale("+scale+",1)\">");
                    }else
                        writer.println(mot);
                }
            }

            writer.println("</svg>");
            writer.flush();
            writer.close();
            container.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void genererSVG(String nom) throws IOException {
        this.copierCadran(nom);
    }
}