package pt.isec.pa.tinypac.model.data;

import java.io.Serializable;
/**
 * A classe Pontuacao representa uma pontuação associada a um nome
 * armazena o nome do jogador e sua pontuação
 *  @author Beatriz Maia
 *  @version 1.0.0
 */
public class Pontuacao implements Serializable {
    /**
     * O nome do jogador
     */
    private String nome;

    /**
     * pontuacao do jogador
     */
    private int pontuacao;

    /**
     * Cria um objeto Pontuacao com o nome e a pontuação
     * @param nome O nome do jogador
     * @param pontuacao A pontuação obtida pelo jogador
     */
    public Pontuacao(String nome, int pontuacao) {
        this.nome = nome;
        this.pontuacao = pontuacao;
    }

    public String getNome() {
        return nome;
    }

    /**
     * Retorna a pontuação do jogador
     * @return pontuação obtida pelo jogador
     */
    public int getPontuacao() {
        return pontuacao;
    }
}