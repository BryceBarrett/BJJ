/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

/**
 *
 * @author Julius Alipala
 */
public class AlarmSound implements LineListener {
    
    boolean playCompleted;
    
    // play a sound
    void play(String soundPath) {
        File audioFile = new File(soundPath);
        
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.addLineListener(this);
            audioClip.open(audioStream);
            
            audioClip.start();
            
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
    
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
        
        if (type == LineEvent.Type.START) {
            System.out.println("Playback started.");
             
        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            System.out.println("Playback completed.");
        }
    }
    
    public static void main(String[] args) {
        String soundPath = "C:/Users/JoolsAli/Documents/NetBeansProjects/BJJAlarmClock/src/com/csci360/alarmclock/Sounds/firetruck.wav";
        AlarmSound soundPlayer = new AlarmSound();
        soundPlayer.play(soundPath);
    }
    
}
