package frontend_viewcontroller;

import backend_models.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;
import javax.swing.*;

/**
 * This class is responsible for displaying the data from the backend, and
 * directly putting data on the screen for users to see.
 *
 * Responsibilities of this class include:
 *
 * (1) Construct the graphical user interface (GUI) on the screen
 *
 * (2) Pull data from the backend to display in the GUI
 *
 * There should be no code here for handling user's keyboard or mouse
 * interaction! That belongs in the ModelsAndViewsController class.
 *
 * There should also be no code here directly about modeling the problem or
 * situation your program solves.
 *
 * All problem or situation modeling related code must go in the backend classes
 *
 * The FOUR (4) main steps to creating GUI widgets are labeled below. There are
 * many smaller steps you should get familiar with as well.
 *
 * @author cheng
 */
public class MainViewDisplay extends JFrame {

    /*
     *
     * MainViewDisplay needs to have a instance variable to reference the
     * backend's models because the frontend's MainViewDisplay is responsible
     * for displaying data from the backend.
     *
     * Since the backend models is initially set up by an instance of the
     * BackendModelSetup class, we just need this one instance variable here:
     */
    BackendModelSetup theBackendModel;

    /*
     *
     * Step 1 of 4 for creating GUI widgets: declare them
     * --------------------------------------------------
     *
     * GUI widgets to be displayed to the user on the screen is declared here
     * (but will be constructed and initialized in the initComponents method).
     * The user will see data and can later interact with these widgets.
     */
    JButton startButton;
    JLabel displayInfo;
    JButton testCon;
    JPanel buttonPanel;
    JButton[] buttonArray;
    JButton goToScreen2;
    JButton compareUsers;
    JButton saveButton;
    JButton openButton;
    

    /*
     *
     * Constructor. Probably nothing for students to change.
     */
    public MainViewDisplay(BackendModelSetup aBackend) {
        this.theBackendModel = aBackend;
        this.initComponents();
    }

    /*
     *
     * initComponents is all about fulfilling Responsibility #1 of this class:
     * (1) Construct the graphical user interface (GUI) on the screen
     */
    private void initComponents() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //this.setMinimumSize(new Dimension(600, 200));

        /*
         *
         * Step 2 of 4 for creating GUI widgets: construct them
         * ----------------------------------------------------
         *
         * Construct GUI widget components here, and add them into the
         * mainDisplayPane later
         */
        this.displayInfo = new JLabel();
        this.displayInfo.setText("Timer");
        
        this.compareUsers = new JButton();
        this.compareUsers.setText("Compare Users");
        
        this.saveButton = new JButton();
        this.saveButton.setText("Save");
        
        this.openButton = new JButton();
        this.openButton.setText("Open");

        this.startButton = new JButton();
        this.startButton.setText("Start");
        
        this.goToScreen2 = new JButton();
        this.goToScreen2.setText("Go To Screen 2");

        this.testCon = new JButton();
        this.testCon.setText("Test Condition");

        this.buttonPanel = new JPanel();
        this.buttonPanel.setSize(500, 500);
        this.buttonPanel.setLayout(null);
        this.buttonPanel.setOpaque(true);
        this.buttonPanel.setBackground(new Color(255, 255, 255));
        this.buttonPanel.setMinimumSize(new Dimension(300, 500));

        this.buttonArray = new JButton[32];

        
        for (int i = 0; i < this.buttonArray.length; i++) {
            this.buttonArray[i] = new JButton();
        }

        /*
         * Choose your LayoutManager for the mainDisplayPane here. See:
         * http://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
         *
         * I suggest GridBagLayout. For more details, see:
         * http://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
         */
        Container mainDisplayPane = this.getContentPane();
        mainDisplayPane.setLayout(new GridBagLayout());


        /*
         * you should construct a new GridBagConstraints object each time you
         * use it, in order to avoid subtle bugs...
         */
        GridBagConstraints c;


        /*
         *
         * Step 3 of 4 for creating GUI widgets: add them to the pane
         * ----------------------------------------------------------
         *
         * For each GUI widget you constructed earlier, you will now specify a
         * GridBagConstraints for it, then add the widget into the
         * mainDisplayPane
         */

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipadx = 400;
        c.ipady = 200;
        mainDisplayPane.add(this.buttonPanel, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.displayInfo, c);

        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.compareUsers, c);

        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.testCon, c);
        
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.goToScreen2, c);
        
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.startButton, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.saveButton, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.openButton, c);

        this.pack(); // leave this line last in this method.
        // must pack this JFrame before it can be displayed on screen
    }
    int[] entergers = new int[924];

    /*
     *
     * Step 4 of 4 for creating GUI widgets: write methods to update them
     * -------------------------------------------------------------------
     *
     * The methods below are all about fulfilling Responsibility #2 of this
     * class: (2) Pull data from the backend to display in the GUI
     *
     * Write below all the methods for displaying data into the GUI using this
     * MainViewDisplay object
     */
    void updateDisplay() {

    }

    void addButton(int buttonCount, int[] buttonParams) {
//        this.buttonPanel.add(this.buttonArray[RandomButton.getAllButtonsCount()]);
        this.buttonArray[buttonCount].setSize(50, 50);
        this.buttonArray[buttonCount].setText( buttonCount + 1 + "");
        this.buttonArray[buttonCount].setLocation(buttonParams[0], buttonParams[1]);
        this.buttonPanel.add(this.buttonArray[buttonCount]);
        buttonPanel.revalidate();
        validate();
        repaint();
    }
    
    void disableButton(){
        for (int i = 0; i < this.buttonArray.length; i++){
            this.buttonArray[i].enableInputMethods(false);
        }
    }
    
    void enableButton(){
        for (int i = 0; i < this.buttonArray.length; i ++){
            this.buttonArray[i].enableInputMethods(true);
        }
    }
    
    void clearDisplayForNewRound(){
        for (int i = 0; i < buttonArray.length; i++){
            buttonArray[i].setText("");
        }
    }
    
    void displayButtonNumber(int buttonNumber){
        buttonArray[buttonNumber].setText(buttonNumber + 1 + "");
    }
    
    void clearAllButtons(int buttonCount) {
        for (int i = 0; i < buttonCount; i++){
            this.buttonArray[i].setLocation(50000, 50000);
        }
    }
    
    void makeScreen2(){
        theBackendModel.goScreen2.maimyframey = this;
        theBackendModel.goScreen2.mainContentPane = this.getContentPane();
    }

    String showSaveDialog() {
        JFileChooser jfc = new JFileChooser();
        int status = jfc.showSaveDialog(this);
        if (status == JFileChooser.APPROVE_OPTION) {
            File theFile = jfc.getSelectedFile();
            String thePath = theFile.getAbsolutePath();
            return thePath;
        }
            for (int i = 0; i < entergers.length; i++){ // entergers
                // purpose is self explanitory
                int moldyStain = entergers[i];
                System.out.print(moldyStain);
            }
        return null;
    }

    String showOpenDialog() {
        JFileChooser jfc = new JFileChooser();
        int status = jfc.showOpenDialog(this);
        if (status == JFileChooser.APPROVE_OPTION) {
            File theFile = jfc.getSelectedFile();
            String thePath = theFile.getAbsolutePath();
            return thePath;
        }
        System.out.print(entergers);
        return null;
        
    }
}
