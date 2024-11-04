/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend_models;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JButton;
import java.util.Date;
import java.text.NumberFormat;

/**
 *
 * @author cswzi
 */
public class RandomButton {

    public int allButtonsCount;
    public int[] buttonX;
    public int[] buttonY;
    boolean containsZero;
    int countDooku;
    int instancesRanPerUser;
    int[] arrayOfTries; //keeps the array of numbers that the user has saved from their tries in the application, later to be saved 
    //*make sure to get rid of edge cases*
    public double[] arrayOfUsers; // the array to store the average of users attempts
    double numOfUsers;
    double recentUser;

    public RandomButton() {
        this.allButtonsCount = 0;
        this.buttonX = new int[32];
        this.buttonY = new int[32];
        this.arrayOfTries = new int[999];
        this.arrayOfUsers = new double[99];
        for (int i = 0; i < this.arrayOfUsers.length; i++) { // could be ArrayList but I dont really want to convert ints into objects
            this.arrayOfUsers[i] = 34;
        }
        this.containsZero = false;
        this.countDooku = 0; //counts how many buttons the user needs to press
        this.instancesRanPerUser = 0;
        this.numOfUsers = 0;
        this.recentUser = 0;
    }

    // for the int[] below  position 0 = randomButtonX 
    //                      position 1 = randomButtonY
    public int[] AddButton() {
        if (allButtonsCount != 32) {
            // allButtonsCount is declared in ViewUserActions class,
            // see near the bottom of this current file.
            // allButtonsCount keeps track of the number of buttons added by the user
            boolean newButton = true;
            int[] totalButtonPosition = new int[2];
            int randomButtonX = 0, randomButtonY = 0;
            Random rndm = new Random();
            while (newButton == true) {
                randomButtonX = rndm.nextInt(8) * 50;
                randomButtonY = rndm.nextInt(4) * 50;
                newButton = false;
                for (int i = 0; i < this.buttonX.length; i++) {
                    if (randomButtonX == 0 && randomButtonY == 0 && this.containsZero == false) {
                        this.containsZero = true;
                        break;
                    } else if (this.buttonX[i] == randomButtonX && this.buttonY[i] == randomButtonY) {
                        newButton = true;
                    }
                }
                this.buttonX[this.allButtonsCount] = randomButtonX;
                this.buttonY[this.allButtonsCount] = randomButtonY;
            }
            //System.out.println(randomButtonX + " " + randomButtonY + " " + buttonX[3]);
            totalButtonPosition[0] = randomButtonX;
            totalButtonPosition[1] = randomButtonY;
            this.allButtonsCount++;
            return totalButtonPosition;
        } else {
            return null;
        }
    }

    public int getAllButtonsCount() {
        return this.allButtonsCount;
    }

    public int getCountDooku() {
        return this.countDooku;
    }

    public void setCountDooku(int i) {
        this.countDooku = i;
    }

    public void restart() {
        for (int i = 0; i < this.buttonX.length; i++) {
            this.buttonX[i] = 0;
            this.buttonY[i] = 0;
            this.containsZero = false;
            this.allButtonsCount = 0;
            this.countDooku = 0;

        }
    }

    public long getTimeInBetween() {
        long timeInBetween = new Date().getTime();
        for (int i = 0; i < this.allButtonsCount; i++) {
            timeInBetween = timeInBetween + 200;
        }
        timeInBetween = timeInBetween + 200;
        return timeInBetween;
    }

    public void instancesRanPlusOne() {
        this.instancesRanPerUser = this.instancesRanPerUser + 1;
    }

    public int getInstancesRanPerUser() {
        return this.instancesRanPerUser;
    }

    public void getArrayOfTries() {
        this.arrayOfTries[this.instancesRanPerUser] = this.allButtonsCount - 1;
    }

    public double getAverageUserButtonsCount() {
        double fullAverage = 0;
        for (int i = 0; i < this.arrayOfTries.length; i++) {
            fullAverage = this.arrayOfTries[i] + fullAverage;
        }
        fullAverage = fullAverage / this.instancesRanPerUser;
        this.arrayOfUsers[(int) (this.numOfUsers)] = fullAverage;
        this.recentUser = fullAverage;
        return fullAverage;
    }

    public void trueReset() {
        this.instancesRanPerUser = 0;
        for (int i = 0; i < arrayOfTries.length; i++) {
            this.arrayOfTries[i] = 0;
        }
    }

    public void numOfUsersPlusOne() {
        this.numOfUsers = this.numOfUsers + 1;
    }

    public double compareUserScores() {
        // P = n/N * 100 where n = position of the value N = number of values in dataset P = percentile(what we want)
        Arrays.sort(this.arrayOfUsers);
        double position = binarySearch(this.arrayOfUsers, this.recentUser);
        double percentile = (position / this.numOfUsers) * 100;
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        nf.format(percentile);
        return percentile;

    }

    //Binary search(search for the user's data)
    private static int binarySearch(double a[], double srchVal) {
        int lb = 0;
        int ub = a.length - 1;
        while (lb <= ub) {
            int mid = (lb + ub) / 2;
            if (a[mid] == srchVal) {
                return mid;
            } else if (srchVal > a[mid]) {
                lb = mid + 1; //set a new lowerbound
            } else {
                ub = mid - 1; //set a new upper bound
            }
        }
        return -1; //srchVal not found
    }

    public void saveString(String path) throws IOException{
        String saveStr = "";
        for (int i = 0; i < this.arrayOfUsers.length; i++) {
            if (this.arrayOfUsers[i] != 34) {
                saveStr = saveStr + this.arrayOfUsers[i] + "\n";
            }
        }
        File f = new File(path);
        FileWriter fw = new FileWriter(f);

        fw.write(saveStr);
        fw.close();
    }
}
