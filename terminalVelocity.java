import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Scanner;
import java.awt.Desktop;
import java.net.URI;

public class terminalVelocity {
    
    private static final String apiKey = "prtl6749387986743898559646983194";
    
    private String destination;
    private String entityID;
    private String checkInDate;
    private String checkOutDate;
    private int guests;
    private int rooms;
    
    private HotelSession sessionObj;
    
    public terminalVelocity() {
	
    }
    
    public static void main(String args[]) {
	terminalVelocity tv = new terminalVelocity();
	tv.takeSearchDetails();
	tv.destinationLookup();
	tv.runHotelSearch();
	tv.openLink(tv.hotelDeeplinkLookup());
    }

    private void runHotelSearch() {
	skyscannerHotel h = new skyscannerHotel(apiKey, entityID, checkInDate, checkOutDate, guests, rooms);
	try {
	    h.createSession();
	    String pollSessionResult = h.pollSession();
	    sessionObj = h.parseResult(pollSessionResult);
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
    
    private void takeSearchDetails() {
	Scanner scanner = new Scanner(System.in);
	
	//Destination name
	System.out.println("Destination name:");
	destination = scanner.nextLine();
	
	//Arrival date
	System.out.println("Arrival date (YYYY-MM-DD):");
	checkInDate = scanner.nextLine();
	
	//Departure date
	System.out.println("Departure date (YYYY-MM-DD):");
	checkOutDate = scanner.nextLine();
	
	//Number of Guests
	System.out.println("Number of guests:");
	guests = Integer.parseInt(scanner.nextLine());
	
	//Number of Rooms
	System.out.println("Number of rooms:");
	rooms = Integer.parseInt(scanner.nextLine());
    }
    
    private void destinationLookup() {
	skyscannerAutoSuggest sas = new skyscannerAutoSuggest(destination, apiKey);
	entityID = sas.getEntityID(chooseDestination());
    }

    private int chooseDestination() {
	Scanner scanner = new Scanner(System.in);
	System.out.println("Choose destination(#)");
	return Integer.parseInt(scanner.nextLine()) - 1;
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
	}
    }

}
