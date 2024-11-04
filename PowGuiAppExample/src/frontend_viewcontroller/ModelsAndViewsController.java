package frontend_viewcontroller;

import backend_models.*;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JRadioButton;

/**
 * This class is responsible for manipulating the data in the backend, and
 * letting the user otherwise control the GUI on the screen.
 *
 * Responsibilities of this class include:
 *
 * (1) Ask the MainViewDisplay object to change the GUI on the screen in
 * response to user input actions (without modifying any data in the backend).
 *
 * (2) Ask the backend models to modify its data, and also ask the
 * MainViewDisplay object to update the GUI on the screen (to match the data in
 * the backend), in response to user input actions.
 *
 * There should be no code here directly about painting graphics on the screen!
 * That belongs in the MainViewDisplay class.
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
public class ModelsAndViewsController {

    /*
     *
     * ModelsAndViewsController needs to have an instance variable to reference
     * the backend's models because the frontend's ModelsAndViewsController is
     * responsible for asking the backend to modify its data.
     *
     * Since the backend models is initially set up by an instance of the
     * BackendModelSetup class, we just need this one instance variable here:
     */
    BackendModelSetup theBackendModel;
    /*
     *
     * This class also needs to have an instance variable to reference the
     * frontend's MainViewDisplay because the ModelsAndViewsController object is
     * responsible for asking the MainViewDisplay object to update itself.
     */
    MainViewDisplay theMainViewDisplay;

    /*
     *
     * Step 1 of 2 to provide user controls: write user action as private class
     * ------------------------------------------------------------------------
     *
     * User actions are written as private inner classes that implement
     * ActionListener, and override the actionPerformed method.
     *
     * Use the following as a template for writting more user actions.
     */
    private class ButtonsInput implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
