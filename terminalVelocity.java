import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Scanner;
import java.util.GregorianCalendar;
import java.awt.Desktop;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class terminalVelocity {
    
    private static final String apiKey = "prtl6749387986743898559646983194";
    
    private String destination;
    private String entityID;
    private String checkInDate;
    private String checkOutDate;
    private int guests;
    private int rooms;
    private int pageSize;
    
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
	
	System.out.format("%-4s%-48s%-18s%-18s%n", "#", "Name", "Rating", "Price");
	System.out.println("----------------------------------------------------------------------------");
	for (int i = 0; i < sessionObj.getNumHotels(); i++) {
	    System.out.format("%-4s%-48s%-18s%-18s%n", i + "."
			      , hotelEntries[i].getName(), hotelEntries[i].getStars(), "$" + hotelEntries[i].getPrice());
	}
	System.out.println("----------------------------------------------------------------------------");
	
    }
    
    private void takeSearchDetails() {
	Scanner scanner = new Scanner(System.in);
	String input;
	System.out.println(getToday());
	//Destination name
	System.out.println("Destination name:");
	destination = formatDestination(scanner.nextLine());
	
	//Arrival date
	System.out.println("Check in date (YYYY-MM-DD) (Default " + getFutureDay(1) + "):");
	input = scanner.nextLine();
	if (input.equals("")) {
	    checkInDate = getFutureDay(1);
	} else {
	    checkInDate = input;
	}
	
	//Departure date
	System.out.println("Check out date (YYYY-MM-DD) (Default " + getFutureDay(2) + "):");
	input = scanner.nextLine();
	if (input.equals("")) {
	    checkOutDate = getFutureDay(2);
	} else {
	    checkOutDate = input;
	}

	//Checks if dates entered are valid;
	if (!validDates(checkInDate, checkOutDate)) {
	    System.out.println("Invalid date(s): " + checkInDate + " - " + checkOutDate);
	    System.exit(1);
	}

	//Number of Guests
	System.out.println("Number of guests (Default 1):");
	input = scanner.nextLine();
	if (input.equals("")) {
	    guests = 1;
	} else {
	    guests = Integer.parseInt(input);
	}

	//Number of Rooms
	System.out.println("Number of rooms (Default 1):");
	input = scanner.nextLine();
	if (input.equals("")) {
	    rooms = 1;
	} else {
	    rooms = Integer.parseInt(input);
	}

	//# of Results 
	System.out.println("Number of results (Default 10);");
	input = scanner.nextLine();
	if (input.equals("")) {
	    pageSize = 10;
	} else {
	    pageSize = Integer.parseInt(input);
	}
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
	    System.exit(1);
	}
    }

    private String getToday() {
	GregorianCalendar c = new GregorianCalendar();
	String date = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	return date;
	
    }

    private String getFutureDay(int days) {
	GregorianCalendar c = new GregorianCalendar();
	c.roll(c.DAY_OF_MONTH, days);
	String date = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	return date;
    }

    private boolean validDates(String date1, String date2) {
	try {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date d1 = sdf.parse(date1);
	    Date d2 = sdf.parse(date2);
	    Date t  = sdf.parse(getToday());
	    if (t.compareTo(d1) > 0 || t.compareTo(d2) > 0) {
		return false;
	    } else if (d1.compareTo(d2) > 0) {
		return false;
	    }
	} catch (Exception e) {
	    return false;
	}
	return true;
    }
    

}
