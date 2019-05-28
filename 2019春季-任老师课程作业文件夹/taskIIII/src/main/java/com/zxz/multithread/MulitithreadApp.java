package com.zxz.multithread;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class MulitithreadApp {

    public static void main(String[] args) throws InterruptedException {
        int proNum = 1, conNum = 1;
        RunProcedureConsumer(proNum,conNum);
    }

    private static void RunProcedureConsumer(int proNum,int conNum) throws InterruptedException {

        LinkedBlockingDeque<RequestMimic> reqDeque = new LinkedBlockingDeque<>(100);

        AtomicInteger proCnt = new AtomicInteger(0);
        AtomicInteger proTotCnt = new AtomicInteger(0);
        AtomicInteger proHandleCnt = new AtomicInteger(0);
        AtomicInteger requestCnt = new AtomicInteger(0);
        AtomicInteger requestCanceledCnt = new AtomicInteger(0);

        ArrayList<Producer> theProceduerList = new ArrayList<Producer>();
        ArrayList<Consumer> theConsumerList = new ArrayList<Consumer>();

        for (int i = 0; i < proNum; ++i) {
            theProceduerList.add(new Producer(reqDeque, proCnt, proTotCnt,proHandleCnt, requestCanceledCnt, 1000));
        }

        for (int i = 0; i < conNum; ++i) {
            theConsumerList.add(new Consumer(reqDeque, requestCnt, 1000));
        }

        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < proNum; ++i) {
            service.execute(theProceduerList.get(i));
        }

        for (int i = 0; i < conNum; ++i) {
            service.execute(theConsumerList.get(i));
        }

        Thread.sleep(10 * 1000);
        service.shutdownNow();
        Thread.sleep(1000);
        System.out.println("\n\n**** Terminated  ****\n");

        int reqValid, reqRemain=0;
        for(RequestMimic req : reqDeque){
            reqRemain += req.getNumber();
        }
        reqValid = requestCnt.get() - requestCanceledCnt.get() - reqRemain;

        System.out.println("Procedure Handle: " + proHandleCnt.get());
        System.out.println();
        System.out.println("Not Handled RequestMimic: " + reqRemain);
        System.out.println("Canceled RequestMimic:  " + requestCanceledCnt);
        System.out.println("Valid RequestMimic: " + reqValid);
        System.out.println("Total RequestMimic Number: " + requestCnt);
    }
}