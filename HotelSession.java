import java.io.Serializable;

public class HotelSession implements Serializable{

    private HotelEntry[] entries;
    private int numHotels;
    private String sessionURL;
    
    public HotelSession(String sessionURL) {
	entries = new HotelEntry[100];
	numHotels = 0;
	this.sessionURL = sessionURL;
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

    public String getSessionURL() {
	return sessionURL;
    }

}
