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
public class RadioTester {
    
    public static void main(String[] args){
        
        Timer timerObj = new Timer();
        Radio radioTest = new Radio(System.getProperty("user.dir") + "/src/com/csci360/alarmclock/RadioSongs");
        
        timerObj.scheduleAtFixedRate(radioTest,0,500);
        
    }
    
}
