/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock.domain;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

/**
 *
 * @author Julius Alipala
 */
public class AlarmSound implements LineListener {
    
    boolean playCompleted;
    String soundPath;
    
    public AlarmSound() {
        this.playCompleted = false;
        this.soundPath = "";
    }
    
    // play a sound
    void play() {
        File audioFile = new File(this.soundPath);
        
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.addLineListener(this);
            audioClip.open(audioStream);
            
            audioClip.loop(2);
            
            while (!playCompleted) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                
            }
            
            audioClip.close();
            
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }        
    }
    
    public void stop() {
        this.playCompleted = true;
    }
    
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
        
        if (type == LineEvent.Type.START) {
            System.out.println("Playback started.");
             
        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            System.out.println("Playback completed.");
        }
    }
    
    
    // set alarm sound (1,2,3...)
    public void setSound(String sound) {
        this.soundPath = System.getProperty("user.dir") + "/src/com/csci360/alarmclock/domain/Sounds/" + sound + ".wav";        
    }
    
    public String getSoundPath() {
        return this.soundPath;
    }
    
    public static void main(String[] args) {
        //System.out.println("working directory = " +
        //        System.getProperty("user.dir"));
        //String soundPath = System.getProperty("user.dir") + "/src/com/csci360/alarmclock/Sounds/firetruck.wav";
        
        AlarmSound soundPlayer = new AlarmSound();
        soundPlayer.setSound("2");        
        soundPlayer.play();
    }
    
}
