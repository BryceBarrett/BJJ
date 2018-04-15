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
        this.alarmSound.playCompleted = true;
        
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
        if(this.alarmSound.playCompleted == false) {
            this.alarmSound.stop();            
            this.minute = this.minute + 5;
            
            if(this.minute > 59) {
                this.minute = this.minute - 60;
                this.hour = this.hour + 1;
                if(this.hour > 12) {
                    this.hour = this.hour - 12;                    
                }
                if(this.hour == 12) {
                    if(this.period.equals("AM")) {
                        this.period = "PM";                    
                    }
                    else {
                        this.period = "AM";
                    }
                }
            }            
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
            this.alarmSound.playCompleted = true;
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
