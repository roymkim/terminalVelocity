<<<<<<< HEAD
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
=======
import javax.swing.*;        
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    String name;
    JFrame frame;
    Container cPane;
    
    public GUI() {
	name = " ";
	createGUI();
    }
    
    public void createGUI(){
	frame = new JFrame("terminalVelocity");
	cPane = frame.getContentPane();
	cPane = setLayout(new GridBagLayout());

	arrangeComponents();
	frame.setSize(500,500);
	frame.setResizable(false);
	frame.setVisible(true);
    }

    public void arrangeComponents(){	
    }
>>>>>>> 79ac8ad1e95c79fbfb91264a106ef345535f4dcd

public class GUI {   
    public static void main(String[] args) {
<<<<<<< HEAD
	SwingUtilities.invokeLater(new Runnable(){
		public void run(){
		    JFrame frame = new MainFrame("terminalVelocity");
		    frame.setSize(500, 400);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setVisible(true);
		}
	    });
    }  
}
=======
	new GUI();
    }
}
>>>>>>> 79ac8ad1e95c79fbfb91264a106ef345535f4dcd
