import java.io.Serializable;

public class HotelSession implements Serializable{

    public HotelEntry[] entries;
    public int numHotels;
    public String sessionURL;
    public String parameters;

    public HotelSession(String sessionURL, String parameters) {
	entries = new HotelEntry[100];
	numsHotels = 0;
	this.sessionURL = sessionURL;
	this.parameters = parameters;
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
