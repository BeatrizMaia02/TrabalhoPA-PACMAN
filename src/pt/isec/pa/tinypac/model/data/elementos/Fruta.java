package pt.isec.pa.tinypac.model.data.elementos;

import pt.isec.pa.tinypac.model.data.IMazeElement;

/**
 * Classe da Fruta
 * @author Beatriz Maia
 * @version 1.0.0
 */
public class Fruta implements IMazeElement {
    private char symbol;

    /**
     * Construtor padrão da classe Fruta
     * Define o símbolo como 'F'
     */
    public Fruta(){
        this.symbol = 'F';
    }

    /**
     * Obtém o símbolo da Fruta
     * @return O símbolo da Fruta
     */
    @Override
    public char getSymbol() {
        return symbol;
    }

}
