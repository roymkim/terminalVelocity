import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class HotelSession implements Serializable{

    private static final String URL = "http://partners.api.skyscanner.net";

    private HotelEntry[] entries;
    private int numHotels;
    private String sessionID;
    
    public HotelSession(String sesssionID) {
	entries = new HotelEntry[100];
	numHotels = 0;
	this.sessionID = sessionID;
    } 

    public boolean addEntry(HotelEntry entry) {
	if (numHotels >= entries.length) {
	    return false;
	}
        entries[numHotels] = entry;
	numHotels++;
	return true;
    }

    private boolean checkSessionIsValid() {
	return false;
    }

    public HotelEntry[] getEntries() {
	return entries;
    }

    public int getNumHotels() {
	return numHotels;
    }

    public String getSessionID() {
	return sessionID;
    }

    public void writeSession() {
	try {
	    FileOutputStream fos = new FileOutputStream("hotelSessionData.txt");
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    oos.writeObject(this);
	    oos.close();
	    fos.close();
	} catch (Exception e) {
	    System.out.println(e);
	}
    }

    public HotelSession readSession() {
	HotelSession s = null;
	try {
	    FileInputStream fis = new FileInputStream("hotelSessionData.txt");
	    ObjectInputStream ois = new ObjectInputStream(fis);
	    s = (HotelSession) ois.readObject();
	    ois.close();
	    fis.close();
	} catch (Exception e) {
	    System.out.println(e);
	}
	return s;
    }

}
