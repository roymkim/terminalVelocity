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

    public static void main(String[] args) {
	new GUI();
    }
}
