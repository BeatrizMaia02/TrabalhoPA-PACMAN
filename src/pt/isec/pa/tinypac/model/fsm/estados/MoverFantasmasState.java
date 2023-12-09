package pt.isec.pa.tinypac.model.fsm.estados;

import pt.isec.pa.tinypac.model.data.Direction;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.TinyPacData;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

/**
 * Estado do jogo quando os fantasmas estão a mover se
 * <p>
 * Este estado controla o movimento dos fantasmas e do Pacman e transições para outros estados tendo em conta
 * determinadas condições como comer todas as bolas e perdaer as vidas.
 * @author Beatriz Maia
 * @version 1.0.0
 */
public class MoverFantasmasState extends TinyPacStateAdapter {

    /**
     * Construtor da classe MoverFantasmasState.
     *
     * @param context O o contexto do do jogo - usado para mudar o estado
     * @param data Os dados do jogo.
     */
    public MoverFantasmasState(TinyPacContext context, TinyPacData data) {
        super(context, data);
    }


    /**
     * Controla o movimento das personagens de jogo durante este estado
     *
     * O move confirma se o Pac-man pode comer fantasmas, se ainda tem vidas e se já acabou o nivel.
     * Muda os estados de acordo com isso
     */
    @Override
    public void move(){
        data.movePacMan();
        if(data.comeuBolaComestivel()){
            data.setVulneravelBlinky(true); // Definir Blinky como vulnerável
            data.setVulneravelClyde(true); // Definir Clyde como vulnerável
           // Pinky.setVulneravel(true);
            changeState(TinyPacState.FANTASMAS_VULNERAVEIS);
            return;
        }

        if(data.comeuBolasTodas()){
            data.startGame();
            data.resetHistoricoClyde();
            data.resetHistoricoBlinky();
            changeState(TinyPacState.PACMAN_INICIO);
            return;
        }


        data.moveBlinky();

        //System.out.printf("\n\nSEGUNDOS:%d\nBLINKY SEGUNDOS:%d", data.getContadorSegundos(), Blinky.getSegundosMovimento());

        if(data.getContadorSegundos() - data.getSegundosMovimentoBlinky () >=5){
            data.moveClyde();
        } else{
            data.resetClyde();
        }

        data.addContadorSegundos(1);
        if(data.getVidas()==0){
            changeState(TinyPacState.PACMAN_GAMEOVER);

        }


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
     * Obtém a pontuação atual do jogo.
     *
     * @return a pontuação atual do jogo.
     */
    @Override
    public int getPontosAtuais(){
        return data.getPontosAtuais();
    }

    /**
     * Muda o estado do jogo para jogo em pausa
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
     * Obtém o estado atual
     *
     * @return o estado atual
     */
    @Override
    public TinyPacState getState() {
        return TinyPacState.MOVER_FANTASMAS;
    }
}
