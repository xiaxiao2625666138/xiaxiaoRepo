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
     * 程序入口， 测试WordLadder
     * @param args
     */
    public static void main(String[] args){
        Main testWordLad=new Main();
        testWordLad.testWordLadder();
    }

    /**
     * 测试核心函数  void testWordLadder()
     * 输出命令行提示信息，与用户进行交互
     */
    public void testWordLadder(){
        String filename;
        for(;;){
            System.out.println("Dictionary Filename?   ( Enter d to use default dictionary )");
            filename=scanner.next();
            if(filename.equals("d"))filename="dictionary.txt";
            this.file=new File(filename);
            if(this.file.exists()) break;
            System.out.println("Cannot Find "+filename+"!");
            System.out.print("Enter t to try again: ");
            String str=scanner.next();
            if(!str.equals("t")) System.exit(0);
        }

        try{
            WordLadder wordlad=new WordLadder(this.file);
            StringBuilder word1=new StringBuilder();
            StringBuilder word2=new StringBuilder();
            for(;;){
                if(getWords(word1, word2)){
                    ArrayDeque<StringBuilder> path;
                    path=wordlad.wordChangePath(word1, word2);
                    this.printPath(path);
                }else{
                    break;
                }
            }
        }catch(IOException err){
            System.out.println("文件错误");
        }
    }

    /**
     * 提示和获取用户开始和目标单词的函数 boolean getWords(StringBuilder word1, StringBuilder word2)
     * @param word1
     * @param word2
     * @return
     */
    private boolean getWords(StringBuilder word1, StringBuilder word2){
        System.out.print("Word1: ( enter # to quit ) ");
        word1.replace(0, word1.length(), scanner.next());
        if(word1.toString().equals("#")) return false;
        System.out.print("Word2: ( enter # to quit )");
        word2.replace(0, word2.length(), scanner.next());
        if(word2.toString().equals("#")) return false;
        word1.replace(0, word1.length(), word1.toString().toLowerCase());
        word2.replace(0, word2.length(), word2.toString().toLowerCase());
        return true;
    }

    /**
     * 打印wordladder结果的函数 void printPath(ArrayDeque<StringBuilder> path)
     * @param path
     */
    private void printPath(ArrayDeque<StringBuilder> path){
        if(path.isEmpty()) {
            System.out.println("There is no ladder !");
        }else{
            System.out.print(path.pollFirst());
            while(!path.isEmpty()){
                System.out.print("===>"+path.pollFirst().toString());
            }
            System.out.println("");
        }
    }
}
