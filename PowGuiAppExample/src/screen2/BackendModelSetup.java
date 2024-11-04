/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package screen2;
import backend_models.*;
/**
 *
 * @author 105075008
 */
public class BackendModelSetup {
    MemoryModel aMemoryModel;
    TimerScreen2 Timer2;
    Thread theThreadedTimer;
    OtherScreen goToScreen1;
    screen2Model aScreen2Model;

    public BackendModelSetup() {

        this.aMemoryModel = new MemoryModel();
        this.goToScreen1 = new OtherScreen();
        this.aScreen2Model = new screen2Model();
    }
}
