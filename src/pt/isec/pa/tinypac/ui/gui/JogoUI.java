package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.TinyPacManager;
import pt.isec.pa.tinypac.model.data.Direction;
import pt.isec.pa.tinypac.model.data.elementos.Fruta;
import pt.isec.pa.tinypac.model.data.elementos.PacMan;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.ui.gui.recursos.CSSManager;
import pt.isec.pa.tinypac.ui.gui.recursos.ImageManager;


import java.util.Optional;

public class JogoUI extends StackPane {
    TinyPacManager tinyPacManager;
    GridPane mapa;
    Label pontosLabel;

    GameEngine gameEngine = new GameEngine();
    private Button pauseButton;
    private VBox pauseMenu;
    private Button resumeButton;
    private Button saveButton;
    private Button exitButton;

    private ImageView[] coracoes;

    private boolean popUpExibido = false;

    public JogoUI(TinyPacManager tinyPacManager) {
        CSSManager.applyCSS(this, "estilo.css");
        this.tinyPacManager = tinyPacManager;

        createViews();
        registerHandlers();
        update();

        gameEngine.registerClient(this.tinyPacManager);
        gameEngine.start(500);
    }


    private void createViews() {

        BorderPane jogoLayout = new BorderPane();


        HBox pontosContainer = new HBox();
        pontosContainer.setAlignment(Pos.CENTER); // Centraliza o conteúdo horizontalmente
        pontosLabel = new Label();
        pontosLabel.setId("pontosLabel");
        pontosContainer.getChildren().add(pontosLabel); // Adiciona o pontosLabel à HBox
        pontosContainer.setPadding(new Insets(10, 0, 0, 0));
        jogoLayout.setTop(pontosContainer); // Adiciona a HBox ao topo da BorderPane
        CSSManager.applyCSS(pontosLabel, "pontos.css");

        mapa = new GridPane();
        mapa.setAlignment(Pos.CENTER);
        jogoLayout.setCenter(mapa);

        this.getChildren().add(jogoLayout);

        // Cria imagens dos corações
        int maxVidas = 3; // Número máximo de vidas
        coracoes = new ImageView[maxVidas];
        for (int i = 0; i < maxVidas; i++) {
            coracoes[i] = new ImageView(ImageManager.getImage("coracao.png"));
        }


        // Cria container para as imagens dos corações
        HBox coracoesContainer = new HBox();
        coracoesContainer.setAlignment(Pos.CENTER); // Centraliza as imagens horizontalmente
        coracoesContainer.setSpacing(5); // Espaçamento entre as imagens dos corações
        coracoesContainer.setPadding(new Insets(0, 50, 0, 350)); // Adiciona preenchimento à direita do container

        // Adiciona as imagens dos corações ao container
        coracoesContainer.getChildren().addAll(coracoes);


        //botão de pausa
        pauseButton = new Button("Pausa");
        pauseButton.setId("PauseBotao");
        pauseButton.setFocusTraversable(false);
        HBox bottomContainer = new HBox(pauseButton, coracoesContainer);
        bottomContainer.setAlignment(Pos.BOTTOM_LEFT);
        jogoLayout.setBottom(bottomContainer);


        // menu de pausa
        pauseMenu = new VBox();
        pauseMenu.setId("pauseMenu");
        resumeButton = new Button("Continuar");
        resumeButton.getStyleClass().add("pauseMenuButton");
        saveButton = new Button("Salvar");
        saveButton.getStyleClass().add("pauseMenuButton");
        exitButton = new Button("Sair");
        exitButton.getStyleClass().add("pauseMenuButton");
        pauseMenu.getChildren().addAll(resumeButton, saveButton, exitButton);
        pauseMenu.setAlignment(Pos.CENTER);
        pauseMenu.setVisible(false);

        // Adiciona o pauseMenu ao StackPane principal
        this.getChildren().add(pauseMenu);
        StackPane.setAlignment(pauseMenu, Pos.CENTER);

    }


