package pt.isec.pa.tinypac.ui.gui;

import javafx.css.CssParser;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import pt.isec.pa.tinypac.model.TinyPacManager;
import pt.isec.pa.tinypac.ui.gui.recursos.CSSManager;
import pt.isec.pa.tinypac.ui.gui.recursos.FontManager;
import pt.isec.pa.tinypac.ui.gui.recursos.ImageManager;

import java.util.Optional;


public class MenuUI extends BorderPane {
    TinyPacManager tinyPacManager;
    Label lblTitulo;
    Button btnStart, btnLoad, btnCredits,btnExit, btnTop5;
    public MenuUI(TinyPacManager tinyPacManager){
        this.tinyPacManager= tinyPacManager;

        createViews();
        registerHandlers();
        CSSManager.applyCSS(this,"estilo.css");

        try {
            update();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void update() {
        if(tinyPacManager.isMostrarCred() || tinyPacManager.getState()!=null || tinyPacManager.isMostrarTOP5()){
            this.setVisible(false);
            return;
        }
            this.setVisible(true);

    }

    private void registerHandlers() {
        tinyPacManager.addClient(TinyPacManager.PROP_CREDITOS, e-> {update();});
        tinyPacManager.addClient(TinyPacManager.PROP_STARTGAME, e->{update();});
        tinyPacManager.addClient(TinyPacManager.PROP_TOP5, e->{update();});

        btnCredits.setOnAction( e ->{
            tinyPacManager.setMostrarCred(true);
        });

        btnStart.setOnAction(e->{
            if(tinyPacManager.verificarJogoGuardado()){
                Alert pergunta= new Alert(Alert.AlertType.WARNING,null,ButtonType.YES,ButtonType.NO);
                tinyPacManager.startGame();


                pergunta.setTitle("Jogo anterior");
                pergunta.setHeaderText("Pertende carregar o jogo guardado");

                pergunta.showAndWait().ifPresent(response ->{
                    switch (response.getButtonData()){
                        case YES -> {
                            tinyPacManager.loadGame();
                        }
                        case NO ->
                        {
                            tinyPacManager.startGame();
                        }
                    }
                });
            }else{
                tinyPacManager.startGame();
            }

        });

        btnTop5.setOnAction(e->{
            tinyPacManager.setMostrarTop5(true);
        });




        btnExit.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirme que deseja sair");
            alert.setHeaderText("Sair do TinyPac");
            alert.setContentText("Tem a certeza que deseja sair do jogo?");

            ButtonType simButton = new ButtonType("Sim", ButtonBar.ButtonData.OK_DONE);
            ButtonType naoButton = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(simButton, naoButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == simButton){
                System.exit(0);
            }
        });
    }

    private void createViews() {
         lblTitulo = new Label("Tiny-PAcMan");
         Font pacFont = FontManager.loadFont("PAC-FONT.ttf", 50);
         lblTitulo.setId("tituloPacman");
         lblTitulo.setFont(pacFont);

        btnStart = new Button("Iniciar jogo".toUpperCase());
        btnStart.setId("btnStart");

        btnTop5 = new Button("Consultar TOP 5".toUpperCase());
        btnTop5.setId("btnTop5");

        btnCredits = new Button("Créditos".toUpperCase());
        btnCredits.setId("btnCredits");

        btnExit  = new Button("Sair".toUpperCase());
        btnExit.setId("btnExit");


        VBox vBox= new VBox(lblTitulo,btnStart,btnTop5,btnCredits,btnExit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        VBox.setMargin(btnStart, new Insets(50, 0, 0, 0));


        this.setCenter(vBox);

        ImageView backgroundImageView = new ImageView(ImageManager.getImage("Pacmenu.png"));

        // escala da imagem para o espaço do menu
        backgroundImageView.fitWidthProperty().bind(this.widthProperty());
        backgroundImageView.fitHeightProperty().bind(this.heightProperty());

        backgroundImageView.getStyleClass().add("background-image");

        // Adiciona a imagem de fundo
        this.getChildren().add(0, backgroundImageView);
    }
}
