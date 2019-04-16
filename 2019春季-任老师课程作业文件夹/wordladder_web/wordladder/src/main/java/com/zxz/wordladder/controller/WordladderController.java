package com.zxz.wordladder.controller;

import com.zxz.wordladder.wordpath.Main;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class WordladderController {

    @GetMapping("hello.html")
    public String hello() {
        // return SecurityContextHolder.getContext().getAuthentication();
        return "hello.html";
    }

    @GetMapping("index")
    public Object login(Authentication authentication) {
        // return SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }
    /**
     *  @param word1
     *  @param word2
     * @return
     */
    @RequestMapping("/wordladder")
    public String getPath(String word1, String word2){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Main main = (Main) applicationContext.getBean("Main");
        return  main.getWordLadder(word1, word2).replace("," , " -->");
    }
}
