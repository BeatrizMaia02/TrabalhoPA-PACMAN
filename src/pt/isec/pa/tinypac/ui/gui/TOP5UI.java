package pt.isec.pa.tinypac.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import pt.isec.pa.tinypac.model.TinyPacManager;
import pt.isec.pa.tinypac.model.data.Pontuacao;
import pt.isec.pa.tinypac.ui.gui.recursos.CSSManager;
import pt.isec.pa.tinypac.ui.gui.recursos.FontManager;
import pt.isec.pa.tinypac.ui.gui.recursos.ImageManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TOP5UI extends BorderPane {
    TinyPacManager tinyPacManager;
    Label lbTitulo;
    TableView<Pontuacao> tabelaPontuacoes;

    public TOP5UI(TinyPacManager tinyPacManager) {
        this.tinyPacManager = tinyPacManager;
        createViews();
        registerHandlers();
        update();
        this.setVisible(false);
    }

    private void update() {
        if (tinyPacManager.isMostrarTOP5()) {
            this.setVisible(true);
            carregarPontuacoes();
        } else {
            this.setVisible(false);
        }
    }

    private void registerHandlers() {
        tinyPacManager.addClient(TinyPacManager.PROP_TOP5, e -> {
            update();
        });
    }

    private void createViews() {
        Font pacFont = FontManager.loadFont("PAC-FONT.ttf", 30);

        // Button to go back to the menu
        Button btnBack = new Button("Voltar");
        btnBack.setId("backButton");
        pacFont = FontManager.loadFont("PAC-FONT.ttf", 15);
        btnBack.setFont(pacFont);
        btnBack.setOnAction(e -> {
            // Set the action para voltar para o menu
            tinyPacManager.setMostrarTop5(false);
        });

        HBox hBoxButton = new HBox(btnBack);
        hBoxButton.setAlignment(Pos.TOP_RIGHT);
        hBoxButton.setPadding(new Insets(10));

        this.setTop(hBoxButton);

        pacFont = FontManager.loadFont("PAC-FONT.ttf", 30);
        lbTitulo = new Label(" TOP FIVE ");
        lbTitulo.setId("tituloCreditos"); // mesma estética
        lbTitulo.setFont(pacFont);
        lbTitulo.setAlignment(Pos.CENTER);


        tabelaPontuacoes = new TableView<>();
        tabelaPontuacoes.setMaxHeight(5 * 70);

        tabelaPontuacoes.setFixedCellSize(50);
        tabelaPontuacoes.prefHeightProperty().bind(tabelaPontuacoes.fixedCellSizeProperty().multiply(5).add(30));
        TableColumn<Pontuacao, String> colunaNome = new TableColumn<>("Nome");
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Pontuacao, Integer> colunaPontuacao = new TableColumn<>("Pontuação");
        colunaPontuacao.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));

        tabelaPontuacoes.getColumns().addAll(colunaNome, colunaPontuacao);

        VBox contentContainer = new VBox(lbTitulo, tabelaPontuacoes);
        contentContainer.setAlignment(Pos.CENTER);
        contentContainer.setSpacing(10);

        this.setCenter(contentContainer);

        ImageView imageView = new ImageView(ImageManager.getImage("TOPfundo.jpg"));
        imageView.fitWidthProperty().bind(this.widthProperty());
        imageView.fitHeightProperty().bind(this.heightProperty());
        this.getChildren().add(0, imageView);

    }

    private void carregarPontuacoes() {
        List<Pontuacao> pontuacoes = lerTop5PontuacoesDoArquivo();
        ObservableList<Pontuacao> listaPontuacoes = FXCollections.observableArrayList(pontuacoes);
        tabelaPontuacoes.setItems(listaPontuacoes);
    }

    private List<Pontuacao> lerTop5PontuacoesDoArquivo() {
        List<Pontuacao> pontuacoes = new ArrayList<>();
        File arquivo = new File("top5.ser");

        if (!arquivo.exists()) {
            return pontuacoes;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            pontuacoes = (List<Pontuacao>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler o arquivo top5.ser: " + e.getMessage());
        }

        return pontuacoes;
    }
}



        

