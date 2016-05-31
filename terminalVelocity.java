import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class terminalVelocity {
    
    private static final String apiKey = "prtl6749387986743898559646983194";

    private static void hotel(String args[]) {
	skyscannerHotel h = new skyscannerHotel(apiKey, args[0], args[1], args[2], 2, 1);
	try {
	    h.createSession();
	    String createSessionResult = h.pollSession();
	    HotelSession sessionObj = h.parseResult(createSessionResult);
	    printHotelResults(sessionObj);

	    FileOutputStream fos = new FileOutputStream("hotelData.txt");
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    oos.writeObject(sessionObj);
	    oos.close();
	} catch (Exception e) { 
	    System.out.println(e);
	}
    }

    private static void printHotelResults(HotelSession sessionObj) {
	HotelEntry[] hotelEntries = sessionObj.getEntries();
	for (int i = 0; i < sessionObj.getNumHotels(); i++) {
	    System.out.format("%-4s%-48s%-18s%-18s%n", i, hotelEntries[i].getName(), hotelEntries[i].getStars(), hotelEntries[i].getPrice());
	}
    }

    public static void main(String args[]) {
	hotel(args);
    }
    
}
