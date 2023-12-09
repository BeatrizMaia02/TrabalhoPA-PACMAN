package pt.isec.pa.tinypac.model.data.elementos;

import pt.isec.pa.tinypac.model.data.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe do Clyde
 * @author Beatriz Maia
 * @version 2.0.0
 */
public class Clyde implements IMazeElement {
    private boolean clydePrimeiroMove = false;
    private Direction direction;
    private IMazeElement proximoElementoEscondido_Clyde;
    TinyPacData data;
    private int x, y;
    private boolean vulneravel = false;

    /**
     * Verifica se o Clyde está vulnerável
     * @return true se Clyde estiver vulnerável, caso contrário, false
     */
    public boolean estaVulneravel() {
        return vulneravel;
    }


    /**
     * Define a vulnerabilidade de Clyde
     * @param novovulneravel O novo valor de vulnerabilidade de Clyde
     */
    public void setVulneravel(boolean novovulneravel) {
        vulneravel = novovulneravel;
    }

   /* public void reset() {
        clydePrimeiroMove = false;
    }*/

    /**
     * Define se é o primeiro movimento de Clyde
     * @param a true se é o primeiro movimento de Clyde, caso contrário, false
     */
    public void setclydeFirstMove(boolean a){
        clydePrimeiroMove =a;
    }

    /**
     * Construtor da classe Clyde
     * @param data Os dados do jogo
     */
    public Clyde(TinyPacData data) {
        this.data = data;
    }

    /**
     * Lista que armazena o histórico de movimentos do Clyde
     */
    private List<Direction> moveHistory = new ArrayList<>();

    /**
     * Método auxiliar para adicionar o movimento atual ao histórico de movimentos
     */
    private void addToMoveHistory(Direction move) {
        moveHistory.add(move);
    }

    /**
     * Método auxiliar para obter o movimento anterior
     */
    public Direction getPreviousMove() {
        if (moveHistory.isEmpty()) {
            return Direction.NONE;
        }
        return moveHistory.get(moveHistory.size() - 1);
    }


    /**
     * Move o Clyde para a posição inicial no labirinto
     *
     * @param maze O labirinto onde Clyde está localizado
     */
    public void returnToInitialPosition(Maze maze) {
        maze.set(y, x, new EspacoVazio());

        x = data.getFantasmaHomeX();
        y = data.getFantasmaHomeY();
        maze.set(y, x, this);

        // Reinicializa a variável de controle para o primeiro movimento
        clydePrimeiroMove = false;
    }

    /**
     * Limpa o histórico de movimentos de Clyde.
     */
    public void resetHistorico(){
        moveHistory.clear();
    }


    /**
     * Move o Clyde no labirinto.
     */
    public void moveClyde() {
        if (vulneravel) {
            // Obtenha o movimento anterior.
            Direction previousMove = data.getPreviousMoveClyde();

            // Mova Clyde no sentido inverso ao movimento anterior
            if (previousMove != Direction.NONE) {
                switch (previousMove) {
                    case UP:
                        data.getLabirinto().set(y, x, proximoElementoEscondido_Clyde);
                        proximoElementoEscondido_Clyde = data.getLabirinto().get(y + 1, x);
                        ++y;
                        break;
                    case DOWN:
                        data.getLabirinto().set(y, x, proximoElementoEscondido_Clyde);
                        proximoElementoEscondido_Clyde = data.getLabirinto().get(y - 1, x);
                        --y;
                        break;
                    case RIGHT:
                        data.getLabirinto().set(y, x, proximoElementoEscondido_Clyde);
                        proximoElementoEscondido_Clyde = data.getLabirinto().get(y, x - 1);
                        --x;
                        break;
                    case LEFT:
                        data.getLabirinto().set(y, x, proximoElementoEscondido_Clyde);
                        proximoElementoEscondido_Clyde = data.getLabirinto().get(y, x + 1);
                        ++x;
                        break;
                }
                data.getLabirinto().set(y, x, this);
                moveHistory.remove(moveHistory.size() - 1);
            }
        } else if (!clydePrimeiroMove) {

            proximoElementoEscondido_Clyde = data.getLabirinto().get(y - 1, x);
            int newY = y - 1;
            int newX = x;
            data.getLabirinto().set(y, x, new SurgirFantasmas());
            data.getLabirinto().set(newY, newX, this);
            data.setYClyde(newY);
            direction = Direction.UP;
            clydePrimeiroMove = true;
            addToMoveHistory(direction);
        } else {
            int pacManX = data.getPacmanX();
            int pacManY = data.getPacmanY();

            if (x == pacManX || y == pacManY) {
                // Se o Clyde vir o Pac-Man, altera a direção do Clyde para perseguir o Pac-Man
                if (x == pacManX) {
                    if (y > pacManY) {
                        direction = Direction.UP;
                    } else {
                        direction = Direction.DOWN;
                    }
                } else {
                    if (x > pacManX) {
                        direction = Direction.LEFT;
                    } else {
                        direction = Direction.RIGHT;
                    }
                }
            }

            auxMoveClyde(data.getLabirinto());
            addToMoveHistory(direction);
        }
    }

