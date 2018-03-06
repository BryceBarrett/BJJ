/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.io.File;

/**
 *
 * @author brycebarrett
 */
public class ReadFiles {
    //private static final String ROOT_FILE_PATH="/";
    private static final String ROOT_FILE_PATH="/Users/brycebarrett/Desktop";
    
    
    public static void main(String[] args){
        
        File f=new File(ROOT_FILE_PATH);
        File[] allSubFiles=f.listFiles();
        System.out.println(allSubFiles[7].getAbsolutePath());
        
        System.out.println();
        
        for (File file : allSubFiles) {
            if(file.isDirectory())
            {
                System.out.println(file.getAbsolutePath()+" is directory");
                //Steps for directory
            }
            else
            {
                System.out.println(file.getAbsolutePath()+" is file");
                //steps for files
            }
        }
}
}
