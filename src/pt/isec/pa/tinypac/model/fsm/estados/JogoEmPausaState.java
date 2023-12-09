package pt.isec.pa.tinypac.model.fsm.estados;

import pt.isec.pa.tinypac.model.data.Direction;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.TinyPacData;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

/**
 * Classe que representa o estado doo jogo quando este está em pausa
 * <p>
 * Neste estado, o jogo para de correr e espera que o jogador volte a jogar
 * ou saia. Também guarda o estado do jogo anterior.
 * @author Beatriz Maia
 * @version 1.0.0
 */
public class JogoEmPausaState extends TinyPacStateAdapter {
    private TinyPacState ultimoEstado;

    /**
     * Construtor da classe JogoEmPausaState
     *
     * @param context o contexto do do jogo - usado para mudar o estado
     * @param data Os dados do jogo.
     * @param ultimoEstado O último estado do jogo antes da pausa
     */
    public JogoEmPausaState(TinyPacContext context, TinyPacData data,TinyPacState ultimoEstado) {
        super(context, data);
        this.ultimoEstado=ultimoEstado;
        //System.out.printf("\n\nULTIMO ESTADO ANTES DO PAUSE: %s", ultimoEstado);
    }

    /**
     * Retorna o estado atual do jogo.
     *
     * @return O estado atual do jogo.
     */
    @Override
    public TinyPacState getState() {
        return TinyPacState.JOGO_EM_PAUSA;
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
     * Retorna o número de vidas do Pac-Man.
     *
     * @return O número de vidas do Pac-Man.
     */
    @Override
    public int getVidas(){
        return data.getVidas();
    }

    /**
     * Retorna o último estado do jogo antes da pausa.
     * 
     * @return O último estado do jogo antes da pausa.
     */
    @Override
    public TinyPacState getUltimoEstado(){
        return ultimoEstado;
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
     * Retoma o jogo, alterando para o último estado do jogo antes da pausa
     */
    @Override
    public void resumeGame(){
        changeState(ultimoEstado);
    }
}
