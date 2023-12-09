package pt.isec.pa.tinypac.ui.text;

import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.utils.PAInput;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.Scanner;
import java.io.IOException;

public class TinyPacUI {
    private static TinyPacContext fsm;

    public TinyPacUI(TinyPacContext fsm){
        this.fsm=fsm;
    }


}
