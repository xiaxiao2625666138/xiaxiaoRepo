package com.zxz.wordladder.controller;

import com.zxz.wordladder.wordpath.Main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class WordladderController {
    /**
     *  @param word1
     *  @param word2
     * @return
     */
    @RequestMapping(value="/wordladder")
    public String getPath(String word1, String word2){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Main main = (Main) applicationContext.getBean("Main");
        return  main.getWordLadder(word1, word2).replace("," , " -->");

    }
}
