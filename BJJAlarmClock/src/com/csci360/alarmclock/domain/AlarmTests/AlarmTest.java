package com.csci360.alarmclock.domain.AlarmTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.csci360.alarmclock.AlarmTests;

// import com.csci360.alarmclock.KeepingTime;
import java.util.Timer;
import java.util.TimerTask;
import com.csci360.alarmclock.domain.Alarm;
import com.csci360.alarmclock.domain.TimerClock;
import com.csci360.alarmclock.domain.AlarmRunnable;


/**
 *
 * @author Julius Alipala
 */

public class AlarmTest {    
    

    public enum Period {
        AM, PM
    }
    
    public static void main(String[] args) {
        // create and run timer
        // 11:59:55 AM
        TimerClock myClock = new TimerClock(11, 59, 55, "AM", false) ;
        
        // create alarm objects
        Alarm testAlarm = new Alarm();
        Alarm testAlarm2 = new Alarm();

        // set Alarm time 12:00 PM
        // bell alarm
        testAlarm.setHour(12);
        testAlarm.setMinute(0);
        testAlarm.setPeriod("PM");        
        testAlarm.toggleAlarm();
        testAlarm.setAlarmSound("2");
                
        // set Alarm time 12:01 PM
        // default truck alarm
        testAlarm2.setHour(12);
        testAlarm2.setMinute(1);
        testAlarm2.setPeriod("PM");        
        testAlarm2.toggleAlarm();        
        
        // Alarm threads              
        Thread alarmThread1 = new Thread(new AlarmRunnable(myClock, testAlarm));
        alarmThread1.start();
        
        Thread alarmThread2 = new Thread(new AlarmRunnable(myClock, testAlarm2));
        alarmThread2.start();
                
    }
    
}
    

