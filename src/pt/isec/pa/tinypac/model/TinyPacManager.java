package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Direction;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.elementos.PacMan;
import pt.isec.pa.tinypac.model.data.elementos.Pinky;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * A classe TinyPacManager é responsável por gerir os estados do jogo e as suas propriedades
 * Também é responsável pela comunicação entre gameEngine e GameContext
 *  @author Beatriz Maia
 *  @version 1.0.0
 */
public class TinyPacManager implements IGameEngineEvolve {

    PropertyChangeSupport pcs;
    TinyPacContext context;
    private boolean mostrarCred = false;
    private boolean mostrarTOP5 = false;

    private boolean isPaused = false;
    private PacMan pacman;
    private Pinky pinky;


    public static final String PROP_CREDITOS = "creditos";
    public static final String PROP_TOP5 = "Top5";
    public static final String PROP_STARTGAME = "startGame";
    public static final String PROP_ATUALIZA = "atualiza";

    /**
     * Construtor da classe TinyPacManager
     * Inicializa a instância de PropertyChangeSupport
     */
    public TinyPacManager() {
        pcs = new PropertyChangeSupport(this);
    }

    /**
     * Inicia o jogo
     * Cria uma nova instância de TinyPacContext e "dispara" o evento PROP_STARTGAME
     */
    public void startGame() {
        context = new TinyPacContext();
        context.startGame();
        pcs.firePropertyChange(PROP_STARTGAME, null, null);

    }

    /**
     * Encerra o jogo
     * Define o estado como nulo e "dispara" o evento PROP_STARTGAME
     */
    public void endGame() {
        context.estadoAnull();
        pcs.firePropertyChange(PROP_STARTGAME, null, null);

    }

    /**
     * Altera a direção do Pac-Man
     * @param direcao direção desejada para o Pac-Man
     */
    public void mudarDirecaoPacman(Direction direcao){
        context.setDirection(direcao);

    }

    /**
     * Obtém o estado atual do jogo
     * @return O estado atual do jogo
     */
    public TinyPacState getState() {
        if(context!=null){
            return context.getState();
        }else{
            return null;
        }

    }

    /**
     * O método utiliza a instância de PropertyChangeSupport chamada pcs para adicionar o listener
     * O método addPropertyChangeListener recebe a propriedade e o listener como argumentos e faz a associação entre eles
     * @param property propriedade para a qual o listener será adicionado.
     * @param listener listener a ser adicionado
     */
    public void addClient(String property, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(property, listener);
    }



    /**
     * Verifica se é necessário mostrar os créditos
     * @return true se os créditos devem ser mostrados, false caso contrário
     */
    public boolean isMostrarCred() {
        return mostrarCred;
    }

    /**
     * Verifica se é necessário mostrar o TOP5
     * @return true se o TOP5 deve ser mostrado, false caso contrário
     */
    public boolean isMostrarTOP5() {
        return mostrarTOP5;
    }

    /**
     * Obtém os pontos atuais do jogo
     * @return Os pontos atuais do jogo
     */
    public int getPontosAtuais(){
        return context.getPontosAtuais();
    }


    /**
     Define se o modo de exibição de créditos está ativo ou não
     Dispara o evento de mudança de créditos
     @param mostrarCred true para ativar o modo de exibição de créditos, false para desativá-lo
     */
    public void setMostrarCred(boolean mostrarCred) {
        this.mostrarCred = mostrarCred;
        pcs.firePropertyChange(PROP_CREDITOS, null, null);
    }

    /**

     Define se o modo de exibição do Top 5 está ativo ou não
     Dispara o evento de mudança do Top 5
     @param mostrarTop5 true para ativar o modo de exibição do Top 5, false para desativá-lo
     */
    public void setMostrarTop5(boolean mostrarTop5) {
        this.mostrarTOP5 = mostrarTop5;
        pcs.firePropertyChange(PROP_TOP5, null, null);
    }


    public Maze getLabirinto() {
        return context.getLabirinto();
    }


    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

        if ( context!=null) {
            context.move();
            pcs.firePropertyChange(PROP_ATUALIZA, null, null);
        }
    }


    public void setDirection(Direction direction) {
        if (pacman != null) {
            context.setDirection(direction);
        }
    }

    public int getVidas(){
        return context.getVidas();
    }

    public Direction getDirection(){
        return context.getDirection();
    }
    public void saveGame() {
        context.saveGame();
    }

    public void loadGame(){
        context= new TinyPacContext();
        context.loadGame();
        pcs.firePropertyChange(PROP_ATUALIZA,null,null);
    }

    public boolean verificarJogoGuardado(){
        try(ObjectInputStream ois= new ObjectInputStream(
                new FileInputStream("saves/JogoGuardado.dat")
        )){
            return true;
        }
        catch(Exception e){
            System.err.println("Nao foi encontrado nenhum jogo");
            return false;
        }
    }


    public void pauseGame() {
        context.pauseGame();
    }

    public void resumeGame() {
        // Retomar o jogo
        context.resumeGame();
    }

    public boolean getpodeTop5() {
        return context.getpodeTop5();
    }

    public void atualizaTOP5(String nomeJogador) {
        context.atualizaTOP5(nomeJogador);
    }


}

