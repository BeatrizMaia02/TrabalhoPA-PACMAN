package pt.isec.pa.tinypac.model.data.elementos;

import pt.isec.pa.tinypac.model.data.IMazeElement;

/**
 * Classe da Parede
 * @author Beatriz Maia
 * @version 1.0.0
 */
public class Parede implements IMazeElement {
    private char symbol;

    /**
     * Construtor padrão da classe Parede
     * Define o símbolo como 'x'
     */
    public Parede(){
        this.symbol = 'x';
    }

    /**
     * Obtém o símbolo da Parede
     * @return símbolo da Parede
     */
    @Override
    public char getSymbol() {
        return symbol;
    }
}
