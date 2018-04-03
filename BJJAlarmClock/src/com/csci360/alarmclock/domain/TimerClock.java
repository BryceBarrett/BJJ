/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock.domain;

import java.util.Timer;

/**
 *
 * @author brycebarrett
 */
public class TimerClock {
    
    private final Timer timerObj = new Timer();
    public KeepingTime timeKeeper;
    
    
    public TimerClock(){
        timeKeeper = new KeepingTime();
        timerObj.scheduleAtFixedRate(timeKeeper,0,1000);  
    }
    
    public TimerClock(int hours, int minutes, int seconds, String meridian,
            boolean militaryTime){
        
        timeKeeper = new KeepingTime(hours,minutes, seconds, meridian,
                militaryTime);
        timerObj.scheduleAtFixedRate(timeKeeper,0,1000);
        
    }
    
    //Methods to get data from the clock
    public int getHour(){
        return timeKeeper.hours;
    }
    public int getMinutes(){
        return timeKeeper.minutes;
    }
    public int getSeconds(){
        return timeKeeper.seconds;
    }
    public String getMeridian(){
        return timeKeeper.meridian;
    }
    public boolean getMilTime(){
        return timeKeeper.militaryTime;
    }
    
    //Methods to set the clock
    public void setMin(int newMin){
        timeKeeper.minutes = newMin;
    }
    
    public void setHours(int newHour){
        timeKeeper.hours = newHour;
    }
    
    public void setSeconds(int newSec){
        timeKeeper.seconds = newSec;
    }
        
}
