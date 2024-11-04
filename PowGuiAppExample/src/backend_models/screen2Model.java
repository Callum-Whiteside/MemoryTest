/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend_models;

/**
 *
 * @author 105075008
 */
import java.util.Date;


public class screen2Model {
    public boolean start;
//    public int[] buttonsArray = new int[999]; idk if I'll use this
    public int instancesCount; // what "round" you're on
    public int timesStarted;
    public long countdownTimer;
    public int countDracula;
    
    public screen2Model(){
        this.start = false;
        this.instancesCount = 0;
        this.timesStarted = 0;
        this.countdownTimer = 0;
    }
    
    public int getRandomButton(){
        return (int)(Math.random() * 4);        
    }
    
    public boolean getStart(){
        return this.start;
    }
    
    public void setStart(boolean reachForTheStars){
        this.start = reachForTheStars;
    }
    
    public void instancePlusOne(){
        this.instancesCount = this.instancesCount + 1;
    }
    
    public void startedPlusOne(){
        this.timesStarted = this.timesStarted + 1;
    }
    
    public int getTimesStarted(){
        return this.timesStarted;
    }
    
    public long getCountDown(){
        this.countdownTimer = this.countdownTimer + 500;
        long currentTime = new Date().getTime();
        currentTime = currentTime + this.countdownTimer;
        return currentTime;
    }
}
