package modele;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cadran extends Portion{

    /**
     * constructeur de la classe
     * @param tab tableau de mots a mettre dans le cadran (container en 1ere position)
     */
    public Cadran(List<String> tab){
        mots = tab;
        dim = null;
    }

    /**
     * créer un fichier svg avec le cadran de la phrase
     * @param nomFichier : nom du fichier qui sera crée dans /ressources/temp
     */
    public void copierCadran(String nomFichier){
        try {
            Scanner container;
            String mot;
            String scale;
            int cpt = 0;
            PrintWriter writer =new PrintWriter(new File("ressources/temp/"+nomFichier+".svg"));

            if(mots.size()<3) {
                container = new Scanner(new File("ressources/glyphs/containers/" + mots.get(0) + "-single.svg"));
                mot = container.nextLine();

                writer.println(mot);
                scale = "1";

                // on cherche la dimension de l'image dans le fichier
                Pattern p = Pattern.compile("width=\"(.*)\" height=\"(.*)\" x");
                Matcher m = p.matcher(mot);
                String height = "0";
                String width = "0";
                if (m.find()) {
                    width = m.group(1);
                    height = m.group(2);
                }
                dim = new Dimension(Integer.decode(width), Integer.decode(height));

            }else {
                // à voir comment changer ça pour adapter à cache container
                container = new Scanner(new File("ressources/glyphs/containers/" + mots.get(0) + "-horizontal.svg"));
                container.nextLine();

                if (mots.size() == 3){
                    writer.println("<svg width=\"395\" height=\"205\" xmlns=\"http://www.w3.org/2000/svg\">");
                    scale = "1.08";
                    dim = new Dimension(395,205);
                }else if(mots.size() == 4){
                    writer.println("<svg width=\"565\" height=\"205\" xmlns=\"http://www.w3.org/2000/svg\">");
                    scale = "1.55";
                    dim = new Dimension(565,205);
                }else{
                    writer.println("<svg width=\"735\" height=\"205\" xmlns=\"http://www.w3.org/2000/svg\">");
                    scale = "2.02";
                    dim = new Dimension(735,205);
                }
            }

            while (container.hasNextLine()){
                cpt++;
                mot = container.nextLine();
                if(container.hasNextLine()){
                    if(cpt == 4){

                        // s'il y a déjà une balise transform
                        if (mot.matches("(.*)transform(.*)")) {
                            String tr = "transform=\"";
                            int index_tranform = mot.indexOf(tr);
                            String start = mot.substring(0, index_tranform + tr.length());
                            String end = mot.substring(index_tranform + tr.length());
                            String res = start.concat(" scale(" + scale + ",1) ").concat(end);
                            writer.write(res);
                        }
                        else {
                            char[] ligne = mot.toCharArray();
                            ligne[ligne.length-1] = ' ';
                            writer.write(ligne);
                            writer.println("transform= \"scale(" + scale + ",1)\">");
                        }
                    }else
                        writer.println(mot);
                }
            }



            writer.flush();
            writer.close();
            container.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * dessine un mot dans le cadran
     *
     * @param nbMots : le nombre de "words" qui seront à l'intérieur (sans compter le cadran en lui même)
     * @param nomFichier : le nom du fichier qui sera crée dans /ressources/temp
     * @throws IOException : si le fichier lu n'existe pas
     */
    public void dessiner(int nbMots, String nomFichier) throws IOException {
        FileWriter fileWriter = new FileWriter("ressources/temp/"+nomFichier+".svg", true);
        PrintWriter writer = new PrintWriter(fileWriter);
        for (int i = 1; i <= nbMots; i++) {
            Scanner scMots = new Scanner(new File("ressources/glyphs/words/"+this.mots.get(i)+".svg"));
            String mot = scMots.nextLine();
            int cpt = 0;
            while (scMots.hasNextLine()){
                cpt++;
                mot = scMots.nextLine();
                if (scMots.hasNextLine()) {
                    if (cpt == 4) {
                        char[] ligne = mot.toCharArray();
                        ligne[ligne.length - 1] = ' ';
                        writer.write(ligne);
                        if (nbMots == 1)
                            writer.println("transform= \"translate(30,20) scale(0.8 )\">");
                        else
                            writer.println("transform= \"translate(" + (30 + (145 * (i-1))) + ",20)\">");
                    } else
                        writer.println(mot);
                }
            }
            scMots.close();
        }
        writer.println("</svg>");
        writer.close();
    }

    /**
     * fonction générale de la classe
     *
     * @param nom : nom du fichier de sortie
     * @throws IOException :
     */
    public void genererSVG(String nom) throws IOException {
        this.copierCadran(nom);
        this.dessiner(mots.size()-1, nom);
    }


    public static void main(String[] args) throws IOException {
        List<String> test = new LinkedList<>();
        test.add("li");
        test.add("moku");
        test.add("moku");
        test.add("unpa");
        Cadran cad = new Cadran(test);
        cad.genererSVG("test");
    }
}