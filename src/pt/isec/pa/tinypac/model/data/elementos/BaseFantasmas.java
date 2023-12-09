package pt.isec.pa.tinypac.model.data.elementos;

import pt.isec.pa.tinypac.model.data.IMazeElement;


/**
 * Classe da base dos fantasmas
 * @author Beatriz Maia
 * @version 1.0.0
 */
public class BaseFantasmas implements IMazeElement {
    /**
     * símbolo da Base Fantasmas
     */
    private char symbol;


    /**
     * Construtor padrão da classe BaseFantasmas
     * Define o símbolo como 'y'
     */
    public BaseFantasmas(){
        this.symbol = 'y';
    }

    /**
     * Obtém o símbolo da BaseFantasmas
     * @return O símbolo da BaseFantasmas
     */
    @Override
    public char getSymbol() {
        return symbol;
    }
}

