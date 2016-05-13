import java.sql.Date;

public class skyscannerLiveFlight {

    private final String apiKey;
    private final String country;
    private final String currency;
    private final String locale;
    private final String originplace;
    private final String destinationplace;
    private final Date outbounddate;
    private final Date inbounddate;
    private final String locationschema;
    private final String cabinclass;
    private final int adults;
    private final int children;
    private final int infants;
    private final boolean  groupPricing;

    private String url = "https://partners.api.skyscanner.net/apiservices/pricing/v1.0";

    public skyscannerLiveFlight(String apiKey, String country, String currency, String locale, String originplace, String destinationplace, Date outbounddate, Date inbounddate, String cabinclass, int adults, int children, int infants, boolean groupPricing) {
	this.apiKey = apiKey;
	this.country = country;
	this.currency = currency;
	this.locale = locale;
	this.originplace = originplace;
	this.destinationplace = destinationplace;
	this.outbounddate = outbounddate;
	this.inbounddate = inbounddate;
	this.cabinclass = cabinclass;
	this.adults = adults;
	this.children = children;
	this.infants = infants;
	this.groupPricing = groupPricing;
    }

    public skyscannerLiveFlight(String apiKey, String originplace, String destinationplace, Date outbounddate, Date inbounddate, String cabinclass, int adults, int children, int infants) {
	this(apiKey, "USA", "USD", "en-US", originplace, destinationplace, outbounddate, inbounddate, cabinclass, adults, children, infants, true);
    }

    

}
