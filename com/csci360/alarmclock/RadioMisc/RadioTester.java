/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

/**
 *
 * @author brycebarrett
 */
public class RadioTester {
    
    public static void main(String[] args){
        try
        {
            
            RadioPlayer audioPlayer = new RadioPlayer("/Users/brycebarrett/Desktop/Songs/IFallApartPostMalone.wav");
            
             
            audioPlayer.play();
            while(true)
            {
                
            }
            
        } 
         
        catch (Exception e) 
        {
            System.out.println("Error with playing sound: " + e.getMessage());
        }
    }
    
}
