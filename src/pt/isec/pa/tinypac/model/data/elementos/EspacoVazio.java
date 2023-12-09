package pt.isec.pa.tinypac.model.data.elementos;

import pt.isec.pa.tinypac.model.data.IMazeElement;

/**
 * Classe para um espaço em branco
 * @author Beatriz Maia
 * @version 1.0.0
 */
public class EspacoVazio implements IMazeElement {
    private final char symbol;

    /**
     * Construtor padrão da classe EspacoVazio
     * Define o símbolo como ' '
     */
    public EspacoVazio(){
        this.symbol = ' ';
    }

    /**
     * Obtém o símbolo do EspacoVazio
     * @return O símbolo EspacoVazio
     */
    @Override
    public char getSymbol() {
        return symbol;
    }
}
