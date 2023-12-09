package pt.isec.pa.tinypac.model.data.elementos;

import pt.isec.pa.tinypac.model.data.*;

import java.util.*;


/**
 * Classe do Blinky
 * @author Beatriz Maia
 * @version 2.0.0
 */
public class Blinky implements IMazeElement {

    /**
     * Variável que indica se é o primeiro movimento do Blinky
     */
    private boolean blinkyPrimeiroMove = false;

    /**
     * Variável que indica se o Blinky está vulnerável.
     */
    private boolean vulneravel = false;

    /**
     * Variável que armazena o número de segundos do movimento do Blinky.
     */
    private int segundosMovimento = 0;

    /**
     * Variável que guarda o próximo elemento para onde o Blinky se move
     */
    private IMazeElement proximoElementoEscondido_Blinky;

    /**
     * Variáveis que armazenam a posição do Blinky no labirinto
     */
    private int x, y;

    /**
     * Variável que indica a direção atual do Blinky
     */
    private Direction direction;

    TinyPacData data;

    /**
     * Lista que armazena o histórico de movimentos do Blinky
     */
    private List<Direction> moveHistory = new ArrayList<>();


    /**
     * Map que mapeia cada direção a um vetor de inteiros representando o movimento naquela direção
     */
    private static final Map<Direction, int[]> DIRECTION_VECTORS = new HashMap<Direction, int[]>() {{
        put(Direction.UP, new int[]{-1, 0});
        put(Direction.DOWN, new int[]{1, 0});
        put(Direction.LEFT, new int[]{0, -1});
        put(Direction.RIGHT, new int[]{0, 1});
    }};

    public Blinky(TinyPacData data) {
        this.data = data;
    }

    /**
     * Obtém o número de segundos desde desde que o Blinky se moveu
     * @return o número de segundos do movimento do Blinky
     */
    public int getSegundosMovimento() {
        return segundosMovimento;
    }

    /**
     * Define se é o primeiro movimento do Blinky
     * @param a boolean que indica se é o primeiro movimento
     */
    public void setblinkyPrimeiroMove(boolean a){
        blinkyPrimeiroMove=a;
    }

    /**
     * Obtém a posição X do Blinky
     * @return A posição X do Blinky
     */
    public int getX() {
        return x;
    }

    /**
     * Obtém a posição Y do Blinky
     * @return A posição Y do Blinky
     */
    public int getY() {
        return y;
    }


    /**
     * Define a posição X do Blinky
     * @param newx A nova posição X do Blinky
     */
    public void setX(int newx) {
        x = newx;
    }

    /**
     * Define a posição Y do Blinky.
     * @param newY A nova posição Y do Blinky.
     */
    public void setY(int newY) {
        y = newY;
    }

    /**
     * Verifica se o Blinky está vulnerável
     * @return true se o Blinky estiver vulnerável, false caso contrário
     */
    public boolean estaVulneravel() {
        return vulneravel;
    }

    /**
     * Define se o Blinky está vulnerável.
     * @param novovulneravel boolean que indica se o Blinky está vulnerável.
     */
    public void setVulneravel(boolean novovulneravel) {
        vulneravel = novovulneravel;
    }

   /* public void reset() {
        blinkyPrimeiroMove = false;
    }*/

    /**
     * Adiciona o movimento à lista de histórico de movimentos do Blinky
     * @param move O movimento a ser adicionado
     */

    private void addToMoveHistory(Direction move) {
        moveHistory.add(move);
    }

    /**
     * Obtém o movimento anterior do Blinky
     * @return o movimento anterior
     */
    public Direction getPreviousMove() {
        if (moveHistory.isEmpty()) {
            return Direction.NONE;
        }
        return moveHistory.get(moveHistory.size() - 1);
    }


    /**
     * Coloca o Blinky na posição inicial no labirinto
     * @param maze O labirinto onde o Blinky está localizado
     */
    public void returnToInitialPosition(Maze maze) {
        maze.set(y, x, new EspacoVazio());

        x = data.getFantasmaHomeX();
        y = data.getFantasmaHomeY();
        maze.set(y, x, this);

        // Reinicializa a variável de controle para o primeiro movimento
        blinkyPrimeiroMove = false;
    }

    /**
     * Limpa o histórico de movimentos do Blinky
     */
    public void resetHistorico(){
        moveHistory.clear();
    }


