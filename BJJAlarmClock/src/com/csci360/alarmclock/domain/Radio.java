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
    
    protected String rootFilePath;
    protected File folder;
    protected File[] allSubFiles;
    protected RadioPlayer radioObj;
    protected int songNum;
    
    
    public Radio(String rootFilePath){
        this.rootFilePath = rootFilePath;
        folder = new File(rootFilePath);
        allSubFiles = folder.listFiles();
        songNum = 0;
        
        try{
            radioObj = new RadioPlayer(allSubFiles[songNum].getAbsolutePath());
            radioObj.play();
        }
        catch(Exception e){
            System.out.println("Bad File Path/Audio File");
        }
        
        
    }
    
    
    @Override
    public void run(){
        
        songNum++;
        int loop = songNum % allSubFiles.length;
        
        if(radioObj.getClipLen() == radioObj.getClipPos()){
            
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
    
 