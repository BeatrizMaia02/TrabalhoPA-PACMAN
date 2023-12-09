package pt.isec.pa.tinypac.model.fsm.estados;

import pt.isec.pa.tinypac.model.data.Direction;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.TinyPacData;
import pt.isec.pa.tinypac.model.data.elementos.Blinky;
import pt.isec.pa.tinypac.model.data.elementos.PacMan;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

/**
 * Classe que representa o estado em que o fantasma se começa a mexer
 * <p>
 * Este estado controla o movimento do pacman e a transição para o estado MoverFantasmasState passado um
 * determinado tempo.
 * @author Beatriz Maia
 * @version 1.0.0
 */
public class MoverPacmanState extends TinyPacStateAdapter {

    /**
     * Construtor da classe MoverPacmanState.
     * @param context o contexto do do jogo - usado para mudar o estado
     * @param data Os dados do jogo.
     */
    public MoverPacmanState(TinyPacContext context, TinyPacData data) {
        super(context, data);
    }

    /**
     * Move o Pac-Man e verifica quando é que passa o tempo para mover os fantasmas.
     * Este método deve ser chamado no loop principal do jogo.
     */
    @Override
    public void move(){
        data.movePacMan();
        if(data.getContadorSegundos() >= 5 ){
            data.moveBlinky();
            changeState(TinyPacState.MOVER_FANTASMAS);

        }
        data.addContadorSegundos(1);
    }

    /**
     * Retorna o número de vidas do Pac-Man.
     *
     * @return O número de vidas do Pac-Man.
     */
    @Override
    public int getVidas(){
        return data.getVidas();
    }

    /**
     * Retorna os pontos atuais do jogo.
     *
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
     *
     * @return O estado atual do jogo.
     */
    @Override
    public TinyPacState getState() {
        return TinyPacState.MOVER_PACMAN;
    }
}
