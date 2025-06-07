import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        int scoreX = 0;
        int scoreO = 0;
        
        System.out.println("Selamat datang di Tic Tac Toe!");
        System.out.println("Pilih mode permainan:");
        System.out.println("1. Player vs Player");
        System.out.println("2. Player vs Komputer");
        System.out.print("Masukkan pilihan (1/2): ");
        int mode = input.nextInt();

        System.out.println("Pilih lempar koin: 1. Kepala  2. Ekor");
        System.out.print("Masukkan pilihan (1/2): ");
        int userCall = input.nextInt();

        int coinResult = rand.nextBoolean() ? 1 : 2;
        System.out.println("Hasil lempar koin: " + (coinResult == 1 ? "Kepala" : "Ekor"));

        int turn;
        if (userCall == coinResult) {
            System.out.println("Kamu menang lempar koin! X mulai duluan.");
            turn = -1;
        } else {
            System.out.println("Lawan menang lempar koin! O mulai duluan.");
            turn = 1;
        }

        System.out.print("Masukkan jumlah babak yang ingin dimainkan: ");
        int totalRounds = input.nextInt();

        Board board = new Board(turn);

        for (int round = 1; round <= totalRounds; round++) {
            System.out.println("\n=== Babak ke-" + round + " ===");
            board.resetBoard();
            board.disp();

            while (!board.gameOver()) {
                boolean validMove = false;
                while (!validMove) {
                    if (mode == 1 || (mode == 2 && board.getTurn() == -1)) {
                        // giliran pemain
                        System.out.print("Giliran " + (board.getTurn() == -1 ? "X" : "O") + ". Pilih baris ke- (1-3): ");
                        int brs = input.nextInt() - 1;
                        System.out.print("Pilih kolom ke- (1-3): ");
                        int kol = input.nextInt() - 1;
                        if(brs < 0 || brs > 2 || kol < 0 || kol > 2) {
                            System.out.println("Input tidak valid. Baris dan kolom harus antara 1-3. Coba lagi.");
                            } else {
                            validMove = board.setBoard(brs, kol);
                            if (!validMove) {
                                System.out.println("Posisi sudah terisi. Coba lagi.");
                            }
                        }
                    } else {
                        // giliran komputer
                        // cek apakah pemain hampir mendapatkan skor
                        int[] blockPos = board.findBlockingMove(-1);
                        if (blockPos != null) {
                            validMove = board.setBoard(blockPos[0], blockPos[1]);
                        } else {
                            // jika tidak ada ancaman, pilih posisi kosong secara acak
                            int brs = rand.nextInt(3);
                            int kol = rand.nextInt(3);
                            validMove = board.setBoard(brs, kol);
                        }
                    }
                }
                board.disp();
            }

            int win = board.winner();
            if (win == -1) {
                System.out.println("X menang babak ini!");
                scoreX++;
            } else if (win == 1) {
                System.out.println("O menang babak ini!");
                scoreO++;
            } else {
                System.out.println("Babak ini seri!");
            }

            System.out.println("Score sementara: X = " + scoreX + " | O = " + scoreO);
        }

        System.out.println("\n=== Permainan selesai ===");
        System.out.println("Score akhir: X = " + scoreX + " | O = " + scoreO);

        if (scoreX > scoreO) {
            System.out.println("Pemenangnya adalah X!");
        } else if (scoreO > scoreX) {
            System.out.println("Pemenangnya adalah O!");
        } else {
            System.out.println("Permainan seri!");
        }

        input.close();
        
    }
    
}
