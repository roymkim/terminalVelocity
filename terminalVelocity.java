import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class terminalVelocity {
    
    private static final String apiKey = "prtl6749387986743898559646983194";

    private static void hotel(String args[]) {
	skyscannerHotel h = new skyscannerHotel(apiKey, args[0], args[1], args[2], 2, 1);
	try {
	    h.createSession();
	    String pollSessionResult = h.pollSession();
	    HotelSession sessionObj = h.parseResult(pollSessionResult);
	    printHotelResults(sessionObj);
	    
	    sessionObj.writeSession();

	   
	} catch (Exception e) { 
	    System.out.println(e);
	}
    }

    private static void printHotelResults(HotelSession sessionObj) {
	HotelEntry[] hotelEntries = sessionObj.getEntries();
	
	System.out.println("----------------------------------------------------------------------------");
	for (int i = 0; i < sessionObj.getNumHotels(); i++) {
	    System.out.format("%-4s%-48s%-18s%-18s%n", i + "."
, hotelEntries[i].getName(), hotelEntries[i].getStars(), hotelEntries[i].getPrice());
	}
	System.out.println();
	System.out.println("----------------------------------------------------------------------------");

    }

    public static void main(String args[]) {
	if (args.length == 0) {
	    System.out.println("Invalid Input");
	    return;
	}
	hotel(args);
    }
    
}
