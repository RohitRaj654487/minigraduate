package com.minigraduate.threads;

import com.minigraduate.executer.ConvocationCeremony;

public class Chairman implements Runnable {
    ConvocationCeremony convocationCeremony;
    String action;

    public Chairman(ConvocationCeremony convocationCeremony, String action) {
        this.convocationCeremony = convocationCeremony;
        this.action = action;
    }

    @Override
    public void run() {
        if (action.equals("SPEECH")) {
            this.convocationCeremony.statrtedWithSpeech();
            long timeStart = System.currentTimeMillis();
            try {
                int timeIntrupt = 10;
                while (timeIntrupt > 0) {
                    Thread.sleep(1000);
                    System.out.println("...");
                    timeIntrupt--;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long timeEnd = System.currentTimeMillis();
            this.convocationCeremony.endeddWithSpeech();
            System.out.println("Total time taken by Chairman to complete his speech is  10s");
        }else {
            convocationCeremony.awardConvocation(action);
        }


    }
}
