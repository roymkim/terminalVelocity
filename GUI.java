import javax.swing.*;        
import java.awt.*;
import java.awt.event.*;

public class GUI {   
    public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable(){
		public void run(){
		    JFrame frame = new MainFrame("terminalVelocity");
		    frame.setSize(500, 500);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setVisible(true);
		}
	    });
    }  
}

