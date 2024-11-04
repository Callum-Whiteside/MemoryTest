package backend_models;

/**
 * Another class to model the problem or situation your program solves
 *
 * There should be no code here directly about painting graphics on the screen,
 * and no code here for directly handling user interaction.
 *
 * All user interface related code must go in the frontend classes.
 *
 * @author cheng
 */
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.text.NumberFormat;

public class Timer implements Runnable {

    public boolean stopRunning;
    public long timeInBetween;
    private JButton[] buttonArray;
    private JLabel timerLabel;
    public boolean runningSeperate; // thing to fix the "Timer: GO" thingy

    public Timer(JButton[] steakYummers, JLabel pForPapas) {
        this.stopRunning = false;
        this.timeInBetween = 0;
        this.buttonArray = steakYummers;
        this.timerLabel = pForPapas;
        this.runningSeperate = false;
    }

    public void run() {
        while (true) {
            if (this.stopRunning) {
                return;
            }
            long date = new Date().getTime();
            long timeThatIWant = this.timeInBetween - date;
            double timerDisplay = (timeThatIWant / 100);
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMaximumFractionDigits(1);
            nf.setMinimumFractionDigits(1);
            if (timerDisplay > 0) {
                timerDisplay = timerDisplay / 10;
                this.timerLabel.setText("Time:" + nf.format(timerDisplay));
            } else if (timerDisplay < 0 && this.runningSeperate == true){
                this.timerLabel.setText("Time: GO");
            }
            if (timeThatIWant < 0 && timeThatIWant > -50){
              for (int i = 0; i < buttonArray.length; i++) {
                    this.buttonArray[i].setText("");
                }
            }
            if (timeThatIWant <= 0) {
                for (int i = 0; i < buttonArray.length; i++) {
                    this.buttonArray[i].setEnabled(true);
                }
            } else {
                for (int i = 0; i < buttonArray.length; i++) {
                    this.buttonArray[i].setEnabled(false);
                    this.buttonArray[i].setText(i + 1 + "");
                }
            }
        }
    }
}