//            System.out.println("¯\\_(ツ)_/¯");
//            theMainViewDisplay.addButton(theBackendModel.aRandomButton.getAllButtonsCount(), theBackendModel.aRandomButton.AddButton());
//            theMainViewDisplay.buttonArray[1].setEnabled(false);
            if (ae.getSource() == theMainViewDisplay.buttonArray[theBackendModel.aRandomButton.getCountDooku()]) {
                //System.out.println("¯\\_(ツ)_/¯");
                theBackendModel.aRandomButton.setCountDooku(theBackendModel.aRandomButton.getCountDooku() + 1);
                theMainViewDisplay.displayButtonNumber(theBackendModel.aRandomButton.getCountDooku() - 1);
                if (theBackendModel.aRandomButton.getAllButtonsCount() == theBackendModel.aRandomButton.getCountDooku()) {
                    theMainViewDisplay.clearDisplayForNewRound();
                    theMainViewDisplay.addButton(theBackendModel.aRandomButton.getAllButtonsCount(), theBackendModel.aRandomButton.AddButton());
                    theBackendModel.aRandomButton.setCountDooku(0);
                    theBackendModel.theTimer.timeInBetween = theBackendModel.aRandomButton.getTimeInBetween();
                }
            } else if (ae.getSource() != theMainViewDisplay.buttonArray[theBackendModel.aRandomButton.getCountDooku()]) {
                theBackendModel.theTimer.runningSeperate = false;
                theMainViewDisplay.displayInfo.setText("Wrong button, " + "your score was " + (theBackendModel.aRandomButton.getAllButtonsCount() - 1));
                theBackendModel.aRandomButton.getArrayOfTries();
                theMainViewDisplay.clearAllButtons(theBackendModel.aRandomButton.getAllButtonsCount());
                theBackendModel.aMemoryModel.setStart(false);
                theBackendModel.theTimer.stopRunning = true;
                theBackendModel.aRandomButton.restart();
            }

        }
    }

    private class StartTestAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (theBackendModel.aMemoryModel.getStart() == false && theBackendModel.aRandomButton.getInstancesRanPerUser() == 0) {
                theMainViewDisplay.addButton(theBackendModel.aRandomButton.getAllButtonsCount(), theBackendModel.aRandomButton.AddButton());
                theBackendModel.theTimer = new Timer(theMainViewDisplay.buttonArray, theMainViewDisplay.displayInfo);
                theBackendModel.theThreadedTimer = new Thread(theBackendModel.theTimer);
                theBackendModel.theTimer.runningSeperate = true;
                theBackendModel.theThreadedTimer.start();
                theBackendModel.aMemoryModel.setStart(true);
                theBackendModel.theTimer.stopRunning = false;
                theBackendModel.theTimer.timeInBetween = theBackendModel.aRandomButton.getTimeInBetween();
                theBackendModel.aRandomButton.instancesRanPlusOne();
            } else if (theBackendModel.aMemoryModel.getStart() == true) {
                theMainViewDisplay.clearAllButtons(theBackendModel.aRandomButton.getAllButtonsCount());
                theBackendModel.aMemoryModel.setStart(false);
                theBackendModel.theTimer.stopRunning = true;
                theBackendModel.aRandomButton.restart();
                theMainViewDisplay.displayInfo.setText("Timer: Stopped");
            } else if (theBackendModel.aMemoryModel.getStart() == false && theBackendModel.aRandomButton.getInstancesRanPerUser() > 0) { //Just don't create a million new timers
                theMainViewDisplay.addButton(theBackendModel.aRandomButton.getAllButtonsCount(), theBackendModel.aRandomButton.AddButton());
                theBackendModel.theThreadedTimer = new Thread(theBackendModel.theTimer);
                theBackendModel.theThreadedTimer.start();
                theBackendModel.theTimer.runningSeperate = true;
                theBackendModel.aMemoryModel.setStart(true);
                theBackendModel.theTimer.stopRunning = false;
                theBackendModel.theTimer.timeInBetween = theBackendModel.aRandomButton.getTimeInBetween();
                theBackendModel.aRandomButton.instancesRanPlusOne();
            }
        }
    }

    private class GoToScreen2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            OtherScreen.goToScreen(2);
            theMainViewDisplay.clearAllButtons(theBackendModel.aRandomButton.getAllButtonsCount());
            if (theBackendModel.aMemoryModel.start == true) {
                theBackendModel.theTimer.stopRunning = true;
            }
            theBackendModel.aMemoryModel.setStart(false);
            theBackendModel.aRandomButton.restart();
            theMainViewDisplay.displayInfo.setText("Timer: Stopped");
        }
    }

    private class TestConditionAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            double average = theBackendModel.aRandomButton.getAverageUserButtonsCount();
            if (average > 0 && theBackendModel.aMemoryModel.start == false) {
                theMainViewDisplay.displayInfo.setText("Your average is " + average);
                theBackendModel.aRandomButton.trueReset();
                theBackendModel.aRandomButton.numOfUsersPlusOne();
            } else {
                theMainViewDisplay.displayInfo.setText("Play first to get results");
            }
        }
    }
    
    private class SaveStuff implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae){
            String pathToFile = theMainViewDisplay.showSaveDialog();
            if (pathToFile != null) {
                try {
                    theBackendModel.aRandomButton.saveString(pathToFile);
                } catch (IOException ex) {
                    Logger.getLogger(ModelsAndViewsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private class CompareUsers implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (theBackendModel.aRandomButton.arrayOfUsers[0] != 34) {
                theMainViewDisplay.displayInfo.setText("You're in the " + theBackendModel.aRandomButton.compareUserScores() + "th percentile");

            }
        }
    }

    /*
     *
     * Constructor. Probably nothing for students to change.
     */
    public ModelsAndViewsController(BackendModelSetup aBackend, MainViewDisplay aMainViewDisplay) {
        this.theBackendModel = aBackend;
        this.theMainViewDisplay = aMainViewDisplay;
        this.initController();
    }

    /*
     *
     * Step 2 of 2 to provide user controls: wire user action to GUI widgets
     * ----------------------------------------------------------------------
     *
     * Once a private inner class has been written for a user action, you can
     * wire it to a GUI widget (i.e. one of the GUI widgets you created in the
     * MainViewDisplay class and which you gave a memorable variable name!).
     *
     * Use the following as templates for wiring in more user actions.
     */
    private void initController() {
        this.theMainViewDisplay.startButton.addActionListener(new StartTestAction());
        this.theMainViewDisplay.testCon.addActionListener(new TestConditionAction());
        this.theMainViewDisplay.compareUsers.addActionListener(new CompareUsers());
        for (int i = 0; i < theMainViewDisplay.buttonArray.length; i++) {
            this.theMainViewDisplay.buttonArray[i].addActionListener(new ButtonsInput());
        }
        this.theMainViewDisplay.goToScreen2.addActionListener(new GoToScreen2());
        this.theMainViewDisplay.makeScreen2();
        this.theMainViewDisplay.saveButton.addActionListener(new SaveStuff());
    }
}
