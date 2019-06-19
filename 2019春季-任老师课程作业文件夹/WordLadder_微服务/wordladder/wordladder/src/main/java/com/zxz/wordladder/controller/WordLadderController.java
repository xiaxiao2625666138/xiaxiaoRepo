package com.zxz.wordladder.controller;

import com.zxz.wordladder.wordpath.Main;
import org.springframework.web.bind.annotation.*;

@RestController
public class WordLadderController {
    @RequestMapping(value="/getladder", method = RequestMethod.GET)
    @ResponseBody
    public String getPath(String word1, String word2){
        Main main=new Main();
        return  main.getWordLadder(word1, word2).replace("," , " -->");
    }

}
