public class skyscannerLiveFlight {

    private String apiKey;
    private String country;
    private String currency;
    private String locale;
    private String originplace;
    private String destinationplace;
    private Date outbounddate;
    private Date inbounddate;
    private String locationschema;
    private String cabinclass;
    private int adults;
    private int children;
    private int infants;

    public skyscannerLiveFlight(String apiKey, String country, String currency, String locale, String originplace, String destinationplace, Date outbounddate, Date inbounddate, String locationscheme, String cabinclass, int adults, int children, int infants) {
	this.apiKey = apiKey;
	this.country = country;
	this.currency = currency;
	this.locale = locale;
	this.originplace = originplace;
	this.destinationplace = destinationplace;
	this.outbounddate = outbounddate;
	this.inbounddate = inbounddate;
	this.locationschema = locationschema;
	this.cabinclass = cabinclass;
	this.adults = adults;
	this.children = children;
	this.infants = infants;
    }

    public skyscannerLiveFlight(String apikey, String originplace, String destinationplace, Date outbounddate, Date inbounddate) {
	this(apikey, 
    }

}
