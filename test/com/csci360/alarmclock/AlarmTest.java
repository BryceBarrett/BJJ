/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Julius Alipala
 */
public class AlarmTest {
    
    public AlarmTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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

    
    /**
     * Test of getHour method, of class Alarm.
     */
    
    /*
    @Test
    
    public void testGetHour() {
        System.out.println("getHour");
        Alarm instance = new Alarm();
        int expResult = 0;
        int result = instance.getHour();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    
    /**
     * Test of setHour method, of class Alarm.
     */
    
    /*
    @Test
    public void testSetHour() {
        System.out.println("setHour");
        int hour = 0;
        Alarm instance = new Alarm();
        instance.setHour(hour);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */

    /**
     * Test of getMinute method, of class Alarm.
     */
    
    /*
    @Test
    public void testGetMinute() {
        System.out.println("getMinute");
        Alarm instance = new Alarm();
        int expResult = 0;
        int result = instance.getMinute();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */

    /**
     * Test of setMinute method, of class Alarm.
     */
    
    /*
    @Test
    public void testSetMinute() {
        System.out.println("setMinute");
        int minute = 0;
        Alarm instance = new Alarm();
        instance.setMinute(minute);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */

    /**
     * Test of getPeriod method, of class Alarm.
     */
    
    /*
    @Test
    public void testGetPeriod() {
        System.out.println("getPeriod");
        Alarm instance = new Alarm();
        Alarm.Period expResult = null;
        Alarm.Period result = instance.getPeriod();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */

    /**
     * Test of setPeriod method, of class Alarm.
     */
    
    /*
    @Test
    public void testSetPeriod() {
        System.out.println("setPeriod");
        Alarm.Period period = null;
        Alarm instance = new Alarm();
        instance.setPeriod(period);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */

    /**
     * Test of isActive method, of class Alarm.
     */
    
    /*
    @Test
    public void testIsActive() {
        System.out.println("isActive");
        Alarm instance = new Alarm();
        boolean expResult = false;
        boolean result = instance.isActive();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */

    /**
     * Test of setActive method, of class Alarm.
     */
    
    /*
    @Test
    public void testSetActive() {
        System.out.println("setActive");
        boolean active = false;
        Alarm instance = new Alarm();
        instance.setActive(active);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */

    /**
     * Test of playAlarm method, of class Alarm.
     */
    
    /*
    @Test
    public void testPlayAlarm() {
        System.out.println("playAlarm");
        Alarm instance = new Alarm();
        instance.playAlarm();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    
    

    /**
     * Test of setAlarmSound method, of class Alarm.
     */
    
    /*
    @Test
    public void testSetAlarmSound() {
        System.out.println("setAlarmSound");
        String sound = "test";
        String expectedPath = System.getProperty("user.dir") + "/src/com/csci360/alarmclock/Sounds/" + sound + ".wav";
        
        Alarm instance = new Alarm();
        instance.setAlarmSound(sound);
        
        assertEquals(expectedPath, instance.getAlarmPath());
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    */
    
    

    /**
     * Test of toggleAlarm method, of class Alarm.
     */
    
    /*
    @Test
    public void testToggleAlarm() {
        System.out.println("toggleAlarm");
        Alarm instance = new Alarm();
        instance.toggleAlarm();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */

    /**
     * Test of compareTime method, of class Alarm.
     */
    @Test
    public void testCompareTime() {
        System.out.println("compareTime");
        TimerClock timerClock = new TimerClock(11, 59, 55, "AM", false);
        Alarm instance = new Alarm();
        
        instance.setHour(11);
        instance.setMinute(59);
        instance.setPeriod(Alarm.Period.AM);        
        
        
        boolean expResult = true;
        boolean result = instance.compareTime(timerClock);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
