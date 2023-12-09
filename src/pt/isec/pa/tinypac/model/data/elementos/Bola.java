package pt.isec.pa.tinypac.model.data.elementos;


import pt.isec.pa.tinypac.model.data.IMazeElement;

/**
 * Classe da Bola normal
 * @author Beatriz Maia
 * @version 1.0.0
 */
public class Bola implements IMazeElement {
    /**
     * símbolo da bola
     */
    private  char symbol;

    /**
     * Construtor padrão da classe Bola
     * Define o símbolo como 'o'
     */
    public Bola(){
        this.symbol = 'o';
    }

    /**
     * Obtém o símbolo da Bola
     * @return símbolo da Bola
     */
    @Override
    public char getSymbol(){return symbol;}
}
