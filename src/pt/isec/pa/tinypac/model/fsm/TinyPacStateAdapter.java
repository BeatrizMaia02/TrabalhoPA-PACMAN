package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Direction;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.TinyPacData;
/**
 * Classe abstrata que serve de base para os diferentes estados do jogo
 * Implementa a interface do ITInyPacState e apresenta as implementações default.
 */
public abstract class TinyPacStateAdapter implements ITinyPacState {
    protected TinyPacContext context;
    protected TinyPacData data;

    protected TinyPacStateAdapter(TinyPacContext context, TinyPacData data) {
        this.data = data;
        this.context = context;
    }


    @Override
    public boolean getpodeTop5() {
        return false;
    }

    @Override
    public void atualizaTOP5(String nomeJogador) {
    }

    @Override
    public int getPontosAtuais() {
        return 0;
    }

    @Override
    public void pauseGame() {

    }

    @Override
    public void resumeGame() {

    }

    @Override
    public void move() {
    }

    @Override
    public Maze getLabirinto(){
        return null;
    }

    @Override
    public int getVidas() {
        return 0;
    }

    @Override
    public void setDirection(Direction direction){

    }

    @Override
    public TinyPacState getUltimoEstado() {
        return null;
    }

    @Override
    public void startGame(){

    }



    protected void changeState(TinyPacState novoEstado) {
        context.changeState(TinyPacState.createState(novoEstado, context, data, getState()));
    }

}
