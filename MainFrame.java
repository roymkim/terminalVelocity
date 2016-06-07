import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class MainFrame extends JFrame {
    private DetailsPanel detailsPanel;

    public MainFrame(String title){
	super(title);

	setLayout(new BorderLayout());

	StyleContext sc = new StyleContext();
	final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
	JTextPane pane = new JTextPane(doc);
	pane.setPreferredSize( new Dimension(300, 270));
	pane.setEditable(false);

	JScrollPane scrollPane = new JScrollPane(pane);

	detailsPanel = new DetailsPanel();

	detailsPanel.addDetailListener(new DetailListener() {
	    public void detailEventOccurred(DetailEvent event){
		String text = event.getText();
		try {
		    doc.insertString(doc.getLength(), text, null);
		} catch (BadLocationException e){
		}
	    }
	});
	
	Container c = getContentPane();

	c.add(scrollPane, BorderLayout.SOUTH);
	c.add(detailsPanel, BorderLayout.NORTH);

    }
}