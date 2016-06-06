import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private DetailsPanel detailsPanel;

    public MainFrame(String title){
	super(title);

	setLayout(new BorderLayout());

	final JTextArea textArea = new JTextArea(15,10);	
	textArea.setEditable(false);

	JScrollPane scrollPane = new JScrollPane(textArea);

	detailsPanel = new DetailsPanel();

	detailsPanel.addDetailListener(new DetailListener() {
	    public void detailEventOccurred(DetailEvent event){
		String text = event.getText();
		
		textArea.append(text);
	    }
	});
	
	Container c = getContentPane();

	c.add(scrollPane, BorderLayout.SOUTH);
	c.add(detailsPanel, BorderLayout.NORTH);

    }
}