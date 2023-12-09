package pt.isec.pa.tinypac.model.fsm.estados;

import pt.isec.pa.tinypac.model.data.Direction;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.TinyPacData;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

/**
 * Estado do jogo quando ele termina
 * <p>
 * Este estado trata as operações que acontecem quando o jogo acaba como verificar
 * se a pontuação do jogador se qualifica para o TOP 5
 * @author Beatriz Maia
 * @version 1.0.0
 */
public class PacmanGameOverState extends TinyPacStateAdapter {

    boolean adicionarTOP5;

    /**
     * Construtor da classe PacmanGameOverState
     *
     * @param context contexto do jogo para mudar de estado
     * @param data os dados do TinyPac.
     */
    public PacmanGameOverState(TinyPacContext context, TinyPacData data) {
        super(context, data);
        adicionarTOP5=data.podeTOP5();

    }

    /**
     * Verifica se é possível adicionar o jogador ao TOP 5
     *
     * @return true se for possível adicionar o jogador ao TOP 5, false caso contrário
     */
    public boolean getpodeTop5(){
        return adicionarTOP5;
    }

    /**
     * Atualiza o TOP 5 com o nome do jogador
     *
     * @param nomeJogador o nome do jogador a ser adicionado ao TOP 5
     */
    public void atualizaTOP5(String nomeJogador){
        data.atualizaTOP5(nomeJogador);
    }

    /**
     * Obtém a pontuação atual do jogo.
     *
     * @return a pontuação atual do jogo.
     */
    @Override
    public int getPontosAtuais(){
        return data.getPontosAtuais();
    }

    /**
     * Obtém o número de vidas restantes
     *
     * @return o número de vidas restantes
     */
    @Override
    public int getVidas(){
        return data.getVidas();
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
     * Obtém o estado atual do TinyPac
     *
     * @return o estado atual do TinyPac
     */
    @Override
    public TinyPacState getState() {
        return TinyPacState.PACMAN_GAMEOVER;
    }
}
