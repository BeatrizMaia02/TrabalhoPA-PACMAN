package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Direction;
import pt.isec.pa.tinypac.model.data.Maze;

import java.io.Serializable;

/**
 * Define a interface para o estado do jogo.
 * <p>
 * O estado do jogo representa a forma como o jogo se comporta.
 * @author Beatriz Maia
 * @version 1.0.0
 */
public interface ITinyPacState extends Serializable {
    /**
     * UID para serializaçao
     */


     static final long serialVersionUID = 1L;

    /**
     * Obtém o estado atual do jogo.
     *
     * @return O estado atual do jogo.
     */
    TinyPacState getState();


    /**
     * Define o comportamento que o jogo deve ter. As implemtações definem o comportamento dependendo do estado do jogo.
     */
    void move();

    /**
     * Devolve o número atual de vidas
     */
    int getVidas();


    /**
     * Devolve o último estado
     */
    TinyPacState getUltimoEstado();

    /**
     * verifica se o jogador pode entrar para o top 5
     */
     boolean getpodeTop5();

    /**
     * atualiza o top 5 com o nome do jogador
     *
     * @param nomeJogador nome do jogador que vai ser adicionado
     */
     void atualizaTOP5(String nomeJogador);

    /**
     * Obtem pontos atuais
     */
     int getPontosAtuais();

    /**
     * Define o comportamente para voltar ao jogo depois do estado de pausa
     */
     void resumeGame();

    /**
     * Define o comportamento para por o jogo em pausa
     */
     void pauseGame();

    Maze getLabirinto();

    void setDirection(Direction direction);

    void startGame();
}

