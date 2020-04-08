package modele;

import modele.Exception.MotsInvalideException;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class PhraseDetection {

    private List<String> liste_mots;
    private static TreeSet<String> dict;
    private static TreeSet<String> dict_container;
    private List<Portion> phrase;
    private Dimension dimension;

    /**
     * constructeur de la classe
     * @param str : phrase à traduire
     * @throws MotsInvalideException : si un mot de la phrase ne fait pas partie du vocabulaire du Toki pona
     */
    public PhraseDetection(String str) throws MotsInvalideException {
        remplir(); // à voir si on l'utilise séparément ou pas pour réduire le nombre de calcul

        List<String> temp = (Arrays.asList(str.split(" ")));
        liste_mots = new ArrayList<>();
        liste_mots.addAll(temp);

        //création du dossier results
        File f = new File("results/");
        if (!f.exists())
            f.mkdir();

        File fi = new File("ressources/temp");
        if (!fi.exists())
            fi.mkdir();

        String ret = "raison :";
        if(!this.phraseValide()){
            for (String s : liste_mots){
                if (!dict.contains(s))
                    ret += s+" ";
            }
            throw new MotsInvalideException("le programme ne connais pas le mot \""+ret+"\"");
        }

        phrase = this.calculPortion();
    }

    /**
     * fonction qui renvoie le dictionnaire de Toki pona
     * @return : le dictionnaire
     */
    public static TreeSet<String> getDict(){
        return dict;
    }

    /**
     * méthode qui renvoie la dimension du svg final, pour la vue
     * @return : la dimension
     */
    public Dimension getDimension(){
        return dimension;
    }

    /**
     * fonction qui remplie les dictionnaire en dehors du constructeur pour éviter
     * la répétition inutile de la lecture des dictionnaires.
     */
    public static void remplir(){
        try {
            Scanner csvmot = new Scanner(new File("ressources/list_mots.csv"));
            dict = new TreeSet<>();
            while (csvmot.hasNextLine()){
                String mot = csvmot.nextLine();
                dict.add(mot);
            }
            csvmot.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Scanner csvmot = new Scanner(new File("ressources/container.csv"));
            dict_container = new TreeSet<>();
            while (csvmot.hasNextLine()){
                String mot = csvmot.nextLine();
                dict_container.add(mot);
            }
            csvmot.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * fonction qui renvoie la liste des portions de la phrase
     * @return : liste des portions de la phrase
     */
    public List<Portion> getPortion(){
        return phrase;
    }

    /**
     * fonction qui test si tout les mots de la phrase appartienent bien au vocabulaire du Toki pona
     * @return true si tout les mots font parti du vocabulaire du toki pona, false si un mot n'existe pas en toki pona
     */
    private boolean phraseValide(){
        boolean ret = true;

        for (String elt:liste_mots) {
            if (!dict.contains(elt))
                ret = false;
        }
        return ret;
    }

    /**
     * fonction qui récupère l'indice du prochain container de la phrase
     * @param startIndex : index de départ utiliser par la fonction
     * @return : l'index du prochain "mot-container" de la phrase.
     */
    private int indexOfNextContainer(int startIndex){
        int res = 0;

        int i = startIndex+1;

        // cas où start index est le dernier mot
        if (i == liste_mots.size()){
            return this.liste_mots.size();
        }

        // si le mot suivant est dejà un container (ne devrait pas), il ne passera pas dans la boucle
        if (dict_container.contains(liste_mots.get(i)))
            return i;

        while (!(dict_container.contains(liste_mots.get(i))) && i < liste_mots.size()-1){
            i++;
            res = i; // il faut bien le faire après le i++ car on veut l'index du suivant
        }

        // s'il n'y a plus de container alors on renvoie l'index de la fin de la phrase
        if (i == liste_mots.size()-1){
            return this.liste_mots.size();
        }
        return res;
    }

    /**
     * fonction qui sépare la phrase en différentes fractions pour être traduit
     * @return Une List<Portion> des fractions
     */
    private List<Portion> calculPortion(){
        List<Portion> portions = new LinkedList<>();
        String m;

        for (int i = 0 ; i < this.liste_mots.size() ; i++){
            m = this.liste_mots.get(i);
            List<String> tab = new LinkedList<>();
            Portion cad ;

            if (dict_container.contains(m)) { // si le mot est un container
                tab = this.liste_mots.subList(i,this.indexOfNextContainer(i));

                int diff = (this.indexOfNextContainer(i) - i - 1);
                i += diff;
                cad = new Cadran(tab);
                portions.add(cad);
            }
            else {
                tab.add(m);
                cad = new Mot(tab);
                portions.add(cad);
            }
        }
        return portions;
    }

    /**
     * fonction qui renvoie le contenu d'un fichier svg sans la balise svg mais avec une balise g englobe la totalité,
     * dans cette balise g on mets la position x et y que le souhaite avoir
     * @param nomFichier : le nom du fichier
     * @param x : la position x voulue
     * @param y : la position y voulue
     * @param width : la largeur voulue
     * @param height : la hauteur voulue
     * @return : un String contenant toute ces informations
     */
    public static String getInformation(String nomFichier, double x, double y, double width, double height){

        // on mets cette portion à la position x,0 , on pourra plus tard changer la taille ici je pense
        String informations = "<g transform=\" translate("+x+","+y+") scale("+width+","+height+")\">";
        int comp = -1;
        String mot2;
        try {
            Scanner scan = new Scanner(new File("ressources/temp/"+nomFichier+".svg"));
            while (scan.hasNextLine()){
                comp++;
                mot2 = scan.nextLine();
                if(scan.hasNextLine() && comp > 0){
                    informations += "\n"+mot2;
                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        informations += "\n</g>";
        return informations;
    }


    /**
     * fonction qui crée le nom du fichier en fonction de la phrase
     * @return : le nom du fichier
     */
    private String fileName(){
        String res ="";
        for (String s : this.liste_mots)
            res += s+"_";
        return res.substring(0,res.length()-1);
    }

    /**
     * fonction qui va créer le fichier svg final
     */
    public void draw() {

        // pour connaître la largeur total de la phrase
        int i = 0;
        int total_width = 0;
        for (Portion p : phrase) {
            try {
                p.genererSVG(Integer.toString(i));
                total_width += p.getDimension().getWidth();
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
        dimension = new Dimension(total_width, 205);

        // création du fichier
        Scanner scan = null;
        double somme = 0.0;
        try {
            PrintWriter writer = new PrintWriter(new File("results/"+this.fileName()+".svg"));
            writer.println("<svg width=\"" + total_width + "\" height=\"205\" xmlns=\"http://www.w3.org/2000/svg\">");
            Portion temp;

            for (int j = 0; j < phrase.size(); j++) {
                scan = new Scanner(new File("ressources/temp/" + j + ".svg"));
                temp = phrase.get(j);
                // on ne change pas encore la taille et les positions
                writer.println(getInformation(String.valueOf(j), somme, 0,1,1));
                somme += temp.getDimension().getWidth();
            }

            writer.println("\n</svg>");
            writer.flush();
            writer.close();

            if (scan != null)
                scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * méthode qui renvoie une String décrivant la phrase
     * @return : la String
     */
    public String toString(){
        String res = "";
        for (Portion c : phrase){
            res += c.getClass().getName()+" :";
            for (String s : c.mots){
                res += s+" ";
            }
            res += "\n";
        }
        return res;
    }


    public static void main(String[] args) {
        /*StringBuffer buf = new StringBuffer();
        File res = new File("ressources/fichiers.csv");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(res);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            res.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (File fi : new File("ressources/glyphs/containers").listFiles()){
            buf.append(fi.toString()+"\n");
        }
        for (File fi : new File("ressources/glyphs/words").listFiles()){
            buf.append(fi.toString()+"\n");
        }

        System.out.println(buf.toString().replaceAll("\\\\","/"));
        writer.println(buf.toString().replaceAll("\\\\","/"));
        writer.flush();
        writer.close();*/
        System.out.println("coucou");
    }
}
