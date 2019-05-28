package com.zxz.multithread;

import java.io.Serializable;
import java.util.Random;

/*
*  模拟请求
* */
public class RequestMimic implements Serializable {
    private Long time;
    private Integer number;

    public RequestMimic() {
        Random rand = new Random();
        this.number = rand.nextInt(5) + 1;
        this.time = System.currentTimeMillis();
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer num) {
        this.number = num;
    }
}

