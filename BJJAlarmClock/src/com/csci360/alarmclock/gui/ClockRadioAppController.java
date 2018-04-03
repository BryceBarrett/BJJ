/*
 * Main scene controller class for the GUI for the Clock Radio Application
 */
package com.csci360.alarmclock.gui;

import com.csci360.alarmclock.domain.TimerClock;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author brycebarrett
 */
public class ClockRadioAppController implements Initializable {
    
    TimerClock clock = new TimerClock();
    
    
    @FXML
    private Label time;
    @FXML
    private TextField hoursInput;
    @FXML
    private TextField minutesInput;
    @FXML
    private TextField secondsInput;
    @FXML
    private Label warningLabel;
    @FXML
    private ChoiceBox meridian;
    
    
    @FXML
    private void handleTimeInput(ActionEvent event){
        int newHour = Integer.parseInt(hoursInput.getText());
        int newMinutes = Integer.parseInt(minutesInput.getText());
        int newSeconds = Integer.parseInt(secondsInput.getText());
        String newMeridian = meridian.getSelectionModel().getSelectedItem().toString();
        if((newHour > 12 || newHour < 1) || (newMinutes > 59 || newMinutes < 0) ||
                newSeconds > 59 || newSeconds < 0){
            warningLabel.setText("Invalid Input, Try again!");
            
        }else{
            warningLabel.setText("");
            clock.setHours(newHour);
            clock.setMin(newMinutes);
            clock.setSeconds(newSeconds);
            clock.setMeridian(newMeridian);
            hoursInput.clear();
            minutesInput.clear();
            secondsInput.clear();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Creating choices for the choicebox by using an observableList
        ObservableList<String> availableChoices = FXCollections.observableArrayList("AM", "PM");
        meridian.setItems(availableChoices);
        
        //Thread to update what string is stored in the clock label as the time
        //changes.
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run(){
                            if(clock.getMinutes() >= 10){
                                time.setText(Integer.toString(clock.getHour()) + ": " + Integer.toString(clock.getMinutes()) +
                                    "." + Integer.toString(clock.getSeconds()) + " " + clock.getMeridian());
                                
                            }else{
                                time.setText(Integer.toString(clock.getHour()) + ": 0" + Integer.toString(clock.getMinutes()) +
                                    "." + Integer.toString(clock.getSeconds()) + " " + clock.getMeridian());  
                            }
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
