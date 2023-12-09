package pt.isec.pa.tinypac.model.data.elementos;

import pt.isec.pa.tinypac.model.data.Direction;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.TinyPacData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pinky implements IMazeElement {
    private final char symbol = 'P';
    private static Direction direction;
    private static int x;
    private static int y;

    public static void setX(int x) {
        Pinky.x = x;
    }

    public static void setY(int y) {
        Pinky.y = y;
    }

    private static boolean vulneravel = false;

    public boolean isVulneravel() {
        return vulneravel;
    }

    public static void setVulneravel(boolean novovulneravel) {
        vulneravel = novovulneravel;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }
    private static int targetCorner = 0; // Índice do canto alvo
   /* private static final int[][] corners = {
            {TinyPacData.getLargura() - 1, 0},  // Canto superior direito
            {TinyPacData.getLargura() - 1, TinyPacData.getAltura() - 1},  // Canto inferior direito
            {0, 0},  // Canto superior esquerdo
            {0, TinyPacData.getAltura() - 1}  // Canto inferior esquerdo
    };
    private static int targetX = corners[targetCorner][0];
    private static int targetY = corners[targetCorner][1];
    private static final int distanceThreshold = (int) (0.1 * TinyPacData.getLargura());  // Largura do labirinto

    public static void movePinky(Maze maze) {
        if (maze.get(y, x) instanceof Parede || maze.get(y, x) instanceof SurgirFantasmas) {
            // Chegou a um obstáculo, precisa sortear nova direção
            if (isNearTarget()) {
                // Está próximo do canto alvo, então muda para o próximo canto
                targetCorner = (targetCorner + 1) % corners.length;
                targetX = corners[targetCorner][0];
                targetY = corners[targetCorner][1];
            }
            direction = sortearDirecao(maze);
        } else {
            if (y == targetY && x == targetX) {
                // Chegou ao canto alvo, sorteia nova direção
                direction = sortearDirecao(maze);
            }
        }

        auxMovePinky(maze);
    }

    private static boolean isNearTarget() {
        int dx = Math.abs(x - targetX);
        int dy = Math.abs(y - targetY);
        return dx <= distanceThreshold && dy <= distanceThreshold;
    }

    private static void auxMovePinky(Maze maze) {
        if (direction != null) {
            switch (direction) {
                case UP:
                    if (!(maze.get(y - 1, x) instanceof Parede || maze.get(y - 1, x) instanceof SurgirFantasmas)) {
                        y--;
                    }
                    break;
                case DOWN:
                    if (!(maze.get(y + 1, x) instanceof Parede || maze.get(y + 1, x) instanceof SurgirFantasmas)) {
                        y++;
                    }
                    break;
                case LEFT:
                    if (!(maze.get(y, x - 1) instanceof Parede || maze.get(y, x - 1) instanceof SurgirFantasmas)) {
                        x--;
                    }
                    break;
                case RIGHT:
                    if (!(maze.get(y, x + 1) instanceof Parede || maze.get(y, x + 1) instanceof SurgirFantasmas)) {
                        x++;
                    }
                    break;
            }
        }
    }

    private static Direction sortearDirecao(Maze maze) {
        List<Direction> directions = new ArrayList<>();
        if (!(maze.get(y - 1, x) instanceof Parede || maze.get(y - 1, x) instanceof SurgirFantasmas)) {
            directions.add(Direction.UP);
        }
        if (!(maze.get(y + 1, x) instanceof Parede || maze.get(y + 1, x) instanceof SurgirFantasmas)) {
            directions.add(Direction.DOWN);
        }
        if (!(maze.get(y, x - 1) instanceof Parede || maze.get(y, x - 1) instanceof SurgirFantasmas)) {
            directions.add(Direction.LEFT);
        }
        if (!(maze.get(y, x + 1) instanceof Parede || maze.get(y, x + 1) instanceof SurgirFantasmas)) {
            directions.add(Direction.RIGHT);
        }

        if (directions.isEmpty()) {
            // Não há direções disponíveis, retorna a direção atual
            return direction;
        }

        // Sorteia uma direção disponível
        int randomIndex = (int) (Math.random() * directions.size());
        return directions.get(randomIndex);
    }



    public static void setStartPosition(int startX, int startY) {
        x = startX;
        y = startY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }*/
}


