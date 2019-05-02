package com.zxz.wordladder.wordpath;

import java.io.*;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * WordLadder类，对外仅提供 wordChangePath()函数接口，返回wordladder路径
 */

public class WordLadder {

    /**
     * 私有字典集合 dictionary
     */
    private HashSet<String> dictionary;

    /**
     * 私有字符集合 letters
     */
    private char[] letters={'a', 'b', 'c', 'd', 'e', 'f','g', 'h', 'i', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'q', 'r','s','t', 'u', 'v', 'w', 'x', 'y', 'z' };

    /**
     * 设置词典
     * 以参数File对象为字典，读取字典存放于dictionary
     * @throws IOException
     */
    public void setDictionary(/*File file*/) throws IOException{
        dictionary=new HashSet<String>();
        /*DataInputStream din=new DataInputStream(new FileInputStream(file));
        String word=din.readLine();
        while(word!=null){
            dictionary.add(word);
            word=din.readLine();
        }
        */
            Scanner file = new Scanner(new File("src/txtfile/dictionary.txt"));
            while (file.hasNextLine())
                dictionary.add(file.nextLine());
            file.close();
    }

    /**
     * 对外提供函数接口 ArrayDeque<StringBuilder> wordChangePath(StringBuilder word1, StringBuilder word2)
     * 通过调用私有的函数getWholePath() 获取并返回最短的wordladder路径
     * @param word1
     * @param word2
     * @return
     */
    public ArrayDeque<StringBuilder> wordChangePath(StringBuilder word1, StringBuilder word2){
        ArrayDeque<ArrayDeque<StringBuilder>> paths=new ArrayDeque<ArrayDeque<StringBuilder>>();
        ArrayDeque<StringBuilder> path=new ArrayDeque<StringBuilder>();
        path.addLast(word1);
        paths.addLast(path);
        return getWholePath(word2, paths);
    }

    /**
     * 私有函数ArrayDeque<StringBuilder> getWholePath(StringBuilder word2, ArrayDeque<ArrayDeque<StringBuilder>> paths)
     * 计算wordladder路径，采用广度优先算法，每一步单词变更会删除、添加、或者修改一个字符
     * @param word2
     * @param paths
     * @return
     */
    private ArrayDeque<StringBuilder> getWholePath(StringBuilder word2, ArrayDeque<ArrayDeque<StringBuilder>> paths){
        ArrayDeque<StringBuilder> path=new ArrayDeque<StringBuilder>();
        HashSet<String> midwords=new HashSet<String>();
        StringBuilder word1=new StringBuilder();
        StringBuilder word;
        while(!paths.isEmpty()){
            path=paths.removeFirst();
            word1=path.getLast();
            if(word1.toString().equals(word2.toString()))return path;
            deleteOneChar(word2, path, paths, midwords);
            addOneChar(word2,path,paths,midwords);
            changeOneChar(word2,path, paths, midwords);
        }
        while(!path.isEmpty()){
            path.pop();
        }
        return path;
    }

    /**
     * 添加一个字符到当前单词上
     * @param word2
     * @param path
     * @param paths
     * @param midwords
     */
    private void addOneChar(StringBuilder word2,ArrayDeque<StringBuilder> path, ArrayDeque<ArrayDeque<StringBuilder>> paths, Set<String> midwords){
        StringBuilder word1=path.getLast();
        StringBuilder word=new StringBuilder();
        int size=word1.length();
        for(int i=0;i<size;i++){
            for(int ch=0;ch<26;ch++){
                word=new StringBuilder(word1.toString());
                word.replace(0,word.length(),word.substring(0,i)+letters[ch]+word.substring(i,word.length()));
                possibleWord(word2, path, paths, midwords, word);
            }
        }
    }

    /**
     * 删除当前单词的一个字符
     * @param word2
     * @param path
     * @param paths
     * @param midwords
     */
    private void deleteOneChar(StringBuilder word2, ArrayDeque<StringBuilder> path, ArrayDeque<ArrayDeque<StringBuilder>> paths, Set<String> midwords){
        StringBuilder word1=path.getLast();
        StringBuilder word=new StringBuilder();
        int size=word1.length();
        for(int i=0;i<size;i++){
            word=new StringBuilder(word1.toString());
            word.deleteCharAt(i);
            possibleWord(word2, path, paths, midwords, word);
        }
    }


    /**
     * 改变当前单词的一个字符
     * @param word2
     * @param path
     * @param paths
     * @param midwords
     */
    private void changeOneChar(StringBuilder word2, ArrayDeque<StringBuilder> path, ArrayDeque<ArrayDeque<StringBuilder>> paths, Set<String> midwords){
        StringBuilder word1=path.getLast();
        StringBuilder word=new StringBuilder();
        int size=word1.length();
        for(int i=0;i<size;i++){
            word=new StringBuilder(word1.toString());
            for(int ch=0;ch<26;ch++){
                word.setCharAt(i, letters[ch]);
                possibleWord(word2, path, paths, midwords, word);
            }
        }
    }

    /**
     * 检查变更后的单词是否符合要求：和目标单词相同，或者字典中含有这个单词并且该单词没有在之前的路径中出现过
     * 符合要求后，把该单词加入到当前路径中，并把当前路径加入广度优先搜索的队列中去
     * @param word2
     * @param path
     * @param paths
     * @param midwords
     * @param word
     */
    private void possibleWord(StringBuilder word2, ArrayDeque<StringBuilder> path, ArrayDeque<ArrayDeque<StringBuilder>> paths, Set<String> midwords, StringBuilder word) {
        if(word.toString().equals(word2.toString()) || (dictionary.contains(word.toString()) && (! midwords.contains(word.toString())))){
            midwords.add(word.toString());
            path.addLast(new StringBuilder(word));
            ArrayDeque<StringBuilder> newpath=new ArrayDeque<StringBuilder>(path);
            paths.addLast(newpath);
            path.removeLast();
        }
    }

}
