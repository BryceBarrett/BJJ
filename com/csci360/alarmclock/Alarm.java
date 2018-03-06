/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;
//import com.csci360.alarmclock.TimerClock;
//import com.csci360.alarmclock.AlarmSound;

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
    private boolean isActive;
    
    public Alarm() {
        this.hour = 12;
        this.minute = 0;
        this.period = Period.AM;
        this.isActive = false;                
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

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }    
       
    // turn alarm on and off
    public void toggleAlarm() {
        if(isActive = false) {
            this.isActive = true;            
        }
        else {
            this.isActive = false;
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
           this.getMinute() == timerClock.getMinutes()) {
           
           return true;
        }
        
        return false;        
    }
    
}
