/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend_models;

/**
 *
 * @author very poggers perpson
 */
import java.awt.Container;
import javax.swing.JFrame;

public class OtherScreen extends javax.swing.JFrame {

    public static frontend_viewcontroller.MainViewDisplay maimyframey;
    public static Container mainContentPane;

    public static screen2.MainViewDisplay mainViewScreen2 = new screen2.MainViewDisplay(new screen2.BackendModelSetup());
    public static screen2.ModelsAndViewsController screen2 = new screen2.ModelsAndViewsController(new screen2.BackendModelSetup(), mainViewScreen2);

    public static void goToScreen(int screen) {
        if (screen == 2) {
            OtherScreen.maimyframey.setContentPane(OtherScreen.mainViewScreen2.getContentPane());
//            BackendModelSetup.screen2.updateThoughtDisplayed(); // go to screen, and auto update data displayed
            OtherScreen.maimyframey.pack();
        }
        if (screen == 1) {
            OtherScreen.maimyframey.setContentPane(OtherScreen.mainContentPane);
            OtherScreen.maimyframey.pack();
        }
    }
}
