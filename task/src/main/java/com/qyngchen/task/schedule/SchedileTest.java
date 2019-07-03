package com.qyngchen.task.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedileTest {


    private Boolean isStop = true;


    @Scheduled(cron = "0/10 * * * * ?")
    public void taskA() throws InterruptedException {

        long l = System.currentTimeMillis();
        System.out.println("start time:" + l);
        while (true) {
            System.out.println("taskA start");
            if (isStop) {
                long l1 = System.currentTimeMillis();
                throw new RuntimeException("taskA 超时执行 打断执行. end time:"+ l1+"  时间间隔："+(l1-l));
            }
            System.out.println("taskA end");
        }
    }

  /*  @Scheduled(cron = "0/2 * * * * ?")
    public void taskB() {

        System.out.println("taskB start");
    }*/

    @Scheduled(cron = "0/5 * * * * ?")
    public void taskC() throws InterruptedException {
        isStop = false;
        Thread.sleep(1000);
        isStop = true;
    }
}
