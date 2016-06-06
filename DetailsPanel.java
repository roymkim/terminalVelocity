import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class DetailsPanel extends JPanel{
    private static final long serialVersionUID = 6915622549267792262L;
    private EventListenerList listenerList = new EventListenerList();

    public DetailsPanel(){
	Dimension size = getPreferredSize();
	size.height = 200;
	setPreferredSize(size);
    
	setBorder(BorderFactory.createTitledBorder("Search Details"));

	JLabel nameLabel = new JLabel("Name: ");
	
	Calendar cal = Calendar.getInstance();
	Date date = cal.getTime();
	SpinnerDateModel model = new SpinnerDateModel();
	model.setValue(date);
	JSpinner spinner = new JSpinner(model);
	spinner.addChangeListener(new ChangeListener() {
	    @Override
		public void stateChanged(ChangeEvent e){
		Date date = (Date) ((JSpinner) e.getSource()).getValue();
	    }
	    });
	SimpleDateFormat format = ((JSpinner.DateEditor) spinner.getEditor()).getFormat();
	format.applyPattern("yyyy-MM-dd");

	final JTextField nameField = new JTextField(10);
    
	JButton addBtn = new JButton("Add");

	addBtn.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e){
	        String name = nameField.getText();
		
		String text = name + "\n";

		fireDetailEvent(new DetailEvent(this, text));
	    }
	});

	setLayout(new GridBagLayout());

	GridBagConstraints gc = new GridBagConstraints();

	gc.anchor = GridBagConstraints.LINE_END;
	gc.weightx = 0.5;
	gc.weighty = 0.5;

	gc.gridx = 0;
	gc.gridy = 0;
	add(nameLabel, gc);

	gc.gridx = 0;
	gc.gridy = 1;

	//2nd
	gc.anchor = GridBagConstraints.LINE_START;
	
	gc.gridx = 1;
	gc.gridy = 0;
	add(nameField, gc);

	gc.gridx = 1;
	gc.gridy = 1;


	gc.gridx = 1;
	gc.gridy = 2;
	add(spinner, gc);
	//Final Row
	gc.weighty = 10;

	gc.anchor = GridBagConstraints.FIRST_LINE_START;
	gc.gridx = 1;
	gc.gridy = 3;
	add(addBtn, gc);
    }
    
    public void fireDetailEvent(DetailEvent event){
	Object[] listeners = listenerList.getListenerList();
	
	for(int i = 0; i < listeners.length; i+=2){
	    if(listeners[i] == DetailListener.class){
		((DetailListener)listeners[i+1]).detailEventOccurred(event);
	    }
	}
    }

    public void addDetailListener(DetailListener listener){
	listenerList.add(DetailListener.class, listener);
    }

    public void removeDetailListener(DetailListener listener){
	listenerList.remove(DetailListener.class, listener);
    }
}

