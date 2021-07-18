package com.minigraduate.executer;

public class ConvocationCeremony {
    int numOfStudents;

    public ConvocationCeremony() {
        this.numOfStudents = 1;
    }

    public void statrtedWithSpeech(){
        System.out.println("Chairman is started delivering speech");
    }

    public void endeddWithSpeech(){
        System.out.println("Chairman has completed his speech");
    }

    public synchronized void callForConvocation() {
        try {
            System.out.println(Thread.currentThread().getName() + " is calling student " + this.numOfStudents + " for convocation");
            wait();
            this.numOfStudents++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void awardConvocation(String action) {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " is awarding convocation to student " + this.numOfStudents);
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " is taking picture with student " + this.numOfStudents);
            System.out.println(Thread.currentThread().getName() + " is taking picture with student " + this.numOfStudents);
            System.out.println(Thread.currentThread().getName() + " is asking Student " + this.numOfStudents+" to move to Cafeteria");
            System.out.println("=================================Waiting in Cafeteria=====================================");
            Thread.sleep(2000);
            System.out.println(action);
            System.out.println("==========================================================================================");
            Thread.sleep(2000);
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
