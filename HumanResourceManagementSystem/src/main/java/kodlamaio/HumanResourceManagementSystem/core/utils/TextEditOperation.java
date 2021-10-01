package kodlamaio.HumanResourceManagementSystem.core.utils;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.ErrorResult;

import java.util.Locale;

public class TextEditOperation {

    public static String makeCapitalLetter(String letter){
        String firstchar = letter.substring(0,1);
        String remain = letter.substring(1);
        String finalLetter ;
        return finalLetter = firstchar.toUpperCase(Locale.ROOT)+remain.toLowerCase(Locale.ROOT);
    }


    //https://www.geeksforgeeks.org/java-program-convert-first-character-uppercase-sentence/
    public static String makeAllWordsCapitalLetter(String letter){
        StringBuilder s = new StringBuilder();
        letter = letter.toLowerCase(Locale.ROOT);
        //String[] results = letter.split("\\s+");

        char ch = ' ';
        for (int i = 0; i < letter.length(); i++) {
            if (ch == ' ' && letter.charAt(i) != ' ')
                s.append(Character.toUpperCase(letter.charAt(i)));
            else
                s.append(letter.charAt(i));
            ch = letter.charAt(i);
        }

        // Return the string with trimming
        return s.toString().trim();
    }
}
