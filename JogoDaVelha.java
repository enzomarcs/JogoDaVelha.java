import java.util.*;

public class JogoDaVelha {
    public static void main(String[] args) {
            JogoDaVelha jv = new JogoDaVelha();
            jv.run();
        }
	
    private void run (){
            Resultado winner = Resultado.NADA;
            Scanner sc = new Scanner(System.in);
            char tabuleiro[][] = {{' ', ' ', ' '}, 
                                    {' ', ' ', ' '}, 
                                    {' ', ' ', ' '}};
            char ply = 'X';
            int cordenadaX, cordenadaY, jogoModo; 
            boolean normalMode;


            System.out.println("SELECIONE O MODO DE JOGO:\n\n      1: Normal\n      2: Inverso");
            jogoModo = sc.nextInt();

            normalMode = jogoModo == 1 ? true : false;


            desenhaTabuleiro(tabuleiro, "JOGADOR: " + ply);

            while (winner == Resultado.NADA) {
                    while (true) {
                        System.out.println("DIGITE A LINHA:");
                        cordenadaY = sc.nextInt() - 1;
                        System.out.println("DIGITE A COLUNA:");
                        cordenadaX = sc.nextInt() - 1;


                        if (tabuleiro[cordenadaY][cordenadaX] == ' ') {
                            break;
                        }


                        desenhaTabuleiro(tabuleiro, "JOGADOR: " + ply);
                        System.out.println("MARQUE UM CAMPO VAZIO...");

                    }

                    tabuleiro[cordenadaY][cordenadaX] = ply;
                    ply = ply == 'X' ? 'O' : 'X';


                    desenhaTabuleiro(tabuleiro, "JOGADOR: " + ply);


                winner = getWinner(tabuleiro, normalMode);

                }

                  if (winner == Resultado.OPLAYER) {
                      desenhaTabuleiro(tabuleiro, "**************\nVENCEDOR:  O\n**************");

                  } else if (winner == Resultado.XPLAYER){
                      desenhaTabuleiro(tabuleiro, "**************\nVENCEDOR:  X\n**************");

                  } else {
                      desenhaTabuleiro(tabuleiro, "**************\nEMPATE\n**************");

                  }

            }


    public static void desenhaTabuleiro(char[][] tab, String gameStatus) {

            System.out.println("\n\n\n\n\n\n\n\n\n\n");
            System.out.println(gameStatus);
            System.out.println("  1 2 3\n");

            for (int i=0; i < 3; i++){
                System.out.println("  _ _ _");
                System.out.println(String.valueOf(i + 1) + "|" + tab[i][0] + '|' + tab[i][1] + '|' + tab[i][2] + '|');

            }
            System.out.println("  _ _ _");
      }

      private static enum Resultado{
              NADA,
              EMPATE,
              OPLAYER,
              XPLAYER
      }

      public static Resultado getWinner(char[][] tab, boolean jMod) {
              char winner;


              for (int i=0; i < 3; i++){
                  winner = tab[i][0]              == tab[i][1] && tab[i][1] == tab[i][2]   ? tab[i][0]     :
                           tab[0][i]              == tab[1][i] && tab[1][i] == tab[2][i]   ? tab[0][i]     : 
                           i < 2 && tab[0][2-i*2] == tab[1][1] && tab[1][1] == tab[2][i*2] ? tab[0][2-i*2] : ' ';


                  if (winner == ' '){
                      continue;

                  }


                  if (jMod){
                      return winner == 'X' ? Resultado.XPLAYER : Resultado.OPLAYER;

                  } else {
                      return winner == 'X' ? Resultado.OPLAYER : Resultado.XPLAYER;

                  }
              }

              for (int i=0; i < 3; i++){
                  if (tab[i][0] != ' ' && tab[i][1] != ' ' && tab[i][2] != ' ') {
                      continue;   

                  }
                  return Resultado.NADA;
              }

              return Resultado.EMPATE;
      }
}
