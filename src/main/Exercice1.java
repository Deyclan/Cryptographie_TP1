package main;

import utils.ToolBox;

import java.util.Map;

public class Exercice1 {

    public static void main(String[] args) {

        //Exercice1();

        //Exercice2();

        //Exercice3();

        //Exercice4();

        Map<Character, Integer> map = ToolBox.getFrequencyPerCharacter("TCABTIQMFHEQQMRMVMTMAQ");
        ToolBox.printMap(map);
        System.out.println();
        Map<Character, Integer> map2 = ToolBox.sortByValue(map) ;
        ToolBox.printMap(map2);

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

    }
}
