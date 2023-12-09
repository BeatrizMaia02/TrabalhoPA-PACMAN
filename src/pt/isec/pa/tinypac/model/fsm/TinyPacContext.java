package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.data.Direction;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.TinyPacData;
import pt.isec.pa.tinypac.model.data.elementos.PacMan;

import java.io.*;
/**
 * Esta classe é responsavel por manter o estado do jogo atual e controlar as transiçoes de estado
 * @author Beatriz Maia
 * @version 1.0.0
 */
public class TinyPacContext implements Serializable {
    private static final long serialVersionUID = 1L;

    private TinyPacData tinyPacData;
    private ITinyPacState estadoAtual;
    private boolean carrega_jogo=false;

    /**
     * Contrutor para um novo objeto TinyPacContext.
     * Inicia a gameEngine
     */
    public TinyPacContext() {
        tinyPacData = new TinyPacData();
        GameEngine gameEngine = new GameEngine();
    }

    /**
     *Tenta carregar o jogo anterior caso isso não aconteça começa um jogo a apartir do nivel 1
     */
    public void startGame(){
        //tinyPacData.setNivelAtual(1);

        if(!carrega_jogo){

            estadoAtual= TinyPacState.createState(TinyPacState.PACMAN_INICIO,this, this.tinyPacData,TinyPacState.PACMAN_INICIO);
            estadoAtual.startGame();
        }
    }

    /**
     *Mete o estado a null de forma a acabar o jogo
     */
    public void estadoAnull(){
        estadoAtual=null;
    }

    /**
     *Devolve o estado atual do jogo
     */
    public TinyPacState getState(){
        if(estadoAtual==null){
            return null;
        }
        return estadoAtual.getState();
    }

    /**
     *Devolve o labirinto
     */
    public Maze getLabirinto(){
        return estadoAtual.getLabirinto();
    }

    /**
     * chama o metodo move referente ao estado atual
     */
    public void move(){
        estadoAtual.move();
    }

    /**
     *Altera o estado atual do jogo
     * @param novoEstado o estado para o qual vai mudar
     */
    void changeState(ITinyPacState novoEstado){
        estadoAtual=novoEstado;
    }

    /**
     * Define a direção do movimento do jogador.
     *
     * @param direction A direção do movimento do jogador.
     */
    public void setDirection(Direction direction){
        estadoAtual.setDirection(direction);
    }

    /**
     * Verifica se é possível adicionar o jogador ao TOP 5.
     *
     * @return true se for possível adicionar ao TOP 5, false caso contrário.
     */
    public boolean getpodeTop5(){
        return estadoAtual.getpodeTop5();
    }

    /**
     * Obtém o número de vidas do jogador.
     *
     * @return O número de vidas do jogador.
     */
    public int getVidas(){
        return estadoAtual.getVidas();
    }

    /**
     * Obtém a direção do movimento do jogador.
     *
     * @return A direção do movimento do jogador.
     */
    public Direction getDirection(){
        return tinyPacData.getDirection();
    }

    /**
     * Atualiza o TOP 5 com o nome do jogador.
     *
     * @param nomeJogador O nome do jogador a ser adicionado ao TOP 5.
     */
    public void atualizaTOP5(String nomeJogador){
        estadoAtual.atualizaTOP5(nomeJogador);
    }

    /**
     * Salva o estado atual do jogo em um arquivo.
     */
    public void saveGame(){
        try(ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("saves/JogoGuardado.dat")
        )){
            oos.writeObject(tinyPacData);
            oos.writeObject(estadoAtual.getUltimoEstado());
        }
        catch(Exception e){
            System.err.println("Erro ao Salvar Ficheiro");
        }
    }

    /**
     * Carrega o estado do jogo a partir de um arquivo.
     */
    public void loadGame(){
        try(ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("saves/JogoGuardado.dat")
        )){
            tinyPacData = (TinyPacData) ois.readObject();
            estadoAtual = TinyPacState.createState(TinyPacState.PACMAN_INICIO, this, tinyPacData, TinyPacState.PACMAN_INICIO);
            carrega_jogo = true;
        }
        catch(Exception e){
            System.err.println("Erro ao carregar ficheiro");
            carrega_jogo=false;
        }
    }

    /**
     * Obtém a pontuação atual do jogador.
     *
     * @return A pontuação atual do jogador.
     */
    public int getPontosAtuais() {
        return estadoAtual.getPontosAtuais();
    }

    /**
     * Pausa o jogo.
     */
    public void pauseGame() {
        estadoAtual.pauseGame();
    }

    /**
     * Resuma o jogo a partir do estado pausado.
     */
    public void resumeGame() {
        estadoAtual.resumeGame();
    }
}
