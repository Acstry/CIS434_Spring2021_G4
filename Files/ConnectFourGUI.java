import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;  
public class ConnectFourGUI {  

     public void setColorR(int r, int i){
	gridButtons[r][i].setBackground(Color.RED);

     }



     ConnectFourGUI() {
        //setup panel
        JFrame f= new JFrame("Connect Four");
        JPanel panel=new JPanel();
	JLabel playerOne = new JLabel("Player 1: Which row do you wish to put your coin in? (1-7)");	 
	playerOne.setBounds(150,10,350,30);
	playerOne.setForeground(Color.RED);
	JLabel playerTwo = new JLabel("Player 2: Which row do you wish to put your coin in? (1-7)");	
	playerTwo.setBounds(150,10,350,30);
	playerTwo.setForeground(Color.BLUE);
	playerTwo.setVisible(false);
//	playerOne.setVisible(false);
	panel.setLayout(null);
        panel.setBounds(40,80,650,675);
        //panel decoration
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,10));

        //setup buttons
        JButton[][] gridButtons = new JButton[7][6];
        for(int col=0; col<6; col++) {
           for(int row=0; row<7; row++) {
              gridButtons[row][col] = new JButton("");
              gridButtons[row][col].setBounds(50 + (row*80),50 + (col*100),70,70);
              gridButtons[row][col].setBackground(Color.LIGHT_GRAY);
              gridButtons[row][col].setBorder(null);
              panel.add(gridButtons[row][col]); 
   	      panel.add(playerOne);    
           }
        }

//        JButton b1 = new JButton("");
//        b1.setBounds(50,100,80,80);
//        panel.add(b1);

//        JButton b1=new JButton("Row 1");
//        b1.setBounds(50,100,80,30);
//        JButton b2=new JButton("Row 2");
//        b2.setBounds(100,100,80,30);
//        JButton b3=new JButton("Row 3");
//        b3.setBounds(150,100,80,30);
//        JButton b4=new JButton("Row 4");
//        b3.setBounds(200,100,80,30);
//        JButton b5=new JButton("Row 5");
//        b3.setBounds(250,100,80,30);
//        JButton b6=new JButton("Row 6");
//        b3.setBounds(300,100,80,30);
//        JButton b7=new JButton("Row 7");
//        b3.setBounds(350,100,80,30);
        //panel.add(b1); panel.add(b2); panel.add(b3); panel.add(b4); panel.add(b5); panel.add(b6); panel.add(b7);

            f.setSize(800,800);
            f.add(panel);
            f.setLayout(null);
            f.setVisible(true);
            panel.add(playerOne);    
	    panel.add(playerTwo);
     }  
        //public static void main(String args[] {
            //new ConnectFourGUI();
        //}
    }  
