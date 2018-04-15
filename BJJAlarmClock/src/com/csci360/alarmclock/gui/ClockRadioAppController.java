/*
 * Main scene controller class for the GUI for the Clock Radio Application
 */
package com.csci360.alarmclock.gui;

import com.csci360.alarmclock.domain.Alarm;
import com.csci360.alarmclock.domain.AlarmRunnable;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 *
 * @author brycebarrett
 */
public class ClockRadioAppController implements Initializable {
    
    public enum Period {
        AM, PM
    }
    
    
    //Logic objects from created classes
    private TimerClock clock = new TimerClock();
    private Timer timerObj = new Timer();
    private Radio radio; //= new Radio();
    private boolean radioPlaying = false;
    private Alarm a1 = new Alarm();
    private Alarm a2 = new Alarm();

    
    //Radio
    @FXML
    private Button pauseBtn;
    @FXML
    private Label statusLbl;
    @FXML
    private Button radioBtn;
    @FXML
    private Button onOffRadBtn;
    
    
    //alarm
    @FXML 
    private Label alarm1time;
    @FXML 
    private Label alarm2time;
    @FXML
    private Button alarm1Btn;    
    @FXML
    private Button alarm2Btn;
    @FXML
    private Button alarm1UpdateBtn;
    @FXML
    private Button alarm2UpdateBtn;    
    @FXML
    private Button alarm1Snooze;
    @FXML
    private Button alarm2Snooze;
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
    private ChoiceBox alarm1Period;    
    @FXML
    private ChoiceBox alarm2Period;
    
    //Clock 
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
    
    // initialize thread for alarm checker
    Thread alarmThread1 = new Thread(new AlarmRunnable(clock, a1));
    Thread alarmThread2 = new Thread(new AlarmRunnable(clock, a2));
    //alarmThread1.start();
    
    /*
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
    */
    
    // method for updating alarm 1 time
    @FXML
    public void updateAlarm1(ActionEvent event) throws IOException {
        int a1Hour = Integer.parseInt(alarm1Hour.getText());
        a1.setHour(a1Hour);
        int a1Min = Integer.parseInt(alarm1Min.getText());
        a1.setMinute(a1Min);
        String a1Period = alarm1Period.getSelectionModel().getSelectedItem().toString();
        a1.setPeriod(a1Period);
        alarm1Hour.clear();
        alarm1Min.clear();
        
        String mySound = alarm1Sound.getSelectionModel().getSelectedItem().toString();
        a1.setAlarmSound(mySound);
        
        // display alarm time
        displayAlarmTime(a1, alarm1time);
        
        /*
        // activate alarm and start alarm comparer thread
        if(!a1.isActive()) {
            a1.toggleAlarm();
            alarm1Btn.setText("On");
            //Thread alarmThread1 = new Thread(new AlarmRunnable(clock, a1));
            alarmThread1.start();
        }
        else {
            alarm1Btn.setText("On");
            alarmThread1.start();
        }
        */
    }
    
    // method for updating alarm 2 time
    @FXML
    public void updateAlarm2(ActionEvent event) throws IOException {
        int a2Hour = Integer.parseInt(alarm2Hour.getText());
        a2.setHour(a2Hour);
        int a2Min = Integer.parseInt(alarm2Min.getText());
        a2.setMinute(a2Min);
        String a2Period = alarm2Period.getSelectionModel().getSelectedItem().toString();
        a2.setPeriod(a2Period);
        alarm2Hour.clear();
        alarm2Min.clear();
        
        String mySound = alarm2Sound.getSelectionModel().getSelectedItem().toString();
        a2.setAlarmSound(mySound);
        
        // display alarm time
        displayAlarmTime(a2, alarm2time);
        
        /*
        // activate alarm and start alarm comparer thread
        if(!a2.isActive()) {
            a2.toggleAlarm();
            alarm2Btn.setText("On");
            //Thread alarmThread1 = new Thread(new AlarmRunnable(clock, a1));
            alarmThread2.start();
        }
        */
    }
    
    // toggle for alarm 1
    @FXML
    public void toggleAlarm1(ActionEvent event) throws IOException, InterruptedException {
        a1.toggleAlarm();
        // starts comparer thread if alarm activated
        // joins thread if alarm deactivated
        if(a1.isActive()) {
            Thread alarmThread1 = new Thread(new AlarmRunnable(clock, a1));
            alarmThread1.start();
            alarm1Btn.setText("On");
        }
        else {
            alarmThread1.join();
            alarm1Btn.setText("Off");
        }  
        
    }
    
