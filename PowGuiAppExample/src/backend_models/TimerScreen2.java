/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend_models;

import java.text.NumberFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author 105075008
 */
public class TimerScreen2 implements Runnable {

    public boolean stopRunning;
    public long timeInBetween;
    public JButton button1;
    public JButton button2;
    public JButton button3;
    public JButton button4;
    public JLabel displayInfo;
    public int instancesRan;
    public boolean startCountdown;
    public int[] buttonArray = new int[999];
    public int countDracula;
    public long conveluted;

    public TimerScreen2(JButton buttonUno, JButton buttonDos, JButton buttonTres, JButton buttonQuatro, JLabel displayLabel) {
        this.stopRunning = false;
        this.timeInBetween = 0;
        this.instancesRan = 0;
        this.countDracula = 0;
        this.conveluted = 0;
        this.startCountdown = false;
        this.button1 = buttonUno;
        this.button2 = buttonDos;
        this.button3 = buttonTres;
        this.button4 = buttonQuatro;
        this.displayInfo = displayLabel;
    }

    public void run() {
        while (true) {
            if (this.stopRunning) {
                return;
            }

            long date = new Date().getTime();
            long coolTimer = this.timeInBetween - date;
            System.out.println(coolTimer);
            if (startCountdown == true) {
                if (coolTimer < 0) {
                    this.button1.setEnabled(false);
                    this.button2.setEnabled(false);
                    this.button3.setEnabled(false);
                    this.button4.setEnabled(false);
                    switch (buttonArray[this.countDracula]) {
                        case 0:
                            this.button1.setText("");
                            this.button2.setText("");
                            this.button3.setText("");
                            this.button4.setText("");
                            this.button1.setText("0");
                            this.countDracula++;
                            break;
                        case 1:
                            this.button1.setText("");
                            this.button2.setText("");
                            this.button3.setText("");
                            this.button4.setText("");
                            this.button2.setText("0");
                            this.countDracula++;
                            break;
                        case 2:
                            this.button1.setText("");
                            this.button2.setText("");
                            this.button3.setText("");
                            this.button4.setText("");
                            this.button3.setText("0");
                            this.countDracula++;
                            break;
                        case 3:
                            this.button1.setText("");
                            this.button2.setText("");
                            this.button3.setText("");
                            this.button4.setText("");
                            this.button4.setText("0");
                            this.countDracula++;
                            break;
                        default:
                            System.out.println("someting went wrong :(");
                    }
                    if (this.countDracula == this.instancesRan) {
                        this.button1.setEnabled(true);
                        this.button2.setEnabled(true);
                        this.button3.setEnabled(true);
                        this.button4.setEnabled(true);
                        this.startCountdown = false;
                        break;
                    }
                }
            }
        }
    }
}
