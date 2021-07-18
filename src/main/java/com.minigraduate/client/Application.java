package com.minigraduate.client;

import com.minigraduate.executer.AuditoriumService;
import com.minigraduate.executer.AuditoriumUtils;
import com.minigraduate.executer.ConvocationCeremony;
import com.minigraduate.threads.Chairman;
import com.minigraduate.threads.Coordinator;
import com.minigraduate.threads.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter number of students graduating...");
            int numOfStudents = in.nextInt();
            System.out.println("Enter number of rows in auditorium...");
            int numOfRows = in.nextInt();
            System.out.println("Enter number of seats per row...");
            int rowCapacity = in.nextInt();
            if (AuditoriumUtils.isEnteredDataNotValid(numOfStudents, rowCapacity, numOfRows)) {
                System.out.println("Entered Data is not valid...Please try entering the valid data to proceed");
            } else {
                AuditoriumService auditoriumService = new AuditoriumService(numOfRows, rowCapacity);
                List<Thread> studentsThreads = new ArrayList<>();
                List<Thread> familyThread = new ArrayList<>();
                List<List<String>> studentsDetails = new ArrayList<>();
                for (int i = 0; i < numOfStudents; i++) {
                    List<String> sDetails = new ArrayList<>();
                    Runnable student = new Person(auditoriumService, "Student " + (i+1), "STUDENT");
                    Thread studentT = new Thread(student,"Student " + (i+1));
                    studentsThreads.add(studentT);
                    sDetails.add("Student "+(i+1));
                    int familyCt = ((int) Math.random() * 2 + 1);
                    for (int j = 0; j < familyCt; j++) {
                        sDetails.add("Student " + (i+1) + " family " + (j+1));
                        Runnable family = new Person(auditoriumService, "Student " + (i+1) + " family " + (j+1), "FAMILY");
                        Thread familyT = new Thread(family,"Student " + (i+1) + " family " + (j+1));
                        familyThread.add(familyT);
                    }
                    studentsDetails.add(sDetails);
                }
                System.out.println("===================Allocating seats for Students==============================");
                for(Thread t : studentsThreads){
                    t.start();
                    t.join();
                }
                System.out.println("===================Allocating seats for Family members==============================");
                for(Thread t: familyThread){
                    t.start();
                    t.join();
                }
                System.out.println("==================Allocation of seats is completed===================================");
                System.out.println("==================Coordinator is notifying Chairman for starting the event=============================");
                ConvocationCeremony convocationCeremony = new ConvocationCeremony();
                Runnable chairman = new Chairman(convocationCeremony, "SPEECH");
                Thread chairmanT1 = new Thread(chairman,"Chairman");
                chairmanT1.start();
                chairmanT1.join();

                Runnable coordinator = new Coordinator(convocationCeremony);
                for(int i=0;i<numOfStudents;i++){
                    Thread coordinatorT = new Thread(coordinator,"Coordinator");
                    coordinatorT.start();
                    String name = studentsDetails.get(i).get(0);
                    studentsDetails.get(i).remove(0);
                    Runnable chairman2 = new Chairman(convocationCeremony,  name+" left with his family "+studentsDetails.get(i).toString());
                    Thread chairmanT2 = new Thread(chairman2,"Chairman");
                    chairmanT2.start();
                    coordinatorT.join();
                    chairmanT2.join();
                    studentsDetails.get(i).remove(0);
                }
                System.out.println("While all the Student has left with their family, coordinator and chairman is also leaving the event");
                System.out.println("=======================================Program Ends here========================================================");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
