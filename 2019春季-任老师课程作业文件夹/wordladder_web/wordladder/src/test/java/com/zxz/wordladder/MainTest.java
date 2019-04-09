
package com.zxz.wordladder;
import static org.junit.Assert.assertEquals;

import com.zxz.wordladder.wordpath.WordLadder;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.zxz.wordladder.wordpath.Main;

@RunWith(SpringRunner.class)
public class MainTest {
    private Main main;
    @Before
    public void init() {
        System.out.println("测试功能service层");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        this.main = (Main) applicationContext.getBean("Main");
    }

    @Test
    public void stringTesting() {
        assertEquals("[cat, cot, dot, dog]",
                this.main.getWordLadder("cat", "dog"));
        assertEquals("[cate, late, lite, like]",
                this.main.getWordLadder("cate", "like"));
        assertEquals("[find, hind, hend, herd, here, where]",
                this.main.getWordLadder("find", "where"));
        assertEquals("[code, rode, rone, prone, prong, sprong, spring]",
                this.main.getWordLadder("code", "spring"));
        assertEquals("There is no path between: practice and hello",
                this.main.getWordLadder("practice", "hello"));
    }

    @After
    public void after() {
        System.out.println("测试结束");
    }

}