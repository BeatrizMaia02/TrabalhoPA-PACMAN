package pt.isec.pa.tinypac.model.data.elementos;

import pt.isec.pa.tinypac.model.data.IMazeElement;


/**
 * Classe da base do Pacman
 * @author Beatriz Maia
 * @version 1.0.0
 */
public class BasePacMan implements IMazeElement {

    /**
     * símbolo da Base do Pacman
     */
    private char symbol;


    /**
     * Construtor padrão da classe BasePacMan
     * Define o símbolo como 'M'
     */
    public BasePacMan(){
        this.symbol = 'M';
    }


    /**
     * Obtém o símbolo da BasePacMan
     * @return O símbolo da BasePacMan
     */
    @Override
    public char getSymbol() {
        return symbol;
    }
}