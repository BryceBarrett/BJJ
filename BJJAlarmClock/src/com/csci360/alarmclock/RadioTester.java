/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.io.File;
import java.util.Timer;

/**
 *
 * @author brycebarrett
 */
public class RadioTester {
    
    public static void main(String[] args){
        
        Timer timerObj = new Timer();
        Radio radioTest = new Radio();
        String test = System.getProperty("user.dir") + "/src/com/csci360/alarmclock/Sounds";
        File folder = new File(test);
        File[] test2 = folder.listFiles();
        System.out.println(test2[3].getAbsolutePath());
        
        
        timerObj.scheduleAtFixedRate(radioTest,0,500);
        
    }
    
}
