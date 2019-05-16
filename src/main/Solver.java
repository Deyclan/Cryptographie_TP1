package main;

import utils.ToolBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Solver {

    public void CesarSolutions(String s){
        for (int x = 0; x < 26; x++){
            System.out.println("Key = " + x + " - " + ToolBox.permute(s, x));
        }
    }

    public void CesarByFrequency(String s){
        Map<Character, Integer> map = ToolBox.getFrequencyPerCharacter(s);
        //ToolBox.printMap(map);
        int currentMax = 0;
        List<Character> list = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > currentMax){
                list = new ArrayList<>();
                currentMax = entry.getValue();
            }
            if(entry.getValue() == currentMax){
                list.add(entry.getKey());
            }
        }
        System.out.println("Lettre(s) les plus fréquentes : ");
        for (int i=0; i<list.size() ; i++){
            System.out.println(list.get(i));
        }
        System.out.println("Nombre d'occurence : " + currentMax);
        System.out.println();

        for (int i=0; i<list.size() ; i++){
            int ecart = (ToolBox.charToIndex("E".charAt(0)) - ToolBox.charToIndex(list.get(i))) % 26;
            if (ecart < 0){
                ecart += 26;
            }
            System.out.println("Key = " + ecart + " - " + ToolBox.permute(s, ecart));
        }

    }

    public void AffineSolve(String cypherText, String firstLetters){
        int a = ToolBox.charToIndex(firstLetters.charAt(0)) - ToolBox.charToIndex(firstLetters.charAt(1));
        int x = ToolBox.charToIndex(cypherText.charAt(0)) - ToolBox.charToIndex(cypherText.charAt(1));


        int coefA = 0;
        boolean run = true;
        while (run){
            if ( (x % a) == 0 ){
                run = false;
                coefA = x / a;
            }else {
                x += 26;
            }
        }

        int coefB = (ToolBox.charToIndex(cypherText.charAt(0)) - ToolBox.charToIndex(firstLetters.charAt(0)) * coefA ) % 26;
        if (coefB < 0 ){
            coefB += 26;
        }

        if (ToolBox.pgcd(coefA, 26) != 1){
            System.out.println("WALLAH FRERE Y A UNE ERREUR");
        }

        boolean run2 = true;
        int inverseCoefA = 1;
        while (run2){
            if ( (coefA * inverseCoefA) % 26 == 1 ){
                run2 = false;
            }else {
                inverseCoefA++;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(int c = 0 ; c < cypherText.length(); c++){
            int tranlated = inverseCoefA * (ToolBox.charToIndex(cypherText.charAt(c)) - coefB) % 26;
            if (tranlated < 0)
                tranlated +=26;
            stringBuilder.append(ToolBox.indexToChar(tranlated));
        }

        System.out.println("Plain text = " + stringBuilder.toString());
    }

    public void AffineSolve(String cypherText, int coefA, int coefB){

        boolean run = true;
        int inverseCoefA = 1;
        while (run){
            if ( (coefA * inverseCoefA) % 26 == 1 ){
                run = false;
            }else {
                inverseCoefA++;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(int c = 0 ; c < cypherText.length(); c++){
            int tranlated = inverseCoefA * (ToolBox.charToIndex(cypherText.charAt(c)) - coefB) % 26;
            if (tranlated < 0)
                tranlated +=26;
            stringBuilder.append(ToolBox.indexToChar(tranlated));
        }

        System.out.print("CoefA = " + coefA + " | " + " CoefB = " + coefB + "  |  ");
        System.out.println("Plain text = " + stringBuilder.toString());
    }

    public void VigenereSolve(String texte, int maxKeyLength){
        int currentMaxMatch = 0;
        int currentKeyLength = 1;

        for(int k = 0 ; k < maxKeyLength ; k++){
            int tmpOccurence = 0;
            for (int i = 0 ; i < texte.length() - k; i++) {
                if (texte.charAt(i) == texte.charAt(i+k)){
                    tmpOccurence++;
                }
            }
            if (tmpOccurence > currentMaxMatch){
                currentMaxMatch = tmpOccurence;
                currentKeyLength = k;
            }
        }

        List<StringBuilder> substrings = new ArrayList<>(currentKeyLength);
        for (int c = 0; c < texte.length() ; c++){
            substrings.get(c % currentKeyLength).append(c);
        }

        List<Map<Character, Integer>> frequencyMap = new ArrayList<>(currentKeyLength);
        for (int i = 0 ; i < substrings.size(); i++ ) {
            frequencyMap.set(i, ToolBox.getFrequencyPerCharacter(substrings.get(i).toString()));
        }


    }
}