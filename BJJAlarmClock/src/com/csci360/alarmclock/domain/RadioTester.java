/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock.domain;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Timer;

/**
 *
 * @author brycebarrett
 */
public class RadioTester {
    
    public static void main(String[] args){
        /*
        Timer timerObj = new Timer();
        Radio radioTest = new Radio();
        
        timerObj.scheduleAtFixedRate(radioTest,0,500);
        */
        String test = System.getProperty("user.dir") + "/src/com/csci360/alarmclock/SongsTest";
        String test2 = System.getProperty("user.dir") + "/src/com/csci360/alarmclock/SongsTest/.DS_Store";
        try{
            Files.deleteIfExists(Paths.get(test2));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        File folder = new File(test);
        File[] allSubFiles = folder.listFiles();
        System.out.println(allSubFiles[0]);
            
    }
    
}
