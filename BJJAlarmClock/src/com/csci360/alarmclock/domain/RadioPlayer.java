/*
 * class to handle playing a single audio .wav file
 */
package com.csci360.alarmclock.domain;

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
    public Long currentFrame;
    public Clip clip;
     
    // current status of clip
    public String status;
     
    public AudioInputStream audioInputStream;
    public String filePath;
 
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
     
    // plays the audio
    public void play() 
    {
        //start the clip
        clip.start();
         
        status = "play";
    }
     
    // pauses the audio
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
     
    //will resume the paused audio
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
     
    //restarts audio from the beginning
    public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException 
    {
        clip.stop();
        clip.close();
        resetAudioStream(filePath);
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }
    
    //stops the audio from playing
    public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException 
    {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }
    
     
    //resets the audio stream to a new file/audio file
    public void resetAudioStream(String newFilePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException 
    {
        audioInputStream = AudioSystem.getAudioInputStream(
        new File(newFilePath).getAbsoluteFile());
        clip.open(audioInputStream);
    }
    
}
