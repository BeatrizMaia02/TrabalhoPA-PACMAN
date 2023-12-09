package pt.isec.pa.tinypac.model.data.elementos;

import pt.isec.pa.tinypac.model.data.IMazeElement;

/**
 * Classe da Bola com Poderes
 * @author Beatriz Maia
 * @version 1.0.0
 */
public class BolaComestivel implements IMazeElement {
    /**
     * símbolo da Bola com poderes
     */
    private char symbol;

    /**
     * Construtor padrão da classe BolaComestivel
     * Define o símbolo como 'O'
     */
    public BolaComestivel(){
        this.symbol = 'O';
    }

    /**
     * Obtém o símbolo da BolaComestivel
     * @return O símbolo da BolaComestivel
     */
    @Override
    public char getSymbol() {
        return symbol;
    }
}