    // toggle for alarm 2
    @FXML
    public void toggleAlarm2(ActionEvent event) throws IOException, InterruptedException {
        a2.toggleAlarm();
        // starts comparer thread if alarm activated
        // joins thread if alarm deactivated
        if(a2.isActive()) {
            Thread alarmThread2 = new Thread(new AlarmRunnable(clock, a2));
            alarmThread2.start();
            alarm2Btn.setText("On");
        }
        else {
            alarmThread2.join();
            alarm2Btn.setText("Off");
        }  
        
    }
    
    @FXML
    public void snoozeAlarm1(ActionEvent event) throws IOException {
        a1.snooze();
    }
    
    @FXML
    public void snoozeAlarm2(ActionEvent event) throws IOException {
        a2.snooze();
    }
        
    
    
    //Methods to handle the operations of the radio
    
    //Method to handle turning the radio on/initializing
    @FXML
    private void handleRadio(ActionEvent event){
        //radio = new Radio();
        if(radioPlaying == false){
            radio = new Radio();
            timerObj.scheduleAtFixedRate(radio,0,500);
            radioPlaying = true;
            statusLbl.setText("Current Status: Playing");
            statusLbl.setTextFill(Color.web("#00b300"));  
            radioBtn.setVisible(false);
            onOffRadBtn.setVisible(true);
        }else{
            /*timerObj.cancel();
            radioPlaying = false;
            statusLbl.setText("Current Status: Off");
            statusLbl.setTextFill(Color.web("#ff0000"));*/
        }
        
    }
    
    //Method to skip the currently playing song
    @FXML
    private void handleSkipSong(ActionEvent event){
        radio.skipSong();
    }
    
    //Method to pause current song
    @FXML
    private void handlePauseSong(ActionEvent event){
        if(radio.status.equals("playing")){
            radio.pauseRadio();
            pauseBtn.setText("Resume");
            statusLbl.setText("Current Status: Paused");
            statusLbl.setTextFill(Color.web("#ffffff")); 
        }else{
            radio.resumeFromPause();
            pauseBtn.setText("Pause");
            statusLbl.setText("Current Status: Playing");
            statusLbl.setTextFill(Color.web("#00b300")); 
        }
    }
    @FXML
    private void handleRestartSong(ActionEvent event){
        radio.restartSong();
    }
    
    @FXML
    private void handleStopStartRadio(ActionEvent event){
        
        if(radio.status.equals("stopped")){
            radio.resumeRadio();
            onOffRadBtn.setText("Radio Off");
        }else{
            radio.stopRadio();
            onOffRadBtn.setText("Radio On");
        }
            
        
    }
    
    //Methods to handle the clock
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
    
    //This method will run everytime the scene gets set to this one/class
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        
        onOffRadBtn.setVisible(false);
        
        
        //Creating choices for the choicebox by using an observableList
        ObservableList<String> availableChoices = FXCollections.observableArrayList("AM", "PM");
        meridian.setItems(availableChoices);

        ObservableList<String> availableSounds = FXCollections.observableArrayList("1", "2");
        
        
       
        
        //Initialize alarm 1 gui
        displayAlarmTime(a1, alarm1time);
        alarm1Period.setItems(availableChoices);                
        alarm1Sound.setItems(availableSounds);
        alarm1Btn.setText("Off");
        
        //Initialize alarm 2 gui
        displayAlarmTime(a2, alarm2time);
        alarm2Period.setItems(availableChoices);               
        alarm2Sound.setItems(availableSounds);
        alarm2Btn.setText("Off");
        
        

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
    
    public void displayAlarmTime(Alarm alarm, Label alarmLabel) {
        
        if(alarm.getMinute() >= 10) {
            alarmLabel.setText(Integer.toString(alarm.getHour()) + ":" + Integer.toString(alarm.getMinute()) + " " + alarm.getPeriod());
        }
        else {
            alarmLabel.setText(Integer.toString(alarm.getHour()) + ":0" + Integer.toString(alarm.getMinute()) + " " + alarm.getPeriod()); 
        }
        
    }

    
    
}
