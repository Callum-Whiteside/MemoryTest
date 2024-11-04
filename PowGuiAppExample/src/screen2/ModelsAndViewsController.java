package screen2;

import frontend_viewcontroller.*;
import backend_models.*;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Random;
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
            if (theBackendModel.aScreen2Model.getStart() == true) {
                if (ae.getSource() == theMainViewDisplay.button1){
                    if (theBackendModel.Timer2.buttonArray[theBackendModel.aScreen2Model.countDracula] == 0){
                        
                    }
                }
            }
            if (theBackendModel.aScreen2Model.instancesCount == theBackendModel.aScreen2Model.countDracula){
                theBackendModel.aScreen2Model.instancePlusOne();
                theBackendModel.Timer2.startCountdown = true;
                theBackendModel.Timer2.instancesRan = theBackendModel.aScreen2Model.instancesCount;
                theBackendModel.Timer2.buttonArray[theBackendModel.aScreen2Model.instancesCount] = theBackendModel.aScreen2Model.getRandomButton();
                theBackendModel.Timer2.timeInBetween = theBackendModel.aScreen2Model.getCountDown();
            }
        }
    }

    private class StartAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (theBackendModel.aScreen2Model.getStart() == false && theBackendModel.aScreen2Model.getTimesStarted() == 0) {
                theBackendModel.aScreen2Model.setStart(true);
                theBackendModel.Timer2 = new TimerScreen2(theMainViewDisplay.button1, theMainViewDisplay.button2, theMainViewDisplay.button3, theMainViewDisplay.button4, theMainViewDisplay.displayInfo);
                theBackendModel.theThreadedTimer = new Thread(theBackendModel.Timer2);
                theBackendModel.theThreadedTimer.start();
                theBackendModel.Timer2.stopRunning = false;
                theBackendModel.Timer2.timeInBetween = theBackendModel.aScreen2Model.getCountDown();
                theBackendModel.Timer2.buttonArray[theBackendModel.aScreen2Model.instancesCount] = theBackendModel.aScreen2Model.getRandomButton();
                theBackendModel.aScreen2Model.instancePlusOne();
                theBackendModel.Timer2.instancesRan = theBackendModel.aScreen2Model.instancesCount;
                theBackendModel.aScreen2Model.startedPlusOne();
                theBackendModel.Timer2.startCountdown = true;
            } else if (theBackendModel.aScreen2Model.getStart() == true){
                theBackendModel.aScreen2Model.setStart(false);
                theBackendModel.Timer2.stopRunning = true;
            } else if (theBackendModel.aScreen2Model.getStart() == false && theBackendModel.aScreen2Model.getTimesStarted() > 0){ //Just don't create a million new timers
                theBackendModel.aScreen2Model.setStart(true);
                theBackendModel.theThreadedTimer.start();
                theBackendModel.Timer2.stopRunning = false;
                theBackendModel.Timer2.timeInBetween = theBackendModel.aScreen2Model.getCountDown();
                theBackendModel.Timer2.buttonArray[theBackendModel.aScreen2Model.instancesCount] = theBackendModel.aScreen2Model.getRandomButton();
                theBackendModel.aScreen2Model.instancePlusOne();
                theBackendModel.Timer2.instancesRan = theBackendModel.aScreen2Model.instancesCount;
                theBackendModel.aScreen2Model.startedPlusOne();
                theBackendModel.Timer2.startCountdown = true;
            }
        }
    }

    private class TestConditionAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

        }
    }

    private class GoToScreen1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            theBackendModel.goToScreen1.goToScreen(1);
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
        this.theMainViewDisplay.goToScreen1.addActionListener(new GoToScreen1());
        this.theMainViewDisplay.button1.addActionListener(new ButtonsInput());
        this.theMainViewDisplay.button2.addActionListener(new ButtonsInput());
        this.theMainViewDisplay.button3.addActionListener(new ButtonsInput());
        this.theMainViewDisplay.button4.addActionListener(new ButtonsInput());
        this.theMainViewDisplay.startButton.addActionListener(new StartAction());

    }
}
