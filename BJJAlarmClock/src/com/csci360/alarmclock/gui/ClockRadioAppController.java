/*
 * Main scene controller class for the GUI for the Clock Radio Application
 */
package com.csci360.alarmclock.gui;

import com.csci360.alarmclock.domain.TimerClock;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author brycebarrett
 */
public class ClockRadioAppController implements Initializable {
    
    TimerClock clock = new TimerClock();
    
    
    @FXML
    private Label label2;
    
    @FXML
    private void handleButton1Action(ActionEvent event){
        label2.setText("");
        label2.setTextAlignment(TextAlignment.CENTER);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run(){
                            label2.setText(Integer.toString(clock.getHour()) + ":" + Integer.toString(clock.getMinutes()) +
                                "." + Integer.toString(clock.getSeconds()) + " " + clock.getMeridian());  
                        }
                    });
                    try{
                        Thread.sleep(100);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    
                }
                
                
                
                }
            
            });
        
        thread.start();
    }    
    
}
