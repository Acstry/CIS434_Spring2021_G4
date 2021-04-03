import java.util.Scanner;

//current version: Connect Four without GUI
//Milica worked on this on 3/01/21
public class ConnectFour {
    //this program runs a connect four game between two people at the same terminal

    public static int[][] grid;
    public static int curRow;
    public static int curCol;
    public static int curCheck; //team currently making a move
    public static int num; //number of concurrent pieces in a row
    public static int round;

    //Milica worked on this on 3/01/21
    public static void main(String[] args) {
        grid = new int[7][6];
        System.out.println("Player 1 will be represented by 1's, and Player 2 will be represented by 2's");
        num = 0;
        round = 1;

        //Milica worked on this on 3/01/21
        //where the game takes place
        while(true) {
            //red is player 1
            getOneMove();
            printGrid();
            if(round > 3) {
                checkSolution();
                num = 0;
            }
            //blue is player 2
            getTwoMove();
            printGrid();
            if(round > 3) {
                checkSolution();
                num = 0;
            }
            round++;
        }
    }

//Skhatri worked on this 3/04/21
    static void getOneMove() {
        //ask which row they want to put the coin in -- shown as zeroes on the board
        System.out.println("Player 1: What row do you wish to put your coin in? 1-7?");
        Scanner input = new Scanner(System.in);
        int row = input.nextInt();
        row=row-1;
        curRow = row;
        for(int i=0;i < 7;i++) {
            while((row < 0) || (row > 7)) {
                System.out.println("Error, invalid row. Please try again");
                row = input.nextInt();
            } 
            if((grid[row][i] != 1) && (grid[row][i] != 2)) {
                grid[row][i] = 1;
                curCol = i;
                curCheck = 1;
                break;
            }
        }
    }

//Skhatri worked on this 3/04/21
    static void getTwoMove() {
        //ask which row they want to put the coin in -- shown as ones on the board
        System.out.println("Player 2: What col do you wish to put your coin in? 1-7?");
        Scanner input = new Scanner(System.in);
        int row = input.nextInt();
        row=row-1;
        curRow = row;
        for(int i=0;i < 7;i++) {
            while((row < 0) || (row > 7)) {
                System.out.println("Error, invalid row. Please try again");
                row = input.nextInt();
            }
            if ((grid[row][i] != 1) && (grid[row][i] != 2)) {
                grid[row][i] = 2;
                curCol = i;
                curCheck = 2;
                break;
            }
        }

    }
//Skhatri worked on this 3/06/21
    static void printGrid() {
        //prints the current game board
        for(int j=5; j >= 0;j--) {
            for (int i = 0; i < 7; i++) {
                if((grid[i][j] == 1) || (grid[i][j] == 2)) {
                    System.out.print(" | " + grid[i][j]);
                } else {
                    System.out.print(" |  ");
                }
            }
            System.out.print(" |\n");
        }
    }

//Val worked on this 3/11/21
    static void checkSolution() {
        //if winner found, declare winner and exit the game
        checkHorizontal();
        checkNum();
        checkVertical();
        checkNum();
        checkDiagonalLeft();
        checkNum();
        checkDiagonalRight();
        checkNum();
    }

//Val worked on this 3/11/21
    static void checkHorizontal() {
        //checks horizontal solution

        for(int i = 0; i < 7; i++) {
            if(grid[i][curCol] == curCheck) {
                num++;
            } else if (num == 4) {
                return;
            } else {
                num = 0;
            }
        }

    }

//Val worked on this 3/11/21
    static void checkVertical() {
        //checks vertical solution
        for(int i = 0; i < 5; i++) {
            if(grid[curRow][i] == curCheck) {
                num++;
            } else if(num == 4) {
                return;
            } else {
                num = 0;
            }
        }
    }

//Val worked on this 3/11/21
    static void checkDiagonalRight() {
        //checks the right diagonal

        //checks first half of right diagonals
        int i, j;
        for(int diaStart = 0; diaStart < 3; diaStart++) {
            num = 0;
            for(i = diaStart, j = 0; (i < 7) && (j < 6); i++, j++) {
                if(grid[i][j] == curCheck) {
                    num++;
                } else if(num == 4) {
                    return;
                } else {
                    num = 0;
                }
            }
        }

        //checks second half of diagonals
        for(int diaStart = 0; diaStart < 2; diaStart++) {
            num = 0;
            for(i = 0, j = diaStart; (i < 7) && (j < 6); i++, j++) {
                if(grid[i][j] == curCheck) {
                    num++;
                } else if(num == 4) {
                    return;
                } else {
                    num = 0;
                }
            }
        }

    }

//Val worked on this 3/11/21
    static void checkDiagonalLeft() {
        //checks the left diagonal

        //checks first half of left diagonals
        int i, j;
        for(int diaStart = 6; diaStart >= 4; diaStart--) {
            num = 0;
            for(i = diaStart, j = 0; (i > 0) && (j < 6); i--, j++) {
                if(grid[i][j] == curCheck) {
                    num++;
                } else if(num == 4) {
                    return;
                } else {
                    num = 0;
                }
            }
        }

        //checks second half of diagonals
        for(int diaStart = 5; diaStart >= 3; diaStart--) {
            num = 0;
            for (i = 0, j = diaStart; (i < 7) && (j > 0); i++, j--) {
                if (grid[i][j] == curCheck) {
                    num++;
                } else if (num == 4) {
                    return;
                } else {
                    num = 0;
                }
            }
        }
    }

//Val worked on this 3/11/21
    static void checkNum() {
        //checks to see if the number of coins in a row is 4
        if(num >= 4) {
            if(curCheck == 1) {
                //Red wins
                System.out.println("Player 1 wins!");
                System.exit(0);
            } else if(curCheck == 2) {
                //Blue wins
                System.out.println("Player 2 wins!");
                System.exit(0);
            }
        }
    }

}
