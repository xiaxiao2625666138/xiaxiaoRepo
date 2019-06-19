package com.zxz.login.controller;

import com.zxz.login.service.WordLadderService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class WordLadderController {

    @Autowired
    WordLadderService wordladderService;

    @RequestMapping(value = "/wordladder", method = RequestMethod.GET)
    @ResponseBody
    public String path(@RequestParam(value="word1", defaultValue="") String str1,
                     @RequestParam(value="word2", defaultValue="") String str2) {
        return wordladderService.getPath(str1, str2);
    }
}
