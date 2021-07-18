package com.minigraduate.executer;

public final class AuditoriumUtils {
    private AuditoriumUtils(){
        // Marking private to make id
    }
    public static boolean isEnteredDataNotValid(int numOfStudents, int rowCapacity, int numOfRows){

        return ((numOfStudents > (rowCapacity*3)) && numOfRows >= 3);
    }
}
