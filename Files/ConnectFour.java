import java.util.Scanner;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
//current version: Connect Four without GUI

public class ConnectFour {
    //this program runs a connect four game between two people at the same terminal

    public static int[][] grid;
    public static int curRow;
    public static int curCol;
    public static int curCheck; //team currently making a move
    public static int num; //number of concurrent pieces in a row
    public static int round;
    public JButton gridButtons[][];
    JFrame f;
    JPanel panel;
    //panel.setFocusable(true);
    //panel.requestFocusInWindow();
    JLabel playerOne = new JLabel("Player 1: Which row do you wish to put your coin in? (1-7)");
    JLabel playerTwo = new JLabel("Player 2: Which row do you wish to put your coin in? (1-7)");
    Action playerOneAction;
    Action playerTwoAction;

    ConnectFour() {
        //instantiate 
        f = new JFrame("Connect Four");
        panel = new JPanel();
       // playerOneAction = new playerOneAction();
       // playerTwoAction = new playerTwoAction();
        playerOne.setBounds(150, 10, 350, 30);
        playerOne.setForeground(Color.RED);
        playerTwo.setBounds(150, 10, 350, 30);
        playerTwo.setForeground(Color.BLUE);
        playerTwo.setVisible(false);
        //playerOne.setVisible(false);
        panel.setLayout(null);
        panel.setBounds(40, 80, 650, 675);
        //sets up grid of buttons
        gridButtons = new JButton[7][6];
        for (int col = 0; col < 6; col++) {
            for (int row = 0; row < 7; row++) {
                gridButtons[row][col] = new JButton("");
                gridButtons[row][col].setBounds(50 + (row * 80), 550 - (col * 100), 70, 70);
                gridButtons[row][col].setBackground(Color.LIGHT_GRAY);
                gridButtons[row][col].setBorder(null);
                panel.add(gridButtons[row][col]);
            }
        }
        //panel decoration
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 10));
        f.setSize(800, 800);
        f.add(panel);
        f.setLayout(null);
        f.setVisible(true);
        panel.add(playerOne);
        panel.add(playerTwo);

         //setup buttons
      
        /*f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        f.addKeyListener(new KeyListener() {
        public void keyTyped(KeyEvent e) {
            if(e.getKeyCode() >= 1 || e.getKeyCode() <=7){
                System.out.println("KEY IS: " + e.getKeyCode()); 
            }
        }
    });
    */
    }
	
     public static void main(String[] args) {
        grid = new int[7][6];
        //System.out.println("Player 1 will be represented by 1's, and Player 2 will be represented by 2's");
        num = 0;
        round = 1;

        ConnectFour myGui = new ConnectFour();
        //Action myACtion = new playerOneAction();
        
        //where the game takes place
        while(true) {
            //red is player 1
            getOneMove(myGui);
           //printGrid();
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

    static void getOneMove(ConnectFour gui) {
        //ask which row they want to put the coin in -- shown as zeroes on the board
        //System.out.println("Player 1: What row do you wish to put your coin in? 1-7?");
        //Scanner input = new Scanner(System.in);
        int row = 1;
        row=row-1;
        //System.out.println("Player 1 stated ROW:" + row);
        
	   /* gui.panel.getInputMap().put(KeyStroke.getKeyStroke("1"), "myAction");
    	gui.panel.getActionMap().put("myAction", playerOneAction);
        Action myAction = new myAction();
	    System.out.println("Keystroke is: "+ getKeyStroke());
	    Action myAction = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
			   System.out.println("Keystroke is: " + getKeyStroke());
			   gui.gridButtons[row][i].setBackground(Color.RED);
		    }
	};*/
        curRow = row;
        for(int i=0;i < 7;i++) {
            if((row < 0) || (row > 7)) {
                System.out.println("Error, invalid row.");
                System.exit(0);
            } else if((grid[row][i] != 1) && (grid[row][i] != 2)) {
            // gui.gridButtons[row][i].setBackground(Color.RED);
                curCol = i;
                curCheck = 1;
                break;
            }
        }
    }


    static void getTwoMove() {
        //ask which row they want to put the coin in -- shown as ones on the board
        //System.out.println("Player 2: What col do you wish to put your coin in? 1-7?");
        Scanner input = new Scanner(System.in);
        int row = input.nextInt();
        row=row-1;
        curRow = row;
        for(int i=0;i < 7;i++) {
            if((row < 0) || (row > 7)) {
                System.out.println("Error, invalid row.");
                System.exit(0);
            } else if ((grid[row][i] != 1) && (grid[row][i] != 2)) {
                grid[row][i] = 2;
                curCol = i;
                curCheck = 2;
                break;
            }
        }

    }

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