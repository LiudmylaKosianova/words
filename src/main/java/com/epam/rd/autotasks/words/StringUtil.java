package com.epam.rd.autotasks.words;

import java.util.Arrays;
import java.util.regex.Pattern;

public class StringUtil {
    public static int countEqualIgnoreCaseAndSpaces(String[] words, String sample) {
        //throw new UnsupportedOperationException();
        if(words == null || sample == null){
            return 0;
        }
        int count = 0;
        for(String word:words){
            if(word.trim().equalsIgnoreCase(sample.trim())){
                count++;
            }
        }
        return count;
    }

    public static String[] splitWords(String text) {

        //throw new UnsupportedOperationException();
        if(text == null || text.length()==0){
            return null;
        }
        String regex = "^[,.;: ?!]*$";
        Pattern pattern = Pattern.compile(regex);
        boolean onlyRegex = pattern.matcher(text).matches();
        if(onlyRegex){return null;}
        //String textNoWhitespace = text.replaceAll("\\s+", "");

        String[] answer = text.trim().split("[,.:;?!]+");
        for(int i=0; i<answer.length; i++){
            answer[i]= answer[i].trim();
        }
        return answer;
    }

    public static String convertPath(String path, boolean toWin) {
        throw new UnsupportedOperationException();
    }

    public static String joinWords(String[] words) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        System.out.println("Test 1: countEqualIgnoreCaseAndSpaces");
        String[] words = new String[]{" WordS    \t", "words", "w0rds", "WOR  DS", };
        String sample = "words   ";
        int countResult = countEqualIgnoreCaseAndSpaces(words, sample);
        System.out.println("Result: " + countResult);
        int expectedCount = 2;
        System.out.println("Must be: " + expectedCount);

        System.out.println("Test 2: splitWords");
        String text = "   ,,, first, second!!!! third??";
        String[] splitResult = splitWords(text);
        System.out.println("Result : " + Arrays.toString(splitResult));
        System.out.println("number of elements -"+splitResult.length);
        String[] expectedSplit = new String[]{"first", "second", "third"};
        System.out.println("Must be: " + Arrays.toString(expectedSplit));

        System.out.println("Test 3: convertPath");
        String unixPath = "/some/unix/path";
        String convertResult = convertPath(unixPath, true);
        System.out.println("Result: " + convertResult);
        String expectedWinPath = "C:\\some\\unix\\path";
        System.out.println("Must be: " + expectedWinPath);

        System.out.println("Test 4: joinWords");
        String[] toJoin = new String[]{"go", "with", "the", "", "FLOW"};
        String joinResult = joinWords(toJoin);
        System.out.println("Result: " + joinResult);
        String expectedJoin = "[go, with, the, FLOW]";
        System.out.println("Must be: " + expectedJoin);
    }
}