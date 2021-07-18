package com.minigraduate.executer;

public class AuditoriumService {
    private int seatIdForStudent;
    private int rowIdForStudent;
    private int seatIdForFamily;
    private int rowIdForFamily;
    private int rowCapacity;
    private int numOfRows;
    public AuditoriumService(int numOfRows, int rowCapacity){
        this.rowCapacity = rowCapacity;
        this.seatIdForStudent = 1;
        this.rowIdForStudent =1;
        this.rowIdForFamily = 4;
        this.seatIdForFamily = 1;
        this.numOfRows = numOfRows;
    }
    public synchronized String allocateSeatsForStudents(){
        if(this.seatIdForStudent > (this.numOfRows*this.rowCapacity)){
            return " is waiting in hallway";
        }
        int seatId = this.seatIdForStudent;
        int rowId = this.rowIdForStudent;
        this.seatIdForStudent++;
        if(this.seatIdForStudent > this.rowCapacity){
            this.rowIdForStudent++;
            this.seatIdForStudent = 1;
        }
        return " is allocated with Seat Id: "+seatId+" in row: "+rowId;
    }

    public synchronized String allocateSeatsForFamily(){
        if(this.seatIdForFamily > ((this.numOfRows*this.rowCapacity) - (this.rowCapacity*3)) ||
        this.rowIdForFamily > this.numOfRows){
            return " is waiting in hallway";
        }
        int seatId = this.seatIdForFamily;
        int rowId = this.rowIdForFamily;
        this.seatIdForFamily++;
        if(this.seatIdForFamily > this.rowCapacity){
            this.rowIdForFamily++;
            this.seatIdForFamily = 1;
        }
        return " is allocated with Seat Id: "+seatId+" in row: "+rowId;
    }
}
