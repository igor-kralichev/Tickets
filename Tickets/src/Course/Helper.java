package Course;

public class Helper {
	BuyTickets buyTickets;
	
	public void getTickets(BuyTickets buyTickets) {
		this.buyTickets=buyTickets;
	}
	public int tickets() {
		return buyTickets.getTickets();
	}
	public int price() {
		return buyTickets.getPrice();
	}
}
