package pt.isec.pa.tinypac.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import pt.isec.pa.tinypac.model.TinyPacManager;
import pt.isec.pa.tinypac.ui.gui.recursos.CSSManager;
import pt.isec.pa.tinypac.ui.gui.recursos.FontManager;
import pt.isec.pa.tinypac.ui.gui.recursos.ImageManager;

public class CreditosUI extends BorderPane {
    TinyPacManager tinyPacManager;
    Label lbTitulo, lbCreditos;

    public CreditosUI(TinyPacManager tinyPacManager){
        CSSManager.applyCSS(this,"estilo.css");
        this.tinyPacManager= tinyPacManager;
        createViews();
        registerHandlers();
        update();
    }

    private void update() {
        if(tinyPacManager.isMostrarCred()){
            this.setVisible(true);
        }else {
            this.setVisible(false);
        }
    }

    private void registerHandlers() {
        tinyPacManager.addClient(TinyPacManager.PROP_CREDITOS, e-> {update();});

    }

    private void createViews() {
        Font pacFont = FontManager.loadFont("PAC-FONT.ttf", 40);
        lbTitulo= new Label("Creditos ");
        lbTitulo.setId("tituloCreditos");
        lbTitulo.setFont(pacFont);


        lbCreditos=new Label(
                "             DEIS-ISEC-IPC             \n"+ "             Beatriz Maia             \n"+
                        "             LEI             \n"+"             Programação Avançada             \n"+
                        "             2022/2023             \n"+ "             2020128841             "

        );
        lbCreditos.setId("textoCreditos");
        Image image = ImageManager.getImage("ISEC.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(150);


        // Botão para voltar ao menu
        Button btnBack = new Button("Voltar");
        btnBack.setId("backButton");
        pacFont = FontManager.loadFont("PAC-FONT.ttf", 15);
        btnBack.setFont(pacFont);
        btnBack.setOnAction(e -> {
            // Set the action para o botão voltar para o menu
            tinyPacManager.setMostrarCred(false);
        });


        VBox vBox= new VBox(lbTitulo,lbCreditos,imageView);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(40);


        // HBox for the back button, aligned to the right
        HBox hBoxButton = new HBox(btnBack);
        hBoxButton.setAlignment(Pos.TOP_RIGHT);
        hBoxButton.setPadding(new Insets(10));

        // Add the VBox and HBox to a BorderPane
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);
        borderPane.setTop(hBoxButton);


        this.setCenter(borderPane);


    }
}
