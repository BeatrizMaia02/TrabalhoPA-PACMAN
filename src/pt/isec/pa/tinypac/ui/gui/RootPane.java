package pt.isec.pa.tinypac.ui.gui;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import pt.isec.pa.tinypac.Main;
import pt.isec.pa.tinypac.model.TinyPacManager;
import pt.isec.pa.tinypac.ui.gui.recursos.CSSManager;

public class RootPane extends BorderPane {
    TinyPacManager tinyPacManager;

    public RootPane(TinyPacManager tinyPacManager) {
        this.tinyPacManager = tinyPacManager;

        createViews();
        registerHandlers();
        update();
    }

    private void update() {
    }

    private void registerHandlers() {
    }

    private void createViews() {
        CSSManager.applyCSS(this, "estilo.css");

        StackPane stackPane = new StackPane(
                new MenuUI(tinyPacManager),
                new TOP5UI(tinyPacManager),
                new CreditosUI(tinyPacManager),
                new JogoUI(tinyPacManager)
        );

        stackPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));


        this.setCenter(stackPane);
    }
}

