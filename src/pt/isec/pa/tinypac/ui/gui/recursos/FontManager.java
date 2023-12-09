package pt.isec.pa.tinypac.ui.gui.recursos;

import javafx.scene.text.Font;

import java.io.InputStream;

public class FontManager {
    private FontManager() {
    }

    public static Font loadFont(String filename, int size) {
        try(InputStream inputStreamFont =
                    FontManager.class.getResourceAsStream("Fontes/" + filename)) {
            return Font.loadFont(inputStreamFont, size);
        } catch (Exception e) {
            return null;
        }
    }
}
