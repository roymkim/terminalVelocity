import javax.swing.*;        
import java.awt.*;
import java.awt.event.*;

public class GUI {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("terminalVelocity");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(400,400);
	frame.setLayout(new GridLayout(3, 1));

        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        //frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		    createAndShowGUI();
		}
	    });
    }
}