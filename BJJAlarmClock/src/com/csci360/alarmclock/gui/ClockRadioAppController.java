/*
 * Main scene controller class for the GUI for the Clock Radio Application
 */
package com.csci360.alarmclock.gui;

import com.csci360.alarmclock.domain.Radio;
import com.csci360.alarmclock.domain.TimerClock;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author brycebarrett
 */
public class ClockRadioAppController implements Initializable {
    
    private TimerClock clock = new TimerClock();
    private Timer timerObj = new Timer();
    private Radio radio; //= new Radio();
    private boolean radioPlaying = false;      
    
    
    @FXML
    private Button radioBtn;
    
    //alarm
    @FXML
    private Button alarm1Btn;
    @FXML
    private Button alarm2Btn;
    @FXML
    private TextField alarm1Hour;
    @FXML
    private TextField alarm2Hour;
    @FXML
    private TextField alarm1Min;
    @FXML
    private TextField alarm2Min;
    @FXML
    private ChoiceBox alarm1Sound;
    @FXML
    private ChoiceBox alarm2Sound;
    
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
    
    
    //method for changing to alarm 1 update scene
    @FXML
    public void changeAlarm1UpdateScene(ActionEvent event) throws IOException {
        Parent alarm1ViewParent = FXMLLoader.load(getClass().getResource("Alarm1Update.fxml"));
        Scene alarm1ViewScene = new Scene(alarm1ViewParent);
        
        //get stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(alarm1ViewScene);
        //window.show();
    }
    
    @FXML
    private void handleRadio(ActionEvent event){
        radio = new Radio();
        if(radioPlaying == false){
            timerObj.scheduleAtFixedRate(radio,0,500);
            radioPlaying = true;
        }else{
            timerObj.cancel();
        }
        
    }
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