    /**
     * Move o Blinky no labirinto
     */
    public void moveBlinky() {
        if (vulneravel) {
            // Obtenha o movimento anterior.
            Direction previousMove = data.getPreviousMoveBlinky();

            // Mova Blinky no sentido inverso ao movimento anterior
            if (previousMove != Direction.NONE) {
                switch (previousMove) {
                    case UP:
                        data.getLabirinto().set(y, x, proximoElementoEscondido_Blinky);
                        proximoElementoEscondido_Blinky = data.getLabirinto().get(y + 1, x);
                        ++y;
                        break;
                    case DOWN:
                        data.getLabirinto().set(y, x, proximoElementoEscondido_Blinky);
                        proximoElementoEscondido_Blinky = data.getLabirinto().get(y - 1, x);
                        --y;
                        break;
                    case RIGHT:
                        data.getLabirinto().set(y, x, proximoElementoEscondido_Blinky);
                        proximoElementoEscondido_Blinky = data.getLabirinto().get(y, x - 1);
                        --x;
                        break;
                    case LEFT:
                        data.getLabirinto().set(y, x, proximoElementoEscondido_Blinky);
                        proximoElementoEscondido_Blinky = data.getLabirinto().get(y, x + 1);
                        ++x;
                        break;
                }
                data.getLabirinto().set(y, x, this);
                moveHistory.remove(moveHistory.size() - 1);
            }
        } else if (!blinkyPrimeiroMove) {
            segundosMovimento = data.getContadorSegundos();
            proximoElementoEscondido_Blinky = data.getLabirinto().get(y - 1, x);
            int newY = data.getYBlinky() - 1;
            int newX = data.getXBlinky();
            data.getLabirinto().set(y, x, new SurgirFantasmas());
            data.getLabirinto().set(newY, newX, this);
            y=newY;
            direction = Direction.UP;
            blinkyPrimeiroMove = true;
            addToMoveHistory(direction);
        } else {
            auxMoveBlinky();
            addToMoveHistory(direction);
        }
    }


    /**
     * Move auxiliar do Blinky
     */
    private void auxMoveBlinky() {
        int currentX = getX();
        int currentY = getY();

        if (!(proximoElementoEscondido_Blinky instanceof Clyde) && !(proximoElementoEscondido_Blinky instanceof BaseFantasmas) && !(proximoElementoEscondido_Blinky instanceof PacMan)) {
            data.getLabirinto().set(currentY, currentX, proximoElementoEscondido_Blinky);
        }

        int[] currentDir = DIRECTION_VECTORS.get(direction);
        proximoElementoEscondido_Blinky = data.getLabirinto().get(currentY + currentDir[0], currentX + currentDir[1]);

        if (proximoElementoEscondido_Blinky instanceof Parede) {
            List<Direction> validDirections = new ArrayList<>(DIRECTION_VECTORS.keySet());
            validDirections.remove(getOppositeDirection(direction));
            final int tempCurrentX = currentX;
            final int tempCurrentY = currentY;
            validDirections.removeIf(dir -> {
                int[] altDir = DIRECTION_VECTORS.get(dir);
                return data.getLabirinto().get(tempCurrentY + altDir[0], tempCurrentX + altDir[1]) instanceof Parede;
            });

            if (validDirections.isEmpty()) {
                validDirections.add(getOppositeDirection(direction));
            }

            direction = validDirections.get((new Random()).nextInt(validDirections.size()));
            int[] chosenDir = DIRECTION_VECTORS.get(direction);
            currentX += chosenDir[1];
            currentY += chosenDir[0];
        } else {
            currentX += currentDir[1];
            currentY += currentDir[0];
        }

        proximoElementoEscondido_Blinky = data.getLabirinto().get(currentY, currentX);
        data.getLabirinto().set(currentY, currentX, this);

        setX(currentX);
        setY(currentY);
    }


    /**
     * Retorna a direção oposta à direção especificada
     * @param dir A direção para a qual se deseja obter a direção oposta
     * @return A direção oposta à direção especificada
     */
    private Direction getOppositeDirection(Direction dir) {
        switch (dir) {
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
            case LEFT:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.LEFT;
            default:
                return Direction.NONE;
        }
    }

    /**
     * Retorna o símbolo que representa o Blinky
     * @return símbolo que representa o Blinky
     */
    @Override
    public char getSymbol() {
        return 'B';
    }
}