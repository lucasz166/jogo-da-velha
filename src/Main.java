
import java.util.Scanner;

class BotaoDeJogo {

    private String escolha;
    private String jogador;

    BotaoDeJogo() {
        this.escolha = " ";
        this.jogador = "";
    }

    public void setEscolha(String escolha) {
        this.escolha = escolha;
    }

    public String getEscolha() {
        return this.escolha;
    }

    public void setJogador(String jogador) {
        this.jogador = jogador;
    }

    public String getJogador() {
        return this.jogador;
    }
}

class TabelaDeJogo {

    private BotaoDeJogo[][] jogo;
    private String[] jogadores;
    private String[] simboloJogador;
    private String ganhador;
    private boolean temResultado;
    private Scanner scanner;
    private boolean temSlotVazio;

    TabelaDeJogo(String[] jogadores) {
        BotaoDeJogo[][] jogoPadrao = new BotaoDeJogo[3][3];

        for (int i = 0; i < jogoPadrao.length; i++) {
            for (int j = 0; j < jogoPadrao[i].length; j++) {
                jogoPadrao[i][j] = new BotaoDeJogo();
            }
        }

        this.jogo = jogoPadrao;
        this.jogadores = jogadores;
        this.simboloJogador = new String[]{"", ""};
        this.ganhador = "";
        this.temResultado = false;
        this.temSlotVazio = true;
        this.scanner = new Scanner(System.in);
    }

    public String getResultado() {
        return this.ganhador;
    }

    public void comecarJogo() {
        int jogadorAtual = 0;

        System.out.print("\nJogador " + this.jogadores[0] + " escolha o simbolo: ");
        this.simboloJogador[0] = this.scanner.nextLine();

        System.out.print("\nJogador " + this.jogadores[1] + " escolha o simbolo: ");
        this.simboloJogador[1] = this.scanner.nextLine();

        System.out.println();

        while (this.temResultado == false && this.temSlotVazio) {
            this.visualizarJogo();

            System.out.print("\nJogador " + this.jogadores[jogadorAtual] + " escolha linha: ");
            int linha = this.scanner.nextInt();

            System.out.print("\nJogador " + this.jogadores[jogadorAtual] + " escolha coluna: ");
            int coluna = this.scanner.nextInt();

            this.jogo[linha - 1][coluna - 1].setJogador(this.jogadores[jogadorAtual]);
            this.jogo[linha - 1][coluna - 1].setEscolha(simboloJogador[jogadorAtual]);

            this.atualizarStatusDaTabela();
            this.validarLinha();
            this.validarColuna();
            this.validarDiagonal();

            if (jogadorAtual == 0) {
                jogadorAtual = 1;
            } else {
                jogadorAtual = 0;
            }
        }

        System.out.flush();

        this.visualizarJogo();

        if (this.ganhador != "") {
            System.out.printf("\nParabéns %s você venceu!", this.ganhador);
        } else {
            System.out.println("\nDeu velha!!");
        }
    }

    private void validarLinha() {
        for (int i = 0; i < this.jogo.length; i++) {
            if (this.jogo[i][0].getEscolha() != " ") {
                if (this.jogo[i][0].getEscolha() == this.jogo[i][1].getEscolha() && this.jogo[i][1].getEscolha() == this.jogo[i][2].getEscolha()) {
                    if (this.jogo[i][0].getEscolha() == this.simboloJogador[0]) {
                        this.ganhador = this.jogadores[0];
                    } else {
                        this.ganhador = this.jogadores[1];
                    }

                    this.temResultado = true;

                    break;
                }
            }
        }
    }

    private void validarColuna() {
        for (int i = 0; i < this.jogo.length; i++) {
            if (this.jogo[0][i].getEscolha() != " ") {
                if (this.jogo[0][i].getEscolha() == this.jogo[1][i].getEscolha() && this.jogo[1][i].getEscolha() == this.jogo[2][i].getEscolha()) {
                    if (this.jogo[0][i].getEscolha() == this.simboloJogador[0]) {
                        this.ganhador = this.jogadores[0];
                    } else {
                        this.ganhador = this.jogadores[1];
                    }

                    this.temResultado = true;

                    break;
                }
            }
        }
    }

    private void validarDiagonal() {
        if (this.jogo[0][0].getEscolha() != " " && this.jogo[1][1].getEscolha() != " " && this.jogo[2][2].getEscolha() != " ") {
            if (this.jogo[0][0].getEscolha() == this.jogo[1][1].getEscolha() && this.jogo[1][1].getEscolha() == this.jogo[2][2].getEscolha()) {
                if (this.jogo[0][0].getEscolha() == this.simboloJogador[0]) {
                    this.ganhador = this.jogadores[0];
                } else {
                    this.ganhador = this.jogadores[1];
                }

                this.temResultado = true;

                return;
            }
        }

        if (this.jogo[0][2].getEscolha() != " " && this.jogo[1][1].getEscolha() != " " && this.jogo[2][0].getEscolha() != " ") {
            if (this.jogo[0][2].getEscolha() == this.jogo[1][1].getEscolha() && this.jogo[1][1].getEscolha() == this.jogo[2][0].getEscolha()) {
                if (this.jogo[0][2].getEscolha() == this.simboloJogador[0]) {
                    this.ganhador = this.jogadores[0];
                } else {
                    this.ganhador = this.jogadores[1];
                }

                this.temResultado = true;

                return;
            }
        }
    }

    private void visualizarJogo() {
        for (int i = 0; i < this.jogo.length; i++) {
            for (int j = 0; j < this.jogo[i].length; j++) {
                System.out.print("|");

                System.out.print(this.jogo[i][j].getEscolha());

                if (j + 1 == this.jogo[i].length) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }

    private void atualizarStatusDaTabela() {
        boolean existeBlocoVazio = false;

        for (int i = 0; i < this.jogo.length; i++) {
            for (int j = 0; j < this.jogo[i].length; j++) {
                if (this.jogo[i][j].getEscolha() == " ") {
                    existeBlocoVazio = true;

                    break;
                }
            }
        }

        this.temSlotVazio = existeBlocoVazio;
    }
}

class Main {

    public static void main(String args[]) {
        String[] jogadores = {"Jogador 1", "Jogador 2"};
        TabelaDeJogo jogo = new TabelaDeJogo(jogadores);

        jogo.comecarJogo();
    }
}
