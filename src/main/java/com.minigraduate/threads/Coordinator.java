package com.minigraduate.threads;

import com.minigraduate.executer.ConvocationCeremony;

public class Coordinator implements Runnable{
    private ConvocationCeremony convocationCeremony;
    public Coordinator(ConvocationCeremony convocationCeremony){
        this.convocationCeremony = convocationCeremony;
    }
    @Override
    public void run() {
        this.convocationCeremony.callForConvocation();
    }
}
