/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend_models;

/**
 *
 * @author cswzi
 */
public class MemoryModel {
    public int age;
    public boolean start;
    
    public MemoryModel(){
    this.age = 0;
    this.start = false;
}
    
    public int getAge(){
        return this.age;
    }
    
    public void setAge(int coolAge){
        this.age = coolAge;
    }
    
    public boolean getStart(){
        return this.start;
    }
    
    public void setStart(boolean boo){
        this.start = boo;
    }
    
    public void dataBase(){
        
    }
}
