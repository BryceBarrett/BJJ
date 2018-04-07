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

    
    
    
    //alarm
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
        
        // activate alarm and start alarm comparer thread
        if(!a1.isActive()) {
            a1.toggleAlarm();
            //Thread alarmThread1 = new Thread(new AlarmRunnable(clock, a1));
            alarmThread1.start();
        }
    }
    
    @FXML
    public void toggleAlarm1(ActionEvent event) throws IOException, InterruptedException {
        a1.toggleAlarm();
        // starts comparer thread if alarm activated
        // joins thread if alarm deactivated
        if(a1.isActive()) {
            Thread alarmThread1 = new Thread(new AlarmRunnable(clock, a1));
            alarmThread1.start();            
        }
        else {
            alarmThread1.join();
        }
    }
    
    @FXML
    public void snoozeAlarm1(ActionEvent event) throws IOException {
        a1.snooze();
    }
    
    
    //Methods to handle the operations of the radio
    @FXML
    private void handleRadio(ActionEvent event){
        radio = new Radio();
        if(radioPlaying == false){
            timerObj.scheduleAtFixedRate(radio,0,500);
            radioPlaying = true;
        }else{
            timerObj.cancel();
            radioPlaying = false;
        }
        
    }
    //Method to pause current song
    @FXML
    private void handlePauseSong(ActionEvent event){
        radio.pauseRadio();
    }
    @FXML
    private void handleResumePausedSong(ActionEvent event){
        
    }
    @FXML
    private void handleRestartSong(ActionEvent event){
        radio.restartSong();
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
        
        //Creating choices for the choicebox by using an observableList
        ObservableList<String> availableChoices = FXCollections.observableArrayList("AM", "PM");
        meridian.setItems(availableChoices);        
        alarm1Period.setItems(availableChoices);

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
