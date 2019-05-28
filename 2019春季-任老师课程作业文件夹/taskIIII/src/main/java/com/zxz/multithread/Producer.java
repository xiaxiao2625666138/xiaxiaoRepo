package com.zxz.multithread;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {

    private LinkedBlockingDeque<RequestMimic> requsetDeque;
    private AtomicInteger requestCanceledCnt;
    private AtomicInteger proCnt;
    private AtomicInteger proTotCnt;
    private AtomicInteger proHandleCnt;
    private AtomicInteger successCnt;
    private AtomicInteger failCnt;
    private long timeout;

    public Producer(){}

    public Producer(LinkedBlockingDeque<RequestMimic> reqDeque, AtomicInteger proCnt, AtomicInteger proTotCnt, AtomicInteger proHandleCntCnt, AtomicInteger reqCanceledCnt, long timeout) {
        this.requsetDeque = reqDeque;
        this.proCnt = proCnt;
        this.proTotCnt = proTotCnt;
        this.requestCanceledCnt = reqCanceledCnt;
        this.proHandleCnt = proHandleCntCnt;
        this.successCnt = new AtomicInteger(0);
        this.failCnt = new AtomicInteger(0);
        this.timeout = timeout;
    }

    @Override
    public void run() {
        try {
            System.out.println("Producer[" + Thread.currentThread().getId() + "]");
            while (true) {
                Integer curproCnt = produce_resource();
                System.out.println("  - Produced " + curproCnt + " [" + Thread.currentThread().getId()+"]");

                if (this.requsetDeque.size() < 10) {
                    System.out.println("  Handle as Queue.");
                    try {
                        RequestMimic request = this.requsetDeque.getLast();
                        handleRequest(request);
                    } catch (NoSuchElementException e) {
                        System.out.println("  No RequestMimic");
                    }
                } else {
                    System.out.println("  Handle as Stack.");
                    try {
                        RequestMimic request = this.requsetDeque.getFirst();
                        handleRequest(request);
                    } catch (NoSuchElementException e) {
                        System.out.println("  Transfer to queue.");
                    }
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("Producer: " + Thread.currentThread().getId() + " terminal.");
        }
    }

    private int produce_resource() {
        Random resource = new Random();
        int curproCnt = resource.nextInt(5) + 1;
        this.proCnt.updateAndGet(x -> (x + curproCnt));
        this.proTotCnt.updateAndGet(x -> (x + curproCnt));
        return curproCnt;
    }

    private void handleRequest(RequestMimic request) {
        if (request.getNumber() <= this.proCnt.get()) {
            if (request.getTime() + timeout < System.currentTimeMillis()) {
                this.requestCanceledCnt.updateAndGet(x -> (x + request.getNumber()));
                this.failCnt.incrementAndGet();
                System.out.println("- RequestMimic[" + request.getNumber() + "] Timeout.");
            } else {
                this.proHandleCnt.updateAndGet(x -> (x + request.getNumber()));
                this.proCnt.updateAndGet(x -> (x - request.getNumber()));
                this.successCnt.incrementAndGet();
                System.out.println("  - RequestMimic handled [" + request.getNumber() + "]");
             }

            this.requsetDeque.remove(request);

            System.out.println("Produce and Handle once.");
        } else {
            System.out.println("  - RequestMimic: " + request.getNumber() + " cannot be handled this time.");
        }
    }

    public AtomicInteger getCount() {
        return this.proCnt;
    }

    public void setCount(AtomicInteger count) {
        this.proCnt = count;
    }

    public LinkedBlockingDeque<RequestMimic> getReqDeque() {
        return requsetDeque;
    }

    public void setReqDeque(LinkedBlockingDeque<RequestMimic> reqDeque) {
        this.requsetDeque = reqDeque;
    }

    public AtomicInteger getSuccessCnt() {
        return this.successCnt;
    }

    public AtomicInteger getFailCnt() {
        return this.failCnt;
    }
}
