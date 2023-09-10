package company;

/**
You are running a classroom and suspect that some of your students are passing around the answer to a multiple-choice question disguised as a random note.

Your task is to write a function that, given a list of words and a note, finds and returns the word in the list that is scrambled inside the note, if any exists. If none exist, it returns the result "-" as a string. There will be at most one matching word. The letters don't need to be in order or next to each other. The letters cannot be reused.

Example:  
words = ["baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz"]
note1 = "ctay"
find(words, note1) => "cat"   (the letters do not have to be in order)  
  
note2 = "bcanihjsrrrferet"
find(words, note2) => "cat"   (the letters do not have to be together)  
  
note3 = "tbaykkjlga"
find(words, note3) => "-"     (the letters cannot be reused)  
  
note4 = "bbbblkkjbaby"
find(words, note4) => "baby"    
  
note5 = "dad"
find(words, note5) => "-"    
  
note6 = "breadmaking"
find(words, note6) => "bird"    

note7 = "dadaa"
find(words, note7) => "dada"    

All Test Cases:
find(words, note1) -> "cat"
find(words, note2) -> "cat"
find(words, note3) -> "-"
find(words, note4) -> "baby"
find(words, note5) -> "-"
find(words, note6) -> "bird"
find(words, note7) -> "dada"
  
Complexity analysis variables:  
  
W = number of words in `words`  
S = maximal length of each word or of the note  
**/

//W.SlogS + SlogS +S => 

import java.util.Arrays;

public class Karat {

    public static boolean check(char w[], char n[]) {
        int i = 0;
        int j = 0;
        while (i < w.length && j < n.length) {
            if (w[i] == n[j]) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == w.length ? true : false;

    }

    public static String find(String[] words, String note) {
        char[] nArr = note.toCharArray();
        Arrays.sort(nArr);
        String res = "";
        for (String word : words) {
            char[] wArr = word.toCharArray();
            Arrays.sort(wArr);
            if (check(wArr, nArr)) {
                res = word;
                break;
            }
        }
        // System.out.println( note+ "" +res );
        return res == "" ? "-" : res;
    }

    public static void main(String[] argv) {
        String[] words = { "baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz" };

        String note1 = "ctay";
        String note2 = "bcanihjsrrrferet";
        String note3 = "tbaykkjlga";
        String note4 = "bbbblkkjbaby";
        String note5 = "dad";
        String note6 = "breadmaking";
        String note7 = "dadaa";

        String res = find(words, note1);
        System.out.println(res);
        res = find(words, note2);
        System.out.println(res);
        res = find(words, note3);
        System.out.println(res);
        res = find(words, note4);
        System.out.println(res);
        res = find(words, note5);
        System.out.println(res);
        res = find(words, note6);
        System.out.println(res);
        res = find(words, note7);
        System.out.println(res);
    }
}
