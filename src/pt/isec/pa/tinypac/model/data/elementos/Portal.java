package pt.isec.pa.tinypac.model.data.elementos;

import pt.isec.pa.tinypac.model.data.IMazeElement;

/**
 * Classe da Portal
 * @author Beatriz Maia
 * @version 1.0.0
 */
public class Portal implements IMazeElement {
    private final char symbol;

    /**
     * Construtor padrão da classe Portal
     * Define o símbolo como 'W'
     */
    public Portal(){
        this.symbol = 'W';
    }

    /**
     * Obtém o símbolo do Portal
     * @return símbolo do Portal
     */
    @Override
    public char getSymbol() {
        return symbol;
    }
}
