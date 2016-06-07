import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

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
	final JSpinner checkInSpinner = new JSpinner(checkInModel);

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
	final JSpinner checkOutSpinner = new JSpinner(checkOutModel);
	
	checkOutSpinner.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent e){
		    Date date = (Date) ((JSpinner) e.getSource()).getValue();
		}
	    });
	SimpleDateFormat checkOutFormat = ((JSpinner.DateEditor) checkOutSpinner.getEditor()).getFormat();
	checkOutFormat.applyPattern("yyyy-MM-dd");

	Integer[] numberOfGuests = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	final JComboBox<Integer> guests = new JComboBox<Integer>(numberOfGuests);

	Integer[] numberOfRooms = {1, 2, 3, 4, 5};
	final JComboBox<Integer> rooms = new JComboBox<Integer>(numberOfRooms);

	guests.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
		    JComboBox combo = (JComboBox)e.getSource();
		}
	    });
	
	final JTextField queryField = new JTextField(10);
    
	JButton search = new JButton("Search");

	search.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
		    //terminalVelocity terminalVelocity = new terminalVelocity();

		    boolean validQuery = false;
		    boolean validCID = false;
		    boolean validCOD = false;

		    String query = queryField.getText();
		    String text = "";

		    if (query.length() < 2){
			text = "Query must be at least two characters \n";
		    } else {
			text += query + "\n";
			validQuery = true;
		    } 

		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    
		    String CID = sdf.format(checkInSpinner.getValue());
		    String COD = sdf.format(checkOutSpinner.getValue());
		    if (isNotImpossibleDateCombination(CID, COD)){
			validCID = true;
			validCOD = true;
			text += sdf.format(checkInSpinner.getValue()) + "\n";
			text += sdf.format(checkOutSpinner.getValue()) + "\n";
		    } else {
			text += "Impossible Date Combination \n";
		    }

		    text += guests.getSelectedItem() + "\n";
		    text += guests.getSelectedItem() + "\n";

		    if (validQuery && validCID && validCOD){
			terminalVelocityGUI tV = new terminalVelocityGUI(text);
		    }

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
	add(queryField, gc);

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
	add(search, gc);
	
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

    public boolean isNotImpossibleDateCombination(String date1, String date2){
	Calendar cal = Calendar.getInstance();
	Date date = cal.getTime();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String CD = sdf.format(date);

	if (Integer.parseInt(date2.substring(0, 4)) < Integer.parseInt(CD.substring(0, 4)) || Integer.parseInt(date1.substring(0, 4)) < Integer.parseInt(CD.substring(0, 4))) {
	    return false;
	}
	if (Integer.parseInt(date2.substring(5, 7)) < Integer.parseInt(CD.substring(5, 7)) || Integer.parseInt(date1.substring(5, 7)) < Integer.parseInt(CD.substring(5, 7))){
	    return false;
	}
	if (Integer.parseInt(date2.substring(8, 10)) < Integer.parseInt(CD.substring(8, 10)) || Integer.parseInt(date1.substring(8, 10)) < Integer.parseInt(CD.substring(8, 10))){
	    return false;
	}
	if (Integer.parseInt(date2.substring(0, 4)) < Integer.parseInt(date1.substring(0, 4))){
	    System.out.println(CD);
	    return false;
	}
	if (Integer.parseInt(date2.substring(5, 7)) < Integer.parseInt(date1.substring(5, 7))){
	    return false;
	}
	if (Integer.parseInt(date2.substring(8, 10)) <= Integer.parseInt(date1.substring(8, 10))){
	    return false;
	}
	return true;
    }
}

