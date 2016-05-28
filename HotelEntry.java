public class HotelEntry {

    private String name;
    private String hotel_id;
    private String agent_id;
    private String stars;
    private String price;
    private String type;

    public HotelEntry(String name, String hotel_id, String agent_id, String stars, String price, String type) {
	this.name = name;
	this.hotel_id = hotel_id;
	this.agent_id = agent_id;
	this.stars = stars;
	this.type = type;
    }

    public String getName() {
	return name;
    }

    public String getHotelID() {
	return hotel_id;
    }

    public String getAgentID() {
	return agent_id;
    }

    public String getStars() {
	return stars;
    }

    public String getPrice() {
	return price;
    }

    public String getType() {
	return type;
    }

}
