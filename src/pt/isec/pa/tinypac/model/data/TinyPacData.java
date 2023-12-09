package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.elementos.*;
import java.io.*;
import java.util.*;

/**
 * Classe que representa os dados do jogo Tiny Pacman
 * Implementa a Serializable para permitir a serialização dos objetos
 * @author Beatriz Maia
 * @version 2.0.0
 */
public class TinyPacData implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Labirinto do jogo.
     */
    Maze maze;

    private int xPacman;
    private int yPacman;
    private int yFantasma;
    private int xFantasma;
    private int xFruta;
    private int yFruta;


    private int contadorFantasmasComidos;
    private int nivelAtual = 1;
    private int pontosAtuais;
    private int contadorBolas;
    private int contadorFrutas;
    private int bolasRestantes;
    private int segundosVulnerabilidade;
    private boolean ativacaoFruta;
    private int contadorSegundos;

    /**
     * Instância do objeto PacMan.
     */
    PacMan pacMan;

    /**
     * Instância do objeto Blinky
     */
    Blinky blinky;

    /**
     * Instância do objeto Clyde
     */
    Clyde clyde;


    /**
     * Construtor da classe TinyPacData.
     * Inicializa os objetos PacMan, Blinky e Clyde.
     * Inicializa os contadores e pontuações do jogo.
     */
    public TinyPacData() {
        pacMan = new PacMan(Direction.NONE, this);
        blinky = new Blinky(this);
        clyde = new Clyde(this);
        nivelAtual=1;
        pontosAtuais = 0;
        contadorBolas = 0;
        contadorFantasmasComidos = 0;
        contadorSegundos = 0;
        bolasRestantes = 0;
    }

    /*public PacMan getPacMan() { return this.pacMan;}*/

    /**
     * Obtém a coordenada X da posição inicial do PacMan
     * @return coordenada X do PacMan
     */
    public int getPacmanHomeX() {
        return xPacman;
    }

    /**
     * Define a coordenada X da posição inicial do PacMan
     * @param xnew nova coordenada X do PacMan
     */
    public void setPacmanHomeX(int xnew) {
        xPacman = xnew;
    }

    /**
     * Obtém a coordenada Y da posição inicial do PacMan
     * @return coordenada Y do PacMan
     */
    public int getPacmanHomeY() {
        return yPacman;
    }

    /**
     * Define a coordenada Y da posição inicial do PacMan
     * @param newy nova coordenada Y do PacMan
     */
    public void setPacmanHomeY(int newy) {
        yPacman = newy;
    }

    /**
     * Obtém a coordenada X da posição inicial dos Fantasmas
     * @return coordenada X dos fantasmas
     */
    public int getFantasmaHomeX(){return xFantasma;}

    /**
     * Define a coordenada X da posição inicial dos Fantasmas
     * @param xnew nova coordenada X dos Fantasmas
     */
    public void setFantasmaHomeX(int xnew){
        xFantasma=xnew;
    }

    /**
     * Obtém a coordenada Y da posição inicial dos Fantasmas
     * @return coordenada Y dos Fantasmas
     */
    public int getFantasmaHomeY(){
        return yFantasma;
    }

    /**
     * Define a coordenada Y da posição inicial dos Fantasmas
     * @param ynew A nova coordenada Y dos Fantasmas
     */
    public void setFantasmaHomeY(int ynew){
        yFantasma=ynew;
    }

    /**
     * Obtém a posição x da fruta
     * @return a posição x da fruta
     */
    public int getxFruta() {
        return xFruta;
    }

    /**
     * Define a posição x da fruta
     * @param xFruta posição x da fruta
     */
    public void setxFruta(int xFruta) {
        this.xFruta = xFruta;
    }

    /**
     * Obtém a posição y da fruta
     * @return posição y da fruta
     */
    public int getyFruta() {
        return yFruta;
    }

    /**
     * Define a posição y da fruta
     * @param yFruta a posição y da fruta
     */
    public void setyFruta(int yFruta) {
        this.yFruta = yFruta;
    }

    /**
     * Incrementa o contador de frutas e atualiza os pontos atuais do jogador
     */
    public void comeFruta() {
        contadorFrutas++;
        incrementaPontosAtuais(25 * contadorFrutas);
    }

    /**
     * Obtém os segundos
     * @return os segundos
     */
    public int getContadorSegundos() {
        return contadorSegundos;
    }

    /**
     * Adiciona a quantidade de segundos ao contador
     * @param contadorSegundos a quantidade de segundos a adicionar
     */
    public void addContadorSegundos(int contadorSegundos) {
        this.contadorSegundos += contadorSegundos;
    }

    /**
     * Define o nível atual do jogo
     * @param n número do nível atual
     */
    public void setNivelAtual(int n) {
        nivelAtual = n;
    }

    /**
     * Desativa a fruta após ser comida
     * Este método é chamado na classe PacMan
     */
    public void setFrutaDesativada() {
        ativacaoFruta = false;
    } //isto desativa a fruta depois de ser comida esta a ser chamado na class PacMan

    /**
     * Obtém a pontuação atual do jogador
     * @return A pontuação atual
     */
    public int getPontosAtuais() {
        return pontosAtuais;
    }

    /**
     * Incrementa a pontuação atual do jogador com a quantidade de pontos especificada
     * @param pontos quantidade de pontos
     */
    public void incrementaPontosAtuais(int pontos) {
        pontosAtuais += pontos;
    }

    /**
     * Reseta o tempo de vulnerabilidade para 20 segundos.
     */
    public void resetSegundosVulnerabilidade() {
        segundosVulnerabilidade = 20;
    }


    //// Bolas ///
    /**
     * Verifica se o Pac-Man comeu uma bola comestível através da função na classe Pacman
     * @return true se o Pac-Man comeu uma bola comestível, false caso contrário
     */
    public boolean comeuBolaComestivel() {
        return pacMan.comeuBolaComestivel();
    }

    /**
     * Incrementa o contador de bolas e verifica se é necessário mostrar a fruta
     * @param maze labirinto do jogo.
     */
    public void incrementaContadorBolas(Maze maze) {
        //System.out.printf("\n\nBOLAS: %d\n\n",contadorBolas);
        ++contadorBolas;
        if (contadorBolas == 20) {
            // Torna a fruta visível

            maze.set(getyFruta(), getxFruta(), new Fruta());

            contadorBolas = 0; // Reinicia o contador
        }
    }

    /**
     * Verifica se todas as bolas foram comidas
     * @return true se todas as bolas foram comidas, false caso contrário
     */
    public boolean comeuBolasTodas() {
        // Verifica se todas as bolas são comidas
        if (bolasRestantes == 0) {
            nivelAtual++;

            return true;
        } else
            return false;
    }

    /**
     * Decrementa o número de bolas restantes
     */
    public void decrementaBolasRestantes() {
        --bolasRestantes;
    }

    public int getContadorBolas() {
        return bolasRestantes;
    }

    ///  Pacman ////

    /**
     * Define a posição X do Pac-Man
     * @param x A posição X do Pac-Man
     */
    private void setPacmanX(int x) {
        pacMan.setPacmanX(x);
    }

    /**
     * Define a posição Y do Pac-Man
     * @param y posição Y do Pac-Man
     */
    private void setPacmanY(int y) {
        pacMan.setPacmanY(y);
    }

    /**
     * Obtém a posição X do Pac-Man
     * @return posição X do Pac-Man
     */
    public int getPacmanX() {
        return pacMan.getPacmanX();
    }

    /**
     * Obtém a posição Y do Pac-Man
     * @return posição Y do Pac-Man
     */
    public int getPacmanY() {
        return pacMan.getPacmanY();
    }

    /**
     * Move o Pac-Man no labirinto através da função na classe Pacman
     */
    public void movePacMan() {
        pacMan.movePacMan(maze);
    }

    /**
     * Obtém o número de vidas do Pac-Man através da função na classe Pacman
     * @return O número de vidas do Pac-Man
     */
    public int getVidas() {
        return pacMan.getVidas();
    }

    /**
     * Obtém a direção atual do Pac-Man através da função na classe Pacman
     * @return A direção atual do Pac-Man
     */
    public Direction getDirection() {
        return pacMan.getDirection();
    }

    /**
     * Define a direção do Pac-Man através da função na classe Pacman
     * @param direction A direção do Pac-Man
     */
    public void setDirection(Direction direction) {
        pacMan.setDirection(direction);
    }


    //// Jogo ////
    /**
     * Inicia o jogo
     */
    public void startGame() {
        contadorBolas = 0;

        if (nivelAtual == 1) {
            contadorSegundos = 0;
            pontosAtuais = 0;
        }
        int nivelInferior = nivelAtual;

        if (nivelAtual < 10) {
            maze = CreateMaze("Level0" + nivelAtual + ".txt");
        } else {
            maze = CreateMaze("Level" + nivelAtual + ".txt");
        }

        while (maze == null) {
            --nivelInferior;

            if (nivelAtual < 10) {
                maze = CreateMaze("Level0" + nivelInferior + ".txt");
            } else {
                maze = CreateMaze("Level" + nivelInferior + ".txt");
            }

            System.err.println("Não foi possível carregar o labirinto. Verifique o arquivo de configuração.");
        }
        if (nivelAtual < 10) {
            fillMaze("Level0" + nivelInferior + ".txt", maze);
        } else {
            fillMaze("Level" + nivelInferior + ".txt", maze);
        }

        blinky.setblinkyPrimeiroMove(false);
        clyde.setclydeFirstMove(false);
        blinky.setVulneravel(false);
        clyde.setVulneravel(false);
        segundosVulnerabilidade = 20;

        maze.set(getPacmanHomeY(), getPacmanHomeX(), pacMan);
        setPacmanX(getPacmanHomeX());
        setPacmanY(getPacmanHomeY());

        maze.set(getFantasmaHomeY(), getFantasmaHomeX(), blinky);

        blinky.setX(getFantasmaHomeX());
        blinky.setY(getFantasmaHomeY());

        maze.set(getFantasmaHomeY(), getFantasmaHomeX(), clyde);

        clyde.setX(getFantasmaHomeX());
        clyde.setY(getFantasmaHomeY());

        maze.set(getyFruta(), getxFruta(), new EspacoVazio());

    }

    static int alt = 0;
    static int lar = 0;

    /**
     * Cria um objeto Maze com base no txt
     * @param filename O nome do ficheiro
     * @return objeto Maze criado
     */
    private static Maze CreateMaze(String filename) {

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (lar == 0) {
                    lar = line.length();
                }
                alt++;
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o ficheiro " + e.getMessage());
            return null;
        }

        Maze maze = new Maze(alt, lar);
        // System.out.println(alt);
        //  System.out.println(lar);

        return maze;
    }

    /**
     * Preenche o labirinto com os elementos do ficheiro
     * @param filename O nome do ficheiro
     * @param maze objeto Maze a ser preenchido
     */
    private void fillMaze(String filename, Maze maze) {
        //Cordenadas cordenadas = new Cordenadas();
        bolasRestantes = 0;
        contadorBolas = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linha;
            int y = 0;
            while ((linha = br.readLine()) != null) {
                for (int x = 0; x < linha.length(); x++) {
                    char symbol = linha.charAt(x);

                    IMazeElement element = null;

                    switch (symbol) {
                        case 'x' -> element = new Parede();
                        case 'W' -> element = new Portal();
                        case 'o' -> {
                            element = new Bola();
                            bolasRestantes++;
                        }
                        case 'F' -> {
                            element = new Fruta();
                            setxFruta(x);
                            setyFruta(y);
                        }
                        case 'M' -> {
                            element = new BasePacMan();
                            setPacmanHomeX(x);
                            setPacmanHomeY(y);
                        }
                        case 'O' -> {
                            element = new BolaComestivel();
                            bolasRestantes++;
                        }
                        case 'Y' -> {
                            element = new SurgirFantasmas();
                            setFantasmaHomeX(x);
                            setFantasmaHomeY(y);
                        }
                        case 'y' -> element = new BaseFantasmas();
                        default -> System.err.println("Simbolo invalido: " + symbol);
                    }
                    maze.set(y, x, element);
                }
                y++;
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
    }

    /**
     * Obtém o labirinto atual
     * @return labirinto atual
     */
    public Maze getLabirinto() {
        return maze;
    }


    ///// TOP 5///////

    /**
     * Verifica se o jogador pode entrar no TOP 5
     * @return true se o jogador pode entrar no TOP 5, false caso contrário
     */
    public boolean podeTOP5() {
        int pontuacaoJogador = getPontosAtuais();
        List<Pontuacao> top5Pontuacoes = lerTop5PontuacoesDoArquivo();

        return top5Pontuacoes.size() < 5 || pontuacaoJogador > Collections.min(top5Pontuacoes, Comparator.comparing(Pontuacao::getPontuacao)).getPontuacao();
    }

    /**
     * Atualiza o TOP 5 com a nova pontuação do jogador
     * @param nomeJogador nome do jogador
     */
    public void atualizaTOP5(String nomeJogador) {
        int pontuacaoJogador = getPontosAtuais();
        List<Pontuacao> top5Pontuacoes = lerTop5PontuacoesDoArquivo();

        // Cria o objeto Pontuacao com o nome e pontuação do jogador
        Pontuacao novaPontuacao = new Pontuacao(nomeJogador, pontuacaoJogador);

        // Adiciona a nova pontuação ao top5
        top5Pontuacoes.add(novaPontuacao);
        Collections.sort(top5Pontuacoes, Comparator.comparing(Pontuacao::getPontuacao).reversed());

        // Remove o registro de pontuação mais baixa (se houver mais de 5 registros)
        if (top5Pontuacoes.size() > 5) {
            top5Pontuacoes.remove(5);
        }

        // Salva as pontuações atualizadas no arquivo
        salvarTop5PontuacoesNoArquivo(top5Pontuacoes);

    }

    /**
     * Lê as pontuações do arquivo "top5.ser".
     * @return Uma lista de objetos Pontuacao contendo as pontuações lidas do ficheiro
     */
    private List<Pontuacao> lerTop5PontuacoesDoArquivo() {
        List<Pontuacao> pontuacoes = new ArrayList<>();
        File arquivo = new File("top5.ser");
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            pontuacoes = (List<Pontuacao>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler o arquivo top5.ser: " + e.getMessage());
        }

        return pontuacoes;
    }


    /**
     * Salva as pontuações no arquivo "top5.ser".
     * @param pontuacoes Uma lista de objetos Pontuacao contendo as pontuações a serem salvas no ficheiro
     */
    private void salvarTop5PontuacoesNoArquivo(List<Pontuacao> pontuacoes) {
        File arquivo = new File("top5.ser");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(pontuacoes);
        } catch (IOException e) {
            System.err.println("Erro ao salvar as pontuações no arquivo top5.ser: " + e.getMessage());
        }
    }


    /**
     * Obtém o tempo restante de vulnerabilidade dos fantasmas
     * @return tempo restante de vulnerabilidade dos fantasmas
     */
    public int getSegundosVulnerabilidade() {
        return segundosVulnerabilidade;
    }

    /**
     * Diminui em 1 o tempo restante de vulnerabilidade dos fantasmas
     */
    public void diminuiSegundosVulnerabilidade() {
        segundosVulnerabilidade -= 1;
    }

    /**
     * Obtém o contador de fantasmas comidos para pontuacao
     * @return contador de fantasmas comidos
     */
    public int getContadorFantasmasComidos() {
        return contadorFantasmasComidos;
    }

    /**
     * Define o contador de fantasmas comidos para dar reset
     * @param contadorFantasmasComidos valor do contador de fantasmas comidos
     */
    public void setContadorFantasmasComidos(int contadorFantasmasComidos) {
        this.contadorFantasmasComidos = contadorFantasmasComidos;
    }

    /**
     * Aumenta o contador de fantasmas comidos
     * @param numero número a ser adicionado ao contador de fantasmas comidos
     */
    public void aumentaContadorFantasmasComidos(int numero) {
        this.contadorFantasmasComidos += numero;
    }



    //////////Blinky/////////
    /**
     * Verifica se o Blinky está vulnerável através da funcao na classe Blinky
     * @return true se o Blinky está vulnerável, false caso contrário
     */
    public boolean estaVulneravelBlinky() {
        return blinky.estaVulneravel();
    }

    /**
     * Obtém a coordenada Y do Blinky através da funcao na classe Blinky
     * @return coordenada Y do Blinky
     */
    public int getYBlinky() {
        return blinky.getY();
    }

    /**
     * Obtém a coordenada X do Blinky através da funcao na classe Blinky
     * @return coordenada X do Blinky
     */
    public int getXBlinky() {
        return blinky.getX();
    }

    /**
     * Obtém a direção anterior do movimento do Blinky através da funcao na classe Blinky
     * @return direção anterior do movimento do Blinky
     */
    public Direction getPreviousMoveBlinky() {
        return blinky.getPreviousMove();
    }

    /**
     * Define se o Blinky está vulnerável ou não através da funcao na classe Blinky
     * @param b boolean que indica se o Blinky está vulnerável
     */
    public void setVulneravelBlinky(boolean b) {
        blinky.setVulneravel(b);
    }

    /**
     * Move o fantasma Blinky através da funcao na classe Blinky
     */
    public void moveBlinky() {
        blinky.moveBlinky();
    }

    /**
     * Obtém o número de segundos de movimento do Blinky através da funcao na classe Blinky
     * @return número de segundos de movimento do Blinky
     */
    public int getSegundosMovimentoBlinky() {
        return blinky.getSegundosMovimento();
    }

    /**
     * Reseta o histórico do Blinky, define-o como não vulnerável e retorna à posição inicial no labirinto
     */
    public void resetHistoricoBlinky() {
        blinky.resetHistorico();
        blinky.setVulneravel(false);
        blinky.returnToInitialPosition(maze);  //volta à posição inicial
    }


    /////// Clyde //////////

    /**
     * Reseta o Clyde, posicionando-o na casa de origem no labirinto
     */
    public void resetClyde() {
        maze.set(getFantasmaHomeY(), getFantasmaHomeX(), clyde);

    }

    /**
     * Verifica se o Clyde está vulnerável através da funcao na classe Clyde
     * @return true se o Clyde está vulnerável, false caso contrário
     */
    public boolean isVulneravelClyde() {
        return clyde.estaVulneravel();
    }

    /**
     * Obtém a direção anterior do movimento do Clyde
     * @return direção anterior do movimento do Clyde
     */
    public Direction getPreviousMoveClyde() {
        return clyde.getPreviousMove();
    }

    /**
     * Define a posição Y do Clyde através da funcao na classe Clyde
     * @param newY A nova posição Y do Clyde.
     */
    public void setYClyde(int newY) {
        clyde.setY(newY);
    }

    /**
     * Define se o Clyde está vulnerável ou não através da funcao na classe Clyde
     * @param b boolean indicando se o fantasma Clyde está vulnerável
     */
    public void setVulneravelClyde(boolean b) {
        clyde.setVulneravel(b);
    }

    /**
     * Move o Clyde através da funcao na classe Clyde
     */
    public void moveClyde() {
        clyde.moveClyde();
    }

    /**
     * Obtém a posição X do Clyde
     * @return A posição X do Clyde
     */
    public int getXClyde() {
        return clyde.getX();
    }

    /**
     * Obtém a posição Y do Clyde
     * @return A posição Y do Clyde
     */
    public int getYClyde() {
        return clyde.getY();
    }

    /**
     * Reseta o histórico do Clyde, retorna à posição inicial no labirinto e define-o como não vulnerável
     */
    public void resetHistoricoClyde() {
        clyde.resetHistorico();
        clyde.returnToInitialPosition(maze); //volta à posição
        clyde.setVulneravel(false);
    }
}
