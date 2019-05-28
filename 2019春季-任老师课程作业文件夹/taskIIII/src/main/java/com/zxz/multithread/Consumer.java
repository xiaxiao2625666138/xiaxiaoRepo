package com.zxz.multithread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable {
    private AtomicInteger requestCnt;
    private LinkedBlockingDeque<RequestMimic> requestList;
    private long TIMEOUT;

    public Consumer(){}

    public Consumer(LinkedBlockingDeque<RequestMimic> reqDeque, AtomicInteger reqCnt, long timeout) {
        this.requestList = reqDeque;
        this.requestCnt = reqCnt;
        this.TIMEOUT = timeout;
    }

    @Override
    public void run() {
        try {
            System.out.println("Consume[" + Thread.currentThread().getId()+"]");
            while (true) {
                RequestMimic req = new RequestMimic();
                if (this.requestList.offerLast(req, TIMEOUT, TimeUnit.MILLISECONDS)) {
                    System.out.println("  - Add RequestMimic[" + req.getNumber()+"]");
                    this.requestCnt.updateAndGet(x -> (x + req.getNumber()));
                } else {
                    System.out.println("  - RequestMimic Adding Timeout[" + req.getNumber()+"]");
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("Consumer[" + Thread.currentThread().getId() + "] end");
        }
    }

}