    private void registerHandlers() { //controlo do pacman
        tinyPacManager.addClient(TinyPacManager.PROP_ATUALIZA, e -> Platform.runLater(() -> update()));
        tinyPacManager.addClient(TinyPacManager.PROP_STARTGAME, e -> {
            Platform.runLater(() -> update());
        });
        this.setFocusTraversable(true);
        // KEY PRESSED HANDLER
        this.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.UP) {
                tinyPacManager.mudarDirecaoPacman(Direction.UP);
            } else if (keyEvent.getCode() == KeyCode.DOWN) {
                tinyPacManager.mudarDirecaoPacman(Direction.DOWN);
            } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                tinyPacManager.mudarDirecaoPacman(Direction.RIGHT);
            } else if (keyEvent.getCode() == KeyCode.LEFT) {
                tinyPacManager.mudarDirecaoPacman(Direction.LEFT);
            }

            else if(keyEvent.getCode() == KeyCode.P){
                pauseButton.fire();
            }
        });
        //  ao botão de pausa
        pauseButton.setOnAction(e -> tinyPacManager.pauseGame());

        // ao menu de pausa
        resumeButton.setOnAction(e -> tinyPacManager.resumeGame());
        saveButton.setOnAction(e -> tinyPacManager.saveGame());
        exitButton.setOnAction(e -> Platform.exit());



        exitButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirme que deseja sair");
            alert.setHeaderText("Sair do TinyPac");
            alert.setContentText("Tem a certeza que deseja sair do jogo?");

            ButtonType simButton = new ButtonType("Sim", ButtonBar.ButtonData.OK_DONE);
            ButtonType naoButton = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(simButton, naoButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == simButton) {
                System.exit(0);
            }
        });
    }


    private void update() {
        if (tinyPacManager.getState() != null ) {
            if(tinyPacManager.getState()==TinyPacState.JOGO_EM_PAUSA){
                pauseMenu.setVisible(true);
            }else{
                pauseMenu.setVisible(false);
            }
            setVisible(true);
        } else {
            setVisible(false);
            return;
        }

        if (tinyPacManager.getState() == TinyPacState.PACMAN_GAMEOVER) {
            if ( tinyPacManager.getpodeTop5()) {
                gameEngine.pause();
                //fazer pop up
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Digite seu nome");
                dialog.setHeaderText(null);
                dialog.setContentText("Nome:");

                // Mostrar o pop-up e aguardar a resposta do utilizador
                java.util.Optional<String> result = dialog.showAndWait();

                // Verificar se o utilizador forneceu um nome
                if (result.isPresent()) {
                    String nomeJogador = result.get();
                    tinyPacManager.atualizaTOP5(nomeJogador);

                }
            }
            gameEngine.unregisterClient(tinyPacManager);
            tinyPacManager.endGame();

        }


            pontosLabel.setText("Pontos: " + tinyPacManager.getPontosAtuais());
        int vidas = tinyPacManager.getVidas();

        for (int i = 0; i < coracoes.length; i++) {
            if (i < vidas) {
                coracoes[i].setVisible(true); // Exibir coração
            } else {
                coracoes[i].setVisible(false); // Ocultar coração
            }
        } // Atualizar as imagens dos corações



        // MAPA DE JOGO
        if (tinyPacManager.getLabirinto() != null) {
            mapa.getChildren().clear();
            char[][] char_maze = tinyPacManager.getLabirinto().getMaze();


            for (int i = 0; i < char_maze.length; i++) {
                for (int j = 0; j < char_maze[i].length; j++) {
                    Label label = new Label();
                    label.setPrefSize(19, 19);

                    switch (char_maze[i][j]) {
                        case 'x':
                            ImageView pacmanImageView = new ImageView(ImageManager.getImage("parede.png"));
                            pacmanImageView.setPreserveRatio(true);
                            pacmanImageView.setFitWidth(label.getPrefWidth());
                            pacmanImageView.setFitHeight(label.getPrefHeight());
                            label.setGraphic(pacmanImageView);
                            break;

                        case '■':
                            pacmanImageView = new ImageView(ImageManager.getImage("pacman.gif"));
                            switch (tinyPacManager.getDirection()) {
                                case UP -> pacmanImageView.setRotate(-90);
                                case DOWN -> pacmanImageView.setRotate(90);
                                case LEFT -> pacmanImageView.setRotate(180);
                            }


                            pacmanImageView.setPreserveRatio(true);
                            pacmanImageView.setFitWidth(label.getPrefWidth());
                            pacmanImageView.setFitHeight(label.getPrefHeight());
                            label.setGraphic(pacmanImageView);
                            break;

                        case 'o':
                            pacmanImageView = new ImageView(ImageManager.getImage("pontoPequeno.png"));
                            pacmanImageView.setPreserveRatio(true);
                            pacmanImageView.setFitWidth(label.getPrefWidth());
                            pacmanImageView.setFitHeight(label.getPrefHeight());
                            label.setGraphic(pacmanImageView);
                            break;

                        case 'O':
                            pacmanImageView = new ImageView(ImageManager.getImage("pontoGrande.gif"));
                            pacmanImageView.setPreserveRatio(true);
                            pacmanImageView.setFitWidth(label.getPrefWidth());
                            pacmanImageView.setFitHeight(label.getPrefHeight());
                            label.setGraphic(pacmanImageView);
                            break;

                        case 'B':
                            if (tinyPacManager.getState() == TinyPacState.FANTASMAS_VULNERAVEIS) {
                                pacmanImageView = new ImageView(ImageManager.getImage("fantasma.png"));
                            } else {
                                pacmanImageView = new ImageView(ImageManager.getImage("blinky.png"));
                            }
                            pacmanImageView.setPreserveRatio(true);
                            pacmanImageView.setFitWidth(label.getPrefWidth());
                            pacmanImageView.setFitHeight(label.getPrefHeight());
                            label.setGraphic(pacmanImageView);
                            break;

                        case 'P':
                            if (tinyPacManager.getState() == TinyPacState.FANTASMAS_VULNERAVEIS) {
                                pacmanImageView = new ImageView(ImageManager.getImage("fantasma.png"));
                            } else {
                                pacmanImageView = new ImageView(ImageManager.getImage("pinky.png"));
                            }
                            pacmanImageView.setPreserveRatio(true);
                            pacmanImageView.setFitWidth(label.getPrefWidth());
                            pacmanImageView.setFitHeight(label.getPrefHeight());
                            label.setGraphic(pacmanImageView);
                            break;

                        case 'C':
                            if (tinyPacManager.getState() == TinyPacState.FANTASMAS_VULNERAVEIS) {
                                pacmanImageView = new ImageView(ImageManager.getImage("fantasma.png"));
                            } else {
                                pacmanImageView = new ImageView(ImageManager.getImage("clyde.png"));
                            }
                            pacmanImageView.setPreserveRatio(true);
                            pacmanImageView.setFitWidth(label.getPrefWidth());
                            pacmanImageView.setFitHeight(label.getPrefHeight());
                            label.setGraphic(pacmanImageView);
                            break;

                        case 'W':
                            pacmanImageView = new ImageView(ImageManager.getImage("portal.png"));
                            pacmanImageView.setPreserveRatio(true);
                            pacmanImageView.setFitWidth(label.getPrefWidth());
                            pacmanImageView.setFitHeight(label.getPrefHeight());
                            label.setGraphic(pacmanImageView);
                            break;

                        case ' ':
                            pacmanImageView = new ImageView(ImageManager.getImage("EspacoVazio.png"));
                            pacmanImageView.setPreserveRatio(true);
                            pacmanImageView.setFitWidth(label.getPrefWidth());
                            pacmanImageView.setFitHeight(label.getPrefHeight());
                            label.setGraphic(pacmanImageView);
                            break;


                        case 'Y':
                            pacmanImageView = new ImageView(ImageManager.getImage("SurgirFantasmas.png"));
                            pacmanImageView.setPreserveRatio(true);
                            pacmanImageView.setFitWidth(label.getPrefWidth());
                            pacmanImageView.setFitHeight(label.getPrefHeight());
                            label.setGraphic(pacmanImageView);
                            break;

                        case 'M':
                            pacmanImageView = new ImageView(ImageManager.getImage("PacmanSpawn.png"));
                            pacmanImageView.setPreserveRatio(true);
                            pacmanImageView.setFitWidth(label.getPrefWidth());
                            pacmanImageView.setFitHeight(label.getPrefHeight());
                            label.setGraphic(pacmanImageView);
                            break;

                        case 'F':

                            pacmanImageView = new ImageView(ImageManager.getImage("fruta.png"));
                            pacmanImageView.setPreserveRatio(true);
                            pacmanImageView.setFitWidth(label.getPrefWidth());
                            pacmanImageView.setFitHeight(label.getPrefHeight());
                            label.setGraphic(pacmanImageView);
                            break;
                    }
                    mapa.add(label, j, i);
                }
            }
        }
    }


}
