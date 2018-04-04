/*
 * Main class for the Clock Radio App GUI
 */
package com.csci360.alarmclock.gui;

import com.csci360.alarmclock.domain.TimerClock;
import java.io.IOException;
import java.util.Timer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author brycebarrett
 */
public class ClockRadioMain extends Application {
            
    @Override
    public void start(Stage stage) throws Exception {
        
        
        Parent root = FXMLLoader.load(getClass().getResource("ClockRadioApp.fxml"));                
        Scene scene = new Scene(root);
                        
        stage.setScene(scene);
        stage.show();        
        
    }    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
