package pt.isec.pa.tinypac.model.data.elementos;

import pt.isec.pa.tinypac.model.data.*;

/**
 * Classe do Pacman
 * @author Beatriz Maia
 * @version 1.0.0
 */
public class PacMan implements IMazeElement {

    private int x;
    private int y;
    private TinyPacData tinyPacData;
    private Direction direction;
    private int vidas;
    private boolean dentroPortal = false;
    private boolean comeBolaComestivel;


    /**
     * Construtor da classe PacMan
     * @param direction A direção inicial do PacMan
     * @param data      Os dados do jogo
     */
    public PacMan(Direction direction, TinyPacData data) {
        this.direction = direction;
        tinyPacData = data;
        vidas=3;
    }

    /**
     * Retorna a coordenada X atual do PacMan
     * @return coordenada X atual do PacMan
     */
    public int getPacmanX() {
        return x;
    }

    /**
     * Retorna a coordenada Y atual do PacMan
     * @return coordenada Y atual do PacMan
     */
    public int getPacmanY() {
        return y;
    }

    /**
     * Define a coordenada Y do PacMan
     * @param ynew nova coordenada Y do PacMan
     */
    public void setPacmanY(int ynew) {
        y = ynew;
    }

    /**
     * Define a coordenada X do PacMan
     * @param xnew A nova coordenada X do PacMan
     */
    public void setPacmanX(int xnew) {
        x = xnew;
    }


    /**
     * Define a direção do PacMan
     * @param newdirection nova direção do PacMan
     */
    public void setDirection(Direction newdirection) {
        direction = newdirection;
    }

    /**
     * Retorna a direção atual do PacMan
     * @return direção atual do PacMan
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Retorna o número de vidas do PacMan
     * @return número de vidas do PacMan
     */
    public int getVidas() {
        return vidas;
    }

    /**
     * Diminui uma vida do PacMan
     */
    public void perdeVida() {
        vidas--;
    }

    /**
     * Verifica se o PacMan comeu uma bola com poderes
     * @return true se o PacMan comeu uma bola com poderes, caso contrário, false
     */
    public boolean comeuBolaComestivel() {
        return comeBolaComestivel;
    }


    /**
     * Move o PacMan no labirinto
     * @param maze labirinto onde o PacMan está localizado
     */
    public void movePacMan(Maze maze) {
        comeBolaComestivel = false;
        int nextX = x;
        int nextY = y;

        if (x == tinyPacData.getXBlinky() && y == tinyPacData.getYBlinky()) {
            if (!tinyPacData.estaVulneravelBlinky()) {
                perdeVida(); // PacMan perde uma vida

                tinyPacData.resetHistoricoClyde();
                tinyPacData.resetHistoricoBlinky();
                voltaPosicaoInicial(tinyPacData.getLabirinto());

                return;
            } else {
                tinyPacData.resetHistoricoBlinky();
                tinyPacData.aumentaContadorFantasmasComidos(1);
                tinyPacData.incrementaPontosAtuais(tinyPacData.getContadorFantasmasComidos()*50);
            }
        }

        if (x == tinyPacData.getXClyde() && y == tinyPacData.getYClyde()) {
            if (!tinyPacData.isVulneravelClyde()) {
                perdeVida(); // PacMan perde uma vida
                tinyPacData.resetHistoricoClyde();
                tinyPacData.resetHistoricoBlinky();
                voltaPosicaoInicial(tinyPacData.getLabirinto());

                return;
            } else {
                tinyPacData.resetHistoricoClyde();
                tinyPacData.aumentaContadorFantasmasComidos(1);
                tinyPacData.incrementaPontosAtuais(tinyPacData.getContadorFantasmasComidos()*50);
            }
        }

        // Calcula a próxima posição do PacMan com base na direção atual
        switch (direction) {
            case UP -> nextY--;
            case DOWN -> nextY++;
            case LEFT -> nextX--;
            case RIGHT -> nextX++;
        }

        // Obtém o próximo elemento no labirinto
        IMazeElement nextElement = maze.get(nextY, nextX);

        if (nextElement instanceof Parede) {
            return;
        }
        if (nextElement instanceof SurgirFantasmas) {
            return;
        }
        if (nextElement instanceof Bola) {
            tinyPacData.incrementaPontosAtuais(1);
            tinyPacData.incrementaContadorBolas(maze);
            tinyPacData.decrementaBolasRestantes();
        }
        if (nextElement instanceof BolaComestivel) {
            tinyPacData.setContadorFantasmasComidos(0);
            tinyPacData.incrementaPontosAtuais(10);
            tinyPacData.decrementaBolasRestantes();
            comeBolaComestivel = true;
        }
        if (nextElement instanceof Fruta) {
            tinyPacData.comeFruta();
            tinyPacData.setFrutaDesativada();
        }

        //para colocar um labirinto caso ele atravesse
        maze.set(y, x, dentroPortal ? new Portal() : new EspacoVazio());

        if (nextElement instanceof Portal) {

            boolean encontrado = false;

            // Percorre o labirinto à procura de outro portal
            for (int i = 0; i < maze.getMaze().length; i++) {
                for (int j = 0; j < maze.getMaze()[i].length; j++) {
                    if (maze.get(i, j) instanceof Portal && (i != nextY || j != nextX)) {
                        encontrado = true;

                        // Define a próxima posição como a posição do outro portal
                        nextY = i;
                        nextX = j;

                        dentroPortal = true;
                        break;
                    }
                }
                if (encontrado) {
                    break;
                }
            }
        } else {
            dentroPortal = false;
        }


        setPacmanX(nextX);
        setPacmanY(nextY);

        maze.set(y, x, this);

        if (x == tinyPacData.getXBlinky() && y == tinyPacData.getYBlinky()) {
            if (!tinyPacData.estaVulneravelBlinky()) {
                perdeVida(); // PacMan perde uma vida

                tinyPacData.resetHistoricoClyde();
                tinyPacData.resetHistoricoBlinky();
                voltaPosicaoInicial(tinyPacData.getLabirinto());
                return;
            } else {
                tinyPacData.resetHistoricoBlinky();
                tinyPacData.aumentaContadorFantasmasComidos(1);
                tinyPacData.incrementaPontosAtuais(tinyPacData.getContadorFantasmasComidos()*50);
            }

        }
        if (x == tinyPacData.getXClyde() && y == tinyPacData.getYClyde()) {
            if (!tinyPacData.isVulneravelClyde()) {
                perdeVida(); // PacMan perde uma vida

                voltaPosicaoInicial(tinyPacData.getLabirinto());

                tinyPacData.resetHistoricoClyde();
                tinyPacData.resetHistoricoBlinky();

                return;
            } else {
                tinyPacData.resetHistoricoClyde();
                tinyPacData.aumentaContadorFantasmasComidos(1);
                tinyPacData.incrementaPontosAtuais(tinyPacData.getContadorFantasmasComidos()*50);
            }
        }
    }


    /**
     * Coloca o PacMan na posição inicial no labirinto
     * @param maze labirinto onde o PacMan está localizado
     */
    private void voltaPosicaoInicial(Maze maze) {
        maze.set(y, x, new EspacoVazio());
        x = tinyPacData.getPacmanHomeX();
        y = tinyPacData.getPacmanHomeY();
        maze.set(y, x, this);
    }

    /**
     * Retorna o símbolo que representa o PacMan no labirinto
     * @return símbolo que representa o PacMan
     */
    @Override
    public char getSymbol() {
        return '■';
    }
}
