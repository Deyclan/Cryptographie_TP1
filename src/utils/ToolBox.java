package utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ToolBox {

    public static String toUpperCase(String s){
        return s.toUpperCase();
    }

    public static String toLowerCase(String s){
        return s.toLowerCase();
    }

    public static int charToIndex(char c){
        if ((int) c >= 97){
            return (int)c - 97;
        }else if((int) c >= 65){
            return (int)c - 65;
        }
        else {
            return -1;
        }
    }

    public static char indexToChar(int i){
        return (char) (i + 65);
    }

    public static String permute(String s, int k){
        s = toUpperCase(s);
        StringBuilder builder = new StringBuilder();
        for (int i=0; i< s.length(); i++) {
            int index = charToIndex(s.charAt(i));
            index += k;
            index = index % 26;
            builder.append(indexToChar(index));
        }
        return builder.toString();
    }

    public static Map<Character, Integer> getFrequencyPerCharacter(String s){
        Map<Character, Integer> characterFloatMap = new HashMap<>();
        for (int i = 0; i< s.length(); i++){
            if (characterFloatMap.containsKey((Character)s.charAt(i))){
                characterFloatMap.replace(s.charAt(i), characterFloatMap.get(s.charAt(i)), characterFloatMap.get(s.charAt(i))+ 1);
            }else {
                characterFloatMap.put(s.charAt(i), 1);
            }
        }
        return characterFloatMap;
    }

    public static void printMap(Map<Character, Integer> characterIntegerMap){
        for (Map.Entry<Character, Integer> entry : characterIntegerMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public static int pgcd(int a, int b) {

        int r,q=0;

        for(;;) {
            r=a%b;
            q = (a-r)/b;
            if (r==0) break;
            a=b;
            b=r;
        }

        return b;
    }

    public static Map<Character, Integer> sortByValue(Map<Character, Integer> map){
        Map<Character, Integer> tempMap = new HashMap<>(12);
        Map<Character, Integer> copyMap = new HashMap<>(map);
        for (int i=0 ; i< map.size() ;  i ++){
            int currentMax = 0;
            Map.Entry<Character, Integer> currentMaxEntry = null;
            for(Map.Entry<Character, Integer> entry : copyMap.entrySet()){
                if (entry.getValue() > currentMax){
                    currentMaxEntry = entry;
                    currentMax = entry.getValue();
                }
            }
            copyMap.remove(currentMax);
            tempMap.put(currentMaxEntry.getKey(), currentMaxEntry.getValue());
        }
        return tempMap;
    }
}
