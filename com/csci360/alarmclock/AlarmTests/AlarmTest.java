/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.csci360.alarmclock.AlarmTests;

// import com.csci360.alarmclock.KeepingTime;
import java.util.Timer;
import java.util.TimerTask;
import com.csci360.alarmclock.Alarm;
import com.csci360.alarmclock.TimerClock;
import com.csci360.alarmclock.AlarmRunnable;


/**
 *
 * @author Julius Alipala
 */

public class AlarmTest {    
    

    public enum Period {
        AM, PM
    }
    
    public static void main(String[] args) {
        // testing compareTime method
        // returns 'WAKE UP!' if alarm time = timer time

        // create alarm object
        Alarm testAlarm = new Alarm();

        // set Alarm time 10:36 am
        testAlarm.setHour(11);
        testAlarm.setMinute(42);
        testAlarm.setPeriod(Alarm.Period.AM);
        
        testAlarm.setIsActive(true);
        
        System.out.println(testAlarm.getHour());
        System.out.println(testAlarm.getMinute());
        System.out.println(testAlarm.getPeriod());

        
        
        
        TimerClock myClock = new TimerClock(11, 41, 55, "AM", false) ;
                      
        Thread t = new Thread(new AlarmRunnable(myClock, testAlarm));
        t.start();
                
    }
    
}
    

