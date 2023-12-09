package pt.isec.pa.tinypac.model.fsm.estados;

import pt.isec.pa.tinypac.model.data.Direction;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.TinyPacData;
import pt.isec.pa.tinypac.model.data.elementos.Blinky;
import pt.isec.pa.tinypac.model.data.elementos.Clyde;
import pt.isec.pa.tinypac.model.data.elementos.PacMan;
import pt.isec.pa.tinypac.model.data.elementos.Pinky;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

/**
 * Classe que representa o estado dos fantasmas vulneráveis no jogo
 * <p>
 * Os fantasmas ficam vulneraveis depois do Pac-man comer uma bola de podderes
 * Controla o comportamente do jogo e fantasmas neste estado
 * @author Beatriz Maia
 * @version 1.0.0
 */
public class FantasmasVulneraveisState extends TinyPacStateAdapter {
    /**
     * Construtor da classe FantasmasVulneraveisState
     *
     * @param context o contexto do do jogo - usado para mudar o estado
     * @param data os dados do TinyPac
     */
    public FantasmasVulneraveisState(TinyPacContext context, TinyPacData data) {
        super(context, data);
    }

    /**
     * Método responsável por mover os fantasmas quando estão vulneráveis.
     *
     * Este metodo é chamado no loop do jogo e faz com que os fantasmas e o Pac-man se movam
     * e confirma quando é que estes voltam ao estado normal
     */

    @Override
    public void move(){
        if(data.getSegundosVulnerabilidade()==0){
            data.setVulneravelBlinky(false); // Definir Blinky como não vulnerável
            data.setVulneravelClyde(false); // Definir Clyde como não vulnerável
            data.resetSegundosVulnerabilidade();
            changeState(TinyPacState.MOVER_FANTASMAS);
            return;
        }

        data.movePacMan();
        if(data.comeuBolasTodas()){
            data.startGame();
            data.resetHistoricoClyde();
            data.resetHistoricoBlinky();
            changeState(TinyPacState.PACMAN_INICIO);
            return;//adicionado 18
        }
        data.moveBlinky();
        data.moveClyde();

        if(data.getXBlinky()== data.getFantasmaHomeX() && data.getYBlinky() == data.getFantasmaHomeY()){
            data.resetHistoricoBlinky();
            data.setVulneravelBlinky(false);
            data.setVulneravelClyde(false);
            changeState(TinyPacState.MOVER_FANTASMAS);
            return;
        } else if(data.getXClyde()== data.getFantasmaHomeX() && data.getYClyde()== data.getFantasmaHomeY()){
            data.resetHistoricoClyde();
            data.setVulneravelClyde(false);
            data.setVulneravelBlinky(false);
            changeState(TinyPacState.MOVER_FANTASMAS);
            return;
        }

        data.diminuiSegundosVulnerabilidade();
        data.addContadorSegundos(1);
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
     * Muda o estado do jogo para jogo em pausa
     */
    @Override
    public void pauseGame(){
        changeState(TinyPacState.JOGO_EM_PAUSA);
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

    /**
     * Obtém o estado atual
     *
     * @return o estado atual
     */
    @Override
    public TinyPacState getState() {
        return TinyPacState.FANTASMAS_VULNERAVEIS;
    }
    

}
