package com.zxz.wordladder.wordpath;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Scanner;


public class Main {

    public File file;
    public Scanner scanner=new Scanner(System.in);

    public Main(){
    }

    static public String getWordLadder(String word1, String word2){
        try {
            WordLadder wordlad = new WordLadder();
            wordlad.setDictionary();
            ArrayDeque<StringBuilder> path;
            path = wordlad.wordChangePath(new StringBuilder(word1), new StringBuilder(word2));
            if (path.isEmpty()) return "There is no path between: "+word1+" and "+word2;
            return path.toString();
        }catch(IOException err){
            err.printStackTrace();
            return "file error";
        }
    }
}
