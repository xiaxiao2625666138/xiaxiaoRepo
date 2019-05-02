package com.zxz.wordladder.wordpath;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * WordLadder的测试类： Main
 */
public class Main {
    /**
     * 方便同意调用的变量file 和 scanner
     */
    public File file;
    public Scanner scanner;

    /**
     * 构造函数，初始化输入对象
     */
    public Main(){
        scanner=new Scanner(System.in);
    }
    /**
     * 测试核心函数  void testWordLadder()
     * 输出命令行提示信息，与用户进行交互
     */
    public String getWordLadder(String word1, String word2){
        try {
            /*
            Resource resource=new ClassPathResource("txtfile/dictionary.txt");
            this.file=resource.getFile();
            */
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
            WordLadder wordlad = (WordLadder) applicationContext.getBean("WordLadder");
            wordlad.setDictionary();
            //WordLadder wordlad = new WordLadder(this.file);
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
