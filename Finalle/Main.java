package Finalle;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nama pemain 1: ");
        String player1Name = scanner.nextLine();

        System.out.print("Masukkan nama pemain 2: ");
        String player2Name = scanner.nextLine();

        BattleShipGame game = new BattleShipGame(player1Name, player2Name);
        game.playGame();
    }
}
