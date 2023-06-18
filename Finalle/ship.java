package Finalle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Kelas abstrak untuk Kapal
abstract class Ship {
    protected String name;
    protected int size;
    protected int[][] coordinates;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.coordinates = new int[size][2];
    }

    public abstract void placeShip();

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int[][] getCoordinates() {
        return coordinates;
    }
}

// Kelas Kapal Perahu
class Perahu extends Ship {
    public Perahu() {
        super("Perahu", 2);
    }

    public void placeShip() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < size; i++) {
            boolean valid = false;
            while (!valid) {
                System.out.print("Masukkan baris koordinat untuk " + name + " (0-4): ");
                int row = scanner.nextInt();

                System.out.print("Masukkan kolom koordinat untuk " + name + " (0-9): ");
                int column = scanner.nextInt();

                if (isValidCoordinate(row, column)) {
                    coordinates[i][0] = row;
                    coordinates[i][1] = column;
                    valid = true;
                } else {
                    System.out.println("Koordinat tidak valid. Ulangi input.");
                }
            }
        }
    }

    private boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row <= 4 && col >= 0 && col <= 9;
    }
}

// Kelas Kapal KapalSelam
class KapalSelam extends Ship {
    public KapalSelam() {
        super("Kapal Selam", 3);
    }

    public void placeShip() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < size; i++) {
            boolean valid = false;
            while (!valid) {
                System.out.print("Masukkan baris koordinat untuk " + name + " (0-4): ");
                int row = scanner.nextInt();

                System.out.print("Masukkan kolom koordinat untuk " + name + " (0-9): ");
                int column = scanner.nextInt();

                if (isValidCoordinate(row, column)) {
                    coordinates[i][0] = row;
                    coordinates[i][1] = column;
                    valid = true;
                } else {
                    System.out.println("Koordinat tidak valid. Ulangi input.");
                }
            }
        }
    }

    private boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row <= 4 && col >= 0 && col <= 9;
    }
}

// Kelas Kapal KapalInduk
class KapalInduk extends Ship {
    public KapalInduk() {
        super("Kapal Induk", 4);
    }

    public void placeShip() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < size; i++) {
            boolean valid = false;
            while (!valid) {
                System.out.print("Masukkan baris koordinat untuk " + name + " (0-4): ");
                int row = scanner.nextInt();

                System.out.print("Masukkan kolom koordinat untuk " + name + " (0-9): ");
                int column = scanner.nextInt();

                if (isValidCoordinate(row, column)) {
                    coordinates[i][0] = row;
                    coordinates[i][1] = column;
                    valid = true;
                } else {
                    System.out.println("Koordinat tidak valid. Ulangi input.");
                }
            }
        }
    }

    private boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row <= 4 && col >= 0 && col <= 9;
    }
}

// Kelas Player
class Player {
    private String name;
    private int[][] map;
    private List<int[]> shotHistory;

    public Player(String name) {
        this.name = name;
        this.map = new int[5][10];
        this.shotHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int[][] getMap() {
        return map;
    }

    public List<int[]> getShotHistory() {
        return shotHistory;
    }

        private void markShipOnMap(Ship ship) {
        int[][] coordinates = ship.getCoordinates();
        for (int i = 0; i < ship.getSize(); i++) {
            int row = coordinates[i][0];
            int col = coordinates[i][1];
            map[row][col] = 1;
        }
    }

    private void printMapWithShips() {
        System.out.println(name + "'s Map (with ships):");
        System.out.println("   0 1 2 3 4 5 6 7 8 9");

        for (int row = 0; row < 5; row++) {
            System.out.print(row + "  ");
            for (int col = 0; col < 10; col++) {
                if (map[row][col] == 1) {
                    System.out.print("O ");
                } else if (map[row][col] == 2) {
                    System.out.print("X ");
                } else if (map[row][col] == -1) {
                    System.out.print("- ");
                } else {
                    System.out.print("~ ");
                }
            }
            System.out.println();
        }
    }

    public void placeShips() {
        Ship[] ships = {new Perahu(), new KapalSelam(), new KapalInduk()};

        for (Ship ship : ships) {
            System.out.println(name + ", tempatkan kapal " + ship.getName() + ":");
            ship.placeShip();
            markShipOnMap(ship);
            printMapWithShips();
            System.out.println();
        }

        System.out.println(name + ", kapal-kapalmu telah ditempatkan!\n");
    }

    

    public void shoot(Player targetPlayer) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(name + ", saatnya menembak!");

        boolean valid = false;
        int row = -1;
        int col = -1;
        while (!valid) {
            System.out.print("Masukkan baris yang akan ditembak (0-4): ");
            row = scanner.nextInt();

            System.out.print("Masukkan kolom yang akan ditembak (0-9): ");
            col = scanner.nextInt();

            if (isValidShot(row, col)) {
                valid = true;
            } else {
                System.out.println("Tembakan tidak valid. Ulangi input.");
            }
        }

        int[] shot = {row, col};
        targetPlayer.recordShot(shot);

        if (targetPlayer.isHit(shot)) {
            System.out.println("TARGET BOMBED!");
            targetPlayer.getMap()[row][col] = 2;
        } else {
            System.out.println("NDA KENA BRO!");
            targetPlayer.getMap()[row][col] = -1;
        }
    }

    private boolean isValidShot(int row, int col) {
        return row >= 0 && row <= 4 && col >= 0 && col <= 9;
    }

    private boolean isHit(int[] shot) {
        int row = shot[0];
        int col = shot[1];

        return map[row][col] == 1;
    }

    private void recordShot(int[] shot) {
        shotHistory.add(shot);
    }

    public boolean hasShipsRemaining() {
        for (int[] row : map) {
            for (int cell : row) {
                if (cell == 1) {
                    return true;
                }
            }
        }
        return false;
    }
}

// Kelas BattleShipGame
class BattleShipGame {
    private static final int NUM_ROUNDS = 3;

    private Player player1;
    private Player player2;

    public BattleShipGame(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
    }

    public void playGame() {
        player1.placeShips();
        player2.placeShips();

        for (int round = 1; round <= NUM_ROUNDS; round++) {
            System.out.println("ROUND " + round + " - " + player1.getName() + " VS " + player2.getName());
            System.out.println("--------------------------");

            while (true) {
                player1.shoot(player2);
                if (!player2.hasShipsRemaining()) {
                    break;
                }

                player2.shoot(player1);
                if (!player1.hasShipsRemaining()) {
                    break;
                }
            }

            printMap(player1, player1.getName());
            printMap(player2, player2.getName());
            System.out.println();
        }

        System.out.println("GAME OVER");
        System.out.println("----------");

        if (player1.hasShipsRemaining() && player2.hasShipsRemaining()) {
            System.out.println("Permainan berakhir seri!");
        } else if (player1.hasShipsRemaining()) {
            System.out.println(player1.getName() + " memenangkan permainan!");
        } else if (player2.hasShipsRemaining()) {
            System.out.println(player2.getName() + " memenangkan permainan!");
        }
    }

    private void printMap(Player player, String playerName) {
        int[][] map = player.getMap();

        System.out.println(playerName + "'s Map:");
        System.out.println("   0 1 2 3 4 5 6 7 8 9");

        for (int row = 0; row < 5; row++) {
            System.out.print(row + "  ");
            for (int col = 0; col < 10; col++) {
                if (map[row][col] == 1) {
                    System.out.print("O ");
                } else if (map[row][col] == 2) {
                    System.out.print("X ");
                } else if (map[row][col] == -1) {
                    System.out.print("- ");
                } else {
                    System.out.print("~ ");
                }
            }
            System.out.println();
        }
    }
}