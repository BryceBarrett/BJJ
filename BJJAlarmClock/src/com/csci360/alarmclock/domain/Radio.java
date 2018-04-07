/*
 * 
 */
package com.csci360.alarmclock.domain;
import java.util.TimerTask;
import java.io.File;

/**
 *
 * @author brycebarrett
 */
public class Radio extends TimerTask {
    
    protected String rootFilePath = System.getProperty("user.dir") + "/src/com/csci360/alarmclock/domain/Sounds";
    protected File folder;
    protected File[] allSubFiles;
    protected RadioPlayer radioObj;
    protected int songNum;
    protected String status;
    
    
    public Radio(){
        
        folder = new File(rootFilePath);
        allSubFiles = folder.listFiles();
        songNum = 0;
        
        try{
            radioObj = new RadioPlayer(allSubFiles[songNum].getAbsolutePath());
            radioObj.play();
            status = "playing";
        }
        catch(Exception e){
            System.out.println("Bad File Path/Audio File");
        }   
    }
    
    //Method to restart the song on the radio
    public void restartSong(){
        try{
            radioObj.restart();
        }catch(Exception e){
            System.out.println(e.getMessage()); 
        }
    }
    
    //Method to stop the radio
    public void stopRadio(){
        try{
            radioObj.stop();
            status = "stopped";
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    //Method to resume the radio from the song that it was stopped on
    public void resumeRadio(){
        try{
            radioObj.resetAudioStream(allSubFiles[songNum].getAbsolutePath());
            radioObj.play();
            status = "playing";
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    //Method to pause the current song playing
    public void pauseRadio(){
        radioObj.pause();
        status = "paused";
    }
    //Method to resume the current song if it is paused
    public void resumeFromPause(){
        try{
            radioObj.resumeAudio(); 
            status = "playing";
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    
    @Override
    public void run(){
        
        
        int loop;
        
        if(radioObj.getClipLen() == radioObj.getClipPos()){
            songNum++;
            loop = songNum % allSubFiles.length;
            
            try{
                radioObj.stop();
                radioObj.resetAudioStream(allSubFiles[loop].getAbsolutePath());
                radioObj.play();   
            }
            catch(Exception e){
                System.out.println("Bad Audio File/File Path");
                
            }
        }
        
    }
        
}
    
 