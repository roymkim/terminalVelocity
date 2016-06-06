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
    
	setBorder(BorderFactory.createTitledBorder("Hotels Search"));

	JLabel queryLabel = new JLabel("Enter Destination or Hotel Name: ");
	JLabel checkInLabel = new JLabel("Enter Check-In Date: ");
	JLabel checkOutLabel = new JLabel("Enter Check-Out Date: ");
	JLabel guestsLabel = new JLabel("Guests: ");
	JLabel roomsLabel = new JLabel("Rooms: ");
	
	Calendar checkInCal = Calendar.getInstance();
	Date checkInDate = checkInCal.getTime();
	SpinnerDateModel checkInModel = new SpinnerDateModel();
	checkInModel.setValue(checkInDate);
	JSpinner checkInSpinner = new JSpinner(checkInModel);

	checkInSpinner.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent e){
		    Date date = (Date) ((JSpinner) e.getSource()).getValue();
		}
	    });
	SimpleDateFormat checkInFormat = ((JSpinner.DateEditor) checkInSpinner.getEditor()).getFormat();
	checkInFormat.applyPattern("yyyy-MM-dd");

	Calendar checkOutCal = Calendar.getInstance();
	checkOutCal.add(Calendar.DATE, 1);
	Date checkOutDate = checkOutCal.getTime();
	SpinnerDateModel checkOutModel = new SpinnerDateModel();
	checkOutModel.setValue(checkOutDate);
	JSpinner checkOutSpinner = new JSpinner(checkOutModel);
	
	checkOutSpinner.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent e){
		    Date date = (Date) ((JSpinner) e.getSource()).getValue();
		}
	    });
	SimpleDateFormat checkOutFormat = ((JSpinner.DateEditor) checkOutSpinner.getEditor()).getFormat();
	checkOutFormat.applyPattern("yyyy-MM-dd");

	Integer[] numberOfGuests = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	JComboBox<Integer> guests = new JComboBox<Integer>(numberOfGuests);

	Integer[] numberOfRooms = {1, 2, 3, 4, 5};
	JComboBox<Integer> rooms = new JComboBox<Integer>(numberOfRooms);

	guests.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
		    JComboBox combo = (JComboBox)e.getSource();
		}
	    });
	
	final JTextField nameField = new JTextField(10);
    
	JButton addBtn = new JButton("Search");

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
	add(queryLabel, gc);

	gc.gridx = 0;
	gc.gridy = 1;
	add(checkInLabel, gc);

	gc.gridx = 0;
	gc.gridy = 2;
	add(checkOutLabel, gc);

	gc.gridx = 0;
	gc.gridy = 3;
	add(guestsLabel, gc);

	gc.gridx = 0;
	gc.gridy = 4;
	add(roomsLabel, gc);

	//2nd
	gc.anchor = GridBagConstraints.LINE_START;
	
	gc.gridx = 1;
	gc.gridy = 0;
	add(nameField, gc);

	gc.gridx = 1;
	gc.gridy = 1;
	add(checkInSpinner, gc);

	gc.gridx = 1;
	gc.gridy = 2;
	add(checkOutSpinner, gc);

	gc.gridx = 1;
	gc.gridy = 3;
	add(guests, gc);

	gc.gridx = 1;
	gc.gridy = 4;
	add(rooms, gc);

	//Final Row
	gc.weighty = 10;

	gc.anchor = GridBagConstraints.FIRST_LINE_START;
	gc.gridx = 1;
	gc.gridy = 5;
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

