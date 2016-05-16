import java.sql.Date;

public class skyscannerLiveHotel{
    private final String apiKey;
    private final String market;
    private final String currency;
    private final String locale;
    private final String entityId;
    private final String checkindate;
    private final String checkoutdate;
    private final int guests;
    private final int rooms;

    public skyscannerLiveHotel(String apiKey, String market, String currency, String locale, String entityId, String checkindate, String checkoutdate, int guests, int rooms){
	this.apiKey = apiKey;
	this.market = market;
	this.currency = currency;
	this.locale = locale;
	this.entityId = entityId;
	this.checkindate = checkindate;
	this.checkoutdate = checkoutdate;
	this.guests = guests;
	this.rooms = rooms;
    }
}