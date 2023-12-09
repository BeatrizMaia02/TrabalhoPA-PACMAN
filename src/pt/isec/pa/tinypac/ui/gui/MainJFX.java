package pt.isec.pa.tinypac.ui.gui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.model.TinyPacManager;
import pt.isec.pa.tinypac.ui.gui.recursos.ImageManager;
import pt.isec.pa.tinypac.Main;

public class MainJFX extends Application {
    TinyPacManager tinyPacManager;

    @Override
    public void init() throws Exception{
        super.init();
        tinyPacManager = Main.tinyPacManager;
    }

    @Override
    public void start(Stage stage) throws Exception {
        RootPane rootPane = new RootPane(tinyPacManager);
        Scene scene = new Scene(rootPane,900,700);
        stage.getIcons().add(ImageManager.getImage("Pac_Man.png"));
        stage.setScene(scene);
        stage.setTitle("TinyPac Game");
        stage.setMinWidth(1000);
        stage.setMinHeight(700);
        stage.show();
    }
}
