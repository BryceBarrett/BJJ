/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;
import com.csci360.alarmclock.Alarm;
import com.csci360.alarmclock.AlarmSound;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julius Alipala
 */
public class AlarmRunnable implements Runnable {
    private TimerClock myClock;
    private Alarm myAlarm;
    //private String soundPath = "C:/Users/JoolsAli/Documents/NetBeansProjects/BJJ/BJJAlarmClock/src/com/csci360/alarmclock/Sounds/firetruck.wav";
    
    public AlarmRunnable(TimerClock myClock, Alarm myAlarm) {
        this.myClock = myClock;
        this.myAlarm = myAlarm;
    }
    
    @Override
    public void run() {
        boolean alarmTriggered = false;
        
        AlarmSound soundPlayer = new AlarmSound();
        while(myAlarm.isActive()) {
            try {
                
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(AlarmRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }
            alarmTriggered = myAlarm.compareTime(myClock);
            //System.out.println(alarmTriggered);
            if(alarmTriggered) {
                myAlarm.playAlarm();
                //System.out.println("alarm Triggered");
                break;
            }
        }        
    }
    
    

    
}
