import java.io.Serializable;

public class HotelSession implements Serializable{

    public HotelEntry[] entries;
    public int numHotels;
    public String sessionURL;
    public String parameters;

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
	return true;
    }
    
    private boolean checkSessionIsValid() {
	return false;
    }

}
