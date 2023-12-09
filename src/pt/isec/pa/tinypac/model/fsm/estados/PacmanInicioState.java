package pt.isec.pa.tinypac.model.fsm.estados;

import pt.isec.pa.tinypac.model.data.Direction;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.TinyPacData;
import pt.isec.pa.tinypac.model.data.elementos.PacMan;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

/**
 * Classe que representa o estado do jogo quando este acabou de começar
 * <p>
 * Este estado é usado no inicio do jogo onde espera até que o jogador inicie o movimento do pacman.
 * Quando isso acontece muda o estado para MoverPacmanState
 * @author Beatriz Maia
 * @version 1.0.0
 */
public class PacmanInicioState extends TinyPacStateAdapter {

    /**
     * Construtor da classe PacmanInicioState.
     * @param context  o contexto do do jogo - usado para mudar o estado
     * @param data Os dados do jogo.
     */
    public PacmanInicioState(TinyPacContext context, TinyPacData data) {
        super(context, data);
    }

    /**
     * Move o Pac-Man apenas se uma direção for definida.
     *
     * Quando uma direção é definida, o estado é alterado para MoverPacmanState.
     */
    @Override
    public void move(){
        if(data.getDirection() != Direction.NONE){
            data.movePacMan();
            changeState(TinyPacState.MOVER_PACMAN);
            data.addContadorSegundos(1);
        }

    }

    /**
     * Retorna o número de vidas do Pac-Man.
     * @return O número de vidas do Pac-Man.
     */
    @Override
    public int getVidas(){
        return data.getVidas();
    }

    /**
     * Retorna os pontos atuais do jogo.
     * @return Os pontos atuais do jogo.
     */
    @Override
    public int getPontosAtuais(){
        return data.getPontosAtuais();
    }

    /**
     * Pausa o jogo, alterando para o estado de jogo em pausa.
     */
    @Override
    public void pauseGame(){
        changeState(TinyPacState.JOGO_EM_PAUSA);
    }


    @Override
    public Maze getLabirinto(){
        return data.getLabirinto();
    }

    @Override
    public void setDirection(Direction direction){
        data.setDirection(direction);
    }

    @Override
    public void startGame(){
        data.startGame();
    }


    /**
     * Retorna o estado atual do jogo.
     * @return O estado atual do jogo.
     */
    @Override
    public TinyPacState getState() {
        return TinyPacState.PACMAN_INICIO;
    }
}
