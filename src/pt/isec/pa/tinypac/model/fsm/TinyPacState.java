package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.TinyPacData;
import pt.isec.pa.tinypac.model.fsm.estados.*;

import java.io.Serializable;
/**
 * O enum TinyPacState representa os diferentes estados do jogo TinyPac.
 */
public enum TinyPacState implements Serializable {
    JOGO_EM_PAUSA, MOVER_PACMAN, PACMAN_INICIO, MOVER_FANTASMAS, FANTASMAS_VULNERAVEIS, PACMAN_GAMEOVER;

    /**
     * Cria uma instância do estado correspondente ao tipo fornecido.
     *
     * @param type          O tipo de estado a ser criado.
     * @param context       O contexto do jogo TinyPac.
     * @param data          Os dados do jogo TinyPac.
     * @param ultimoEstado  O último estado do jogo.
     * @return              Uma instância do estado correspondente ao tipo fornecido.
     */
    static ITinyPacState createState(TinyPacState type, TinyPacContext context, TinyPacData data, TinyPacState ultimoEstado) {
        return switch (type) {
            case PACMAN_INICIO -> new PacmanInicioState(context, data);
            case MOVER_PACMAN -> new MoverPacmanState(context, data);
            case MOVER_FANTASMAS -> new MoverFantasmasState(context, data);
            case FANTASMAS_VULNERAVEIS -> new FantasmasVulneraveisState(context, data);
            case PACMAN_GAMEOVER -> new PacmanGameOverState(context, data);
            case JOGO_EM_PAUSA -> new JogoEmPausaState(context, data, ultimoEstado);
        };
    }
}
