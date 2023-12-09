package pt.isec.pa.tinypac;

import javafx.application.Application;
import pt.isec.pa.tinypac.model.TinyPacManager;
import pt.isec.pa.tinypac.model.data.TinyPacData;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.ui.gui.MainJFX;
import pt.isec.pa.tinypac.ui.text.TinyPacUI;


/**
 * Main class
 * <p>
 *
 * @ author Beatriz Maia
 *
 */
public class Main {
    public static TinyPacManager tinyPacManager;
    static{
        tinyPacManager=new TinyPacManager();
    }
    public static void main(String[] args) {
        Application.launch(MainJFX.class, args);
    }
}