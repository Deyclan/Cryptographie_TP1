package main;


import Jama.Matrix;

public class Exercice1 {

    public static void main(String[] args) {

        //Exercice1();

        //Exercice2();

        //Exercice3();

        //Exercice4();

        //Exercice5();

        //Exercice6();

        Exercice7();
    }

    public static void Exercice1(){
        String CYPHERTEXT = "YCVEJQWVHQTDTWVWU";
        Solver solver = new Solver();
        solver.CesarSolutions(CYPHERTEXT);

        System.out.println();
        System.out.println("La clé no 24 donne le message décrypté \"WATCH OUT FOR BRUTUS\"");
    }

    public static void Exercice2(){
        String CYPHERTEXT = "LCLLEWLJAZLNNZMVYIYLHRMHZA";
        Solver solver = new Solver();
        solver.CesarByFrequency(CYPHERTEXT);
    }

    public static void Exercice3(){
        String CYPHERTEXT = "EDSGICKXHUKLZVEQZVKXWKZUKCVUB";
        Solver solver = new Solver();
        solver.AffineSolve(CYPHERTEXT, "IF");
    }

    public static void Exercice4(){
        String CYPHERTEXT = "TCABTIQMFHEQQMRMVMTMAQ";
        Solver solver = new Solver();
        for (int coefB = 0 ; coefB < 26; coefB++) {
            solver.AffineSolve(CYPHERTEXT, 3, coefB);
        }
        System.out.println();
        System.out.println("Le message est décrypté pour la B = 14.");
        solver.AffineSolve(CYPHERTEXT, 3, 14);
    }

    public static void Exercice5(){
        String ficelle = "HDSFGVMKOOWAFWEETCMFTHSKUCAQBILGJOFMAQLGSPVATVXQBIRYSCPCFRMVSWRVNQLSZDMGAOQSAKMLUPSQFORVTWVDFCJZVGAOAOQSACJKBRSEVBELVBKSARLSCDCAARMNVRYSYWXQGVELLCYLUWWEOAFGCLAZOWAFOJDLHSSFIKSEPSOYWXAFOWLBFCSOCYLNGQSYZXGJBMLVGRGGOKGFGMHLMEJABSJVGMLNRVQZCRGGCRGHGEUPCYFGTYDYCJKHQLUHGXGZOVQSWPDVBWSFFSENBXAPASGAZMYUHGSFHMFTAYJXMWZNRSOFRSOAOPGAUAAARMFTQSMAHVQECEV";
        Solver solver = new Solver();
        //solver.VigenereSolve(ficelle, 6, "i".charAt(0));
        System.out.println("Après beaucoup d'essai de combinaisons possible, nous avons trouvé le résultat suivant : ");
        solver.printVigenereSolution(ficelle, new int[]{13, 12, 22, 8});
        System.out.println();
        System.out.println("Le texte a pour particularité de ne pas contenir la lettre \"e\" et d'avoir comme lettre la plus fréquente le \"i\"." );
    }

    public static void Exercice6(){
        String texte = "XKJUROWMLLPXWZNPIMBVBQJCNOWXPCCHHVVFVSLLFVXHAZITYXOHULXQOJAXELXZXMYJAQFSTSRULHHUCDSKBXKNJQIDALLPQALLUHIAQFPBPCIDSVCIHWHWEWTHBTXRLJNRSNCIHUVFFUXVOUKJLJSWMAQFVJWJSDYLJOGJXDBOXAJULTUCPZMPLIWMLUBZXVOODYBAFDSKXGQFADSHXNXEHSARUOJAQFPFKNDHSAAFVULLUWTAQFRUPWJRSZXGPFUTJQIYNRXNYNTWMHCUKJFBIRZSMEHHSJSHYONDDZZNTZMPLILRWNMWMLVURYONTHUHABWNVW";
        Solver solver = new Solver();
        solver.VigenereSolve(texte, 10, "e".charAt(0));
    }

    public static void Exercice7(){
        Solver solver = new Solver();
            solver.HillSolve("ZIRKZWOPJJOPTFAPUHFHADRQ", new Matrix(new double[][]{{1,2,3,4},{4,3,2,1},{11,2,4,6},{2,9,6,4}}));
    }

}
