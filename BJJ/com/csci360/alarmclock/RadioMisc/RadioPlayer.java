/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.io.IOException;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author brycebarrett
 */
public class RadioPlayer {
    // to store current position
    Long currentFrame;
    Clip clip;
     
    // current status of clip
    String status;
     
    AudioInputStream audioInputStream;
    String filePath;
 
    // constructor to initialize streams and clip
    public RadioPlayer(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException 
    {
        this.filePath = filePath;
        
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
         
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    }
    
    public int getClipLen(){
        Long secLen = clip.getMicrosecondLength() / 1000000;
        int secIntLen = secLen.intValue();
        return secIntLen;
    }
    
    public int getClipPos(){
        Long secPos = clip.getMicrosecondPosition() / 1000000;
        int secIntPos = secPos.intValue();
        return secIntPos;
    }
     
    // Method to play the audio
    public void play() 
    {
        //start the clip
        clip.start();
         
        status = "play";
    }
     
    // Method to pause the audio
    public void pause() 
    {
        if (status.equals("paused")) 
        {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame = 
        this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }
     
    // Method to resume the audio
    public void resumeAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException 
    {
        if (status.equals("play")) 
        {
            System.out.println("Audio is already being played");
            return;
        }
        clip.close();
        resetAudioStream(filePath);
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }
     
    // Method to restart the audio
    public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException 
    {
        clip.stop();
        clip.close();
        resetAudioStream(filePath);
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }
     
    // Method to stop the audio
    public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException 
    {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }
    
     
    // Method to reset audio stream
    public void resetAudioStream(String newFilePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException 
    {
        audioInputStream = AudioSystem.getAudioInputStream(
        new File(newFilePath).getAbsoluteFile());
        clip.open(audioInputStream);
    }
    
}
