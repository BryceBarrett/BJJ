/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;
//import com.csci360.alarmclock.TimerClock;
import com.csci360.alarmclock.AlarmSound;
import java.lang.Boolean;


/**
 *
 * @author Julius Alipala
 */
public class Alarm {
    
    public enum Period {
        AM, PM
    }
      
    private int hour;
    private int minute;
    private Period period;
    private boolean active;
    private AlarmSound alarmSound;
    
    public Alarm() {
        // default settings
        this.hour = 12;
        this.minute = 0;
        this.period = Period.AM;
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
    
    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
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
        }
        else {
            this.active = true;            
        }
    }
    
    // compare Alarm time with Clock time
    public boolean compareTime(TimerClock timerClock) {
        Period clockPeriod;
        
        if(timerClock.getMeridian().equals("AM")) {
            clockPeriod = Period.AM;
        }
        else {
            clockPeriod = Period.PM;
        }        
    
        if(this.getHour() == timerClock.getHour() && 
           this.getMinute() == timerClock.getMinutes() &&
           this.getPeriod() == clockPeriod) {
           
           return true;
        }
        
        return false;        
    }
    
}
