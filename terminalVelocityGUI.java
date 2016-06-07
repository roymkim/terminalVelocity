import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Scanner;
import java.awt.Desktop;
import java.net.URI;
import java.text.SimpleDateFormat;

public class terminalVelocityGUI {
    
    private static final String apiKey = "prtl6749387986743898559646983194";
    
    private String destination;
    private String entityID;
    private String checkInDate;
    private String checkOutDate;
    private int guests;
    private int rooms;
    private int pageSize;
    
    private HotelSession sessionObj;
    
    public terminalVelocityGUI(String text) {
	Scanner sc = new Scanner(text);
	destination = sc.nextLine();
	checkInDate = sc.nextLine();
	checkOutDate = sc.nextLine();
	guests = Integer.parseInt(sc.nextLine());
	rooms = Integer.parseInt(sc.nextLine());
	System.out.println(destination);
	destinationLookup();
	runHotelSearch();
    }
      
    private void runHotelSearch() {
	skyscannerHotel h = new skyscannerHotel(apiKey, entityID, checkInDate, checkOutDate, guests, rooms);
	try {
	    h.createSession();
	    String pollSessionResult = h.pollSession(pageSize);
	    sessionObj = h.parseResult(pollSessionResult);
	    printHotelResults(sessionObj);
	    sessionObj.writeSession();
	       
	} catch (Exception e) { 
	    System.out.println(e);
	    System.exit(1);
	}
    }
    
    private static void printHotelResults(HotelSession sessionObj) {
	HotelEntry[] hotelEntries = sessionObj.getEntries();
	
	System.out.println("Search Results for entityID: " + entityID);
	System.out.println();
	System.out.format("%-4s%-48s%-18s%-18s%n", "#", "Name", "Rating", "Price");
	System.out.println("----------------------------------------------------------------------------");
	for (int i = 0; i < sessionObj.getNumHotels(); i++) {
	    System.out.format("%-4s%-48s%-18s%-18s%n", i + "."
			      , hotelEntries[i].getName(), hotelEntries[i].getStars(), "$" + hotelEntries[i].getPrice());
	}
	System.out.println("----------------------------------------------------------------------------");
	
    }
    
    private String formatDestination(String dest) {
	char[] chars = dest.toCharArray();
	for (int i = 0; i < chars.length; i++) {
	    if (chars[i] == ' ') {
		chars[i] = '_';
	    }
	}
	return String.valueOf(chars);
    }
 

    private void destinationLookup() {
	skyscannerAutoSuggest sas = new skyscannerAutoSuggest(destination, apiKey);
	entityID = sas.getEntityID(0);
    }
    
    private String hotelDeeplinkLookup() {
	return sessionObj.getHotelDeeplink(chooseHotel());
    }
    
    private int chooseHotel() {
	Scanner scanner = new Scanner(System.in);
	System.out.println("Choose hotel(#)");
	return Integer.parseInt(scanner.nextLine());
    }

    private void openLink(String link) {
	try {
	    if(Desktop.isDesktopSupported()) {
		Desktop.getDesktop().browse(new URI(link));
	    }
	} catch (Exception e) {
	    System.out.println(e);
	    System.exit(1);
	}
    }    

}