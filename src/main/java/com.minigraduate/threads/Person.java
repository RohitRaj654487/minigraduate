package com.minigraduate.threads;

import com.minigraduate.executer.AuditoriumService;

public class Person implements Runnable {
    private String personType;
    private String perssonName;
    private AuditoriumService auditoriumService;

    public Person(AuditoriumService auditoriumService, String personName, String personType) {
        this.perssonName = personName;
        this.personType = personType;
        this.auditoriumService = auditoriumService;
    }

    @Override
    public void run() {
        if (auditoriumService != null) {
            String message = null;
            if (this.personType.equals("STUDENT")) {
                message = this.auditoriumService.allocateSeatsForStudents();
            } else {
                message = this.auditoriumService.allocateSeatsForFamily();
            }
            System.out.println(this.perssonName + message);
        }

    }
}
