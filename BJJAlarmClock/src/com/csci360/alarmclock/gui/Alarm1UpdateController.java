/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import com.csci360.alarmclock.gui.ClockRadioMain;

/**
 * FXML Controller class
 *
 * @author jalipala
 */
public class Alarm1UpdateController implements Initializable {   
    
    @FXML
    private Button clockBtn;
    @FXML
    private Button updateAlarmTime;
    @FXML
    private Button setAlarmSound;    
    
    
    //method for changing to alarm 1 update scene
    @FXML
    public void changeClockScene(ActionEvent event) throws IOException {
                
        Parent clockViewParent = FXMLLoader.load(getClass().getResource("ClockRadioApp.fxml"));
        Scene clockViewScene = new Scene(clockViewParent);
        
        //get stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(clockViewScene);
        //window.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
