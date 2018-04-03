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
    
    
    public Radio(){
        
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
    
 