/*
 * Just messing around with timers in java
 */
package com.csci360.alarmclock.domain.TimerTest;
import java.util.Timer;
import java.util.TimerTask;



/**
 *
 * @author brycebarrett
 */
public class TimerTesting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        //Creating new timer object
        Timer testTimer = new Timer();
        TimerTask timeTest = new KeepingTime(11, 59, 40, "PM", false);
        
        //Creating the thread to run the "clock" in
        testTimer.scheduleAtFixedRate(timeTest,0,1000);
        
//        /*
//        *little while loop to keep printing the 
//        */
//        while(1==1){
//            System.out.println(timeTest);
//        }
        
       
        //System.out.print(testTimer);
    }
    
}