    /**
     * Método auxiliar para mover o Clyde no labirinto
     * @param maze labirinto onde o Clyde está localizado
     */
    private void auxMoveClyde(Maze maze) {
        switch (direction) {
            case UP:
                if (!(proximoElementoEscondido_Clyde instanceof Blinky) && !(proximoElementoEscondido_Clyde instanceof BaseFantasmas) && !(proximoElementoEscondido_Clyde instanceof PacMan)) {
                    data.getLabirinto().set(getY(), getX(), proximoElementoEscondido_Clyde);
                }

                if (maze.get(y - 1, x) instanceof Parede || maze.get(y - 1, x) instanceof SurgirFantasmas) {
                    if (maze.get(y, x - 1) instanceof Parede) {
                        maze.set(y, x, proximoElementoEscondido_Clyde);

                        proximoElementoEscondido_Clyde = maze.get(y, x + 1);
                        ++x;
                        maze.set(y, x, this);
                        direction = Direction.RIGHT;
                    } else {
                        maze.set(y, x, proximoElementoEscondido_Clyde);

                        proximoElementoEscondido_Clyde = maze.get(y, x - 1);
                        --x;
                        maze.set(y, x, this);
                        direction = Direction.LEFT;
                    }
                } else {
                    if (!(proximoElementoEscondido_Clyde instanceof Blinky)) {
                        maze.set(y, x, proximoElementoEscondido_Clyde);
                    }
                    proximoElementoEscondido_Clyde = maze.get(y - 1, x);
                    maze.set(y - 1, x, this);
                    y--;
                }
                break;
            case DOWN:
                if (!(proximoElementoEscondido_Clyde instanceof Blinky) && !(proximoElementoEscondido_Clyde instanceof BaseFantasmas) && !(proximoElementoEscondido_Clyde instanceof PacMan)) {
                    data.getLabirinto().set(getY(), getX(), proximoElementoEscondido_Clyde);
                }

                if (maze.get(y + 1, x) instanceof Parede || maze.get(y + 1, x) instanceof SurgirFantasmas) {
                    if (maze.get(y, x + 1) instanceof Parede) {
                        maze.set(y, x, proximoElementoEscondido_Clyde);

                        proximoElementoEscondido_Clyde = maze.get(y, x - 1);
                        --x;
                        maze.set(y, x, this);
                        direction = Direction.LEFT;
                    } else {
                        maze.set(y, x, proximoElementoEscondido_Clyde);

                        proximoElementoEscondido_Clyde = maze.get(y, x + 1);
                        ++x;
                        maze.set(y, x, this);
                        direction = Direction.RIGHT;
                    }
                } else {
                    if (!(proximoElementoEscondido_Clyde instanceof Blinky)) {
                        maze.set(y, x, proximoElementoEscondido_Clyde);
                    }
                    proximoElementoEscondido_Clyde = maze.get(y + 1, x);
                    maze.set(y + 1, x, this);
                    y++;
                }
                break;
            case LEFT:
                if (!(proximoElementoEscondido_Clyde instanceof Blinky) && !(proximoElementoEscondido_Clyde instanceof BaseFantasmas) && !(proximoElementoEscondido_Clyde instanceof PacMan)) {
                    data.getLabirinto().set(getY(), getX(), proximoElementoEscondido_Clyde);
                }

                if (maze.get(y, x - 1) instanceof Parede || maze.get(y, x - 1) instanceof SurgirFantasmas) {
                    if (maze.get(y - 1, x) instanceof Parede) {
                        maze.set(y, x, proximoElementoEscondido_Clyde);

                        proximoElementoEscondido_Clyde = maze.get(y + 1, x);
                        ++y;
                        maze.set(y, x, this);
                        direction = Direction.DOWN;
                    } else {
                        maze.set(y, x, proximoElementoEscondido_Clyde);
                        proximoElementoEscondido_Clyde = maze.get(y - 1, x);
                        --y;
                        maze.set(y, x, this);
                        direction = Direction.UP;
                    }

                } else {
                    if (!(proximoElementoEscondido_Clyde instanceof Blinky)) {
                        maze.set(y, x, proximoElementoEscondido_Clyde);
                    }
                    proximoElementoEscondido_Clyde = maze.get(y, x - 1);
                    maze.set(y, x - 1, this);
                    x--;
                }
                break;
            case RIGHT:
                if (!(proximoElementoEscondido_Clyde instanceof Blinky) && !(proximoElementoEscondido_Clyde instanceof BaseFantasmas) && !(proximoElementoEscondido_Clyde instanceof PacMan)) {
                    data.getLabirinto().set(getY(), getX(), proximoElementoEscondido_Clyde);
                }

                if (maze.get(y, x + 1) instanceof Parede || maze.get(y, x + 1) instanceof SurgirFantasmas) {
                    if (maze.get(y + 1, x) instanceof Parede) {
                        maze.set(y, x, proximoElementoEscondido_Clyde);
                        proximoElementoEscondido_Clyde = maze.get(y - 1, x);
                        --y;
                        maze.set(y, x, this);
                        direction = Direction.UP;
                    } else {
                        maze.set(y, x, proximoElementoEscondido_Clyde);

                        proximoElementoEscondido_Clyde = maze.get(y + 1, x);
                        ++y;
                        maze.set(y, x, this);
                        direction = Direction.DOWN;
                    }

                } else {
                    if (!(proximoElementoEscondido_Clyde instanceof Blinky)) {
                        maze.set(y, x, proximoElementoEscondido_Clyde);
                    }
                    proximoElementoEscondido_Clyde = maze.get(y, x + 1);
                    maze.set(y, x + 1, this);
                    x++;
                }
                break;
        }

    }

    /**
     * Define a coordenada Y do Clyde
     * @param newY A nova coordenada Y do Clyde
     */
    public void setY(int newY) {
        y = newY;
    }

    /**
     * Define a coordenada X do Clyde
     * @param newX A nova coordenada X do Clyde
     */
    public void setX(int newX) {
        x = newX;
    }

    /**
     * Retorna a coordenada Y atual do Clyde
     * @return coordenada Y atual do Clyde
     */
    public int getY() {
        return y;
    }

    /**
     * Retorna a coordenada X atual do Clyde
     * @return coordenada X atual do Clyde
     */
    public int getX() {
        return x;
    }

    /**
     * Retorna o símbolo que representa o Clyde
     * @return símbolo de Clyde
     */
    @Override
    public char getSymbol() {
        return 'C';
    }
}
