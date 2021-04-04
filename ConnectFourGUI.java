import java.awt.*;  
import javax.swing.*;  
public class ConnectFourGUI {  
     ConnectFourGUI()  
        {  
        JFrame f= new JFrame("Panel Example");    
        JPanel panel=new JPanel();  
        panel.setBounds(40,80,600,600);    
        panel.setBackground(Color.gray);  
        JButton b1=new JButton("Row 1");     
        b1.setBounds(50,100,80,30);     
        JButton b2=new JButton("Row 2");   
        b2.setBounds(100,100,80,30);     
        JButton b3=new JButton("Row 3");
        b3.setBounds(150,100,80,30);
        JButton b4=new JButton("Row 4");
        b3.setBounds(200,100,80,30);
        JButton b5=new JButton("Row 5");
        b3.setBounds(250,100,80,30);
        JButton b6=new JButton("Row 6");
        b3.setBounds(300,100,80,30);
        JButton b7=new JButton("Row 7");
        b3.setBounds(350,100,80,30);
        panel.add(b1); panel.add(b2); panel.add(b3); panel.add(b4); panel.add(b5); panel.add(b6); panel.add(b7);
        f.add(panel);  
                f.setSize(800,800);    
                f.setLayout(null);    
                f.setVisible(true);    
        }  
        public static void main(String args[])  
        {  
        new ConnectFourGUI();  
        }  
    }  