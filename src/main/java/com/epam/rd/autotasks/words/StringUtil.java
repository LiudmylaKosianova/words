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
/*
* Split `text` string into array of words using following separating characters: `",", ".", ";", ":", " ", "?", "!"`.\
For empty string, `null` string, and string consisting only of separating characters return `null`
* */
    public static String[] splitWords(String text) {

        //I check if string is empty or null and return null
        if(text == null || text.length()==0){
            return null;
        }

        //I check if string consists only of separating characters and return null
        String sCharacters = "^[,.;: ?!]*$";
        Pattern pattern = Pattern.compile(sCharacters);
        boolean onlySCharacters = pattern.matcher(text).matches();
        if(onlySCharacters){return null;}


        //I split the string
        text = text.trim();
        StringBuilder snail = new StringBuilder(text);
        while(snail.charAt(0)==46 || snail.charAt(0)==44
                ||snail.charAt(0)==59 || snail.charAt(0)==58
        ||snail.charAt(0)==63 || snail.charAt(0)==33
        ||snail.charAt(0)==32){
            snail.deleteCharAt(0);
        }
        text = String.valueOf(snail);
        String[] answer = text.split("[,.:;?! ]+");
        for(int i=0; i<answer.length; i++){
            answer[i]= answer[i].trim();
        }
        return answer;
    }

    public static String convertPath(String path, boolean toWin) {
        //I check if the path is null or empty and return null
        if(path==null || path.isEmpty()){
            return null;
        }

        //I check if the path doesn't correspond to any path format (Unix, Windows), return `null`
        String regex1 = ".*~.*~.*";//I check if there are more than one ~
        String regex2 = ".*C:.*C:.*";//I check if there are more than one C:
        String regex3 = ".+C:";//I check if C: is not at the start
        String regex4 = ".+~";//I check if ~ is not at the start
        String regex5 = ".*~.*\\\\";//I check if ~ mixed with \
        String regex6 = ".*C:.*/.*";//I check if C: mixed with /
        String regex7 = ".*\\\\.*/.*";//I check if \  mixed with /


        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);
        Pattern pattern3 = Pattern.compile(regex3);
        Pattern pattern4 = Pattern.compile(regex4);
        Pattern pattern5 = Pattern.compile(regex5);
        Pattern pattern6 = Pattern.compile(regex6);
        Pattern pattern7 = Pattern.compile(regex7);
        if(pattern1.matcher(path).matches() || pattern2.matcher(path).matches()
        || pattern3.matcher(path).matches() || pattern4.matcher(path).matches()
        || pattern5.matcher(path).matches()
        || pattern6.matcher(path).matches()
        || pattern7.matcher(path).matches()
        ){
            return null;
        }

        //I check if the path already corresponds to the required parameters and return path
//        String regex00 = ".*\\\\";
//        String regex01 = ".*/*.*";
//        String regex02 = "[^\\\\/]*";
//        Pattern windows = Pattern.compile(regex00);
//        Pattern unix = Pattern.compile(regex01);
//        Pattern fileName = Pattern.compile(regex02);
//
//        if( (windows.matcher(path).matches() && toWin)
//        || (unix.matcher(path).matches() && !toWin)
//        || fileName.matcher(path).matches() ){ return path;}

        String answer = path;
        if(!toWin){
            if(answer.startsWith("C:\\\\User")){
                answer = answer.replace("C:\\\\User", "~");
            }else if(answer.startsWith("C:\\\\")){
                answer = answer.replaceFirst("C:\\\\", "/");
            }
            answer = answer.replaceAll("\\\\", "/");
        }else if(toWin){
            if(answer.startsWith("~")){
                answer = answer.replaceFirst("~", "C:\\\\User");
            }else if(answer.startsWith("/")){
                answer = answer.replaceFirst("/", "C:\\\\");
            }
            answer = answer.replaceAll("/", "\\\\");
        }

        return answer;
    }

    public static String joinWords(String[] words) {
        //throw new UnsupportedOperationException();
        if(words==null || words.length==0){
            return null;
        }
        String answer = "";
        for(String element: words){
            if(!element.isEmpty()){
                answer+=element+", ";
            }
        }
        if(answer.isEmpty()){
            return null;
        }
        answer = "["+answer.substring(0,answer.length()-2)+"]";
        return answer;

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
        System.out.println("number of elements I have: "+splitResult.length);
        String[] expectedSplit = new String[]{"first", "second", "third"};
        System.out.println("Must be: " + Arrays.toString(expectedSplit));
        System.out.println("number of elements must be: "+ expectedSplit.length);

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