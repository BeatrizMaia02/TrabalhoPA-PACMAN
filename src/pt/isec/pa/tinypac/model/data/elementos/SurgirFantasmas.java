package pt.isec.pa.tinypac.model.data.elementos;

import pt.isec.pa.tinypac.model.data.IMazeElement;

/**
 * Classe do SurgirFantasma
 * @author Beatriz Maia
 * @version 1.0.0
 */
public class SurgirFantasmas implements IMazeElement {
    private char symbol;

    /**
     * Construtor padrão da classe SurgirFantasma
     * Define o símbolo como 'Y'
     */
    public SurgirFantasmas() {
        this.symbol = 'Y';
    }

    /**
     * Obtém o símbolo do SurgirFantasma
     * @return símbolo do SurgirFantasma
     */
    @Override
    public char getSymbol() {
        return symbol;
    }
}

