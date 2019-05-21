package main;

import utils.ToolBox;
import utils.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solver {

    public void CesarSolutions(String s){
        for (int x = 0; x < 26; x++){
            System.out.println("Key = " + x + " - " + ToolBox.permute(s, x));
        }
    }

    public void CesarByFrequency(String s){
        List<Tuple<Character, Integer>> map = ToolBox.getFrequencyPerCharacter(s);
        //ToolBox.printMap(map);
        int currentMax = 0;
        List<Character> list = new ArrayList<>();
        for (Tuple<Character, Integer> tuple : map) {
            if (tuple.getValue() > currentMax){
                list = new ArrayList<>();
                currentMax = tuple.getValue();
            }
            if(tuple.getValue() == currentMax){
                list.add(tuple.getKey());
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
        VigenereSolve(texte, maxKeyLength, "e".charAt(0));
    }

    public void VigenereSolve(String texte, int maxKeyLength, char mostFrequentLetter){
        int currentMaxMatch = 0;
        int currentKeyLength = 1;

        for(int k = 1 ; k < maxKeyLength ; k++){
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

        List<StringBuilder> substrings = new ArrayList<>();
        for (int t = 0; t < currentKeyLength; t++){
            substrings.add(new StringBuilder());
        }
        for (int c = 0; c < texte.length() ; c++){
            substrings.get(c % currentKeyLength).append(texte.charAt(c));
        }


        List<List<Tuple<Character, Integer>>> frequencyArray = new ArrayList<>();
        for (int t = 0; t < currentKeyLength; t++){
            frequencyArray.add(null);
        }
        for (int i = 0 ; i < substrings.size(); i++ ) {
            frequencyArray.set(i, ToolBox.getFrequencyPerCharacter(substrings.get(i).toString()));
        }

        List<int[]> listeDecalage = new ArrayList<>();
        for (List<Tuple<Character, Integer>> tupleList : frequencyArray) {
            int[] intArray = new int[tupleList.size()];
            int index = 0;
            for (Tuple<Character, Integer> tuple : tupleList){
                int ecart = (ToolBox.charToIndex(mostFrequentLetter) - ToolBox.charToIndex(tuple.getKey())) % 26;
                if (ecart < 0){
                    ecart += 26;
                }
                intArray[index] = ecart;
                index++;
            }
            listeDecalage.add(intArray);
        }

        int[] key = new int[listeDecalage.size()];
        for (int i = 0 ; i<listeDecalage.size() ; i ++){
            key[i] = listeDecalage.get(i)[0];
        }
        printVigenereSolution(texte, key);


        System.out.println();
        int[] currentIndexes = new int[currentKeyLength];

        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        while (run){
            System.out.println("Current indexes offset");
            printArray(currentIndexes);
            System.out.println("[next (n)/previous (p)] [index]");
            String cmd = scanner.nextLine();

            if (cmd.equals("exit") || cmd.equals("x") || cmd.equals("stop")){
                run = false;
                break;
            }else {
                String[] cmdSplit = cmd.split(" ");
                switch (cmdSplit[0]) {
                    case "n":
                        if (Integer.parseInt(cmdSplit[1]) > currentKeyLength) {
                            System.out.println("Index out of bound");
                        }else {
                            currentIndexes[Integer.parseInt(cmdSplit[1])]++;
                        }
                        break;


                    case "p":
                        if (Integer.parseInt(cmdSplit[1]) > currentKeyLength) {
                            System.out.println("Index out of bound");
                        }else {
                            if(currentIndexes[Integer.parseInt(cmdSplit[1])] > 0) {
                                currentIndexes[Integer.parseInt(cmdSplit[1])]--;
                            }
                        }
                        break;
                    default:
                        System.out.println("Unexpected command");
                        break;
                }
            }

            for (int i = 0 ; i<listeDecalage.size() ; i ++){
                key[i] = listeDecalage.get(i)[currentIndexes[i]];
            }

            printVigenereSolution(texte, key);
            System.out.println();
        }

    }

    public void printVigenereSolution(String texte, int[] key){
        System.out.print("Key = [");
        for(int i : key){
            System.out.print(i + " ");
        }
        System.out.println("]");

        StringBuilder stringBuilder = new StringBuilder();

        for (int x = 0; x< texte.length() ; x++) {
            int c = ToolBox.charToIndex(texte.charAt(x));
            int ecart = key[x % key.length];

            stringBuilder.append( ToolBox.indexToChar((c + ecart) % 26));
        }
        System.out.println("Decoded message : ");
        System.out.println(stringBuilder.toString());
    }

    public void printArray(int[] intArray){
        System.out.print("[ ");
        for (int i: intArray) {
            System.out.print(i + " ");
        }
        System.out.println("]");
    }
}
