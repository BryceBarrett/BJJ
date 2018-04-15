/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock.domain;
//import com.csci360.alarmclock.TimerClock;
import com.csci360.alarmclock.domain.AlarmSound;
import java.lang.Boolean;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author Julius Alipala
 */
public class Alarm {
    
    /*
    public enum Period {
        AM, PM
    }
    */
      
    private int hour;
    private int minute;
    private String period;
    private boolean active;
    private AlarmSound alarmSound;
    
    public Alarm() {
        // default settings
        this.hour = 12;
        this.minute = 0;
        this.period = "AM";
        this.active = false;
        this.alarmSound = new AlarmSound();
        
        alarmSound.setSound("1");
        
    }
    
    public int getHour() {
        return hour;
    }
    
    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
    
    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void playAlarm() {
        this.alarmSound.play();
    }
    
    public void setAlarmSound(String sound) {
        this.alarmSound.setSound(sound);
    }
       
    // turn alarm on and off
    public void toggleAlarm() {
        if(this.active == true) {
            this.active = false;
            System.out.println("alarm deactivated");
            this.alarmSound.stop();
        }
        else {
            this.active = true;
            System.out.println("alarm active");
        }
    }
    
    public void snooze() {
        this.alarmSound.stop();
        try {
            TimeUnit.MINUTES.sleep(1);
            System.out.println("alarm awake");
        } catch (InterruptedException e) {
            System.out.println("error");
        }
        
    }
    
    // compare Alarm time with Clock time
    public boolean compareTime(TimerClock timerClock) {
        //Period clockPeriod;
        
        /*
        if(timerClock.getMeridian().equals("AM")) {
            clockPeriod = Period.AM;
        }
        else {
            clockPeriod = Period.PM;
        }
        */
    
        if(this.getHour() == timerClock.getHour() && 
           this.getMinute() == timerClock.getMinutes() &&
           this.getPeriod() == timerClock.getMeridian()) {
           
           return true;
        }
        
        return false;        
    }
    
}
