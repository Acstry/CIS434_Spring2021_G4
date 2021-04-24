import java.awt.event.*;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;




//this program runs a connect four game between two people at the same terminal
public class ConnectFour {

    public static ConnectFour myGui;


    public static int bestOf;
    public static int numGames;
    public static int winsNeeded;
    public static int oneWins;
    public static int twoWins;

    public static int[][] grid;
    public static int curRow;
    public static int row;
    public static int curCol;
    public static int curCheck; //team currently making a move, 1 is player 1 and 2 is player 2
    boolean playerTurn = false; //false means player one's turn, true means player two's turn
    public static int num; //number of concurrent pieces in a row
    public static int round;

    //graphics for ConnectFour class
    public RoundedButton[][] gridButtons;
    static JFrame f;
    static JPanel panel;
    static JLabel bestOfGames = new JLabel("Game one of one");
    static JLabel playerOne = new JLabel("Player 1: Which row do you wish to put your coin in? (1-7)");
    static JLabel playerTwo = new JLabel("Player 2: Which row do you wish to put your coin in? (1-7)");
    static Action playerAction;
    

    ConnectFour() {
        if(numGames == 0) {
            //Instantiation
            f = new JFrame("Connect Four");

            panel = new JPanel();
            // panel2 = new JPanel();
            playerAction = new playerAction();
            playerOne.setBounds(150, 10, 350, 30);
            playerOne.setForeground(Color.RED);
            playerOne.setHorizontalAlignment(JLabel.CENTER);
            playerOne.setVisible(true);

            playerTwo.setBounds(150, 10, 350, 30);
            playerTwo.setForeground(Color.BLUE);
            playerTwo.setHorizontalAlignment(JLabel.CENTER);
            playerTwo.setVisible(false);

            bestOfGames.setBounds(300, 25, 100, 30);


            panel.setLayout(null);
            panel.setBounds(40, 40, 650, 675);

            //checks to see if keys 1-7 are pressed
            panel.addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {
                    int code = e.getKeyChar();
                    if (code < 49 || code > 55) {
                        JOptionPane.showMessageDialog(null, "invalid input, please enter 1-7");
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    // TODO Auto-generated method stub
                }
            });

            //Sets up grid of buttons
            gridButtons = new RoundedButton[7][6];
            for (int col = 0; col < 6; col++) {
                for (int row = 0; row < 7; row++) {
                    gridButtons[row][col] = new RoundedButton("");
                    gridButtons[row][col].setBounds(50 + (row * 80), 550 - (col * 100), 70, 70);
                    gridButtons[row][col].setBackground(new Color(222, 222, 222));
                    panel.add(gridButtons[row][col]);
                }
            }

            //panel decoration
            panel.setBackground(new Color(249, 235, 77));
            panel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 247), 10));

            f.setSize(800, 800);
            f.setLayout(null);
            f.setVisible(true);

            panel.setVisible(true);
            f.getContentPane().add(panel);

            panel.add(playerOne);
            panel.add(playerTwo);
            panel.add(bestOfGames);

            // User input based actions
            panel.getInputMap().put(KeyStroke.getKeyStroke("1"), "myAction");
            panel.getInputMap().put(KeyStroke.getKeyStroke("2"), "myAction");
            panel.getInputMap().put(KeyStroke.getKeyStroke("3"), "myAction");
            panel.getInputMap().put(KeyStroke.getKeyStroke("4"), "myAction");
            panel.getInputMap().put(KeyStroke.getKeyStroke("5"), "myAction");
            panel.getInputMap().put(KeyStroke.getKeyStroke("6"), "myAction");
            panel.getInputMap().put(KeyStroke.getKeyStroke("7"), "myAction");
            panel.getActionMap().put("myAction", playerAction);

            // Ensures program exits when GUI window is closed
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
            for (int col = 0; col < 6; col++) {
                for (int row = 0; row < 7; row++) {
                    gridButtons[row][col].setBackground(new Color(222, 222, 222));
                    grid[row][col] = '\0';
                }
            }
        }
    }
	
  public class playerAction extends AbstractAction{

    @Override
    public void actionPerformed(ActionEvent e) {
        
        checkSolution();
        round = 1;
        playerOne.setText("Player 1: Which row do you wish to put your coin in? (1-7)");

        if(round > 3) {
            checkSolution();
            num = 0;
        }
        if(e.getActionCommand().equals("1")){
            row = 0;
        }
        else if(e.getActionCommand().equals("2")){
            row = 1;
        }
        else if(e.getActionCommand().equals("3")){
            row = 2;
        }
        else if(e.getActionCommand().equals("4")){
            row = 3;
        }
        else if(e.getActionCommand().equals("5")){
            row = 4;
        }
        else if(e.getActionCommand().equals("6")){
            row = 5;
        }
        else if(e.getActionCommand().equals("7")){
            row = 6;
        }

        curRow = row;
        for(int i=0; i<7;i++) {
            if((row < 0) || (row > 7)) {
                System.out.println("Error, invalid row.");
                System.exit(0);
            } else if((grid[row][i] != 1) && (grid[row][i] != 2)) {
                if(!playerTurn){        //player 1's turn
                    gridButtons[row][i].setBackground(Color.RED);
                    panel.repaint();
                    playerOne.setVisible(false);
                    playerTwo.setVisible(true);
                    checkSolution();
                    playerTurn = true;
                    grid[row][i] = 1;
                    curCol = i;
                    curCheck = 1;
                    round++;
                    checkSolution();
                } else if(playerTurn){ //player 2's turn
                    gridButtons[row][i].setBackground(Color.BLUE);
                    panel.repaint();
                    playerTwo.setVisible(false);
                    playerOne.setVisible(true);
                    checkSolution();
                    playerTurn = false;
                    grid[row][i] = 2;
                    curCol = i;
                    curCheck = 2;
                    round++;
                    checkSolution();
                }

                checkSolution();
                break;
            }
        }
    }

}
     public static void main(String[] args) throws IOException {

        String[] options = {"1","3","5"};
        numGames = 0;
        int x = JOptionPane.showOptionDialog(null, "Best of how many games?", "Click a button", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if(x == 0){
            bestOf = 1;
            bestOfGames.setText("Game " + (numGames+1) +" of " + bestOf);
            winsNeeded = 1;
        }
        else if(x == 1){
            bestOf = 3;
            bestOfGames.setText("Game " + (numGames+1) +" of " + bestOf);
            winsNeeded = 2;
        }
        else if(x == 2){
            bestOf = 5;
            bestOfGames.setText("Game " + (numGames+1) +" of " + bestOf);
            winsNeeded = 3;
        }

        grid = new int[7][6];
        myGui = new ConnectFour();
        getMove();

    }
   
    static void getMove() throws IOException {
        System.in.read();
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
                JOptionPane.showMessageDialog(null, "Player 1 wins game " + (numGames+1) +" of " + bestOf + "!");
                oneWins++;
                checkBestOfWins();
            } else if(curCheck == 2) {
                //Blue wins
                JOptionPane.showMessageDialog(null, "Player 2 wins game " + (numGames+1) +" of " + bestOf + "!");
                twoWins++;
                checkBestOfWins();
            }
        }
    }

    static void checkBestOfWins() {
        if(oneWins == winsNeeded) {
            JOptionPane.showMessageDialog(null, "Player 1 wins the series!");
            System.exit(0);
        } else if(twoWins == winsNeeded) {
            JOptionPane.showMessageDialog(null, "Player 2 wins the series!");
            System.exit(0);
        } else {
            numGames++;
            grid = new int[7][6];
            curRow = 0;
            row = 0;
            curCol = 0;
            curCheck = 1;
            myGui.playerTurn = false; //false means player one's turn, true means player two's turn
            num = 0;
            round = 0;

            myGui = new ConnectFour();
            try {
                getMove();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class RoundedButton extends Component {

        ActionListener actionListener;     // Post action events to listeners
        String label;                      // The Button's text
        protected boolean pressed = false; // true if the button is detented.

        /**
         * Constructs a RoundedButton with no label.
         */
        public RoundedButton() {
            this("");
        }

        /**
         * Constructs a RoundedButton with the specified label.
         *
         * @param label the label of the button
         */
        public RoundedButton(String label) {
            this.label = label;
            enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
            invalidate();
            repaint();
        }

        /**
         * paints the RoundedButton
         */
        @Override
        public void paint(Graphics g) {

            // paint the interior of the button
            if (pressed) {
                g.setColor(getBackground().darker().darker());
            } else {
                g.setColor(getBackground());
            }
            g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 50, 50);

            // draw the perimeter of the button
            g.setColor(getBackground());
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 50, 50);

            // draw the label centered in the button
            Font f = getFont();
            if (f != null) {
                FontMetrics fm = getFontMetrics(getFont());
                g.setColor(getForeground());
                g.drawString(label, getWidth() / 2 - fm.stringWidth(label) / 2, getHeight() / 2 + fm.getMaxDescent());
            }
        }

        /**
         * The preferred size of the button.
         */
        @Override
        public Dimension getPreferredSize() {
            Font f = getFont();
            if (f != null) {
                FontMetrics fm = getFontMetrics(getFont());
                int max = Math.max(fm.stringWidth(label) + 40, fm.getHeight() + 40);
                return new Dimension(max, max);
            } else {
                return new Dimension(100, 100);
            }
        }

        /**
         * The minimum size of the button.
         */
        @Override
        public Dimension getMinimumSize() {
            return new Dimension(100, 100);
        }

        /**
         * Adds the specified action listener to receive action events from this
         * button.
         *
         * @param listener the action listener
         */
        public void addActionListener(ActionListener listener) {
            actionListener = AWTEventMulticaster.add(actionListener, listener);
            enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        }

        /**
         * Removes the specified action listener so it no longer receives action
         * events from this button.
         *
         * @param listener the action listener
         */
        public void removeActionListener(ActionListener listener) {
            actionListener = AWTEventMulticaster.remove(actionListener, listener);
        }

        /**
         * Determine if click was inside round button.
         */
        @Override
        public boolean contains(int x, int y) {
            int mx = getSize().width / 2;
            int my = getSize().height / 2;
            return (((mx - x) * (mx - x) + (my - y) * (my - y)) <= mx * mx);
        }

        /**
         * Paints the button and distribute an action event to all listeners.
         */
        @Override
        public void processMouseEvent(MouseEvent e) {
            Graphics g;
            switch (e.getID()) {
                case MouseEvent.MOUSE_PRESSED:
                    // render myself inverted....
                    pressed = true;

                    // Repaint might flicker a bit. To avoid this, you can use
                    // double buffering (see the Gauge example).
                    repaint();
                    break;
                case MouseEvent.MOUSE_RELEASED:
                    if (actionListener != null) {
                        actionListener.actionPerformed(new ActionEvent(
                                this, ActionEvent.ACTION_PERFORMED, label));
                    }
                    // render myself normal again
                    if (pressed == true) {
                        pressed = false;

                        // Repaint might flicker a bit. To avoid this, you can use
                        // double buffering (see the Gauge example).
                        repaint();
                    }
                    break;
                case MouseEvent.MOUSE_ENTERED:

                    break;
                case MouseEvent.MOUSE_EXITED:
                    if (pressed == true) {
                        // Cancel! Don't send action event.
                        pressed = false;

                        // Repaint might flicker a bit. To avoid this, you can use
                        // double buffering (see the Gauge example).
                        repaint();

                        // Note: for a more complete button implementation,
                        // you wouldn't want to cancel at this point, but
                        // rather detect when the mouse re-entered, and
                        // re-highlight the button. There are a few state
                        // issues that that you need to handle, which we leave
                        // this an an excercise for the reader (I always
                        // wanted to say that!)
                    }
                    break;
            }
            super.processMouseEvent(e);
        }
    }

    /*
boolean playGame = true;// condition for user to play the game
         while (playGame) { 
 
            System.out.print("How many games would you like to play the best of (best of 3, 5...): ");
            String bestOf = input.nextLine();
            int bestOfInt = Integer.parseInt(bestOf);
    
            
                if (bestOfInt == (int)bestOfInt) {
                    System.out.println("Starting game to play a best of " + bestOf + "...");
                } else {
                    System.out.print("Invalid integer, please only enter an integer number of games: ");
                    bestOf = input.nextLine();
                    bestOfInt = Integer.parseInt(bestOf);
                }
        */
}