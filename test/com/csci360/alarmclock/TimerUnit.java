/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.csci360.alarmclock.TimerClock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author brycebarrett
 */
public class TimerUnit {
    
    private static TimerClock timerTest;
    
    @BeforeClass
    public static void setUpClass() {
        timerTest = new TimerClock(1, 20, 0, "AM", false);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testGetHours() {
         assertEquals(1, timerTest.getHour());
     }
     
     @Test
     public void testGetMinutes() {
         assertEquals(20, timerTest.getMinutes());
     }
     
     @Test
     public void testGetMeridian() {
         assertEquals("AM", timerTest.getMeridian());
     }
     
     @Test
     public void testGetMilTime() {
         boolean fal = false;
         assertEquals(fal, timerTest.getMilTime());
     }
     
     @Test
     public void testTogMilTime() {
         boolean fal = true;
         timerTest.toggleMilTime();
         assertEquals(1, timerTest.getHour());
         assertEquals(1, timerTest.getHour());
         assertEquals(fal, timerTest.getMilTime());
     }
}
