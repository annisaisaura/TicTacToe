public class Board {

    private int[][] data;   // menyimpan nilai 0 / 1 / -1
    private int turn;       // menyimpan nilai 1 / -1
    
    /*
    constructor
    inisialisasi board dengan nilai 0
    dan turn dengan 1 atau -1 sesuai input user
    */
    public Board(int turn) {
        this.data = new int[3][3];
        this.turn = turn;
    }

    // cek giliran saat ini
    public int getTurn() {
        return this.turn;
    }
    
    // cetak board di layar
    public void disp() {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                switch(this.data[i][j]) {
                    case 0  -> System.out.print("  -  ");
                    case -1 -> System.out.print("  X  ");
                    case 1  -> System.out.print("  O  ");
                }
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }
    
    /*
    setting board pada baris dan kolom yang telah ditentukan
    dengan nilai 1 atau -1 sesuai dengan giliran/turn
    */
    public boolean setBoard(int brs, int kol) {
        if(this.data[brs][kol]==0) {
            this.data[brs][kol] = turn;
            turn = -turn;
            return true;
        }
        else
            return false;
    }
    
    public int winner() {
        // cek baris
        for (int i=0; i<3; i++) {
            int a = data[i][0];
            int b = data[i][1];
            int c = data[i][2];
            if (a != 0 && a == b && b == c) {
                return a;
            }
        }

        // cek kolom
        for (int j=0; j<3; j++) {
            int a = data[0][j];
            int b = data[1][j];
            int c = data[2][j];
            if (a != 0 && a == b && b == c) {
                return a;
            }
        }

        // cek diagonal utama (dari kiri atas ke kanan bawah)
        int a = data[0][0];
        int b = data[1][1];
        int c = data[2][2];
        if (a != 0 && a == b && b == c) {
            return a;
        }

        // cek diagonal sekunder (dari kanan atas ke kiri bawah)
        a = data[0][2];
        b = data[1][1];
        c = data[2][0];
        if (a != 0 && a == b && b == c) {
            return a;
        }
        
        // belum ada pemenang
        return 0;
    }
    
    public boolean gameOver() {
        // cek board kosong
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (data[i][j] == 0) {
                    return false;
                }
            }
        }
        
        // semua board terisi
        return true;
    }
    
    public void resetBoard() {
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                this.data[i][j] = 0;
    }

    // cari posisi untuk blok langkah pemain yang hampir mendapatkan skor
    public int[] findBlockingMove(int player) {
        // cek baris
        for (int i = 0; i < 3; i++) {
            int countPlayer = 0;
            int countEmpty = 0;
            int emptyCol = -1;
            for (int j = 0; j < 3; j++) {
                if (data[i][j] == player) countPlayer++;
                else if (data[i][j] == 0) {
                    countEmpty++;
                    emptyCol = j;
                }
            }
            if (countPlayer == 2 && countEmpty == 1) {
                return new int[]{i, emptyCol};
            }
        }

        // cek kolom
        for (int j = 0; j < 3; j++) {
            int countPlayer = 0;
            int countEmpty = 0;
            int emptyRow = -1;
            for (int i = 0; i < 3; i++) {
                if (data[i][j] == player) countPlayer++;
                else if (data[i][j] == 0) {
                    countEmpty++;
                    emptyRow = i;
                }
            }
            if (countPlayer == 2 && countEmpty == 1) {
                return new int[]{emptyRow, j};
            }
        }

        // cek diagonal utama
        int countPlayer = 0, countEmpty = 0, emptyPos = -1;
        for (int i = 0; i < 3; i++) {
            if (data[i][i] == player) countPlayer++;
            else if (data[i][i] == 0) {
                countEmpty++;
                emptyPos = i;
            }
        }
        if (countPlayer == 2 && countEmpty == 1) {
            return new int[]{emptyPos, emptyPos};
        }

        // cek diagonal sekunder
        countPlayer = 0; countEmpty = 0; emptyPos = -1;
        for (int i = 0; i < 3; i++) {
            int j = 2 - i;
            if (data[i][j] == player) countPlayer++;
            else if (data[i][j] == 0) {
                countEmpty++;
                emptyPos = i;
            }
        }
        if (countPlayer == 2 && countEmpty == 1) {
            return new int[]{emptyPos, 2 - emptyPos};
        }

        // tidak ada posisi untuk blok
        return null;
    }

}
