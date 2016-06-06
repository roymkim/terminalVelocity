import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private DetailsPanel detailsPanel;

    public MainFrame(String title){
	super(title);

	setLayout(new BorderLayout());

	final JTextArea textArea = new JTextArea();
	JButton button = new JButton("Click Me!");

	detailsPanel = new DetailsPanel();

	detailsPanel.addDetailListener(new DetailListener() {
	    public void detailEventOccurred(DetailEvent event){
		String text = event.getText();
		    
		textArea.append(text);
	    }
	});
	
	Container c = getContentPane();

	c.add(textArea, BorderLayout.CENTER);
	c.add(button, BorderLayout.SOUTH);
	c.add(detailsPanel, BorderLayout.WEST);

	button.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e){
		textArea.append("Hello\n");
	    }
	});
    }
}